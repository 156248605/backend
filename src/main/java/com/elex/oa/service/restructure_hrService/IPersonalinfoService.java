package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Personalinfo;

import java.util.List;

public interface IPersonalinfoService {
    Boolean changeTable();

    List<Personalinfo> queryAllUsersByEMPLOYEE_ON();
}
