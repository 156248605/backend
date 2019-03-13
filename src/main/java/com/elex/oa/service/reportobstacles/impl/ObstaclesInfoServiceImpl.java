package com.elex.oa.service.reportobstacles.impl;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.common.reportObstacles.ReportObstaclesCommons;
import com.elex.oa.dao.business.IBusinessAttachmentDao;
import com.elex.oa.dao.reportobstacles.IObstaclesInfoDao;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.reportObstacles.ObstaclesInfo;
import com.elex.oa.entity.reportObstacles.ObstaclesQueryInfo;
import com.elex.oa.service.reportobstacles.IObstaclesInfoService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\4 0004 13:51
 * @Version 1.0
 **/
@Service
public class ObstaclesInfoServiceImpl implements IObstaclesInfoService {
    @Resource
    private IObstaclesInfoDao iObstaclesInfoDao;
    @Resource
    private HrUtils hrUtils;
    @Resource
    IBusinessAttachmentDao iBusinessAttachmentDao;

    private static Logger logger = LoggerFactory.getLogger(ObstaclesInfoServiceImpl.class);

    @Override
    public Boolean addObstaclesInfo(ObstaclesInfo obstaclesInfo) {
        if(null==obstaclesInfo)return false;
        //先添加附件
        List<BusinessAttachment> attachmentList = obstaclesInfo.getAttachmentList();
        if(null!=attachmentList && !attachmentList.isEmpty()){
            for (BusinessAttachment ba:attachmentList
                 ) {
                try {
                    ba.setCode("attachment_"+System.currentTimeMillis());
                    iBusinessAttachmentDao.insert(ba);
                } catch (Exception e) {
                    logger.info(String.valueOf(e.getCause()));
                    return false;
                }
            }
        }
        //再添加报障信息
        obstaclesInfo.setCreatetime(hrUtils.getDateStringByTimeMillis(System.currentTimeMillis()));
        obstaclesInfo.setState(ReportObstaclesCommons.OBSTACLES_ON);
        try {
            iObstaclesInfoDao.insertSelective(obstaclesInfo);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return false;
        }
        return true;
    }

    @Override
    public List<ObstaclesInfo> queryAllObstaclesInfo() {
        List<ObstaclesInfo> obstaclesInfoList = iObstaclesInfoDao.selectAll();
        //获取相应的附件信息
        for (ObstaclesInfo obs:obstaclesInfoList
             ) {
            List<BusinessAttachment> attachmentList = iBusinessAttachmentDao.select(new BusinessAttachment(obs.getId()));
            obs.setAttachmentList(attachmentList);
        }
        return obstaclesInfoList;
    }

    @Override
    public PageInfo<ObstaclesInfo> queryObstaclesByConditions(Integer pageNum, Integer pageSize, ObstaclesQueryInfo obstaclesQueryInfo) {
        PageHelper.startPage(pageNum,pageSize);
        List<ObstaclesInfo> obstaclesInfoList = iObstaclesInfoDao.selectByConditions(obstaclesQueryInfo);
        //获取相应的附件信息
        for (ObstaclesInfo obs:obstaclesInfoList
        ) {
            List<BusinessAttachment> attachmentList = iBusinessAttachmentDao.select(new BusinessAttachment(obs.getId()));
            obs.setAttachmentList(attachmentList);
        }
        return new PageInfo<>(obstaclesInfoList);
    }

    @Override
    public Object changeObstaclesState(String id, String flag, String locationDescription, String processDescription) {
        //过滤条件
        if(StringUtils.isBlank(id) || StringUtils.isBlank(flag))return RespUtil.response("500", Commons.RESP_FAIL,"id,flag都不能为空");
        ObstaclesInfo obstaclesInfo = iObstaclesInfoDao.selectByPrimaryKey(id);
        if(null==obstaclesInfo)return RespUtil.response("500",Commons.RESP_FAIL,"id所在报障不存在！");
        //修改状态
        if(flag.equals(ReportObstaclesCommons.OBSTACLES_FIND)){
            obstaclesInfo.setState(ReportObstaclesCommons.OBSTACLES_FIND);
            obstaclesInfo.setLocationDescription(locationDescription);
        }else if(flag.equals(ReportObstaclesCommons.OBSTACLES_OFF)){
            obstaclesInfo.setState(ReportObstaclesCommons.OBSTACLES_OFF);
            obstaclesInfo.setProcessDescription(processDescription);
        }else {
            return RespUtil.response("500",Commons.RESP_FAIL,"flag报障状态错误！");
        }
        try {
            iObstaclesInfoDao.updateByPrimaryKeySelective(obstaclesInfo);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500",Commons.RESP_FAIL,"报障状态修改失败！");
        }
        return RespUtil.response("200",Commons.RESP_SUCCESS,null);
    }


}
