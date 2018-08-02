package com.elex.oa.dao.project;

import com.elex.oa.entity.project.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WeeklyPlanDao {
    //数据列表查询
    List queryList(WeeklyPlanQuery weeklyPlanQuery);
    //根据关联id查询计划
    List<WeeklyPlan> queryPlansById(int relatedId);
    //根据姓名查询项目信息
    List<ApprovalList> queryCodeByName(String name);
    //根据code 查询详情
    Map<String,String> qeuryDetailByCode(String code);
    //添加周计划
    void addWeeklyPlan(WeeklyPlan weeklyPlan);
    //查询最大的id
    int queryMaxId();
    //添加周计划的详情
    void addWeeklyPlanDetail(List<WeeklyPlanDetail> weeklyPlanDetails);
    //更新周计划
    void updateWeeklyPlan(WeeklyPlan weeklyPlan);
    //查询项目信息
    List<ApprovalList> queryProjectName(OperationQuery operationQuery);
    //修改周计划
    void amendPlans(WeeklyPlan weeklyPlan);
    //删除周计划
    void deleteWeek(int id);
    //条件查询当前周报中的阶段信息
    String queryPlanByCon(Map<String,String> content);
    //查询某个阶段的项目编号
    List<String> queryCodeByPhase(String phase);
}
