package com.elex.oa.service.restructure_hrService;

import java.util.Map;

public interface IPostinfoService {
    Boolean changeTable();

    Boolean updateNodelevel();

    Map<String,Object> getPostTree();
}
