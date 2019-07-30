package com.elex.oa.dao.project;

import com.elex.oa.entity.project.ProjectLabor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
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

    String queryLaborStatus ();

    void updateFrequentProjectInfo(ProjectLabor projectLabor);

    String queryFrequentProjectInfo(ProjectLabor projectLabor);

    void updateLockingInfo(@Param("date") String date);

    String queryLaborHourInfoByDepartment(@Param("employeeNumber") String employeeNumber,
                                          @Param("projectCode") String projectCode,
                                          @Param("startDate") String startDate,
                                          @Param("endDate") String endDate);

    List<ProjectLabor> queryProject(@Param("employeeNumber") String employeeNumber);

    List queryDepartment(@Param("deptId") String deptId);

    List<HashMap<String,Object>> queryEmployee(@Param("deptId") String deptId);

    List<HashMap<String,Object>> queryLaborHourInfoByMonth (@Param("startDate") String startDate,
                                    @Param("endDate") String endDate);

    List<HashMap<String,Object>> queryEmployeeByMonth(@Param("projectCode") String projectCode,
                              @Param("startDate") String startDate,
                              @Param("endDate") String endDate);
}
