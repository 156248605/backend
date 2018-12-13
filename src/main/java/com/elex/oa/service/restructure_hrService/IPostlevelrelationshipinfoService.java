package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.entity.restructure_hrentity.Postlevelrelationshipinfo;

import java.util.List;

public interface IPostlevelrelationshipinfoService {
    Boolean changeTable();

    List<Hrdatadictionary> queryPostgradeByPostfamilycode(String postfamilycode);

    Boolean add(Postlevelrelationshipinfo postlevelrelationshipinfo);

    List<Postlevelrelationshipinfo> queryAllPostlevelrelationshipinfo();

    Boolean removeByIds(List<String> ids);
}
