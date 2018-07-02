package com.elex.oa.controller.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.NProjectQuery;
import com.elex.oa.entity.project.NewProject;
import com.elex.oa.service.project.NewProjcetService;
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
@RequestMapping("/newProject")
public class NewProjectController {
    //项目管理，立项相关


    @Autowired
    private NewProjcetService newProjcetService;

    //新建项目时弹窗初始化信息（部门、人员、项目编号）
    @RequestMapping("/initialize_info")
    @ResponseBody
    public Map<String,Object> initializeInfo() {
        return newProjcetService.initializeInfo();
    }

    //对新建项目进行处理
    @RequestMapping("/new_submit")
    @ResponseBody
    public Map<String,Object> newSubmit(NewProject newProject) {
        return newProjcetService.newSubmit(newProject);
    }

    //对某人已建项目进行列表查询
    @RequestMapping("/list_query")
    @ResponseBody
    public PageInfo<NewProject> listQuery(Page page, NProjectQuery nProjectQuery){
        return newProjcetService.listQuery(page,nProjectQuery);
    }

    //获取表头部分select中的内容
    @RequestMapping("/select_data")
    @ResponseBody
    public List<NewProject> selectData(String proposer) {
        return newProjcetService.selectData(proposer);
    }
}
