package com.elex.oa.service.business.Impl;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.business.IBusinessAttachmentDao;
import com.elex.oa.dao.business.IClueDao;
import com.elex.oa.dao.business.IOpportunityDao;
import com.elex.oa.dao.business.ITrackInfoDao;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.business.Clue;
import com.elex.oa.entity.business.Opportunity;
import com.elex.oa.entity.business.TrackInfo;
import com.elex.oa.service.business.IOpportunityService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\10 0010 15:22
 * @Version 1.0
 **/
@Service
@Transactional
public class OpportunityServiceImpl implements IOpportunityService {
    @Resource
    IOpportunityDao iOpportunityDao;
    @Resource
    HrUtilsTemp hrUtilsTemp;
    @Resource
    ITrackInfoDao iTrackInfoDao;
    @Resource
    IBusinessAttachmentDao iBusinessAttachmentDao;
    @Resource
    IClueDao iClueDao;

    @Override
    public Boolean transforClueToOpportunity(Opportunity opportunity) {
        //添加商机（步骤：1.跟踪->2.商机->3.附件）
        //添加跟踪信息
        TrackInfo trackInfo_opportunity = getTrackInfoByObject(opportunity);
        iTrackInfoDao.insert(trackInfo_opportunity);
        //添加商机信息
        opportunity.setTrackid(trackInfo_opportunity.getCode());
        opportunity.setState(Commons.OPPORTUNITY_ON);
        iOpportunityDao.insertSelective(opportunity);
        //添加附件信息
        Boolean aBoolean = getaBooleanByAddAttachment(opportunity);
        if(aBoolean==false)return false;
        //设置显示状态为“已转商机状态”
        aBoolean = getaBooleanBySetClueState(opportunity.getClueid());
        return aBoolean;
    }

    private Boolean getaBooleanBySetClueState(String clueid) {
        if(StringUtils.isEmpty(clueid))return false;
        try {
            Clue clue = iClueDao.selectByPrimaryKey(clueid);
            if(null==clue)return false;
            clue.setState(Commons.CLUE_TRANSFOR_OPPORTUNITY);
            clue.setTrackcontent("最后阶段：此线索已转商机！");
            TrackInfo trackInfo_clue = getTrackInfoByObject(clue);
            iTrackInfoDao.insert(trackInfo_clue);
            clue.setTrackid(trackInfo_clue.getCode());
            iClueDao.updateByPrimaryKeySelective(clue);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private TrackInfo getTrackInfoByObject(Object obj) {
        //在tb_business_track表中添加跟踪信息
        TrackInfo trackInfo = new TrackInfo();
        long l = System.currentTimeMillis();
        trackInfo.setCode("track_"+l);
        if (obj instanceof Opportunity) {
            trackInfo.setDependence_code(((Opportunity)obj).getCode());
            trackInfo.setTrack_content(((Opportunity)obj).getTrackcontent());
        } else if(obj instanceof Clue){
            trackInfo.setDependence_code(((Clue)obj).getCode());
            trackInfo.setTrack_content(((Clue)obj).getTrackcontent());
        }
        //获得时间
        trackInfo.setTrack_date(hrUtilsTemp.getDateStringByTimeMillis(l));
        return trackInfo;
    }

    private Boolean getaBooleanByAddAttachment(Opportunity opportunity) {
        if(null==opportunity.getBusinessAttachmentList())return false;
        try {
            for (BusinessAttachment b:opportunity.getBusinessAttachmentList()
            ) {
                String attachmentCode = "attachment_"+System.currentTimeMillis();
                b.setCode(attachmentCode);
                b.setDependence_code(opportunity.getCode());
                iBusinessAttachmentDao.insert(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}