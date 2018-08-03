package com.elex.oa.controller.project;

import com.elex.oa.entity.project.ProjectVarious;
import com.elex.oa.service.project.ProjectSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    //添加项目类型
    @RequestMapping("/add_type")
    @ResponseBody
    public String addType(String name) {
        return projectSetService.addType(name);
    }

    //添加项目来源
    @RequestMapping("/add_source")
    @ResponseBody
    public String addSource(String name) {
        return projectSetService.addSource(name);
    }

    //添加项目状态
    @RequestMapping("/add_status")
    @ResponseBody
    public String addStatus(String name) {
        return projectSetService.addStatus(name);
    }

    //添加项目阶段
    @RequestMapping("/add_phase")
    @ResponseBody
    public String addPhase(String name) {
        return projectSetService.addPhase(name);
    }

    //删除项目类型
    @RequestMapping("/delete_type")
    @ResponseBody
    public String deleteType(int code) {
        return projectSetService.deleteType(code);
    }

    //删除项目来源
    @RequestMapping("/delete_source")
    @ResponseBody
    public String deleteSource(int code) {
        return projectSetService.deleteSource(code);
    }

    //删除项目状态
    @RequestMapping("/delete_status")
    @ResponseBody
    public String deleteStatus(int code) {
        return projectSetService.deleteStatus(code);
    }

    //删除项目阶段
    @RequestMapping("/delete_phase")
    @ResponseBody
    public String deletePhase(int code) {
        return projectSetService.deletePhase(code);
    }

}
