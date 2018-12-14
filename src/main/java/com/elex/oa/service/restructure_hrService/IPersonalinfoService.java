package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Personalinfo;

import java.util.List;
import java.util.Map;

public interface IPersonalinfoService {
    Boolean changeTable();

    List<Map<String,String>> queryAllUsersByEMPLOYEE_ON();
}
