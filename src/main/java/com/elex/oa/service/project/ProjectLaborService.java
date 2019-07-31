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
    String[] queryFrequentProjectInfo(String employeeNumber);
    // 查询员工常用项目
    String updateFrequentProjectInfo(HttpServletRequest request,String employeeNumber);
    // 锁定工时数据
    String insertLockingInfo(String date);
    // 查询部门下每人每月工时
    Map<String,Object> queryLaborHourInfoByDepartment (HttpServletRequest request,String deptId);
    // 获取部门下所有人
    Map<String, Object> queryDeptEmployee (HttpServletRequest request);
    // 查询某月内所有工时
    Map<String, Object> queryLaborHourInfoByMonth (HttpServletRequest request);
    // 查询某项目某年所有工时
    List queryLaborHourInfoByProject (HttpServletRequest request);
    // 查询某年所有项目工时
    List queryLaborHourInfoByYear (HttpServletRequest request);
}
