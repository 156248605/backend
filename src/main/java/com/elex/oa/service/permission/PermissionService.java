package com.elex.oa.service.permission;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PermissionService {
    //登录验证
    Map<String,Object> login(String name, String password, HttpServletRequest request);
    //查询某员工的权限详情
    List personnelPermission(String id);
    //人员选取部分初始化信息
    Map<String,Object> personnelSelect();
    //根据选取的权限编号查询子权限的数量以及名称
    Map<String,Object> queryPermissionNum(String permissions);
    //查询某些员工对应的某些权限
    Map<String,Object> emVsPer(String employees, String permissions);
    //查询某些岗位对应的某些权限
    Map<String,Object> jobVsPer(String jobs, String permissions);
}
