package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.project.OperationDao;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.*;
import com.elex.oa.service.project.OperationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OperationImpl implements OperationService {
    @Resource
    private OperationDao operationDao;

    //列表查询主表数据
    @Override
    public PageInfo queryMainList(OperationQuery operationQuery, Page page) {
        List<String> list4 = JSONArray.parseArray(operationQuery.getSelect4(),String.class);
        if(list4.size() > 0) {
            operationQuery.setList4(list4);
        }
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List list = operationDao.queryMainList(operationQuery);
        return new PageInfo(list);
    }

    //列表查询当前可新建的项目
    @Override
    public PageInfo queryProjectList(OperationQuery operationQuery, Page page) {
        List<String> list4 = JSONArray.parseArray(operationQuery.getSelect4(),String.class);
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list4.size() > 0) {
            operationQuery.setList4(list4);
        }
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List list = operationDao.queryProjectList(operationQuery);
        return new PageInfo(list);
    }

    //添加收入合同信息
    @Override
    @Transactional
    public String addIncome(ProjectIncome projectIncome, String contract) {
        operationDao.modifyAppIncome(projectIncome.getProjectCode()); //修改审批清单中income的值
        operationDao.insertIncome(projectIncome); //添加收入合同信息
        List<ProjectIncomeDetail> list = JSONArray.parseArray(contract,ProjectIncomeDetail.class);
        for(ProjectIncomeDetail projectIncomeDetail: list) {
            projectIncomeDetail.setProjectCode(projectIncome.getProjectCode());
        }
        operationDao.insertIncomeDetail(list); //添加收入合同的详情
        return "1";
    }

    //查询收入合同信息
    @Override
    public Map<String, Object> queryIncomeDetail(String projectCode) {
        Map<String,Object> map = new HashMap<>();
        ProjectIncome projectIncome = operationDao.queryProjectIncome(projectCode); //查询收入合同的信息
        List<ProjectIncomeDetail> projectIncomeDetails = operationDao.queryProjectIncomeDetail(projectCode); //查询收入合同详情
        map.put("income",projectIncome);
        map.put("detail",projectIncomeDetails);
        return map;
    }

    //修改收入合同信息
    @Override
    @Transactional
    public String modifyIncome(ProjectIncome projectIncome, String contract) {
        operationDao.modifyIncome(projectIncome); //修改收入合同信息
        List<ProjectIncomeDetail> projectIncomeDetails = JSONArray.parseArray(contract,ProjectIncomeDetail.class);
        List<Integer> deleteDetail = operationDao.queryProjectIncomeDetailId(projectIncome.getProjectCode()); //根据项目编号查询收入合同的id
        List<ProjectIncomeDetail> modifyDetail = new ArrayList<>();
        List<ProjectIncomeDetail> addDetail = new ArrayList<>();
        for(ProjectIncomeDetail projectIncomeDetail: projectIncomeDetails) {
            if(projectIncomeDetail.getId() == 0 ) {
                projectIncomeDetail.setProjectCode(projectIncome.getProjectCode());
                addDetail.add(projectIncomeDetail);
            } else if(deleteDetail.contains(projectIncomeDetail.getId())) {
                modifyDetail.add(projectIncomeDetail);
                deleteDetail.remove(new Integer(projectIncomeDetail.getId()));
            }
        }
        if(addDetail.size() > 0) {
            operationDao.insertIncomeDetail(addDetail); //添加收入合同详情
        }
        if(modifyDetail.size() > 0) {
            operationDao.updateIncomeDetail(modifyDetail); //修改收入合同详情
        }
        if(deleteDetail.size() > 0) {
            operationDao.deleteIncomeDetail(deleteDetail); //删除收入合同详情
        }
        return "1";
    }

    //查询物品消耗详情
    @Override
    public Map<String, Object> queryMaterialDetail(String projectCode) {
        ProjectMaterial projectMaterial = operationDao.queryMaterial(projectCode); //查询物品消耗信息
        List<ProjectMaterialDetail> projectMaterialDetails = operationDao.queryMaterialDetail(projectCode); //查询物品消耗详情
        Map<String,Object> map = new HashMap<>();
        map.put("material",projectMaterial);
        map.put("detail",projectMaterialDetails);
        return map;
    }

    //添加人力成本
    @Override
    @Transactional
    public String addHuman(ProjectHuman projectHuman, String detail) {
        operationDao.modifyAppHuman(projectHuman.getProjectCode()); //修改审批清单中human的值
        operationDao.insertHuman(projectHuman); //添加人力成本信息
        List<ProjectHumanDetail> projectHumanDetails = JSONArray.parseArray(detail,ProjectHumanDetail.class);
        for(ProjectHumanDetail projectHumanDetail: projectHumanDetails) {
            projectHumanDetail.setProjectCode(projectHuman.getProjectCode());
        }
        operationDao.insertHumanDetail(projectHumanDetails); //添加人力成本信息详情
        return "1";
    }

    //查询人力成本信息
    @Override
    public Map<String, Object> queryHumanDetail(String projectCode) {
        Map<String,Object> map = new HashMap<>();
        ProjectHuman projectHuman = operationDao.queryProjectHuman(projectCode); //查询人力成本信息
        List<ProjectHumanDetail> details = operationDao.queryProjectHumanDetail(projectCode); //查询人力成本详情
        map.put("human",projectHuman);
        map.put("detail",details);
        return map;
    }

    //修改人力成本
    @Override
    @Transactional
    public String modifyHuman(ProjectHuman projectHuman, String detail) {
        operationDao.modifyHuman(projectHuman); //修改人力成本信息
        List<ProjectHumanDetail> projectHumanDetails = JSONArray.parseArray(detail,ProjectHumanDetail.class);
        List<Integer> deleteList = operationDao.queryProjectHumanDetailId(projectHuman.getProjectCode()); //根据项目编号查询人力成本详情id
        List<ProjectHumanDetail> insertList = new ArrayList<>();
        List<ProjectHumanDetail> modifyList = new ArrayList<>();
        for(ProjectHumanDetail projectHumanDetail: projectHumanDetails) {
            if(projectHumanDetail.getId() == 0) {
                projectHumanDetail.setProjectCode(projectHuman.getProjectCode());
                insertList.add(projectHumanDetail);
            } else if(deleteList.contains(projectHumanDetail.getId())) {
                modifyList.add(projectHumanDetail);
                deleteList.remove(new Integer(projectHumanDetail.getId()));
            }
        }
        if(insertList.size() > 0) {
            operationDao.insertHumanDetail(insertList);
        }
        if(deleteList.size() > 0) {
            operationDao.deleteHumanDetail(deleteList); //删除人力成本详情
        }
        if(modifyList.size() > 0) {
            operationDao.modifyHumanDetail(modifyList); //修改人力成本详情
        }
        return "1";
    }

    //添加费用报销
    @Override
    @Transactional
    public String addExpense(ProjectExpense projectExpense, String detail) {
        operationDao.modifyAppExpense(projectExpense.getProjectCode()); //修改审批清单中的expense值
        operationDao.insertExpense(projectExpense); //添加费用报销信息
        List<ProjectExpenseDetail> projectExpenseDetails = JSONArray.parseArray(detail,ProjectExpenseDetail.class);
        for(ProjectExpenseDetail projectExpenseDetail:projectExpenseDetails) {
            projectExpenseDetail.setProjectCode(projectExpense.getProjectCode());
        }
        operationDao.insertExpenseDetail(projectExpenseDetails); //添加费用报销信息详情
        return "1";
    }

    //查询费用报销信息
    @Override
    public Map<String, Object> queryExpenseDetail(String projectCode) {
        Map<String,Object> map = new HashMap<>();
        ProjectExpense projectExpense = operationDao.queryProjectExpense(projectCode); //查询费用报销信息
        List<ProjectExpenseDetail> details = operationDao.queryProjectExpenseDetail(projectCode); //查询费用报销详情
        map.put("expense",projectExpense);
        map.put("detail",details);
        return map;
    }

    //修改人力成本
    @Override
    @Transactional
    public String modifyExpense(ProjectExpense projectExpense, String detail) {
        operationDao.modifyExpense(projectExpense); //修改费用报销信息
        List<ProjectExpenseDetail> projectExpenseDetails = JSONArray.parseArray(detail,ProjectExpenseDetail.class);
        List<Integer> deleteList = operationDao.queryProjectExpenseDetailId(projectExpense.getProjectCode()); //根据项目编号查询费用报销详情id
        List<ProjectExpenseDetail> insertList = new ArrayList<>();
        List<ProjectExpenseDetail> modifyList = new ArrayList<>();
        for(ProjectExpenseDetail projectExpenseDetail: projectExpenseDetails) {
            if(projectExpenseDetail.getId() == 0) {
                projectExpenseDetail.setProjectCode(projectExpense.getProjectCode());
                insertList.add(projectExpenseDetail);
            } else if(deleteList.contains(projectExpenseDetail.getId())) {
                modifyList.add(projectExpenseDetail);
                deleteList.remove(new Integer(projectExpenseDetail.getId()));
            }
        }
        if(insertList.size() > 0) {
            operationDao.insertExpenseDetail(insertList);
        }
        if(deleteList.size() > 0) {
            operationDao.deleteExpenseDetail(deleteList); //删除费用报销详情
        }
        if(modifyList.size() > 0) {
            operationDao.modifyExpenseDetail(modifyList); //修改费用报销详情
        }
        return "1";
    }


    //查询项目收入相关可新建的项目
    @Override
    public PageInfo<ProjectInfor> queryProjectIncome(OperationQuery operationQuery, Page page) {
        List<String> incomeCodes = operationDao.queryIncomeCodes(); //查询已建立收入的项目编号
        List<String> list4 = JSONArray.parseArray(operationQuery.getSelect4(),String.class);
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list4.size() > 0) {
            operationQuery.setList4(list4);
        }
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        if(incomeCodes.size() > 0) {
            operationQuery.setCodes(incomeCodes);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ProjectInfor> projectInfors = operationDao.queryInforIncome(operationQuery);
        return new PageInfo<>(projectInfors);
    }

    //添加新的项目收入
    @Override
    @Transactional
    public String insertIncome(ProjectIncome projectIncome, String contract1, String contract2) {
        List<IncomeOne> ones = JSONArray.parseArray(contract1,IncomeOne.class);
        List<IncomeTwo> twos = JSONArray.parseArray(contract2,IncomeTwo.class);
        operationDao.addIncome(projectIncome); //添加项目收入
        if(ones.size() > 0) {
            for(IncomeOne incomeOne: ones) {
                incomeOne.setProjectCode(projectIncome.getProjectCode());
            }
            operationDao.addIncomeOne(ones); //添加项目收入合同
        }
        if(twos.size() > 0) {
            for(IncomeTwo incomeTwo: twos) {
                incomeTwo.setProjectCode(projectIncome.getProjectCode());
            }
            operationDao.addIncomeTwo(twos); //添加项目收入的收入合同
        }
        return "1";
    }

    //查询项目收入列表
    @Override
    public PageInfo<ProjectInfor> queryIncomeList(OperationQuery operationQuery, Page page) {
        List<String> list4 = JSONArray.parseArray(operationQuery.getSelect4(),String.class);
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list4.size() > 0) {
            operationQuery.setList4(list4);
        }
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ProjectInfor> list = operationDao.queryIncomeList(operationQuery);
        return new PageInfo<>(list);
    }

    //查询项目收入的内容
    @Override
    public Map<String, Object> queryIncomeContent(String projectCode) {
        ProjectIncome projectIncome = operationDao.queryIncomeContent(projectCode); //查询项目收入的内容
        List<IncomeOne> incomeOnes = operationDao.queryIncomeOnes(projectCode); //查询项目收入的合同
        List<IncomeTwo> incomeTwos = operationDao.queryIncomeTwos(projectCode); //查询项目收入的收入合同
        Map<String,Object> map = new HashMap<>();
        map.put("income",projectIncome);
        map.put("one",incomeOnes);
        map.put("two",incomeTwos);
        return map;
    }

    @Override
    @Transactional
    public String updateIncome(ProjectIncome projectIncome, String contract1, String contract2) {
        operationDao.deleteIncomeOnes(projectIncome.getProjectCode()); //删除项目收入的合同
        operationDao.deleteIncomeTwos(projectIncome.getProjectCode()); //删除项目收入的收入合同
        operationDao.updateIncome(projectIncome); //更新项目收入内容
        List<IncomeOne> ones = JSONArray.parseArray(contract1,IncomeOne.class);
        List<IncomeTwo> twos = JSONArray.parseArray(contract2,IncomeTwo.class);
        if(ones.size() > 0) {
            for(IncomeOne incomeOne: ones) {
                incomeOne.setProjectCode(projectIncome.getProjectCode());
            }
            operationDao.addIncomeOne(ones); //添加项目收入合同
        }
        if(twos.size() > 0) {
            for(IncomeTwo incomeTwo: twos) {
                incomeTwo.setProjectCode(projectIncome.getProjectCode());
            }
            operationDao.addIncomeTwo(twos); //添加项目收入的收入合同
        }
        return "1";
    }

    //查询人力成本相关可新建的项目
    @Override
    public PageInfo<ProjectInfor> queryProjectHuman(OperationQuery operationQuery, Page page) {
        List<String> humanCodes = operationDao.queryHumanCodes(); //查询已建立人力的项目编号
        List<String> list4 = JSONArray.parseArray(operationQuery.getSelect4(),String.class);
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list4.size() > 0) {
            operationQuery.setList4(list4);
        }
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        if(humanCodes.size() > 0) {
            operationQuery.setCodes(humanCodes);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ProjectInfor> projectInfors = operationDao.queryInforHuman(operationQuery);
        return new PageInfo<>(projectInfors);
    }

    //查询人力成本列表
    @Override
    public PageInfo<ProjectInfor> queryHumanList(OperationQuery operationQuery, Page page) {
        List<String> list4 = JSONArray.parseArray(operationQuery.getSelect4(),String.class);
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list4.size() > 0) {
            operationQuery.setList4(list4);
        }
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ProjectInfor> list = operationDao.queryHumanList(operationQuery);
        return new PageInfo<>(list);
    }

    //查询费用成本列表
    @Override
    public PageInfo<ProjectInfor> queryExpenseList(OperationQuery operationQuery, Page page) {
        List<String> list4 = JSONArray.parseArray(operationQuery.getSelect4(),String.class);
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list4.size() > 0) {
            operationQuery.setList4(list4);
        }
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ProjectInfor> list = operationDao.queryExpenseList(operationQuery);
        return new PageInfo<>(list);
    }

    //查询费用成本相关可新建的项目
    @Override
    public PageInfo<ProjectInfor> queryProjectExpense(OperationQuery operationQuery, Page page) {
        List<String> humanCodes = operationDao.queryExpenseCodes(); //查询已建立费用的项目编号
        List<String> list4 = JSONArray.parseArray(operationQuery.getSelect4(),String.class);
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list4.size() > 0) {
            operationQuery.setList4(list4);
        }
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        if(humanCodes.size() > 0) {
            operationQuery.setCodes(humanCodes);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ProjectInfor> projectInfors = operationDao.queryInforExpense(operationQuery);
        return new PageInfo<>(projectInfors);
    }
}
