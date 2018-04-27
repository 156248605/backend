package com.elex.oa.mongo.permission;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.permission.Employee;
import com.elex.oa.entity.permission.Job;
import com.elex.oa.util.util_per.SpellUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeMongo {
    @Autowired
    private MongoTemplate mongoTemplate;
    //添加员工信息
    public int addEmployee(String id, String name, Integer depid, Integer postid, Integer isactive) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setDepartment(depid+"");
        employee.setPassword("123456");
        employee.setPhoneticize(SpellUtils.phoneticize(name));
        List<String> list = new ArrayList<>();
        list.add(postid+"");
        employee.setJobList(list);
        if(isactive == 1) {
            employee.setStatus("y");
        } else {
            employee.setStatus("n");
        }
        mongoTemplate.save(employee);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return (int) mongoTemplate.count(query,Employee.class);
    }
    //修改员工的部门信息
    public int departmentModify(String id, Integer department) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update  update = Update.update("department",department+"");
        return mongoTemplate.updateFirst(query,update,Employee.class).getN();
    }

    //修改员工的禁用状态
    public int statusModify(String id, String status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = Update.update("status",status);
        return mongoTemplate.updateFirst(query,update, Employee.class).getN();
   }

    //员工详情列表查询
    public Map<String,Object> listQuery(Page page, Employee employee) {
        Query query = new Query();
        query.skip((page.getCurrentPage()-1)*page.getRows());
        query.limit(page.getRows());
        if(employee.getId().equals("")) {

        } else {
            query.addCriteria(Criteria.where("id").is(employee.getId()));
        }
        if(employee.getName().equals("")) {

        } else {
            query.addCriteria(Criteria.where("name").regex(".*"+employee.getName()+".*"));
        }
        if(employee.getStatus().equals("")) {

        } else {
            query.addCriteria(Criteria.where("status").is(employee.getStatus()));
        }
        List<Employee> employeeList = mongoTemplate.find(query,Employee.class);
        for(Employee emp:employeeList){
            StringBuilder stringBuilder = new StringBuilder();
            if(emp.getJobList() == null){
                continue;
            }
            for(int k=0;k<emp.getJobList().size();k++){
                Query query1 = new Query();
                query1.addCriteria(Criteria.where("id").is(emp.getJobList().get(k)));
                Job job = mongoTemplate.findOne(query1,Job.class);
                if(k<emp.getJobList().size()-1){
                    stringBuilder.append(job.getName()+";");
                } else {
                    stringBuilder.append(job.getName());
                }
            }
            emp.setJobs(stringBuilder.toString());
        }
        long total = mongoTemplate.count(query,Employee.class);
        long pageNum = total%(page.getRows()) ==0? total/(page.getRows()):total/(page.getRows())+1;
        Map<String,Object> result = new HashMap<>();
        result.put("list",employeeList);
        result.put("total",total);
        result.put("pages",pageNum);
        result.put("pageNum",page.getCurrentPage());
        return result;
    }

    //修改员工的岗位信息
    public int modifyJobById(String id, String jobs) {
        List jobList = JSONArray.parseArray(jobs,String.class);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = Update.update("jobList",jobList);
        return mongoTemplate.updateFirst(query,update,Employee.class).getN();
    }

    //根据id查询某员工的岗位信息，以字符串的形式返回
    public String queryJobsById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Employee employee = mongoTemplate.findOne(query,Employee.class);
        List<String> jobs = employee.getJobList();
        if(jobs == null || jobs.size() == 0) {
            return "当前没有岗位";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int k = 0; k < jobs.size(); k++) {
            Query query1 = new Query();
            query1.addCriteria(Criteria.where("id").is(jobs.get(k)));
            Job job = mongoTemplate.findOne(query1,Job.class);
            stringBuilder.append(job.getName());
            if(k == jobs.size() - 1) {

            } else {
                stringBuilder.append("、");
            }
        }
        return  stringBuilder.toString();
    }

    //查询所有员工
    public List<Employee> findAllEmployees() {
        return mongoTemplate.findAll(Employee.class);
    }

    //删除某一岗位时，清空该岗位的所有用户
    public int removeEmployeeForJob(String id)  {
        List<Employee> employeeList = mongoTemplate.findAll(Employee.class);
        for(Employee employee:employeeList) {
            if(employee.getJobList().contains(id)) {
                employee.getJobList().remove(id);
                Query query = new Query();
                query.addCriteria(Criteria.where("id").is(employee.getId()));
                Update update = Update.update("jobList",employee.getJobList());
                int judgment = mongoTemplate.updateFirst(query,update,Employee.class).getN();
                if(judgment == 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

    //修改员工密码
    public int modifyPasswordById(String id, String password) {
        Query  query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = Update.update("password",password);
        return mongoTemplate.updateFirst(query,update,Employee.class).getN();
    }
}
