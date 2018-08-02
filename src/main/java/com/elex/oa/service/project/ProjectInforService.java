package com.elex.oa.service.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.ProjectInfor;
import com.elex.oa.entity.project.ProjectInforQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ProjectInforService {
    //数据列表查询
    PageInfo<ProjectInfor> listQuery(Page page, ProjectInforQuery projectInforQuery);
    //根据姓名查询其新建时可用的项目编号
    List<String> queryCodeByName(String name);
    //根据编号查询某已通过审批的项目基本信息
    ApprovalList queryContentByCode(String projectCode);
    //新建项目详情
    Map<String,String> constructionDetails(ProjectInfor projectInfor);
    //修改项目详情
    Map<String,String> modifyDetails(ProjectInfor projectInfor);
    //删除详情
    Map<String,String> deleteDetails(Integer id);
    //查询可新建详情的项目信息
    PageInfo<ApprovalList> queryProjectList(OperationQuery operationQuery, Page page);
    //查询跟某人相关的项目
    List<ProjectInfor> queryProName(String name);
}
