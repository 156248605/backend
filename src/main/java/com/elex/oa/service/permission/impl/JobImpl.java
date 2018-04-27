package com.elex.oa.service.permission.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.permission.Job;
import com.elex.oa.mongo.permission.EmployeeMongo;
import com.elex.oa.mongo.permission.JobMongo;
import com.elex.oa.service.permission.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobImpl implements JobService {
    @Autowired
    private JobMongo jobMongo;
    @Autowired
    private EmployeeMongo employeeMongo;

    //岗位添加
    @Override
    public int addJob(Integer id, String postname) {
        return jobMongo.addJob(id,postname);
    }

    //岗位信息列表查询
    @Override
    public Map<String, Object> listQuery(Page page, Job job) {
        return jobMongo.listQuery(page,job);
    }

    //删除岗位
    @Override
    public Map<String, String> deleteJobById(String id) {
        Map<String,String> result = new HashMap<>();
        int judgment1 = employeeMongo.removeEmployeeForJob(id); //删除某一岗位时，清空该岗位的所有用户
        if(judgment1 == 0) {
            result.put("result","failure");
            result.put("message","该岗位有特殊员工占用，不能删除！");
            return result;
        }
        int judgment2 = jobMongo.deleteJobById(id);
        if(judgment2 == 0) {
            result.put("result","failure");
            result.put("message","岗位特殊，不予删除！");
        } else {
            result.put("result","success");
            result.put("message","已删除该岗位！");
        }
        return result;
    }

    //查询岗位详情
    @Override
    public Map<String, Object> queryDetailsById(String id) {
        return jobMongo.queryDetailsById(id);
    }

    //岗位详情修改
    @Override
    public Map<String, String> jobInfoModify(String id, String name, String permission, String workflow, String formManage, String equipment, String organization, String personalRecords) {
        List<Integer> list = new ArrayList<>();

        if(!permission.equals("") || permission != null) { //权限管理部分
            List<String> permissionList = JSONArray.parseArray(permission,String.class);
            int marker = 1;
            for(String str: permissionList) {
                if(marker == 1) {
                    int header = Integer.parseInt(str) - (Integer.parseInt(str)%100);
                    list.add(header);
                    marker++;
                }
                list.add(Integer.parseInt(str));
            }
        }
        if(!workflow.equals("") || workflow != null) { //流程管理
            List<String> workflowList = JSONArray.parseArray(workflow,String.class);
            int marker = 1;
            for(String str: workflowList) {
                if(marker == 1) {
                    int header = Integer.parseInt(str) - (Integer.parseInt(str)%100);
                    list.add(header);
                    marker++;
                }
                list.add(Integer.parseInt(str));
            }
        }

        if(!formManage.equals("") || formManage != null) { //表单管理
            List<String> formManageList = JSONArray.parseArray(formManage,String.class);
            int marker = 1;
            for(String str: formManageList) {
                if(marker == 1) {
                    int header = Integer.parseInt(str) - (Integer.parseInt(str)%100);
                    list.add(header);
                    marker++;
                }
                list.add(Integer.parseInt(str));
            }
        }

        if(!equipment.equals("") || equipment != null) { //设备管理
            List<String> equipmentList = JSONArray.parseArray(equipment,String.class);
            int marker = 1;
            for(String str: equipmentList) {
                if(marker == 1) {
                    int header = Integer.parseInt(str) - (Integer.parseInt(str)%100);
                    list.add(header);
                    marker++;
                }
                list.add(Integer.parseInt(str));
            }
        }

        if(!organization.equals("") || organization != null) { //组织机构
            List<String> organizationList = JSONArray.parseArray(organization,String.class);
            int marker = 1;
            for(String str: organizationList) {
                if(marker == 1) {
                    int header = Integer.parseInt(str) - (Integer.parseInt(str)%100);
                    list.add(header);
                    marker++;
                }
                list.add(Integer.parseInt(str));
            }
        }

        if(!personalRecords.equals("") || personalRecords != null) { //人事档案
            List<String> personalRecordsList = JSONArray.parseArray(personalRecords,String.class);
            int marker = 1;
            for(String str: personalRecordsList) {
                if(marker == 1) {
                    int header = Integer.parseInt(str) - (Integer.parseInt(str)%100);
                    list.add(header);
                    marker++;
                }
                list.add(Integer.parseInt(str));
            }
        }
        int judgment = jobMongo.jobInfoModify(id,name,list);

        Map<String,String> result = new HashMap<>();
        if(judgment == 1){
            result.put("result","success");
            result.put("message","更新成功！");
        } else if(judgment == 0){
            result.put("result","failure");
            result.put("message","跟新失败！");
        } else {
            result.put("result","failure");
            result.put("message","系统出现意外状况！");
        }

        return result;
    }

    //所有岗位
    @Override
    public List<Job> queryAllJobs() {
        return jobMongo.queryAllJobs();
    }
}
