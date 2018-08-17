package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.project.ProjectInforDao;
import com.elex.oa.dao.project.ProjectSetDao;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.ProjectInfor;
import com.elex.oa.entity.project.ProjectVarious;
import com.elex.oa.service.project.ProjectInforService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectInforImpl implements ProjectInforService {

    @Resource
    private ProjectInforDao projectInforDao;

    @Resource
    private ProjectSetDao projectSetDao;


    //查询已立项成功的项目，添加到项目信息中
    @Override
    public void addInfor() {
        List<String> codes = projectInforDao.queryCodes(); //查询已建立项目详情信息的编号

        Map<String,Object> content = new HashMap<>();
        if(codes.size() > 0) {
            content.put("marker","1");
            content.put("codes",codes);
        } else {
            content.put("marker","0");
        }
        List<ApprovalList> approvalLists = projectInforDao.queryApproval(content); //查询已成功立项但没有建立项目详情的项目信息
        if(approvalLists.size() == 0) {
            return;
        }
        for(ApprovalList approvalList: approvalLists) {
            approvalList.setWriteDate(approvalList.getWriteDate().substring(0,10));
        }
        projectInforDao.addInfor(approvalLists); //添加项目详情信息
    }

    //列表查询项目详情信息
    @Override
    public PageInfo queryList(OperationQuery operationQuery, Integer pageNum) {
        List<String> list4 = JSONArray.parseArray(operationQuery.getSelect4(),String.class);
        if(list4.size() > 0) {
            operationQuery.setList4(list4);
        }
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        System.out.println(operationQuery);
        PageHelper.startPage(pageNum,10);
        List<ProjectInfor> list = projectInforDao.queryList(operationQuery);
        return new PageInfo(list);
    }

    //修改项目信息
    @Override
    public String amendInfor(ProjectInfor projectInfor) {
        projectInforDao.amendInfor(projectInfor);
        return "1";
    }
}
