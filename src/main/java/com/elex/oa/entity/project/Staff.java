package com.elex.oa.entity.project;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

public class Staff {
    @Id
    private int id;
    private String phoneticize; //英文名
    private String employeeNumber; //员工编号
    private String employeeName; // 员工姓名
    private String deptId; //部门id
    private String deptName; //部门名称
    private List<Map<String,Object>> post; //岗位信息

    public Staff() {
    }

    public Staff(int id, String phoneticize, String employeeNumber, String employeeName, String deptId, String deptName, List<Map<String, Object>> post) {
        this.id = id;
        this.phoneticize = phoneticize;
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.deptId = deptId;
        this.deptName = deptName;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneticize() {
        return phoneticize;
    }

    public void setPhoneticize(String phoneticize) {
        this.phoneticize = phoneticize;
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Map<String, Object>> getPost() {
        return post;
    }

    public void setPost(List<Map<String, Object>> post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", phoneticize='" + phoneticize + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", post=" + post +
                '}';
    }
}
