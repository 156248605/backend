package com.elex.oa.mongo.permission;

import com.elex.oa.entity.permission.Employee;
import com.elex.oa.entity.permission.Job;
import com.elex.oa.entity.permission.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionMongo {
    @Autowired
    private MongoTemplate mongoTemplate;

    //登录验证
    public Employee login(String name, String password) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name).and("password").is(password));
        Employee employee = mongoTemplate.findOne(query,Employee.class);
        return employee;
    }
    //查询某员工的权限详情
    public List personnelPermission(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Employee employee = mongoTemplate.findOne(query,Employee.class);
        List resultList = new ArrayList();
        for(String str: employee.getJobList()) {
            Query query1 = new Query();
            query1.addCriteria(Criteria.where("id").is(str));
            Job job = mongoTemplate.findOne(query1,Job.class);

            Map<String,Object> baseMap = new HashMap<>();
            List<String> nameList = new ArrayList<>();

            for(int t:job.getPermissions()){
                Query query2 = new Query();
                query2.addCriteria(Criteria.where("id").is(t));
                Permission permission = mongoTemplate.findOne(query2,Permission.class);
                if(permission.getParentId()==0){
                    List<String> strings = new ArrayList<>();
                    strings.add(permission.getName());
                    nameList.add(permission.getId()+"");
                    baseMap.put(permission.getId()+"",strings);
                };
            }
            for(int k:job.getPermissions()){
                Query query3 = new Query();
                query3.addCriteria(Criteria.where("id").is(k));
                Permission permission = mongoTemplate.findOne(query3,Permission.class);
                if(permission.getParentId()!=0){
                    List<String> strings1= (List<String>) baseMap.get(permission.getParentId()+"");
                    strings1.add(" "+permission.getName()+" ");
                    baseMap.put(permission.getParentId()+"",strings1);
                }
            }
            List baseList = new ArrayList();
            baseList.add(job.getName()+"组权限");
            for(String name:nameList){
                baseList.add(baseMap.get(name));
            }
            resultList.add(baseList);
        }
        return  resultList;
    }
    //根据选取的权限编号查询子权限的数量以及名称
    public Map<String,Object> queryPermissionNum(List<String> list) {
        List<String> countList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        for(String str: list) {
            Query query1 = new Query();
            query1.addCriteria(Criteria.where("id").is(Integer.parseInt(str)));
            Permission p = mongoTemplate.findOne(query1,Permission.class);

            String name = p.getName();
            nameList.add(name);
            Query query2 = new Query();
            query2.addCriteria(Criteria.where("parentId").is(Integer.parseInt(str)));
            String count = mongoTemplate.count(query2,Permission.class)+"";
            countList.add(count);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("countList",countList);
        result.put("nameList",nameList);
        return result;
    }

    //查询某项大权限下小权限的详情
    public List<Permission> queryPermissionDetails(String str) {
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("parentId").is(Integer.parseInt(str)));
        return mongoTemplate.find(query1,Permission.class);
    }

    //根据角色Id查询权限
    public List<Integer> queryPerByRole(String str) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(str));
        Job job =mongoTemplate.findOne(query,Job.class);
        return  job.getPermissions();
    }
}
