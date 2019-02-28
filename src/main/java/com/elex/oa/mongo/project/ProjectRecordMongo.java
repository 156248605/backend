package com.elex.oa.mongo.project;

import com.elex.oa.entity.project.ProjectRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectRecordMongo {
    @Autowired
    private MongoTemplate mongoTemplate;

    //添加记录
    public void addRecord(ProjectRecord projectRecord) {
        mongoTemplate.save(projectRecord);
    }

    public List<ProjectRecord> queryList(Query query) {
        return mongoTemplate.find(query, ProjectRecord.class);
    }
}
