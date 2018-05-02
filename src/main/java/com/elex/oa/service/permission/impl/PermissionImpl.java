package com.elex.oa.service.permission.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.common.permission.CommonP;
import com.elex.oa.dao.dao_shiyun.IDeptDao;
import com.elex.oa.entity.entity_shiyun.Dept;
import com.elex.oa.entity.permission.Employee;
import com.elex.oa.entity.permission.Job;
import com.elex.oa.entity.permission.Permission;
import com.elex.oa.mongo.permission.EmployeeMongo;
import com.elex.oa.mongo.permission.JobMongo;
import com.elex.oa.mongo.permission.PermissionMongo;
import com.elex.oa.service.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionImpl implements PermissionService {
    @Autowired
    private PermissionMongo permissionMongo;
    @Autowired
    private JobMongo jobMongo;
    @Autowired
    private EmployeeMongo employeeMongo;
    @Autowired
    private IDeptDao iDeptDao;

    //登录验证
    @Override
    public Map<String, Object> login(String name, String password, HttpServletRequest request) {
        Employee receive = permissionMongo.login(name,password);
        Map<String,Object> result = new HashMap<>();
        if(receive == null) {
            result.put("result","failure");
            result.put("message","账号或密码错误");
            return result;
        } else {
            if(receive.getStatus().equals("n")){
                result.put("result","failure");
                result.put("message","账号被禁用");
                return result;
            }
            List<String> jobList = receive.getJobList();
            List<Integer> permissionList = new ArrayList<>();
            permissionList.add(1);
            int marker = 1;
            for(String id:jobList) {
                Job job = jobMongo.queryJobById(id);
                if(marker ==1){
                    result.put("job",job.getId());
                    result.put("job1",job.getName());
                    marker++;
                }
                for(int permission:job.getPermissions()) {
                    if(permissionList.contains(permission)) {

                    } else {
                        permissionList.add(permission);
                    }
                }
            }
            result.put("result","success");
            result.put("name",receive.getName());
            result.put("password",receive.getPassword());
            result.put("access",permissionList);
            result.put("department",receive.getDepartment());
            if(receive.getDepartment().equals("000")) {
                result.put("department1","系统");
            } else {
                List<Dept> depts = iDeptDao.selectAllDept();
                for(Dept dept:depts) {
                    if(dept.getId().toString().equals(receive.getDepartment())){
                        System.out.println("dept.getDepname():"+dept.getDepname());
                        result.put("department1",dept.getDepname());
                        break;
                    }
                }
            }
            result.put("employeeId",receive.getId());
            request.getSession().setAttribute(CommonP.SESSION_EMPLOYEE_ID,receive.getId());
            request.getSession().setAttribute(CommonP.SESSION_EMPLOYEE_NAME,receive.getName());
            request.getSession().setAttribute(CommonP.SESSION_EMPLOYEE_PERMISSIONS,permissionList);
            return result;
        }
    }

    //查询某员工的权限详情
    @Override
    public List personnelPermission(String id) {
        return permissionMongo.personnelPermission(id);
    }

    //人员选取部分初始化信息
    @Override
    public Map<String, Object> personnelSelect() {
        List<Employee> employeeList = employeeMongo.findAllEmployees();
        List<Job> jobList = jobMongo.queryAllJobs();
        List<Dept> depts = iDeptDao.selectAllDept();
        List<Map<String,String>> departmentList = new ArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("id","000");
        map1.put("name","系统");
        departmentList.add(map1);
        for(Dept d:depts){
            Map<String,String> map2 = new HashMap<>();
            map2.put("id",d.getId()+"");
            map2.put("name",d.getDepname());
            departmentList.add(map2);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("employeeList",employeeList);
        result.put("jobList",jobList);
        result.put("departmentList",departmentList);
        return result;
    }
    //根据选取的权限编号查询子权限的数量以及名称
    @Override
    public Map<String, Object> queryPermissionNum(String permissions) {
        List<String> list = JSONArray.parseArray(permissions,String.class);
        return permissionMongo.queryPermissionNum(list);
    }

    //查询某些员工对应的某些权限
    @Override
    public Map<String, Object> emVsPer(String employees, String permissions) {
        List<Employee> employeeList = JSONArray.parseArray(employees,Employee.class);
        List<String> permissionList = JSONArray.parseArray(permissions,String.class);

        //查询表头
        List<Map<String,Object>> titleList = new ArrayList<>();
        List<Map<String,Object>> titleList1 = new ArrayList<>();
        Map<String,Object> map1= new HashMap<>();
        map1.put("title","员工");
        map1.put("key","employee");
        map1.put("align","center");
        titleList1.add(map1);

        for(String str: permissionList){
            List<Permission> permissionList1 = permissionMongo.queryPermissionDetails(str); //查询某项大权限下小权限的详情
            for(Permission p:permissionList1){
                Map<String,Object> map = new HashMap<>();
                map.put("title",p.getName());
                map.put("key",p.getId()+"");
                map.put("align","center");
                map.put("width",100);
                titleList.add(map);
                titleList1.add(map);
            }
        }

        //查询内容
        List<Map<String,String>> contentList = new ArrayList<>();
        List<Map<String,String>> contentList1 = new ArrayList<>();

        for(Employee e:employeeList) {
            //查询某员工的全部权限
            List<String> jobs = e.getJobList();
            List<Integer> perList = new ArrayList<>();
            for(String str:jobs){
                List<Integer> integerList = permissionMongo.queryPerByRole(str);
                for(int i: integerList){
                    if(perList.contains(i)){

                    } else{
                        perList.add(i);
                    }
                }
            }

            Map<String,String> content = new HashMap<>();
            Map<String,String> content1 = new HashMap<>();
            content1.put("employee",e.getName());

            for(Map<String,Object> m:titleList){
                int value = Integer.parseInt(m.get("key").toString());
                boolean marker = true;

                for(int i : perList){
                    if(i == value){
                        content.put(m.get("key").toString(),"√");
                        content1.put(m.get("key").toString(),"√");
                        marker = false;
                        break;
                    }
                }
                if(marker){
                    content.put(m.get("key").toString(),"");
                    content1.put(m.get("key").toString(),"");
                }
            }
            contentList.add(content);
            contentList1.add(content1);

        }

        Map<String,Object> result = new HashMap<>();
        result.put("title",titleList);
        result.put("title1",titleList1);
        result.put("content",contentList);
        result.put("content1",contentList1);
        return result;
    }
    //查询某些岗位对应的某些权限
    @Override
    public Map<String, Object> jobVsPer(String jobs, String permissions) {
        List<Job> jobList = JSONArray.parseArray(jobs,Job.class);
        List<String> permissionList = JSONArray.parseArray(permissions,String.class);

        //表头部分
        List<Map<String,Object>> headerList = new ArrayList<>();
        List<Map<String,Object>> headerList1 = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("title","岗位");
        map1.put("key","job");
        map1.put("align","center");
        headerList1.add(map1);

        for(String str:permissionList){
            List<Permission> permissionList1 = permissionMongo.queryPermissionDetails(str);
            for(Permission p:permissionList1){
                Map<String,Object> map = new HashMap<>();
                map.put("title",p.getName());
                map.put("key",p.getId()+"");
                map.put("align","center");
                map.put("width",100);
                headerList.add(map);
                headerList1.add(map);
            }
        }

        //内容部分
        List<Map<String,String>> contentList =new ArrayList<>();
        List<Map<String,String>> contentList1 = new ArrayList<>();
        for(Job job:jobList) {
            List<Integer> perList = job.getPermissions();
            Map<String,String> content = new HashMap<>();
            Map<String,String> content1 = new HashMap<>();
            content1.put("job",job.getName());
            for(Map<String,Object> m:headerList){
                int value = Integer.parseInt(m.get("key").toString());
                boolean marker = true;

                for(int i : perList){
                    if(i == value){
                        content.put(m.get("key").toString(),"√");
                        content1.put(m.get("key").toString(),"√");
                        marker = false;
                        break;
                    }
                }
                if(marker){
                    content.put(m.get("key").toString(),"");
                    content.put(m.get("key").toString(),"");
                }
            }
            contentList.add(content);
            contentList1.add(content1);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("columns",headerList);
        result.put("columns1",headerList1);
        result.put("content",contentList);
        result.put("content1",contentList1);
        return result;
    }
}
