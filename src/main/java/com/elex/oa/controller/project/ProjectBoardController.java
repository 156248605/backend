package com.elex.oa.controller.project;

import com.elex.oa.entity.project.Staff;
import com.elex.oa.service.project.ProjectBoardService;
import com.github.pagehelper.PageInfo;
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
    public Map<String,Object> overview(String department) {
        return projectBoardService.overview(department);
    }

    //详情
    @RequestMapping("/detail")
    @ResponseBody
    public Map<String,Object> detail(String projectCode) {
        return projectBoardService.detail(projectCode);
    }


    //看板手机部门相关详情（手机）
    @RequestMapping("/project_total")
    @ResponseBody
    List<Map<String, String>> projectTotal(String department){
        return projectBoardService.projectTotal(department);
    }

    //看板根据类型查询概况（手机）
    @RequestMapping("/project_survey")
    @ResponseBody
    List<Map<String, String>> projectSurvey(String status, String type, String department){
        return projectBoardService.projectSurvey(status,type,department);
    }

    //查看某一类型的项目
    @RequestMapping("/project_various")
    @ResponseBody
    public PageInfo projectVarious(Integer pageNum, String status, String type, String department){
        return projectBoardService.projectVarious(pageNum, status, type, department);
    }

    //跟新人员信息
    @RequestMapping("/update_staff")
    @ResponseBody
    public String updateStaff() {
        projectBoardService.informationUpdate();
        return "1";
    }

    //查询所有人员
    @RequestMapping("/query_staff")
    @ResponseBody
    public List<Staff> queryStaff() {
        return projectBoardService.queryStaff();
    }

    //查看某类型的项目
    @RequestMapping("/project_status")
    @ResponseBody
    public PageInfo peojectStatus(Integer pageNum, String status, String type, String department) {
        return projectBoardService.projectStatus(pageNum, status, type, department);
    }

    //查看某阶段的项目
    @RequestMapping("/project_phase")
    @ResponseBody
    public PageInfo peojectPhase(Integer pageNum, String phase, String type, String department) {
        return projectBoardService.projectPhase(pageNum, phase, type, department);
    }

    //查看是否延期的项目
    @RequestMapping("/project_week")
    @ResponseBody
    public PageInfo projectWeek(Integer pageNum, String punctuality, String type, String department) {
        return projectBoardService.projectWeek(pageNum, punctuality, type, department);
    }
}
