package com.elex.oa.service.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.WeeklyPlan;
import com.elex.oa.entity.project.WeeklyPlanQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface WeeklyPlanService {
    //数据列表查询
    PageInfo queryList(WeeklyPlanQuery weeklyPlanQuery, Page page);
    //根据关联id查询计划
    List<WeeklyPlan> queryPlansById(String related);
    //根据姓名查询项目信息
    PageInfo<ApprovalList> queryCodeByName(String name, Page page);
    //根据code 查询详情
    Map<String,String> queryDetailByCode(String code);
    //添加周计划
    String addPlans(WeeklyPlan weeklyPlan);
    //审批周计划
    String approvalPlans(WeeklyPlan weeklyPlan);
    //查询项目信息
    PageInfo<ApprovalList> queryProjectName(OperationQuery operationQuery, Page page);
    //修改周计划
    String amendPlans(WeeklyPlan weeklyPlan);
    //删除周计划
    String deleteWeek(int id);
}
