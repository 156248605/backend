package com.elex.oa.mongo.project;

import com.elex.oa.entity.project.ProjectCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NewProjectMongo {
    @Autowired
    private MongoTemplate mongoTemplate;


    //修改数据库中的项目编码
    public void modifyProjectCode(String id,String projectCode) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = Update.update("code",projectCode);
        mongoTemplate.updateFirst(query,update,ProjectCode.class);
    }

    //查询某部门对应的项目编号
    public ProjectCode queryProjectCode(String s) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(s));
        ProjectCode projectCode = mongoTemplate.findOne(query,ProjectCode.class);
        if(projectCode == null) { //如果不存在当前部门所在公司的编号,新建
            projectCode.setId(s);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(new Date()).substring(0,8);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(date);
            stringBuilder.append("0001");
            projectCode.setCode(stringBuilder.toString());
            mongoTemplate.save(projectCode);
        }
        return projectCode;
    }

    //更新项目编号
    public void updateProjectCode(ProjectCode projectCode) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(projectCode.getId()));
        Update update = Update.update("code",projectCode.getCode());
    }
}
