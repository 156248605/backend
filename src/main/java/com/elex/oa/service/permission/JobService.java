package com.elex.oa.service.permission;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.permission.Job;

import java.util.List;
import java.util.Map;

public interface JobService {
    //添加岗位信息
    int addJob(Integer id, String postname);
    //岗位信息列表查询
    Map<String,Object> listQuery(Page page, Job job);
    //删除岗位
    Map<String,String> deleteJobById(String id);
    //查询岗位详情
    Map<String,Object> queryDetailsById(String id);
    //岗位详情修改
    Map<String,String> jobInfoModify(String id, String name, String permission, String workflow, String formManage, String equipment, String organization, String personalRecords);
    //所有岗位
    List<Job> queryAllJobs();
}
