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

}
