package com.elex.oa.service.business.Impl;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.business.IBusinessAttachmentDao;
import com.elex.oa.dao.business.IClueDao;
import com.elex.oa.dao.business.ITrackInfoDao;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.business.Clue;
import com.elex.oa.entity.business.TrackInfo;
import com.elex.oa.service.business.IClueService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\5 0005 16:32
 * @Version 1.0
 **/
@Service
@Transactional
public class ClueServiceImpl implements IClueService {
    @Resource
    IClueDao iClueDao;
    @Resource
    ITrackInfoDao iTrackInfoDao;
    @Resource
    HrUtilsTemp hrUtilsTemp;
    @Resource
    IBusinessAttachmentDao iBusinessAttachmentDao;

    @Override
    public PageInfo<Clue> getPageInfoByCondition(Integer pageNum, Integer pageSize, Clue clue) {
        PageHelper.startPage(pageNum,pageSize);
        List<Clue> clueList = iClueDao.select(clue);
        for (Clue c:clueList
             ) {
            c = getClueByClue(c);
        }
        return new PageInfo<Clue>(clueList);
    }

    @Override
    public Boolean addClueInfo(Clue clue) {
        Boolean aBoolean = true;
        //步骤：1.跟踪-->2.线索-->3.附件
        //添加跟踪信息
        aBoolean = getaBooleanByAddTrackInfo(clue,aBoolean);
        return aBoolean;
    }

    @Override
    public Clue getDetailClueinfo(String cluecode) {
        if(StringUtils.isEmpty(cluecode))return null;
        Clue clue = iClueDao.selectByPrimaryKey(cluecode);
        if(null==clue)return null;
        //获得销售人和方案人
        clue = getClueByClue(clue);
        //获得跟踪日志
        List<TrackInfo> trackInfoList = iTrackInfoDao.select(new TrackInfo(cluecode));
        clue.setTrackInfoList(trackInfoList);
        return clue;
    }

    @Override
    public Boolean modifyClueInfo(Clue clue) {
        Boolean aBoolean = true;
        //步骤：1.跟踪-->2.线索-->3.附件
        //添加跟踪信息
        aBoolean = getaBooleanByUpdateTrackInfo(clue,aBoolean);
        return aBoolean;
    }

    @Override
    public Boolean closeClueInfo(String cluecode) {
        if(StringUtils.isEmpty(cluecode))return false;
        Clue clue = iClueDao.selectByPrimaryKey(cluecode);
        if(null==clue)return false;
        Boolean aBoolean = true;
        try {
            clue.setState(Commons.CLUE_OFF);
            //添加关闭跟踪
            clue.setTrackcontent("最后阶段：线索没有价值，已关闭！");
            TrackInfo trackInfo = getTrackInfoByClue(clue);
            iTrackInfoDao.insert(trackInfo);
            //更新线索状态
            clue.setTrackid(trackInfo.getCode());
            iClueDao.updateByPrimaryKeySelective(clue);
        } catch (Exception e) {
            e.printStackTrace();
            aBoolean = false;
        }
        return aBoolean;
    }

    private Boolean getaBooleanByUpdateTrackInfo(Clue clue, Boolean aBoolean) {
        TrackInfo trackInfo = getTrackInfoByClue(clue);
        iTrackInfoDao.insert(trackInfo);
        //更新线索信息
        aBoolean = getaBooleanByUpdateClue(clue,trackInfo.getCode(),aBoolean);
        return aBoolean;
    }

    private Boolean getaBooleanByUpdateClue(Clue clue, String newTrackid, Boolean aBoolean) {
        if(aBoolean==false)return false;
        String oldTrackid = clue.getTrackid();//将跟踪编码旧值截留保存
        try {
            clue.setTrackid(newTrackid);
            iClueDao.updateByPrimaryKeySelective(clue);//根据主键更新属性不为null的值
        } catch (Exception e) {
            e.printStackTrace();
            //添加线索失败需要回滚
            //iTrackInfoDao.deleteByPrimaryKey(newTrackid);
            return false;
        }
        //添加附件信息
        aBoolean = getaBooleanByAddAttachment(clue, aBoolean,true,oldTrackid);
        return aBoolean;
    }

    private Boolean getaBooleanByAddTrackInfo(Clue clue,Boolean aBoolean) {
        TrackInfo trackInfo = getTrackInfoByClue(clue);
        iTrackInfoDao.insert(trackInfo);
        //添加线索信息
        aBoolean = getaBooleanByAddClue(clue, trackInfo.getCode(),aBoolean);
        return aBoolean;
    }

    private Boolean getaBooleanByAddClue(Clue clue, String trackCode,Boolean aBoolean) {
        if(aBoolean==false)return false;
        try {
            clue.setTrackid(trackCode);
            clue.setState(Commons.CLUE_ON);
            if(StringUtils.isEmpty(clue.getCreatetime().trim()))clue.setCreatetime(hrUtilsTemp.getDateStringByTimeMillis(System.currentTimeMillis()));
            iClueDao.insert(clue);
        } catch (Exception e) {
            e.printStackTrace();
            //添加线索失败需要回滚
            //iTrackInfoDao.deleteByPrimaryKey(trackCode);
            return false;
        }
        //添加附件信息
        aBoolean = getaBooleanByAddAttachment(clue, aBoolean,false,null);
        return aBoolean;
    }

    private Boolean getaBooleanByAddAttachment(Clue clue, Boolean aBoolean,Boolean isAdd,String oldTrackid) {
        if(null==clue.getBusinessAttachmentList())return aBoolean;
        try {
            for (BusinessAttachment b:clue.getBusinessAttachmentList()
                 ) {
                String attachmentCode = "attachment_"+System.currentTimeMillis();
                b.setCode(attachmentCode);
                b.setDependence_code(clue.getCode());
                iBusinessAttachmentDao.insert(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //添加附件失败需要回滚
            /*if (isAdd) {
                iClueDao.deleteByPrimaryKey(clue.getCode());//添加回滚
            } else {
                clue.setTrackid(oldTrackid);
                iClueDao.updateByPrimaryKeySelective(clue);//更新回滚
            }
            iTrackInfoDao.deleteByPrimaryKey(clue.getTrackid());*/
            return false;
        }
        return aBoolean;
    }

    private TrackInfo getTrackInfoByClue(Clue clue) {
        //在tb_business_track表中添加跟踪信息
        TrackInfo trackInfo = new TrackInfo();
        long l = System.currentTimeMillis();
        trackInfo.setCode("track_"+l);
        trackInfo.setDependence_code(clue.getCode());
        trackInfo.setTrack_content(clue.getTrackcontent());
        //获得时间
        trackInfo.setTrack_date(hrUtilsTemp.getDateStringByTimeMillis(l));
        return trackInfo;
    }

    private Clue getClueByClue(Clue c) {
        if(null==c)return null;
        //获得最新的跟踪描述
        c.setTrackcontent(hrUtilsTemp.getTrackcontentByTrackid(c.getTrackid()));
        //获得销售人姓名
        c.setSale_truename(hrUtilsTemp.getTruenameByEmployeenumber(c.getSale_employeenumber()));
        //获得方案人姓名
        c.setScheme_truename(hrUtilsTemp.getTruenameByEmployeenumber(c.getScheme_employeenumber()));
        return c;
    }
}