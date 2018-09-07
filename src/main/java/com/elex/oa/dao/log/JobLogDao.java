package com.elex.oa.dao.log;

import com.elex.oa.entity.log.JobLog;
import com.elex.oa.entity.log.JobLogQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobLogDao {
    //查询工作日志记录
    List<JobLog> queryList(JobLogQuery jobLogQuery);
    //删除工作日志记录
    void trash(String code);
}
