package com.elex.oa.mongo.project;

import com.elex.oa.entity.permission.Employee;
import com.elex.oa.entity.permission.Job;
import com.elex.oa.entity.project.ProjectCode;
import com.elex.oa.util.project.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewProjectMongo {
    @Autowired
    private MongoTemplate mongoTemplate;

    //获取项目编号
    public String getProjectCode() {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is("NC-ELEXTEC"));
        ProjectCode projectCode = mongoTemplate.findOne(query,ProjectCode.class);
        boolean marker = ProjectUtils.codeValidate(projectCode.getProjectCode());
        if(marker) {
            return projectCode.getProjectCode();
        }
        return ProjectUtils.projectCode(projectCode.getProjectCode());
    }

    //获取所有岗位信息
    public List<Job> getJobs() {
        return mongoTemplate.findAll(Job.class);
    }

    // 获取所有员工信息
    public List<Employee> getEmployees() {
        return mongoTemplate.findAll(Employee.class);
    }
    //修改数据库中的项目编码
    public void modifyProjectCode(String projectCode) {
        String current = ProjectUtils.projectCode(projectCode);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is("NC-ELEXTEC"));
        Update update = Update.update("projectCode",current);
        mongoTemplate.updateFirst(query,update,ProjectCode.class);
    }
}
