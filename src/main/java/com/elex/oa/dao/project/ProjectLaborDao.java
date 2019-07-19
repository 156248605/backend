package com.elex.oa.dao.project;

import com.elex.oa.entity.project.ProjectLabor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ProjectLaborDao {
    void updateLaborHourInfo(ProjectLabor projectLabor);

    List<ProjectLabor> queryLaborHourInfo(@Param("employeeNumber") String employeeNumber,
                                          @Param("projectCode") String projectCode,
                                          @Param("startDate") String startDate,
                                          @Param("endDate") String endDate);

    String queryDateLabor (@Param("employeeNumber") String employeeNumber,
                           @Param("projectCode") String projectCode,
                           @Param("fillingDate") String fillingDate);
}
