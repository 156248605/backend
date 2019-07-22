package com.elex.oa.service.project;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface ProjectLaborService {
    // 查询工时信息
    List queryLaborHourInfo(HttpServletRequest request,String employeeNumber) throws ParseException;
    // 添加工时信息
    String updateLaborHourInfo(HttpServletRequest request,String employeeNumber);
    // 添加员工常用项目
    String[] queryCommonProjectInfo(String employeeNumber);
    // 查询员工常用项目
    String updateCommonProjectInfo(HttpServletRequest request,String employeeNumber);
}
