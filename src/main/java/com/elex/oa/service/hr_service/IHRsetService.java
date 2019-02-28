package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:HRset
 * @Date:Created in  18:38 2018\5\11 0011
 * @Modify By:
 */
public interface IHRsetService {
    Object addOne(HRset hRset);

    List<HRset> queryAll();

    List<HRset> queryByConditions(HRset hRset);

    HRset queryById(Integer id);

    PageInfo queryByParam(Map<String, Object> paramMap);

    Boolean removeOne(Integer id);

    HRset modifyOne(HRset hRset);

    Object modifyHRset(HRset hRset);

    Map<Integer,String> removeMultiple(List<Integer> ids);

    Boolean queryValidateHRset(HRset hRset);

    List<HRset> queryPostgradeByPostfamilyid(Integer postfamilyid);

    Boolean updateDatacode();

    Object supplyDatacode();

    Object addPostfamilyAndPostgrade(Integer postfamilyid, Integer postgradeid);
}
