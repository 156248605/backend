package com.elex.oa.service.business.impl;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.business.IBusinessAttachmentDao;
import com.elex.oa.dao.business.IClueDao;
import com.elex.oa.dao.business.ITrackInfoDao;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.business.Clue;
import com.elex.oa.entity.business.Opportunity;
import com.elex.oa.entity.business.TrackInfo;
import com.elex.oa.service.business.IClueService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
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
    HrUtils hrUtils;
    @Resource
    IBusinessAttachmentDao iBusinessAttachmentDao;

    static Logger logger = LoggerFactory.getLogger(ClueServiceImpl.class);
    @Override
    public PageInfo<Clue> getPageInfoByCondition(Integer pageNum, Integer pageSize, Clue clue, String flag) {
        String orderBy = "trackid DESC";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<Clue> clueList = null;
        PageInfo<Clue> cluePageInfo = null;
        if("DEP".equals(flag)) {//部门领导只能查看本部门的
            clueList = iClueDao.selectByClueAndPrincipalUsername(clue);
            cluePageInfo = new PageInfo<>(clueList);
        } else {//普通员工只能查看自己的
            clueList = iClueDao.select(clue);
            cluePageInfo = new PageInfo<>(clueList);
        }
        List<Clue> clueListTemp = cluePageInfo.getList();
        for (Clue c:clueListTemp
             ) {
            getClueByClue(c);
        }
        cluePageInfo.setList(clueListTemp);
        return cluePageInfo;
    }

    @Override
    public Object addClueInfo(Clue clue) {
        if(StringUtils.isBlank(clue.getCluename())){
            return RespUtil.response("500","内容不能为空",null);
        }
        if(StringUtils.isBlank(clue.getTrackcontent())){
            return RespUtil.response("500","进展不能为空",null);
        }
        if(StringUtils.isBlank(clue.getSale_employeenumber()) || "undefined".equals(clue.getSale_employeenumber())){
            return RespUtil.response("500","跟踪人不能为空",null);
        }
        Boolean aBoolean = true;
        //步骤：1.跟踪-->2.线索-->3.附件
        //添加跟踪信息
        aBoolean = getaBooleanByAddTrackInfo(clue,aBoolean);
        if(aBoolean){
            return RespUtil.response("200","请求成功",clue.getCode());
        }else {
            return RespUtil.response("500","请求失败",null);
        }
    }

    @Override
    public Clue getDetailClueinfo(String cluecode) {
        if(StringUtils.isEmpty(cluecode))return null;
        Clue clue = iClueDao.selectByPrimaryKey(cluecode);
        if(null==clue)return null;
        //获得销售人和方案人和参与人
        getClueByClue(clue);
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
    public Object closeClueInfo(String cluecode, String trackcontent, String username) {
        if(StringUtils.isBlank(cluecode))return RespUtil.response("500","线索编号不能为空！",null);
        try {
            Clue clue = iClueDao.selectByPrimaryKey(cluecode);
            if(null==clue)return RespUtil.response("500","线索编号不存在！",cluecode);
            clue.setState(Commons.CLUE_OFF);
            //添加关闭跟踪
            clue.setTrackcontent(username+":"+trackcontent);
            TrackInfo trackInfo = getTrackInfoByClue(clue);
            iTrackInfoDao.insert(trackInfo);
            //更新线索状态
            clue.setTrackid(trackInfo.getCode());
            iClueDao.updateByPrimaryKeySelective(clue);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500","请求失败！",e.getCause());
        }
        return RespUtil.response("200","关闭成功！",cluecode);
    }

    private Boolean getaBooleanByUpdateTrackInfo(Clue clue, Boolean aBoolean) {
        TrackInfo trackInfo = getTrackInfoByClue(clue);
        iTrackInfoDao.insert(trackInfo);
        //更新线索信息
        aBoolean = getaBooleanByUpdateClue(clue,trackInfo.getCode(),aBoolean);
        return aBoolean;
    }

    private Boolean getaBooleanByUpdateClue(Clue clue, String newTrackid, Boolean aBoolean) {
        if(!aBoolean)return false;
        try {
            clue.setTrackid(newTrackid);
            iClueDao.updateByPrimaryKeySelective(clue);//根据主键更新属性不为null的值
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            //添加线索失败需要回滚
            return false;
        }
        //添加附件信息
        aBoolean = getaBooleanByAddAttachment(clue, aBoolean);
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
        if(!aBoolean)return false;
        try {
            clue.setTrackid(trackCode);
            clue.setState(Commons.CLUE_ON);
            if(StringUtils.isEmpty(clue.getCreatetime()) || clue.getCreatetime().length()<19)clue.setCreatetime(hrUtils.getDateStringByTimeMillis(System.currentTimeMillis()));
            iClueDao.insert(clue);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            //添加线索失败需要回滚
            return false;
        }
        //添加附件信息
        aBoolean = getaBooleanByAddAttachment(clue, aBoolean);
        return aBoolean;
    }

    private Boolean getaBooleanByAddAttachment(Clue clue, Boolean aBoolean) {
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
            logger.info(String.valueOf(e.getCause()));
            //添加附件失败需要回滚
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
        trackInfo.setTrack_content(clue.getUsername()+":"+clue.getTrackcontent());
        //获得时间
        trackInfo.setTrack_date(hrUtils.getDateStringByTimeMillis(l));
        return trackInfo;
    }

    private Clue getClueByClue(Clue c) {
        if(null==c)return null;
        //获得最新的跟踪描述
        c.setTrackcontent(hrUtils.getTrackcontentByTrackid(c.getTrackid()));
        //获得销售人姓名
        c.setSale_truename(hrUtils.getTruenameByEmployeenumber(c.getSale_employeenumber()));
        //获得方案人姓名
        c.setScheme_truename(hrUtils.getTruenameByEmployeenumber(c.getScheme_employeenumber()));
        //获得部门名称
        c.setDepname(hrUtils.getDepnameByEmployeenumber(c.getSale_employeenumber()));
        //获得账号ID
        c.setUsername(hrUtils.getUsernameByEmployeenumber(c.getSale_employeenumber()));
        //获取参与人姓名
        String[] content = {};
        String participate = "";
        if (c.getParticipate() != null) {
            content = c.getParticipate().split(",");
            for (String name : content) {
                participate += hrUtils.getTruenameByEmployeenumber(name) + "," ;
            }
            c.setParticipateName(participate.substring(0,participate.length()-1));
        } else {
            c.setParticipateName("");
        }
        return c;
    }

    @Override
    public String getUpdateTime(HttpServletRequest request) {
        String dependeceCode = request.getParameter("dependenceCode");
        return iClueDao.getUpdateTime(dependeceCode);
    }

    @Override
    public List getRelativeEvent (HttpServletRequest request) {
        String code = request.getParameter("code");
        String clueid = request.getParameter("clueid");
        Opportunity opportunity = new Opportunity();
        opportunity.setCode(code);
        opportunity.setClueid(clueid);
        return iClueDao.getRelativeEvent(opportunity);
    }
}