package com.elex.oa.dao.project;

import com.elex.oa.entity.project.AListQuery;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.MileStonePlan;
import com.elex.oa.entity.project.OperationQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MileStoneDao {
    //数据列表查询
    List<ApprovalList> queryList(AListQuery aListQuery);
    //查询某项目编号相关的里程碑计划
    List<MileStonePlan> queryPlanByCode(String projectCode);
    //查询某人可新建计划的项目编号
    List<String> queryCodeByName(String manager);
    //根据项目编号查询项目信息
    ApprovalList queryInforByCode(String projectCode);
    //修改已通过审批项目的状态 n => y
    int modifyStatusByCode(String projectCode);
    //添加计划
    int addPlan(MileStonePlan mileStonePlan);
    //修改已通过审批项目的状态 y => n
    int modifyStatus2ByCode(String projectCode);
    //根据项目编号删除计划
    void deletePlansByCode(String projectCode);
    //根据id删除计划
    void deletePlanById(int id);
    //根据id更新计划
    int updatePlanById(MileStonePlan mileStonePlan1);
    //查询可新建的项目信息
    List<ApprovalList> queryProjectList(OperationQuery operationQuery);
}
