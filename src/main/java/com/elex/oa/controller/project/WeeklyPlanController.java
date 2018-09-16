package com.elex.oa.controller.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.*;
import com.elex.oa.service.project.WeeklyPlanService;
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
@RequestMapping("/weeklyPlan")
public class WeeklyPlanController {
    @Autowired
    private WeeklyPlanService weeklyPlanService;

    //数据列表查询
    @RequestMapping("/query_list")
    @ResponseBody
    public PageInfo queryList(WeeklyPlanQuery weeklyPlanQuery, Page page) {
        return weeklyPlanService.queryList(weeklyPlanQuery,page);
    }

    //根据关联id查询计划
    @RequestMapping("/query_plan_id")
    @ResponseBody
    public List<WeeklyPlan> queryPlansById(String related) {
        return weeklyPlanService.queryPlansById(related);
    }

    //根据姓名查询项目编号
    @RequestMapping("/query_code_name")
    @ResponseBody
    public PageInfo<ApprovalList> queryCodeByName(String name, Page page) {
        return weeklyPlanService.queryCodeByName(name,page);
    }

    //根据code 查询详情
    @RequestMapping("/query_detail_code")
    @ResponseBody
    public Map<String,String> queryDetailByCode(String code) {
        return weeklyPlanService.queryDetailByCode(code);
    }

    //添加周计划
    @RequestMapping("/add_plans")
    @ResponseBody
    public String addPlans(WeeklyPlan weeklyPlan) {
        return weeklyPlanService.addPlans(weeklyPlan);
    }

    //审批周计划
    @RequestMapping("/approval_plans")
    @ResponseBody
    public String approvalPlans(WeeklyPlan weeklyPlan) {
        return weeklyPlanService.approvalPlans(weeklyPlan);
    }

    //查询项目信息
    @RequestMapping("/query_project_name")
    @ResponseBody
    public PageInfo<ProjectInfor> queryProjectName(OperationQuery operationQuery, Page page) {
        return weeklyPlanService.queryProjectName(operationQuery, page);
    }

    //修改周计划
    @RequestMapping("/amend_plans")
    @ResponseBody
    public String amendPlans(WeeklyPlan weeklyPlan) {
        return weeklyPlanService.amendPlans(weeklyPlan);
    }

    //删除周计划
    @RequestMapping("/delete_week")
    @ResponseBody
    public String deleteWeek(int id) {
        return weeklyPlanService.deleteWeek(id);
    }

    //查询某项目的里程碑计划
    @RequestMapping("/query_mileStone")
    @ResponseBody
    public List<String> queryMileStone(String code) {
        return weeklyPlanService.queryMileStone(code);
    }
}
