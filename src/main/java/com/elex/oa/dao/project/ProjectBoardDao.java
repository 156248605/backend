package com.elex.oa.dao.project;

import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.MileStonePlan;
import com.elex.oa.entity.project.ProjectInfor;
import com.elex.oa.entity.project.WeeklyPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectBoardDao {
    //查询立项相关的项目
    List<ApprovalList> queryApprovalList();
    //查询项目详情信息
    List<ProjectInfor> queryProjectInfor();
    //根据编号查询详情
    ProjectInfor queryInforDetail(String projectCode);
    //查询项目收入
    List<String> queryIncome(String projectCode);
    //查询物品消耗
    String queryMaterials(String projectCode);
    //查询人力成本
    String queryHuman(String projectCode);
    //查询费用报销
    String queryExpense(String projectCode);
    //查询项目里程碑计划
    List<MileStonePlan> queryMileStonePlans(String projectCode);
    //查询当前周计划
    WeeklyPlan queryWeeklyPlan(Map<String, String> content);
    //查询周计划详情
    List<String> queryWeeklyPlanDetail(int id);
}
