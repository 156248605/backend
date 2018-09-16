package com.elex.oa.dao.project;

import com.elex.oa.entity.project.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MileStoneDao {
    //查询已建立的里程碑计划的项目编号
    List<String> queryCodes();
    //里程碑计划列表查询
    List<ProjectInfor> queryList(AListQuery aListQuery);
    //查询可新建里程碑计划的项目信息
    List<ProjectInfor> queryProjectList(OperationQuery operationQuery);
    //添加里程碑信息
    void addMileStone(MileStone mileStone);
    //添加里程碑信息详情
    void addMileStonePlans(List<MileStonePlan> mileStonePlans);
    //查询里程碑计划详情
    List<MileStonePlan> queryPlansCode(String projectCode);
    //删除里程碑信息
    void deleteMileStone(String projectCode);
    //删除里程碑信息详情
    void deleteMileStonePlans(String projectCode);
}
