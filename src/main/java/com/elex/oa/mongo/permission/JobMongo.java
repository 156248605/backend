package com.elex.oa.mongo.permission;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.permission.Employee;
import com.elex.oa.entity.permission.Job;
import com.elex.oa.entity.permission.Permission;
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
public class JobMongo {
    @Autowired
    private MongoTemplate mongoTemplate;

    //添加岗位
    public int addJob(Integer id, String postname) {
        Job job = new Job();
        job.setId(id+"");
        job.setName(postname);
        mongoTemplate.save(job);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id+""));
        return (int) mongoTemplate.count(query, Job.class);
    }

    public Job queryJobById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query,Job.class);
    }
    //查询所有的岗位
    public List<Job> queryAllJobs() {
        return mongoTemplate.findAll(Job.class);
    }

    //岗位信息列表查询
    public Map<String,Object> listQuery(Page page, Job job) {
        Query query = new Query();
        query.skip((page.getCurrentPage() - 1) * page.getRows());
        query.limit(page.getRows());
        if(job.getId().equals("")) {

        } else {
            query.addCriteria(Criteria.where("id").is(job.getId()));
        }
        if(job.getName().equals("")) {

        } else {
            query.addCriteria(Criteria.where("name").regex(".*"+job.getName()+".*"));
        }
        List<Job> jobs = mongoTemplate.find(query,Job.class);
        List<Employee> employeeList = mongoTemplate.findAll(Employee.class);
        List<Map<String,String>> jobList = new ArrayList<>();
        int marker = 0;
        for(Job j:jobs) {
            Map<String,String> map = new HashMap<>();
            map.put("id",j.getId());
            map.put("name",j.getName());
            StringBuilder stringBuilder = new StringBuilder();
            marker = 0;
            emp:
            for(Employee employee: employeeList) {
                if(employee.getJobList().contains(j.getId())) {
                    if(marker<3) {
                        stringBuilder.append(employee.getName()+" ");
                        marker++;
                    } else {
                        stringBuilder.append(employee.getName()+"...");
                        break emp;
                    }
                }
            }
            map.put("employees",stringBuilder.toString());
            jobList.add(map);
        }

        long total = mongoTemplate.count(query,Job.class);
        long pageNum = total%(page.getRows()) ==0? total/(page.getRows()):total/(page.getRows())+1;
        Map<String,Object> result = new HashMap<>();
        result.put("list",jobList);
        result.put("total",total);
        result.put("pages",pageNum);
        return  result;
    }

    //删除岗位
    public int deleteJobById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.remove(query,Job.class).getN();
    }

    //查询岗位详情
    public Map<String,Object> queryDetailsById(String id) {
        Map<String,Object> result = new HashMap<>();
        Query query = new Query(); //查询所有权限大项
        query.addCriteria(Criteria.where("parentId").is(0));
        List<Permission> contrast = mongoTemplate.find(query,Permission.class);

        Query query1 = new Query(); //根据id查询岗位
        query1.addCriteria(Criteria.where("id").is(id));
        Job job = mongoTemplate.findOne(query1,Job.class);
        List<Integer> permissions = job.getPermissions();
        result.put("name",job.getName());

        List<Employee> employeeList = mongoTemplate.findAll(Employee.class); //查询该岗位上的所有员工
        List<Employee> employees = new ArrayList<>();
        for(Employee e: employeeList) {
            if(e.getJobList().contains(id)){
                employees.add(e);
            }
        }
        result.put("employees",employees);

        List<Permission>  permissionDetails = new ArrayList<>();//某岗位的所有权限 （数据库信息）
        for(int k:permissions){
            Query query3 = new Query();
            query3.addCriteria(Criteria.where("id").is(k));
            Permission permission = mongoTemplate.findOne(query3,Permission.class);
            permissionDetails.add(permission);
        }

        for(Permission pe:contrast) {
            Map<String,Object> map = new HashMap<>();
            map.put("indeterminate",false);
            map.put("value",false);
            for(int per:permissions) {
                if(per == pe.getId()){
                    Query query2 =new Query();
                    query2.addCriteria(Criteria.where("parentId").is(per));
                    List<Permission> permissionList = mongoTemplate.find(query2,Permission.class);
                    int count = permissionList.size();
                    int marker = 0;
                    List<String> basePermission = new ArrayList<String>();
                    for(int perm:permissions){
                        if(0<perm%per&&perm%per<100){
                            marker++;
                            for(Permission p:permissionDetails){
                                if(p.getId() == perm){
                                    basePermission.add(p.getId()+"");
                                    break;
                                }
                            }
                        }
                    }
                    if(marker == count){
                        map.put("indeterminate",false);
                        map.put("value",true);
                    } else if(marker>0){
                        map.put("indeterminate",true);
                        map.put("value",false);
                    } else {
                        map.put("indeterminate",false);
                        map.put("value",false);
                    }
                    map.put("list",basePermission);
                }
            }
            result.put(pe.getNameE(),map);
        }
        return result;
    }

    //岗位详情修改
    public int jobInfoModify(String id, String name, List<Integer> list) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update;
        if(name == null || name.equals("")) {
            update = Update.update("permissions",list);
        } else {
            update = Update.update("name",name).set("permissions",list);
        }
        return mongoTemplate.updateFirst(query,update,Job.class).getN();
    }
}
