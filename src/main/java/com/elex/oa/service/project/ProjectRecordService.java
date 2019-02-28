package com.elex.oa.service.project;

import com.elex.oa.entity.project.ProjectRecord;

import java.util.List;

public interface ProjectRecordService {
    //查询修改记录
    List<ProjectRecord> queryList(String projectRecord);
}
