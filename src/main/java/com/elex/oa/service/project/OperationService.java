package com.elex.oa.service.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.ProjectExpense;
import com.elex.oa.entity.project.ProjectHuman;
import com.elex.oa.entity.project.ProjectIncome;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface OperationService {
    //列表查询主表数据
    PageInfo queryMainList(OperationQuery operationQuery, Page page);
    //列表查询当前可新建的项目
    PageInfo queryProjectList(OperationQuery operationQuery, Page page);
    //添加收入合同信息
    String addIncome(ProjectIncome projectIncome, String contract);
    //查询收入合同信息
    Map<String,Object> queryIncomeDetail(String projectCode);
    //修改收入合同信息
    String modifyIncome(ProjectIncome projectIncome, String contract);
    //查询物品消耗详情
    Map<String,Object> queryMaterialDetail(String projectCode);
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
}
