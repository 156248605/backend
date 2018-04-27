package com.elex.oa.service.permission.impl;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.permission.Employee;
import com.elex.oa.entity.permission.Job;
import com.elex.oa.mongo.permission.EmployeeMongo;
import com.elex.oa.mongo.permission.JobMongo;
import com.elex.oa.service.permission.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeMongo employeeMongo;

    @Autowired
    private JobMongo jobMongo;

    //添加员工信息
    @Override
    public int addEmployee(String name, String id, Integer depid, Integer postid, Integer isactive) {
        return employeeMongo.addEmployee(id,name,depid,postid,isactive);
    }

    //修改员工的部门信息
    @Override
    public int departmentModify(String id, Integer department) {
        return employeeMongo.departmentModify(id,department);
    }

    //修改员工的禁用状态
    @Override
    public Map<String, String> statusModify(String id, String status) {
        int judgment = employeeMongo.statusModify(id,status);
        Map<String,String> map = new HashMap<>();
        if(judgment == 1) {
            map.put("result","success");
            if(status.equals("y")){
                map.put("message","已启用");
            }else {
                map.put("message","已禁用");
            }
        } else {
            map.put("result","failure");
            map.put("message","修改失败，稍后再试");
        }
        return map;
    }
    //员工详情列表查询
    @Override
    public Map<String, Object> listQuery(Page page, Employee employee) {
        return employeeMongo.listQuery(page,employee);
    }

    //查询某员工的岗位信息
    @Override
    public Map<String, Object> queryJobsById(String id) {
        Map<String,Object> map = new HashMap<>();
        String jobs = employeeMongo.queryJobsById(id);
        map.put("jobs",jobs);
       /* List<Position> positionList = permissionDao.queryAllJob();*/
        List<Job> jobList = jobMongo.queryAllJobs();
        map.put("jobList",jobList);
        return map;
    }

    //修改员工的岗位信息
    @Override
    public Map<String, Object> modifyJobById(String id, String jobs) {
        int result = employeeMongo.modifyJobById(id,jobs);
        Map<String,Object> map = new HashMap<>();
        if(result == 1){
            map.put("result","success");
            map.put("message","更新成功！");
        } else {
            map.put("result","failure");
            map.put("message","更新失败！");
        }
        return map;
    }

    //查询所有员工
    @Override
    public List<Employee> findAllEmployees() {
        return employeeMongo.findAllEmployees();
    }

    //修改员工密码
    @Override
    public Map<String, String> modifyPasswordById(String id, String password) {
        int judgment = employeeMongo.modifyPasswordById(id,password);
        Map<String,String> map = new HashMap<>();
        if(judgment == 1){
            map.put("result","success");
            map.put("message","更新成功！");
        } else {
            map.put("result","failure");
            map.put("message","更新失败！");
        }
        return map;
    }
}
