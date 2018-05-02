package com.elex.oa.controller.permission;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.permission.Job;
import com.elex.oa.service.permission.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    //岗位信息列表查询
    @RequestMapping("list_query")
    @ResponseBody
    public Map<String,Object> listQuery(Page page, Job job) {
        return jobService.listQuery(page,job);
    }
    //删除岗位
    @RequestMapping("/delete_job_id")
    @ResponseBody
    public Map<String,String> deleteJobById(String id) {
        return jobService.deleteJobById(id);
    }
    //查询岗位详情
    @RequestMapping("/query_details_id")
    @ResponseBody
    public Map<String,Object> queryDetailsById(String id) {
        return jobService.queryDetailsById(id);
    }

    //岗位详情修改
    @RequestMapping("/job_info_modify")
    @ResponseBody
    public Map<String,String> jobInfoModify(String id,String name,String permission,String workflow,String formManage,String equipment,String organization,String personalRecords) {
        return jobService.jobInfoModify(id,name,permission,workflow,formManage,equipment,organization,personalRecords);
    }

    //所有岗位
    @RequestMapping("/query_all_jobs")
    @ResponseBody
    public List<Job> queryAllJobs() {
        return jobService.queryAllJobs();
    }
}
