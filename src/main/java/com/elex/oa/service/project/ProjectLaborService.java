package com.elex.oa.service.project;

import javax.servlet.http.HttpServletRequest;

public interface ProjectLaborService {
    // 查询工时信息
    String queryLaborHourInfo(HttpServletRequest request);
    // 添加工时信息
    String updateLaborHourInfo(HttpServletRequest request);
}
