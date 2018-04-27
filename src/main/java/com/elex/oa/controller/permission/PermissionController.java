package com.elex.oa.controller.permission;

import com.elex.oa.service.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
@CrossOrigin
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    //登录验证
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String name, String password, HttpServletRequest request) {
        return permissionService.login(name,password,request);
    }

    //查询某员工的权限详情
    @RequestMapping("/personnel_permissions")
    @ResponseBody
    public List personnelPermission (String id) {
        return permissionService.personnelPermission(id);
    }

    //人员选取部分初始化信息
    @RequestMapping("/personnel_select")
    @ResponseBody
    public Map<String,Object> personnelSelect() {
        return permissionService.personnelSelect();
    }

    //根据选取的权限编号查询子权限的数量以及名称
    @RequestMapping("query_permission_num")
    @ResponseBody
    public Map<String,Object> queryPermissionNum(String permissions) {
        return permissionService.queryPermissionNum(permissions);
    }

    //查询某些员工对应的某些权限
    @RequestMapping("/em_vs_per")
    @ResponseBody
    public Map<String,Object> emVsPer(String employees,String permissions){
        return permissionService.emVsPer(employees,permissions);
    }

    //查询某些岗位对应的某些权限
    @RequestMapping("/job_vs_per")
    @ResponseBody
    public Map<String,Object> jobVsPer(String jobs,String permissions){
        return permissionService.jobVsPer(jobs,permissions);
    }

}
