package com.elex.oa.controller.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.ProjectExpense;
import com.elex.oa.entity.project.ProjectHuman;
import com.elex.oa.entity.project.ProjectIncome;
import com.elex.oa.service.project.OperationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/operate")
public class OperationController {

    @Autowired
    private OperationService operationService;

    //列表查询主表数据
    @RequestMapping("/query_main_list")
    @ResponseBody
    public PageInfo queryMainList (OperationQuery operationQuery, Page page) {
        return operationService.queryMainList(operationQuery,page);
    }

    //列表查询当前可新建的项目
    @RequestMapping("/query_project_list")
    @ResponseBody
    public PageInfo queryProjectList(OperationQuery operationQuery, Page page) {
        return operationService.queryProjectList(operationQuery, page);
    }

    //添加收入合同信息
    @RequestMapping("/add_income")
    @ResponseBody
    public String addIncome(ProjectIncome projectIncome, String contract) {
        return operationService.addIncome(projectIncome,contract);
    }

    //查询收入合同信息
    @RequestMapping("/query_income_detail")
    @ResponseBody
    public Map<String,Object> queryIncomeDetail (String projectCode) {
        return operationService.queryIncomeDetail(projectCode);
    }

    //修改收入合同信息
    @RequestMapping("/modify_income")
    @ResponseBody
    public String modifyIncome(ProjectIncome projectIncome, String contract) {
        return operationService.modifyIncome(projectIncome,contract);
    }

    //查询物品消耗详情
    @RequestMapping("/query_material_detail")
    @ResponseBody
    public Map<String,Object> queryMaterialDetail(String projectCode) {
        return operationService.queryMaterialDetail(projectCode);
    }

    //添加人力成本
    @RequestMapping("/add_human")
    @ResponseBody
    public String addHuman(ProjectHuman projectHuman, String detail) {
        return operationService.addHuman(projectHuman,detail);
    }

    //查询人力成本信息
    @RequestMapping("/query_human_detail")
    @ResponseBody
    public Map<String,Object> queryHumanDetail (String projectCode) {
        return operationService.queryHumanDetail(projectCode);
    }

    //修改人力成本
    @RequestMapping("/modify_human")
    @ResponseBody
    public String modifyHuman(ProjectHuman projectHuman, String detail) {
        return operationService.modifyHuman(projectHuman, detail);
    }

    //添加费用报销
    @RequestMapping("/add_expense")
    @ResponseBody
    public String addExpense(ProjectExpense projectExpense, String detail) {
        return operationService.addExpense(projectExpense,detail);
    }

    //查询费用报销信息
    @RequestMapping("/query_expense_detail")
    @ResponseBody
    public Map<String,Object> queryExpenseDetail (String projectCode) {
        return operationService.queryExpenseDetail(projectCode);
    }

    //修改费用报销
    @RequestMapping("/modify_expense")
    @ResponseBody
    public String modifyExpense(ProjectExpense projectExpense, String detail) {
        return operationService.modifyExpense(projectExpense, detail);
    }

}
