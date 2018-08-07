package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.project.WeeklyPlanDao;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.WeeklyPlan;
import com.elex.oa.entity.project.WeeklyPlanQuery;
import com.elex.oa.service.project.WeeklyPlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeeklyPlanImpl implements WeeklyPlanService {

    @Resource
    private WeeklyPlanDao weeklyPlanDao;

    //数据列表查询
    @Override
    public PageInfo queryList(WeeklyPlanQuery weeklyPlanQuery, Page page) {
        Map<String,String> map = new HashMap<>();
        List<String> date4 = JSONArray.parseArray(weeklyPlanQuery.getDate4(),String.class);
        if(date4.get(0).equals("")) {

        } else {
            weeklyPlanQuery.setDate4a(date4.get(0));
            weeklyPlanQuery.setDate4b(date4.get(1));
        }
        List<String> date9 = JSONArray.parseArray(weeklyPlanQuery.getDate9(),String.class);
        if(date9.get(0).equals("")) {

        } else {
            weeklyPlanQuery.setDate9a(date9.get(0));
            weeklyPlanQuery.setDate9b(date9.get(1));
        }
        List<String> date10 = JSONArray.parseArray(weeklyPlanQuery.getDate10(),String.class);
        if(date10.get(0).equals("")) {

        } else {
            weeklyPlanQuery.setDate10a(date10.get(0));
            weeklyPlanQuery.setDate10b(date10.get(1));
        }
        List<String> list6 = JSONArray.parseArray(weeklyPlanQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(weeklyPlanQuery.getSelect8(),String.class);
        if(list6.size() > 0) {
            weeklyPlanQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            weeklyPlanQuery.setList8(list8);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List list = weeklyPlanDao.queryList(weeklyPlanQuery);
        return new PageInfo(list);
    }

    //根据关联id查询计划
    @Override
    public List<WeeklyPlan> queryPlansById(String related) {
        int relatedId = Integer.parseInt(related);
        return weeklyPlanDao.queryPlansById(relatedId);
    }

    //根据姓名查询项目信息
    @Override
    public PageInfo<ApprovalList> queryCodeByName(String name, Page page) {
        PageHelper.offsetPage(page.getCurrentPage(),page.getRows());
        List<ApprovalList> list = weeklyPlanDao.queryCodeByName(name);
        return new PageInfo<>(list);
    }

    //根据code 查询详情
    @Override
    public Map<String, String> queryDetailByCode(String code) {
        return weeklyPlanDao.qeuryDetailByCode(code);
    }

    //添加周计划
    @Override
    @Transactional
    public String addPlans(WeeklyPlan weeklyPlan) {
        weeklyPlanDao.addWeeklyPlan(weeklyPlan); //添加周计划
        return "1";
    }

    //审批周计划
    @Override
    @Transactional
    public String approvalPlans(WeeklyPlan weeklyPlan) {
        weeklyPlanDao.updateWeeklyPlan(weeklyPlan);
        return "1";
    }

    //查询项目信息
    @Override
    public PageInfo<ApprovalList> queryProjectName(OperationQuery operationQuery, Page page) {
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }

        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ApprovalList> list = weeklyPlanDao.queryProjectName(operationQuery);
        return new PageInfo<>(list);
    }

    //修改周计划
    @Override
    public String amendPlans(WeeklyPlan weeklyPlan) {
        weeklyPlanDao.amendPlans(weeklyPlan);
        return "1";
    }

    //删除周计划
    @Override
    public String deleteWeek(int id) {
        weeklyPlanDao.deleteWeek(id);
        return "1";
    }
}
