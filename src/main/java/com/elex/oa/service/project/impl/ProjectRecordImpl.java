package com.elex.oa.service.project.impl;

import com.elex.oa.entity.project.ProjectRecord;
import com.elex.oa.mongo.project.ProjectRecordMongo;
import com.elex.oa.service.project.ProjectRecordService;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectRecordImpl implements ProjectRecordService {

    @Resource
    private ProjectRecordMongo projectRecordMongo;

    @Override
    public List<ProjectRecord> queryList(String projectCode) {
        Query query = new Query();
        query.addCriteria(Criteria.where("projectCode").is(projectCode));
        query.with(new Sort(Sort.Direction.DESC, "_id"));
        return projectRecordMongo.queryList(query);
    }
}
