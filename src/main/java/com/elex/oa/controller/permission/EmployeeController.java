package com.elex.oa.controller.permission;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.permission.Employee;
import com.elex.oa.service.permission.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    //员工禁用状态修改
    @RequestMapping("/status_modify")
    @ResponseBody
    public Map<String,String>  statusModify (String id,String status){
        return employeeService.statusModify(id,status);
    }

    //员工详情列表查询
    @RequestMapping("/list_query")
    @ResponseBody
    public Map<String,Object> listQuery(Page page, Employee employee) {
        return employeeService.listQuery(page,employee);
    }

    //查询某员工的岗位信息
    @RequestMapping("/query_jobs_id")
    @ResponseBody
    public Map<String,Object> queryJobsById(String id) {
        return employeeService.queryJobsById(id);
    }

    //修改员工的岗位信息
    @RequestMapping("/modify_job_id")
    @ResponseBody
    public Map<String,Object> modifyJobById(String id, String jobs) {
        return employeeService.modifyJobById(id,jobs);
    }

    //查询所有员工
    @RequestMapping("/find_all_employees")
    @ResponseBody
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    //修改员工密码
    @RequestMapping("/modify_password_id")
    @ResponseBody
    public Map<String,String> modifyPasswordById(String id, String password) {
        return employeeService.modifyPasswordById(id,password);
    }
}
