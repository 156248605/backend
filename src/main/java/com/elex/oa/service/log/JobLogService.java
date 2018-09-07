package com.elex.oa.service.log;

import com.elex.oa.entity.log.JobLogQuery;
import com.github.pagehelper.PageInfo;

public interface JobLogService {
    //查询工作日志记录
    PageInfo queryList(JobLogQuery jobLogQuery, int pageNum);
    //删除工作日志记录
    String trash(String code);
}
