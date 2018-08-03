package com.elex.oa.controller.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.ProjectInfor;
import com.elex.oa.entity.project.ProjectInforQuery;
import com.elex.oa.service.project.ProjectInforService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/proInfor")
public class ProjectInforController {
    //项目信息部分

    @Autowired
    private ProjectInforService projectInforService;

    //数据列表查询
    @RequestMapping("/list_query")
    @ResponseBody
    public PageInfo<ProjectInfor> listQuery(Page page, ProjectInforQuery projectInforQuery) {
        return projectInforService.listQuery(page,projectInforQuery);
    }

    //根据姓名查询其新建时可用的项目编号
    @RequestMapping("/query_code_name")
    @ResponseBody
    public List<String> queryCodeByName(String name) {
        return projectInforService.queryCodeByName(name);
    }

    //根据编号查询某已通过审批的项目基本信息
    @RequestMapping("/query_content_code")
    @ResponseBody
    public ApprovalList queryContentByCode(String projectCode) {
        return projectInforService.queryContentByCode(projectCode);
    }

    //新建项目详情
    @RequestMapping("/construction_details")
    @ResponseBody
    public Map<String,String> constructionDetails(ProjectInfor projectInfor) {
        return projectInforService.constructionDetails(projectInfor);
    }

    //修改项目详情
    @RequestMapping("/modify_details")
    @ResponseBody
    public Map<String,String> modifyDetails(ProjectInfor projectInfor) {
        return projectInforService.modifyDetails(projectInfor);
    }

    //删除详情
    @RequestMapping("/delete_details")
    @ResponseBody
    public Map<String,String> deleteDetails(Integer id){
        return projectInforService.deleteDetails(id);
    }

    //查询可新建详情的项目信息
    @RequestMapping("/query_project_list")
    @ResponseBody
    public PageInfo<ApprovalList> queryProjectList(OperationQuery operationQuery, Page page) {
        return projectInforService.queryProjectList(operationQuery, page);
    }


    //查询跟某人相关的项目
    @RequestMapping("/query_pro_name")
    @ResponseBody
    public List<ProjectInfor> queryProName(String name) {
        return  projectInforService.queryProName(name);
    }
}
