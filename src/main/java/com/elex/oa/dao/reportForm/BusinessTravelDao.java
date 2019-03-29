package com.elex.oa.dao.reportForm;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BusinessTravelDao {
    // 查询出差表单信息
    List getBusinessTravelForm(
            @Param("name") String name,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime,
            @Param("projectId") String projectId,
            @Param("category") String category,
            @Param("department") String department
    );
}
