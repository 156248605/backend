package com.elex.oa.service.ou_service;

import com.elex.oa.entity.ou.OuDep;

import java.util.List;
import java.util.Map;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\22 0022 15:12
 * @Version 1.0
 **/
public interface IOuDepService {
    Object addOuDep(OuDep ouDep);

    Object listDepts();

    Object queryOneDepByDepcode(String code);

    Object queryAllDepinfoButSelf(String depcode);

    Object queryAllDepByDepON();

    Object modifyOuDep(OuDep ouDep, String username);

    Object removeDeptsByDepcode(String depcode);

    Object querySortdataByParentDepcode(String parentDepcode);

    Object submitSortdata(List<Map> sortData);
}
