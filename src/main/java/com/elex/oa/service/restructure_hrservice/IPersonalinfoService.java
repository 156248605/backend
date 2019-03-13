package com.elex.oa.service.restructure_hrservice;

import java.util.List;
import java.util.Map;

public interface IPersonalinfoService {
    Boolean changeTable();

    List<Map<String,String>> queryAllUsersByEmployeeON();
}
