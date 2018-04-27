package com.elex.oa.service.permission;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.permission.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    //添加员工信息
    int addEmployee(String name, String id, Integer depid, Integer postid, Integer isactive);
    //修改员工的部门信息
    int departmentModify(String id, Integer department);
    //修改员工的禁用状态
    Map<String,String> statusModify(String id, String status);
    //员工详情列表查询
    Map<String,Object> listQuery(Page page, Employee employee);
    //查询某员工的岗位信息
    Map<String,Object> queryJobsById(String id);
    //修改员工的岗位信息
    Map<String,Object> modifyJobById(String id, String jobs);
    //查询所有员工
    List<Employee> findAllEmployees();
    //修改员工密码
    Map<String,String> modifyPasswordById(String id, String password);
}
