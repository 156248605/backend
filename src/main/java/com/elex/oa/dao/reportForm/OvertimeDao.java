package com.elex.oa.dao.reportForm;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OvertimeDao {
    // 查询加班表单信息
    List getOvertimeForm(
            @Param("name") String name,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime,
            @Param("projectId") String projectId,
            @Param("category") String category,
            @Param("department") String department
    );
}
