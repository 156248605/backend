package com.elex.oa.service.log.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.log.JobLogDao;
import com.elex.oa.entity.log.JobLog;
import com.elex.oa.entity.log.JobLogQuery;
import com.elex.oa.service.log.JobLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobLogImpl implements JobLogService {
    @Resource
    private JobLogDao jobLogDao;

    //查询工作日志记录
    @Override
    public PageInfo queryList(JobLogQuery jobLogQuery, int pageNum) {
        List<String> list3 = JSONArray.parseArray(jobLogQuery.getTime3(),String.class);
        if(list3.get(0).equals("") || list3.get(0) == null) {

        } else {
            jobLogQuery.setTime3a(list3.get(0));
            jobLogQuery.setTime3b(list3.get(1));
        }
        PageHelper.startPage(pageNum,10);
        List<JobLog> list = this.jobLogDao.queryList(jobLogQuery);
        return new PageInfo(list);
    }

    //删除工作日志记录
    @Override
    public String trash(String code) {
        this.jobLogDao.trash(code);
        return "1";
    }
}
