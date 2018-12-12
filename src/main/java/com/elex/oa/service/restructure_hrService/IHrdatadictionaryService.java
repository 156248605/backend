package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.github.pagehelper.PageInfo;

public interface IHrdatadictionaryService {
    Boolean changeTable();

    PageInfo<Hrdatadictionary> getPageInfo(Integer pageNum,Integer pageSize,Hrdatadictionary hrdatadictionary);

    Boolean add(Hrdatadictionary hrdatadictionary);
}
