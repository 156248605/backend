package com.elex.oa.service.project;

import java.util.List;
import java.util.Map;

public interface ProjectBoardService {
    //列表总览
    Map<String,Object> overview();
    //详情
    Map<String,Object> detail(String projectCode);
    //看板信息（手机）
    List<Map<String,Object>> projectPhone();
}
