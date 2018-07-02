package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.project.MileStoneDao;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.AListQuery;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.MileStonePlan;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.service.project.MileStoneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MileStoneImpl implements MileStoneService {

    @Resource
    private MileStoneDao mileStoneDao;

    //数据列表查询
    @Override
    public PageInfo<ApprovalList> queryList(AListQuery aListQuery , Page page) {
        List<String> date3 = JSONArray.parseArray(aListQuery.getDate3(),String.class);
        if(date3.get(0).equals("")) {

        } else {
            aListQuery.setDate31(date3.get(0));
            aListQuery.setDate32(date3.get(1));
        }
        List<String> list4 = JSONArray.parseArray(aListQuery.getSelect4(),String.class);
        List<String> list5 = JSONArray.parseArray(aListQuery.getSelect5(),String.class);
        if(list4.size() > 0) {
            aListQuery.setList4(list4);
        }
        if(list5.size() > 0) {
            aListQuery.setList5(list5);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ApprovalList> list = mileStoneDao.queryList(aListQuery);
        return new PageInfo<ApprovalList>(list);
    }

    //查询某项目编号相关的里程碑计划
    @Override
    public List<MileStonePlan> queryPlanByCode(String projectCode) {
        return mileStoneDao.queryPlanByCode(projectCode);
    }

    //查询某人可新建计划的项目编号
    @Override
    public List<String> queryCodeByName(String manager) {
        return mileStoneDao.queryCodeByName(manager);
    }

    //根据项目编号查询项目信息
    @Override
    public ApprovalList queryInforByCode(String projectCode) {
        return mileStoneDao.queryInforByCode(projectCode);
    }

    //某个项目添加计划
    @Override
    @Transactional
    public Map<String, String> addPlans(String dataS) {
        Map<String,String> map = new HashMap<>();
        List<MileStonePlan> mileStonePlans = JSONArray.parseArray(dataS,MileStonePlan.class);
        boolean firstMarker = true;
        for (MileStonePlan mileStonePlan:mileStonePlans) {
            if(firstMarker) {
                int result = mileStoneDao.modifyStatusByCode(mileStonePlan.getProjectCode()); //修改已通过审批项目的状态 n => y
                if(result == 1) {

                } else {
                    map.put("result","failure");
                    map.put("message","添加失败！");
                    return  map;
                }
                firstMarker = false;
            }
            int result1 = mileStoneDao.addPlan(mileStonePlan); //添加计划
            if(result1 == 1) {

            } else {
                map.put("result","failure");
                map.put("message","添加失败！");
                return  map;
            }
        }
        map.put("result","success");
        map.put("message","添加成功！");
        return map;
    }

    //修改某项目的计划
    @Override
    @Transactional
    public Map<String, String> modifyPlansByCode(String projectCode, String plans) {
        Map<String,String> map = new HashMap<>();
        List<MileStonePlan> list = JSONArray.parseArray(plans,MileStonePlan.class);
        if(list.size() == 0) {
            int result1 = mileStoneDao.modifyStatus2ByCode(projectCode); //修改已通过审批项目的状态 y => n
            if(result1 == 1) {

            } else {
                map.put("result","failure");
                map.put("message","更新失败！");
                return map;
            }
            mileStoneDao.deletePlansByCode(projectCode); //根据项目编号删除计划
        } else {
            List<MileStonePlan> mileStonePlans = mileStoneDao.queryPlanByCode(projectCode);
            boolean marker = true;
            for(MileStonePlan mileStonePlan: mileStonePlans) {
                marker = true;
                breakpoint:
                for(MileStonePlan mileStonePlan1: list) {
                    if(mileStonePlan.getId() == mileStonePlan1.getId()) {
                        int result2 = mileStoneDao.updatePlanById(mileStonePlan1); //根据id更新计划
                        if(result2 == 1) {

                        } else {
                            map.put("result","failure");
                            map.put("message","更新失败！");
                            return map;
                        }
                        marker = false;
                        break breakpoint;
                    }
                }
                if(marker) {
                    mileStoneDao.deletePlanById(mileStonePlan.getId()); //根据id删除计划
                }
            }
        }
        map.put("result","success");
        map.put("message","已更新！");
        return map;
    }

    //查询可新建的项目信息
    @Override
    public PageInfo<ApprovalList> queryProjectList(OperationQuery operationQuery, Page page) {
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ApprovalList> list = mileStoneDao.queryProjectList(operationQuery);
        return new PageInfo<>(list);
    }
}
