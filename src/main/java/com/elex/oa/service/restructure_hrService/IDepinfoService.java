package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Depinfo;

import java.util.List;
import java.util.Map;

public interface IDepinfoService {
    Boolean changeTable();

    Map<String,Object> gerDepTree();

    Depinfo queryOneByDepcode(String depcode);
}
