package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.restructure_hr.IDeploginfoDao;
import com.elex.oa.entity.restructure_hrentity.Deploginfo;
import com.elex.oa.service.restructure_hrService.IDeploginfoService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\17 0017 16:06
 * @Version 1.0
 **/
@Service
public class DeploginfoServiceImpl implements IDeploginfoService {
    @Resource
    IDeploginfoDao iDeploginfoDao;
    @Resource
    HrUtilsTemp hrUtilsTemp;

    @Override
    public PageInfo<Deploginfo> queryDeptLogInformations(Integer pageNum, Integer pageSize, Deploginfo deploginfo) {
        PageHelper.startPage(pageNum,pageSize);
        List<Deploginfo> deploginfoList = iDeploginfoDao.selectDeploginfoListByConditions(deploginfo);
        for (Deploginfo d:deploginfoList
             ) {
            d = getDetailDeploginfoByDeploginfo(d);
        }
        return new PageInfo<>(deploginfoList);
    }

    @Override
    public List<Deploginfo> queryAllDeptLogInformations() {
        List<Deploginfo> deploginfoList = iDeploginfoDao.selectAll();
        for (Deploginfo d:deploginfoList
             ) {
            d = getDetailDeploginfoByDeploginfo(d);
        }
        return deploginfoList;
    }

    private Deploginfo getDetailDeploginfoByDeploginfo(Deploginfo deploginfo){
        deploginfo.setDepname(hrUtilsTemp.getDepnameByDepcode(deploginfo.getDepcode()));
        deploginfo.setTransactortruename(hrUtilsTemp.getTruenameByEmployeenumberInnewtable(deploginfo.getTransactoruserid()));
        return deploginfo;
    }
}