package com.elex.oa.controller.project;

import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.ProjectInfor;
import com.elex.oa.service.project.ProjectInforService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/proInfor")
public class ProjectInforController {

    @Autowired
    private ProjectInforService projectInforService;


    //列表查询项目详情信息
    @RequestMapping("/query_list")
    @ResponseBody
    public PageInfo queryList(OperationQuery operationQuery, Integer pageNum) {
        return projectInforService.queryList(operationQuery, pageNum);
    }

    //修改项目信息
    @RequestMapping("/amend_infor")
    @ResponseBody
    public String amendInfor(ProjectInfor projectInfor) {
        return projectInforService.amendInfor(projectInfor);
    }

    //添加新项目
    @RequestMapping("add_infor")
    @ResponseBody
    public String addInfor(){
        projectInforService.addInfor();
        return "1";
    }

    @RequestMapping("/business_manager")
    @ResponseBody
    public String businessManager(){
        return projectInforService.businessManager();
    }
    //修改项目成员的编号信息
    @RequestMapping("/project_members")
    @ResponseBody
    public String projectMembers(){
        return projectInforService.projectMembers();
    }
    //修改相关成员的编号信息
    @RequestMapping("/related_members")
    @ResponseBody
    public String relatedMembers(){
        return projectInforService.relatedMembers();
    }
    //修改交付经理的编号信息
    @RequestMapping("/project_manager")
    @ResponseBody
    public String projectManager(){
        return projectInforService.projectManager();
    }
    //修改项目状态的编号信息
    @RequestMapping("/project_status")
    @ResponseBody
    public String projectStatus() {
        return projectInforService.projectStatus();
    }
    //修改项目来源的编号信息
    @RequestMapping("/project_source")
    @ResponseBody
    public String projectSource() {
        return projectInforService.projectSource();
    }
    //修改项目类型的编号信息
    @RequestMapping("/project_type")
    @ResponseBody
    public String projectType() {
        return projectInforService.projectType();
    }

}
