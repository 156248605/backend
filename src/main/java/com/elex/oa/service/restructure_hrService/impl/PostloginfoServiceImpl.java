package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.restructure_hr.IPostloginfoDao;
import com.elex.oa.entity.restructure_hrentity.Postloginfo;
import com.elex.oa.service.restructure_hrService.IPostloginfoService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\24 0024 13:18
 * @Version 1.0
 **/
@Service
public class PostloginfoServiceImpl implements IPostloginfoService {
    @Resource
    IPostloginfoDao iPostloginfoDao;
    @Resource
    HrUtilsTemp hrUtilsTemp;

    @Override
    public PageInfo<Postloginfo> queryDeptLogInformations(Integer pageNum, Integer pageSize, Postloginfo postloginfo) {
        PageHelper.startPage(pageNum,pageSize);
        List<Postloginfo> postloginfoList = iPostloginfoDao.selectPostloginfoListByConditions(postloginfo);
        for (Postloginfo p:postloginfoList
             ) {
            p = getDetailPostloginfoByPostloginfo(p);
        }
        return new PageInfo<>(postloginfoList);
    }

    private Postloginfo getDetailPostloginfoByPostloginfo(Postloginfo postloginfo) {
        postloginfo.setPostname(hrUtilsTemp.getPostnameByPostcode(postloginfo.getPostcode()));
        postloginfo.setTransactortruename(hrUtilsTemp.getTruenameByEmployeenumberInnewtable(postloginfo.getTransactoruserid()));
        return postloginfo;
    }
}