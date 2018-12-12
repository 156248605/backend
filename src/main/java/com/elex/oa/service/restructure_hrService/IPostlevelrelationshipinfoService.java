package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;

import java.util.List;

public interface IPostlevelrelationshipinfoService {
    Boolean changeTable();

    List<Hrdatadictionary> queryPostgradeByPostfamilycode(String postfamilycode);
}
