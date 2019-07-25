package com.elex.oa.entity.project;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectLabor {
    // 唯一主键
    String id;
    // 工号
    String employeeNumber;
    // 员工姓名
    String employeeName;
    // 项目编号
    String projectCode;
    // 项目名称
    String projectName;
    // 开始日期
    Date fillingDate;
    // 工时
    BigDecimal laborHour;
    // 状态
    String status;

    public ProjectLabor(String id, String employeeNumber, String employeeName, String projectCode, String projectName, Date fillingDate, BigDecimal laborHour, String status) {
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.fillingDate = fillingDate;
        this.laborHour = laborHour;
        this.status = status;
    }

    public ProjectLabor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getFillingDate() {
        return fillingDate;
    }

    public void setFillingDate(Date fillingDate) {
        this.fillingDate = fillingDate;
    }

    public BigDecimal getLaborHour() {
        return laborHour;
    }

    public void setLaborHour(BigDecimal labor_hour) {
        this.laborHour = labor_hour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProjectLabor{" +
                "id='" + id + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", fillingDate=" + fillingDate +
                ", laborHour=" + laborHour +
                ", status=" + status +
                '}';
    }
}
