package com.elex.oa.controller.project;

import com.elex.oa.service.project.ProjectBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/board")
@CrossOrigin
@Controller
public class ProjectBoardController {

    @Autowired
    private ProjectBoardService projectBoardService;

    //列表总览
    @RequestMapping("/overview")
    @ResponseBody
    public Map<String,Object> overview() {
        return projectBoardService.overview();
    }

    //详情
    @RequestMapping("/detail")
    @ResponseBody
    public Map<String,Object> detail(String projectCode) {
        return projectBoardService.detail(projectCode);
    }

    //看板信息（手机）
    @RequestMapping("/project_phone")
    @ResponseBody
    public List<Map<String,Object>> projectPhone() {
        return projectBoardService.projectPhone();
    }
}
