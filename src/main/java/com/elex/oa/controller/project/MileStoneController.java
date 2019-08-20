package com.elex.oa.controller.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.AListQuery;
import com.elex.oa.entity.project.MileStone;
import com.elex.oa.entity.project.MileStonePlan;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.service.project.MileStoneService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.jgss.HttpCaller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/mileStone")
public class MileStoneController {

    @Autowired
    private MileStoneService mileStoneService;

    //列表查询里程碑计划
    @RequestMapping("/query_list")
    @ResponseBody
    public PageInfo queryList(AListQuery aListQuery, Page page, HttpServletRequest request) {
        return mileStoneService.queryList(aListQuery, page, request);
    }

    //查询可新建里程碑计划的项目信息
    @RequestMapping("/query_project_list")
    @ResponseBody
    public PageInfo queryProjectList(OperationQuery operationQuery, int currentPage) {
        return mileStoneService.queryProjectList(operationQuery, currentPage);
    }
    //添加里程碑计划
    @RequestMapping("/add_plans")
    @ResponseBody
    public String addPlans(String dataS, MileStone mileStone) {
        return  mileStoneService.addPlans(dataS, mileStone);
    }

    //查询里程碑计划详情
    @RequestMapping("/query_plan_code")
    @ResponseBody
    public List<MileStonePlan> queryPlansCode(String projectCode) {
        return mileStoneService.queryPlansCode(projectCode);
    }

    //修改里程碑计划详情
    @RequestMapping("/modify_plans_code")
    @ResponseBody
    public String modifyPlansCode(String dataS, MileStone mileStone) {
        return mileStoneService.modifyPlansCode(dataS, mileStone);
    }

}
