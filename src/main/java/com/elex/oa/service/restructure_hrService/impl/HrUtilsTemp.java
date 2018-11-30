package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.hr.IDeptDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.entity.hr_entity.Dept;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\29 0029 19:23
 * @Version 1.0
 **/
@Service
public class HrUtilsTemp {
    @Resource
    IHRsetDao ihRsetDao;
    @Resource
    IHrdatadictionaryDao iHrdatadictionaryDao;
    @Resource
    IDeptDao iDeptDao;

    public String getDatacodeByHrsetid(Integer hrsetid) {
        if(null==hrsetid)return null;
        HRset hRset = ihRsetDao.selectById(hrsetid);
        if(null==hRset)return null;
        List<Hrdatadictionary> hrdatadictionaryList = iHrdatadictionaryDao.selectByEntity(new Hrdatadictionary(hRset.getDatatype(), hRset.getDatavalue()));
        if(null==hrdatadictionaryList || hrdatadictionaryList.size()==0){
            //没有则添加
            String datacode = hRset.getDatatype() + "_" + System.currentTimeMillis();
            iHrdatadictionaryDao.insert(new Hrdatadictionary(datacode,hRset.getDatatype(),hRset.getDatavalue()));
            return datacode;
        }else if(hrdatadictionaryList.size()==1){
            //有一个则直接拿取
            return hrdatadictionaryList.get(0).getDatacode();
        }else {
            //有多个则说明有重复数据，需要删除多余的数据
            for(int i = 1;i<hrdatadictionaryList.size();i++){
                iHrdatadictionaryDao.deleteByDatacode(hrdatadictionaryList.get(i).getDatacode());
            }
            String datacode = hrdatadictionaryList.get(0).getDatacode();
            return datacode;
        }
    }

    public String getDepcodeByDepid(Integer depid) {
        if(null==depid)return null;
        Dept dept = iDeptDao.selectDeptByDepid(depid);
        if(null==dept)return null;
        return dept.getDepcode();
    }
}    