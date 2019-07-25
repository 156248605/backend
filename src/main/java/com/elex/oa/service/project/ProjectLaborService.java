package com.elex.oa.service.project;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ProjectLaborService {
    // 查询工时信息
    Map<String, Object> queryLaborHourInfo(HttpServletRequest request, String employeeNumber) throws ParseException;
    // 添加工时信息
    String updateLaborHourInfo(HttpServletRequest request,String employeeNumber);
    // 添加员工常用项目
    String[] queryCommonProjectInfo(String employeeNumber);
    // 查询员工常用项目
    String updateCommonProjectInfo(HttpServletRequest request,String employeeNumber);
    // 锁定工时数据
    String insertLockingInfo(String date);
}
