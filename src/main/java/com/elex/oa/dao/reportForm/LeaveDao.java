package com.elex.oa.dao.reportForm;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaveDao {
    // 查询请假表单信息
    List getLeaveForm(String name,String startTime,String endTime,String projectId,String category,String department);

}
