package com.elex.oa.dao.project;

import com.elex.oa.entity.project.*;
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
    List<ProjectInfor> queryProjectInforByDepartment(List<String> deptList);
    //条件查询已立项项目
    List<ApprovalList> queryApprovalByCon(Map<String,String> conditions);
    //条件查询项目信息
    List<ProjectInfor> queryInforByCon(Map<String,String> conditions);
    //条件查询项目信息
    List<ProjectInfor> queryInforCon(Map<String,Object> condition);
    //条件查询项目信息
    List<ProjectInfor> queryInforPhase(Map<String,Object> content);
    //查询本周周报相关的项目编号
    List<String> queryWeekByContent(Map<String,String> content);
    //根据部门查询正在进行中的项目信息
    List<ProjectInfor> queryProjectInforByDepartment1(Map<String,Object> deptList);
    //查询项目金额以及回款率
    ProjectIncome queryProjectIncome(String projectCode);
    //根据本周周报阶段查询相关的项目编号
    List<String> queryPhaseByContent(Map<String,Object> content1);
    //查询进行中的项目编号
    List<String> queryProceed(String proceed);
    //查询项目详情信息
    List<ProjectInfor> queryInforInProceed(Map<String,Object> content2);
    //查询所有进行中的项目详情信息
    List<ProjectInfor> queryInforProceed(String proceed);
}
