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
import com.elex.oa.util.hr_util.HrUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    HrUtils hrUtils;
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
        if (null==opportunity.getCreatetime() || StringUtils.isEmpty(opportunity.getCreatetime().trim()) || opportunity.getCreatetime().length()<19) {
            opportunity.setCreatetime(hrUtils.getDateStringByTimeMillis(System.currentTimeMillis()));
        }
        iOpportunityDao.insertSelective(opportunity);
        //添加附件信息
        Boolean aBoolean = getaBooleanByAddAttachment(opportunity,true);
        if(aBoolean==false)return false;
        //设置显示状态为“已转商机状态”
        aBoolean = getaBooleanBySetClueState(opportunity.getClueid());
        return aBoolean;
    }

    @Override
    public PageInfo<Opportunity> getPageInfoByCondition(Integer pageNum, Integer pageSize, Opportunity opportunity, String flag) {
        String orderBy = "trackid DESC";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<Opportunity> opportunityList = null;
        PageInfo<Opportunity> opportunityPageInfo = null;
        if ("ALL".equals(flag)) {
            opportunityList = iOpportunityDao.select(opportunity);
            opportunityPageInfo = new PageInfo<Opportunity>(opportunityList);
        } else if("DEP".equals(flag)){
            opportunityList = iOpportunityDao.selectByOpportunityAndPrincipalUsername(opportunity);
            opportunityPageInfo = new PageInfo<Opportunity>(opportunityList);
        }else if("PRIVATE".equals(flag)){
            opportunityList = iOpportunityDao.select(opportunity);
            opportunityPageInfo = new PageInfo<Opportunity>(opportunityList);
        }
        List<Opportunity> opportunityListTemp = opportunityPageInfo.getList();
        for (Opportunity o:opportunityListTemp
        ) {
            o = getOpportunityByOpportunity(o);
        }
        opportunityPageInfo.setList(opportunityListTemp);
        return opportunityPageInfo;
    }

    @Override
    public Opportunity getDetailOpportunityinfo(String opportunitycode) {
        if(StringUtils.isEmpty(opportunitycode))return null;
        Opportunity opportunity = iOpportunityDao.selectByPrimaryKey(opportunitycode);
        if(null==opportunity)return null;
        //获得销售人和方案人
        opportunity = getOpportunityByOpportunity(opportunity);
        //获得跟踪日志
        List<TrackInfo> trackInfoList = iTrackInfoDao.select(new TrackInfo(opportunitycode));
        opportunity.setTrackInfoList(trackInfoList);
        return opportunity;
    }

    @Override
    public Opportunity getDetailOpportunityinfoByCluecode(String cluecode) {

        List<Opportunity> opportunityList = iOpportunityDao.select(new Opportunity(cluecode, null));
        if(null==opportunityList || opportunityList.size()>1)return null;
        Opportunity opportunity = getDetailOpportunityinfo(opportunityList.get(0).getCode());
        return opportunity;
    }

    @Override
    public Boolean modifyOpportunityInfo(Opportunity opportunity) {
        Boolean aBoolean = true;
        //步骤：1.跟踪-->2.线索-->3.附件
        //添加跟踪信息
        aBoolean = getaBooleanByUpdateTrackInfo(opportunity,aBoolean);
        return aBoolean;
    }

    @Override
    public Boolean closeOpportunityInfo(String opportunitycode) {
        if(StringUtils.isEmpty(opportunitycode))return false;
        Opportunity opportunity = iOpportunityDao.selectByPrimaryKey(opportunitycode);
        if(null==opportunity)return false;
        Boolean aBoolean = true;
        try {
            opportunity.setState(Commons.OPPORTUNITY_OFF);
            //添加关闭跟踪
            opportunity.setTrackcontent("最后阶段：商机没有价值，已关闭！");
            TrackInfo trackInfo = getTrackInfoByObject(opportunity);
            iTrackInfoDao.insert(trackInfo);
            //更新线索状态
            opportunity.setTrackid(trackInfo.getCode());
            iOpportunityDao.updateByPrimaryKeySelective(opportunity);
        } catch (Exception e) {
            e.printStackTrace();
            aBoolean = false;
        }
        return aBoolean;
    }

    @Override
    public Map<String, Object> getBusinessInfoByState_OFF() {
        Map<String, Object> respMap = new HashMap<>();
        List<Clue> clueList = iClueDao.select(new Clue(Commons.CLUE_OFF));
        respMap.put("clueList",clueList);
        List<Opportunity> opportunityList = iOpportunityDao.select(new Opportunity(Commons.OPPORTUNITY_OFF));
        respMap.put("opportunityList",opportunityList);
        return respMap;
    }

    private Boolean getaBooleanByUpdateTrackInfo(Opportunity opportunity, Boolean aBoolean) {
        if(null==opportunity)return false;
        TrackInfo trackInfo = getTrackInfoByObject(opportunity);
        iTrackInfoDao.insert(trackInfo);
        //更新线索信息
        aBoolean = getaBooleanByUpdateOpportunity(opportunity,trackInfo.getCode(),aBoolean);
        return aBoolean;
    }

    private Boolean getaBooleanByUpdateOpportunity(Opportunity opportunity, String newTrackid, Boolean aBoolean) {
        if(aBoolean==false)return false;
        //String oldTrackid = opportunity.getTrackid();//将跟踪编码旧值截留保存
        try {
            opportunity.setTrackid(newTrackid);
            iOpportunityDao.updateByPrimaryKeySelective(opportunity);//根据主键更新属性不为null的值
        } catch (Exception e) {
            e.printStackTrace();
            //添加线索失败需要回滚
            //iTrackInfoDao.deleteByPrimaryKey(newTrackid);
            return false;
        }
        //添加附件信息
        aBoolean = getaBooleanByAddAttachment(opportunity,aBoolean);
        return aBoolean;
    }

    private Opportunity getOpportunityByOpportunity(Opportunity opportunity) {
        if(null==opportunity)return null;
        //获得最新的跟踪描述
        opportunity.setTrackcontent(hrUtils.getTrackcontentByTrackid(opportunity.getTrackid()));
        //获得销售人姓名
        opportunity.setSale_truename(hrUtils.getTruenameByEmployeenumber(opportunity.getSale_employeenumber()));
        //获得方案人姓名
        opportunity.setScheme_truename(hrUtils.getTruenameByEmployeenumber(opportunity.getScheme_employeenumber()));
        //获得部门名称
        opportunity.setDepname(hrUtils.getDepnameByEmployeenumber(opportunity.getSale_employeenumber()));
        //获得用户的账号ID
        opportunity.setUsername(hrUtils.getUsernameByEmployeenumber(opportunity.getSale_employeenumber()));
        return opportunity;
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
        trackInfo.setTrack_date(hrUtils.getDateStringByTimeMillis(l));
        return trackInfo;
    }

    private Boolean getaBooleanByAddAttachment(Opportunity opportunity,Boolean aBoolean) {
        if(null==opportunity.getBusinessAttachmentList())return aBoolean;
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
        return aBoolean;
    }
}