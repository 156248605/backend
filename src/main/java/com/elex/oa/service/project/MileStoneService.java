package com.elex.oa.service.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.AListQuery;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.MileStonePlan;
import com.elex.oa.entity.project.OperationQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface MileStoneService {
    //数据列表查询
    PageInfo<ApprovalList> queryList(AListQuery aListQuery, Page page);
    //查询某项目编号相关的里程碑计划
    List<MileStonePlan> queryPlanByCode(String projectCode);
    //查询某人可新建计划的项目编号
    List<String> queryCodeByName(String manager);
    //根据项目编号查询项目信息
    ApprovalList queryInforByCode(String projectCode);
    //某个项目添加计划
    Map<String,String> addPlans(String dataS);
    //修改某项目的计划
    Map<String,String> modifyPlansByCode(String projectCode, String plans);
    //查询可新建的项目信息
    PageInfo<ApprovalList> queryProjectList(OperationQuery operationQuery, Page page);
}
