package com.elex.oa.service.reportObstacles.impl;

import com.elex.oa.common.reportObstacles.ReportObstaclesCommons;
import com.elex.oa.dao.business.IBusinessAttachmentDao;
import com.elex.oa.dao.reportObstacles.IObstaclesInfoDao;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.reportObstacles.ObstaclesInfo;
import com.elex.oa.entity.reportObstacles.ObstaclesQueryInfo;
import com.elex.oa.service.reportObstacles.IObstaclesInfoService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public Boolean addObstaclesInfo(ObstaclesInfo obstaclesInfo) {
        if(null==obstaclesInfo)return false;
        //先添加附件
        List<BusinessAttachment> attachmentList = obstaclesInfo.getAttachmentList();
        if(null!=attachmentList && attachmentList.size()>0){
            for (BusinessAttachment ba:attachmentList
                 ) {
                try {
                    ba.setCode("attachment_"+System.currentTimeMillis());
                    iBusinessAttachmentDao.insert(ba);
                } catch (Exception e) {
                    e.printStackTrace();
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
            e.printStackTrace();
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
    public Object changeObstaclesState(String id, String flag, String location_description, String process_description) {
        //过滤条件
        if(StringUtils.isBlank(id) || StringUtils.isBlank(flag))return RespUtil.response("500","修改失败！","id,flag都不能为空");
        ObstaclesInfo obstaclesInfo = iObstaclesInfoDao.selectByPrimaryKey(id);
        if(null==obstaclesInfo)return RespUtil.response("500","修改失败！","id所在报障不存在！");
        //修改状态
        if(flag.equals(ReportObstaclesCommons.OBSTACLES_FIND)){
            obstaclesInfo.setState(ReportObstaclesCommons.OBSTACLES_FIND);
            obstaclesInfo.setLocationDescription(location_description);
        }else if(flag.equals(ReportObstaclesCommons.OBSTACLES_OFF)){
            obstaclesInfo.setState(ReportObstaclesCommons.OBSTACLES_OFF);
            obstaclesInfo.setProcessDescription(process_description);
        }else {
            return RespUtil.response("500","修改失败！","flag报障状态错误！");
        }
        try {
            iObstaclesInfoDao.updateByPrimaryKeySelective(obstaclesInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.response("500","修改失败！","报障状态修改失败！");
        }
        return RespUtil.response("200","修改成功！",null);
    }


}
