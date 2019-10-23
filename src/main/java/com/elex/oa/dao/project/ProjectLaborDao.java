package com.elex.oa.dao.project;

import com.elex.oa.entity.project.ProjectLabor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface ProjectLaborDao {
    void updateLaborHourInfo(ProjectLabor projectLabor);

    List<ProjectLabor> queryLaborHourInfo(
            @Param("employeeNumber") String employeeNumber,
            @Param("projectCode") String projectCode,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    String queryDateLabor (
            @Param("employeeNumber") String employeeNumber,
            @Param("projectCode") String projectCode,
            @Param("fillingDate") String fillingDate
    );

    String queryLaborStatus ();

    void updateFrequentProjectInfo(ProjectLabor projectLabor);

    String queryFrequentProjectInfo(ProjectLabor projectLabor);

    String checkLockingInfo();

    String queryLaborHourInfoByDepartment(
            @Param("employeeNumber") String employeeNumber,
            @Param("projectCode") String projectCode,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    List<ProjectLabor> queryProject(@Param("employeeNumber") String employeeNumber);

    List queryDepartment(@Param("deptId") String deptId);

    List<HashMap<String,Object>> queryEmployee(@Param("deptId") String deptId);

    List<HashMap<String,Object>> queryLaborHourInfoByMonth (
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    List<HashMap<String,Object>> queryEmployeeByMonth(
            @Param("projectCode") String projectCode,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    List<HashMap<String,Object>> queryProjectByYear(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    void saveLockingInfo(
            @Param("id") String id,
            @Param("employeeNumber") String employeeNumber,
            @Param("employeeName") String employeeName,
            @Param("fillingDate") String date,
            @Param("projectCode") String projectCode,
            @Param("projectName") String projectName,
            @Param("laborHour") String laborHour
    );

    void updateIntoproLaborMonth(
            @Param("locking_time") String locking_time
    );

    void plusLockingInfoByYear(
            @Param("id") String id,
            @Param("employeeNumber") String employeeNumber,
            @Param("employeeName") String employeeName,
            @Param("fillingDate") String date,
            @Param("projectCode") String projectCode,
            @Param("projectName") String projectName,
            @Param("laborHour") String laborHour
    );

    String queryLaborHourInRecord(
            @Param("fillingDate") String date,
            @Param("projectCode") String projectCode
    );

    String queryLaborHourInLabor(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("projectCode") String projectCode
    );

    // 查询时间段内填写记录 项目编号列表
    List<String> queryLaborRecordProjectCodeByDateInterval(
      @Param("startDate") String startDate,
      @Param("endDate") String endDate,
      @Param("employeeNumber") String employeeNumber
    );

}
