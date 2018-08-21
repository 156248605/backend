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
    //查询项目详情信息
    List<ProjectInfor> queryProjectInfor();
    //根据编号查询详情
    ProjectInfor queryInforDetail(String projectCode);
    //查询物品消耗
    String queryMaterials(String projectCode);
    //查询人力成本
    String queryHuman(String projectCode);
    //查询费用报销
    String queryExpense(String projectCode);
    //查询项目里程碑计划
    List<MileStonePlan> queryMileStonePlans(String projectCode);
    //查询当前周计划
    WeeklyPlan queryWeeklyPlan(Map<String, String> content);;
    //根据部门信息查询项目详情
    List<ProjectInfor> queryProjectInforByDepartment(String department);
    //条件查询已立项项目
    List<ApprovalList> queryApprovalByCon(Map<String,String> conditions);
    //条件查询项目信息
    List<ProjectInfor> queryInforByCon(Map<String,String> conditions);
    //查询南京总部的项目详情信息
    List<ProjectInfor> queryInforMain();
    //查询南京总部的项目信息
    List<ProjectInfor> queryInforExclusive();
    //条件查询项目信息
    List<ProjectInfor> queryInforCon(Map<String,String> condition);
    //条件查询项目信息
    List<ProjectInfor> queryInforPhase(Map<String,Object> content);
    //查询本周周报相关的项目编号
    List<String> queryWeekByContent(Map<String,String> content);

}
