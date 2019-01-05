package com.elex.oa.service.reportObstacles.impl;

import com.elex.oa.common.reportObstacles.ReportObstaclesCommons;
import com.elex.oa.dao.business.IBusinessAttachmentDao;
import com.elex.oa.dao.reportObstacles.IObstaclesInfoDao;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.reportObstacles.ObstaclesInfo;
import com.elex.oa.service.reportObstacles.IObstaclesInfoService;
import com.elex.oa.util.hr_util.HrUtils;
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
}
