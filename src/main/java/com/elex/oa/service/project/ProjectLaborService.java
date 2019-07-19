package com.elex.oa.service.project;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface ProjectLaborService {
    // 查询工时信息
    List queryLaborHourInfo(HttpServletRequest request,String employeeNumber) throws ParseException;
    // 添加工时信息
    String updateLaborHourInfo(HttpServletRequest request,String employeeNumber);
}
