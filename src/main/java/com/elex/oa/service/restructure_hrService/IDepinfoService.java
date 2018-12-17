package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Depinfo;

import java.util.List;
import java.util.Map;

public interface IDepinfoService {
    Boolean changeTable();

    Map<String,Object> getDepTree();

    Depinfo queryOneByDepcode(String depcode);

    Boolean addOneDepartment(Depinfo depinfo,String transactorusername);

    List<Depinfo> queryDepartmentinfoList();

    List<Map<String,String>> queryDepartmentsRemoveChilren(String depcode);

    Boolean updateOneDepartment(Depinfo depinfo,String transactorusername,String oldDepcode);

    List<Map<String,Object>> getSortDepinformation(String depcode);

    Map<String,Object> submitSortdata(List<Map> respMap);

    Boolean deleteDeptsByDepcode(String depcode);
}
