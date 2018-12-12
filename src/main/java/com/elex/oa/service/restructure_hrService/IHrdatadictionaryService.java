package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IHrdatadictionaryService {
    Boolean changeTable();

    PageInfo<Hrdatadictionary> getPageInfo(Integer pageNum,Integer pageSize,Hrdatadictionary hrdatadictionary);

    Boolean add(Hrdatadictionary hrdatadictionary);

    List<Hrdatadictionary> queryByConditions(Hrdatadictionary hrdatadictionary);

    List<Hrdatadictionary> queryAll();

    Boolean queryValidateHrdatadictionary(Hrdatadictionary hrdatadictionary);

    Map<String,String> removeMultipleByCodes(List<String> ids);

    Boolean modifyHrdatadictionary(Hrdatadictionary hrdatadictionary);
}
