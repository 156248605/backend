package com.elex.oa.service.ouService.Impl;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.ou.OuDepDao;
import com.elex.oa.dao.ou.OuPostDao;
import com.elex.oa.entity.ou.OuPost;
import com.elex.oa.service.ouService.IOuPostService;
import com.elex.oa.util.resp.RespUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\22 0022 15:17
 * @Version 1.0
 **/
@Service
public class OuPostServiceImpl implements IOuPostService {
    @Resource
    private OuPostDao ouPostDao;

    @Override
    public Object addOuPost(OuPost ouPost) {
        //先判断岗位编号
        if(StringUtils.isBlank(ouPost.getPostcode()))return RespUtil.successResp("500","岗位编号不能为空",null);
        List<OuPost> ouPostListTemp = ouPostDao.select(new OuPost(ouPost.getPostcode()));
        if(ouPostListTemp.size()>0)return RespUtil.successResp("500","岗位编号已存在",null);
        if(StringUtils.isBlank(ouPost.getPostname()))return RespUtil.successResp("500","岗位名称不能为空",null);
        if(null==ouPost.getPostlevelid())return RespUtil.successResp("500","岗级不能为空",null);
        ouPost.setId("post_"+System.currentTimeMillis());
        ouPost.setState(Commons.POST_ON);
        try {
            ouPostDao.insertSelective(ouPost);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("500","岗位添加失败",null);
        }
        return RespUtil.successResp("200","岗位添加成功",null);
    }
}