package com.elex.oa.service.project;

import java.util.Map;

public interface ProjectBoardService {
    //列表总览
    Map<String,Object> overview();
    //详情
    Map<String,Object> detail(String projectCode);
}
