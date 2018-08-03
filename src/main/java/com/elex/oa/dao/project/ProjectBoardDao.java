package com.elex.oa.dao.project;

import com.elex.oa.entity.project.*;
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
    List<WeeklyPlanDetail> queryWeeklyPlanDetail(int id);
    //查询项目信息 （手机）
    List<ProjectInfor> queryProInforP();
    //查询所有项目类型
    List<ProjectVarious> queryProjectType();
    //查询所有项目来源
    List<ProjectVarious> queryProjectSource();
    //查询项目收入的内容
    ProjectIncome queryIncomeContent(String projectCode);
    //查询项目收入详情
    List<String> queryIncomeDetail(String projectCode);
    //查询物品消耗
    ProjectMaterial queryProjectMaterial(String projectCode);
    //查询人力成本
    ProjectHuman queryProjectHuman(String projectCode);
    //查询费用报销
    ProjectExpense queryProjectExpense(String projectCode);
    //根据部门查询立项信息
    List<ApprovalList> queryApprovalByDepartment(String department);
    //根据部门信息查询项目详情
    List<ProjectInfor> queryProjectInforByDepartment(String department);
    //条件查询已立项项目
    List<ApprovalList> queryApprovalByCon(Map<String,String> conditions);
    //条件查询项目信息
    List<ProjectInfor> queryInforByCon(Map<String,String> conditions);
    //根据项目类型查询项目立项信息
    List<ApprovalList> queryApprovalByType(String type);
    //通过项目类型、项目状态查询项目信息
    List<ProjectInfor> queryInforByType(Map<String,String> condition);
    //查询南京总部的项目详情信息
    List<ProjectInfor> queryInforMain();
    //查询南京总部的项目信息
    List<ProjectInfor> queryInforExclusive();
    //条件查询项目信息
    List<ProjectInfor> queryInforCon(Map<String,String> condition);
    //条件查询项目信息
    List<ProjectInfor> queryInforPhase(Map<String,Object> content);

}
