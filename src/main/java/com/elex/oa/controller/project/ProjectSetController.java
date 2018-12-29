package com.elex.oa.controller.project;

import com.elex.oa.entity.project.ProjectVarious;
import com.elex.oa.service.project.ProjectSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/hammer")
@Controller
@CrossOrigin
public class ProjectSetController {

    @Autowired
    private ProjectSetService projectSetService;

    //查询所有项目类型
    @RequestMapping("/query_type")
    @ResponseBody
    public List<ProjectVarious> queryType() {
        return projectSetService.queryType();
    }

    //查询所有项目来源
    @RequestMapping("/query_source")
    @ResponseBody
    public List<ProjectVarious> querySource() {
        return projectSetService.querySource();
    }

    //查询所有项目状态
    @RequestMapping("/query_status")
    @ResponseBody
    public List<ProjectVarious> queryStatus() {
        return projectSetService.queryStatus();
    }

    //查询所有项目阶段
    @RequestMapping("/query_phase")
    @ResponseBody
    public List<ProjectVarious> queryPhase() {
        return projectSetService.queryPhase();
    }


    //查询所有项目属性类型
    @RequestMapping("/query_various")
    @ResponseBody
    public Map<String,List<ProjectVarious>> queryVarious() {
        return projectSetService.queryVarious();
    }
}
