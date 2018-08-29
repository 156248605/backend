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

@Controller
@CrossOrigin
@RequestMapping("/newProject")
public class NewProjectController {
    //项目管理，立项相关


    @Autowired
    private NewProjcetService newProjcetService;



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

    //根据人名获取项目编号
    @RequestMapping("/obtain_code_name")
    @ResponseBody
    public String obtainCodeName(String name) {
        return newProjcetService.obtainCodeName(name);
    }
}
