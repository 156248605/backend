package com.elex.oa.service.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.*;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface OperationService {
    //列表查询主表数据
    PageInfo queryMainList(OperationQuery operationQuery, Page page);
    //列表查询当前可新建的项目
    PageInfo queryProjectList(OperationQuery operationQuery, Page page);
    //列表查询物品消耗
    PageInfo queryMaterialList(OperationQuery operationQuery, Page page);
    //查询物品消耗详情
    Map<String,Object> queryMaterialDetail(String projectCode);
    //添加物品消耗信息
    void addMaterialInfor(String outId);
    //添加人力成本
    String addHuman(ProjectHuman projectHuman, String detail);
    //查询人力成本信息
    Map<String,Object> queryHumanDetail(String projectCode);
    //修改人力成本
    String modifyHuman(ProjectHuman projectHuman, String detail);
    //添加费用报销
    String addExpense(ProjectExpense projectExpense, String detail);
    //查询费用报销信息
    Map<String,Object> queryExpenseDetail(String projectCode);
    //修改费用报销
    String modifyExpense(ProjectExpense projectExpense, String detail);
    //查询项目收入相关可新建的项目
    PageInfo<ProjectInfor> queryProjectIncome(OperationQuery operationQuery, Page page);
    //添加新的项目收入
    String insertIncome(ProjectIncome projectIncome, String contract1, String contract2);
    //查询项目收入列表
    PageInfo<ProjectInfor> queryIncomeList(OperationQuery operationQuery, Page page);
    //查询项目收入的内容
    Map<String,Object> queryIncomeContent(String projectCode);
    //更新项目收入的内容
    String updateIncome(ProjectIncome projectIncome, String contract1, String contract2);
    //查询人力成本相关可新建的项目
    PageInfo<ProjectInfor> queryProjectHuman(OperationQuery operationQuery, Page page);
    //查询人力成本列表
    PageInfo<ProjectInfor> queryHumanList(OperationQuery operationQuery, Page page);
    //查询费用成本列表
    PageInfo<ProjectInfor> queryExpenseList(OperationQuery operationQuery, Page page);
    //查询费用成本相关可新建的项目
    PageInfo<ProjectInfor> queryProjectExpense(OperationQuery operationQuery, Page page);

}
