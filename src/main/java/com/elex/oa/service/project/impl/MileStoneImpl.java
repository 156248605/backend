package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.project.MileStoneDao;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.*;
import com.elex.oa.service.project.MileStoneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MileStoneImpl implements MileStoneService {

    @Resource
    private MileStoneDao mileStoneDao;

    //列表查询里程碑计划
    @Override
    public PageInfo queryList(AListQuery aListQuery, Page page) {
        List<String> date3 = JSONArray.parseArray(aListQuery.getDate3(),String.class);
        if(date3.get(0).equals("") || date3.get(0) == null) {

        } else {
            aListQuery.setDate31(date3.get(0));
            aListQuery.setDate32(date3.get(1));
        }
        List<String> list4 = JSONArray.parseArray(aListQuery.getSelect4(),String.class);
        if(list4.size() > 0) {
            aListQuery.setList4(list4);
        }
        List<String> list5 = JSONArray.parseArray(aListQuery.getSelect5(),String.class);
        if(list5.size() > 0) {
            aListQuery.setList5(list5);
        }
        List<ProjectInfor> list = new ArrayList<>();
        List<String> codes = mileStoneDao.queryCodes(); //查询已建立的里程碑计划的项目编号
        if(codes.size() > 0) {
            aListQuery.setCodes(codes);
            PageHelper.startPage(page.getCurrentPage(),10);
            list = mileStoneDao.queryList(aListQuery); //里程碑计划列表查询
        } else {

        }
        return new PageInfo(list);
    }

    //查询可新建里程碑计划的项目信息
    @Override
    public PageInfo queryProjectList(OperationQuery operationQuery, int currentPage) {
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        List<String> codes = mileStoneDao.queryCodes();
        if(codes.size() > 0) {
            operationQuery.setCodes(codes);
        }
        PageHelper.startPage(currentPage,5);
        List<ProjectInfor> list = mileStoneDao.queryProjectList(operationQuery);
        return new PageInfo(list);
    }

    //添加里程碑计划
    @Override
    @Transactional
    public String addPlans(String dataS, MileStone mileStone) {
        List<MileStonePlan> mileStonePlans = JSONArray.parseArray(dataS,MileStonePlan.class);
        mileStoneDao.addMileStone(mileStone); //添加里程碑信息
        mileStoneDao.addMileStonePlans(mileStonePlans); //添加里程碑信息详情
        return "1";
    }

    //查询里程碑计划详情
    @Override
    public List<MileStonePlan> queryPlansCode(String projectCode) {
        return mileStoneDao.queryPlansCode(projectCode);
    }

    //修改里程碑计划详情
    @Override
    @Transactional
    public String modifyPlansCode(String dataS, MileStone mileStone) {
        List<MileStonePlan> mileStonePlans = JSONArray.parseArray(dataS,MileStonePlan.class);
        mileStoneDao.deleteMileStone(mileStone.getProjectCode()); //删除里程碑信息
        mileStoneDao.addMileStone(mileStone); //添加里程碑信息
        mileStoneDao.deleteMileStonePlans(mileStone.getProjectCode()); //删除里程碑信息详情
        mileStoneDao.addMileStonePlans(mileStonePlans);
        return "1";
    }
}
