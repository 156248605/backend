package com.elex.oa.service.business.impl;

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
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    private static Logger logger = LoggerFactory.getLogger(OpportunityServiceImpl.class);

    @Override
    public Object transforClueToOpportunity(Opportunity opportunity) {
        //先判断必选项
        if(StringUtils.isBlank(opportunity.getOpportunityname()))return RespUtil.response("500","商机内容不能为空！",null);
        if(StringUtils.isBlank(opportunity.getTrackcontent()))return RespUtil.response("500","商机进展不能为空！",null);
        if(StringUtils.isBlank(opportunity.getSale_employeenumber()))return RespUtil.response("500","商机跟踪人不能为空！",null);
        if(StringUtils.isBlank(opportunity.getCustom_budget()))return RespUtil.response("500","客户预算人不能为空！",null);
        if(StringUtils.isBlank(opportunity.getOpportunity_budget()))return RespUtil.response("500","商机预算人不能为空！",null);
        if(StringUtils.isBlank(opportunity.getCustom()))return RespUtil.response("500","客户不能为空！",null);
        //添加商机（步骤：1.跟踪->2.商机->3.附件）
        //添加跟踪信息
        TrackInfo trackInfoOpportunity = getTrackInfoByObject(opportunity);
        iTrackInfoDao.insert(trackInfoOpportunity);
        //添加商机信息
        opportunity.setTrackid(trackInfoOpportunity.getCode());
        opportunity.setState(Commons.OPPORTUNITY_ON);
        if (null==opportunity.getCreatetime() || StringUtils.isEmpty(opportunity.getCreatetime().trim()) || opportunity.getCreatetime().length()<19) {
            opportunity.setCreatetime(hrUtils.getDateStringByTimeMillis(System.currentTimeMillis()));
        }
        iOpportunityDao.insertSelective(opportunity);
        //添加附件信息
        Boolean aBoolean = getaBooleanByAddAttachment(opportunity,true);
        if(!aBoolean)return RespUtil.response("500","添加附件出错！",null);
        //设置显示状态为“转商机”
        aBoolean = getaBooleanBySetClueState(opportunity.getUsername(),opportunity.getClueid());
        if(!aBoolean)return RespUtil.response("500","将线索更新为'转商机'时出错！",null);
        return RespUtil.response("200","请求成功！",null);
    }

    @Override
    public PageInfo<Opportunity> getPageInfoByCondition(Integer pageNum, Integer pageSize, Opportunity opportunity, String flag) {
        String orderBy = "trackid DESC";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<Opportunity> opportunityList = null;
        PageInfo<Opportunity> opportunityPageInfo = null;
        if("DEP".equals(flag)){
            opportunityList = iOpportunityDao.selectByOpportunityAndPrincipalUsername(opportunity);
            opportunityPageInfo = new PageInfo<>(opportunityList);
        }else {
            opportunityList = iOpportunityDao.select(opportunity);
            opportunityPageInfo = new PageInfo<>(opportunityList);
        }
        List<Opportunity> opportunityListTemp = opportunityPageInfo.getList();
        for (Opportunity o:opportunityListTemp
        ) {
            getOpportunityByOpportunity(o);
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
        getOpportunityByOpportunity(opportunity);
        //获得跟踪日志
        List<TrackInfo> trackInfoList = iTrackInfoDao.select(new TrackInfo(opportunitycode));
        opportunity.setTrackInfoList(trackInfoList);
        return opportunity;
    }

    @Override
    public Opportunity getDetailOpportunityinfoByCluecode(String cluecode) {

        List<Opportunity> opportunityList = iOpportunityDao.select(new Opportunity(cluecode, null));
        if(null==opportunityList || opportunityList.size()>1)return null;
        return getDetailOpportunityinfo(opportunityList.get(0).getCode());
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
    public Object closeOpportunityInfo(String opportunitycode, String trackcontent, String username) {
        if(StringUtils.isEmpty(opportunitycode))return RespUtil.response("500","关闭的商机编号不能为空！",null);
        try {
            Opportunity opportunity = iOpportunityDao.selectByPrimaryKey(opportunitycode);
            if(null==opportunity)return RespUtil.response("500","关闭的商机编号不存在！",opportunitycode);
            opportunity.setState(Commons.OPPORTUNITY_OFF);
            //添加关闭跟踪
            opportunity.setTrackcontent(username+":"+trackcontent);
            TrackInfo trackInfo = getTrackInfoByObject(opportunity);
            iTrackInfoDao.insert(trackInfo);
            //更新线索状态
            opportunity.setTrackid(trackInfo.getCode());
            iOpportunityDao.updateByPrimaryKeySelective(opportunity);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500","请求失败！",e.getCause());
        }
        return RespUtil.response("200","关闭成功！",opportunitycode);
    }

    @Override
    public Map<String, Object> getBusinessInfoByStateOFF() {
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
        if(!aBoolean)return false;
        //String oldTrackid = opportunity.getTrackid();//将跟踪编码旧值截留保存
        try {
            opportunity.setTrackid(newTrackid);
            iOpportunityDao.updateByPrimaryKeySelective(opportunity);//根据主键更新属性不为null的值
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            //添加线索失败需要回滚
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
        //获得参与人姓名
        String[] content = {};
        String participate = "";
        if (opportunity.getParticipate() != null) {
            content = opportunity.getParticipate().split(",");
            for (String name : content) {
                participate += hrUtils.getTruenameByEmployeenumber(name) + "," ;
            }
            opportunity.setParticipateName(participate.substring(0,participate.length()-1));
        } else {
            opportunity.setParticipateName("");
        }
        return opportunity;
    }

    private Boolean getaBooleanBySetClueState(String username,String clueid) {
        if(StringUtils.isEmpty(clueid))return false;
        try {
            Clue clue = iClueDao.selectByPrimaryKey(clueid);
            if(null==clue)return false;
            clue.setUsername(username);
            clue.setState(Commons.CLUE_TRANSFOR_OPPORTUNITY);
            clue.setTrackcontent("最后阶段,此线索已转商机！");
            TrackInfo trackInfoClue = getTrackInfoByObject(clue);
            iTrackInfoDao.insert(trackInfoClue);
            clue.setTrackid(trackInfoClue.getCode());
            iClueDao.updateByPrimaryKeySelective(clue);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
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
            Opportunity opportunity = (Opportunity)obj;
            trackInfo.setDependence_code(opportunity.getCode());
            trackInfo.setTrack_content(opportunity.getUsername()+":"+opportunity.getTrackcontent());
        } else if(obj instanceof Clue){
            Clue clue = (Clue) obj;
            trackInfo.setDependence_code(clue.getCode());
            trackInfo.setTrack_content(clue.getUsername()+":"+clue.getTrackcontent());
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
            logger.info(String.valueOf(e.getCause()));
            return false;
        }
        return aBoolean;
    }
}