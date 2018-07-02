package com.elex.oa.dao.project;

import com.elex.oa.entity.project.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationDao {
    //列表查询主表数据
    List queryMainList(OperationQuery operationQuery);

    List<ApprovalList> queryProjectList(OperationQuery operationQuery);
    //修改审批清单中income的值
    void modifyAppIncome(String projectCode);
    //添加收入合同信息
    void insertIncome(ProjectIncome projectIncome);
    //添加收入合同的详情
    void insertIncomeDetail(List<ProjectIncomeDetail> list);
    //查询收入合同的信息
    ProjectIncome queryProjectIncome(String projectCode);
    //查询收入合同详情
    List<ProjectIncomeDetail> queryProjectIncomeDetail(String projectCode);
    //修改收入合同信息
    void modifyIncome(ProjectIncome projectIncome);
    //根据项目编号查询收入合同的id
    List<Integer> queryProjectIncomeDetailId(String projectCode);
    //修改收入合详情
    void updateIncomeDetail(List<ProjectIncomeDetail> modifyDetail);
    //删除收入合同详情
    void deleteIncomeDetail(List<Integer> deleteDetail);
    //查询物品消耗信息
    ProjectMaterial queryMaterial(String projectCode);
    //查询物品消耗详情
    List<ProjectMaterialDetail> queryMaterialDetail(String projectCode);
    //修改审批清单中human的值
    void modifyAppHuman(String projectCode);
    //添加人力成本信息
    void insertHuman(ProjectHuman projectHuman);
    //添加人力成本信息详情
    void insertHumanDetail(List<ProjectHumanDetail> projectHumanDetails);
    //查询人力成本信息
    ProjectHuman queryProjectHuman(String projectCode);
    //查询人力成本详情
    List<ProjectHumanDetail> queryProjectHumanDetail(String projectCode);
    //修改人力成本信息
    void modifyHuman(ProjectHuman projectHuman);
    //根据项目编号查询人力成本详情id
    List<Integer> queryProjectHumanDetailId(String projectCode);
    //删除人力成本详情
    void deleteHumanDetail(List<Integer> deleteList);
    //修改人力成本详情
    void modifyHumanDetail(List<ProjectHumanDetail> modifyList);
    //修改审批清单中的expense值
    void modifyAppExpense(String projectCode);
    //添加费用报销信息
    void insertExpense(ProjectExpense projectExpense);
    //添加费用报销信息详情
    void insertExpenseDetail(List<ProjectExpenseDetail> projectExpenseDetails);
    //查询费用报销信息
    ProjectExpense queryProjectExpense(String projectCode);
    //查询费用报销详情
    List<ProjectExpenseDetail> queryProjectExpenseDetail(String projectCode);
    //修改费用报销信息
    void modifyExpense(ProjectExpense projectExpense);
    //根据项目编号查询费用报销详情id
    List<Integer> queryProjectExpenseDetailId(String projectCode);
    //删除费用报销详情
    void deleteExpenseDetail(List<Integer> deleteList);
    //修改费用报销详情
    void modifyExpenseDetail(List<ProjectExpenseDetail> modifyList);
}
