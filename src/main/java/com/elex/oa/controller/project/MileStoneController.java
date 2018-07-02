package com.elex.oa.controller.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.AListQuery;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.MileStonePlan;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.service.project.MileStoneService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/mileStone")
public class MileStoneController {

    @Autowired
    private MileStoneService mileStoneService;

    //数据列表查询
    @RequestMapping("/query_list")
    @ResponseBody
    public PageInfo<ApprovalList> queryList(AListQuery aListQuery, Page page) {
        return mileStoneService.queryList(aListQuery,page);
    }

    //查询某项目编号相关的里程碑计划
    @RequestMapping("/query_plan_code")
    @ResponseBody
    public List<MileStonePlan> queryPlanByCode(String projectCode) {
        return mileStoneService.queryPlanByCode(projectCode);
    }

    //查询某人可新建计划的项目编号
    @RequestMapping("/query_code_name")
    @ResponseBody
    public List<String> queryCodeByName(String manager) {
        return mileStoneService.queryCodeByName(manager);
    }

    //根据项目编号查询项目信息
    @RequestMapping("/query_infor_code")
    @ResponseBody
    public ApprovalList queryInforByCode(String projectCode) {
        return mileStoneService.queryInforByCode(projectCode);
    }

    //某个项目添加计划
    @RequestMapping("/add_plans")
    @ResponseBody
    public Map<String,String> addPlans(String dataS) {
        return mileStoneService.addPlans(dataS);
    }

    //修改某项目的计划
    @RequestMapping("/modify_plans_code")
    @ResponseBody
    public Map<String,String> modifyPlansByCode(String projectCode,String plans) {
        return mileStoneService.modifyPlansByCode(projectCode,plans);
    }

    //查询可新建的项目信息
    @RequestMapping("/query_project_list")
    @ResponseBody
    public PageInfo<ApprovalList> queryProjectList(OperationQuery operationQuery, Page page) {
        return mileStoneService.queryProjectList(operationQuery, page);
    }

}
