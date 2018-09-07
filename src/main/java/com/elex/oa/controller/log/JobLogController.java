package com.elex.oa.controller.log;

import com.elex.oa.entity.log.JobLogQuery;
import com.elex.oa.service.log.JobLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/joblog")
public class JobLogController {
    @Autowired
    private JobLogService jobLogService;

    //查询工作日志记录
    @RequestMapping("/query_list")
    @ResponseBody
    public PageInfo queryList(JobLogQuery jobLogQuery, int pageNum) {
        return jobLogService.queryList(jobLogQuery, pageNum);
    }

    //删除工作日志记录
    @RequestMapping("/trash")
    @ResponseBody
    public String trash(String code) {
        return jobLogService.trash(code);
    }

}
