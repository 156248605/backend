package com.elex.oa.entity.permission;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Employee {
    //权限及登录相关 用户实体类

    @Id
    private String id; //员工工号
    private String name; //员工姓名
    private String phoneticize; //员工姓名拼音，用于查询
    private String password; //  登录密码
    private String department; //部门id
    private List<String>  jobList; //员工岗位信息
    private String jobs; //员工岗位信息，用于前端展示
    private String status; //状态 是否启用

    public Employee() {
    }

    public Employee(String id, String name, String phoneticize, String password, String department, List<String> jobList, String jobs, String status) {
        this.id = id;
        this.name = name;
        this.phoneticize = phoneticize;
        this.password = password;
        this.department = department;
        this.jobList = jobList;
        this.jobs = jobs;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneticize() {
        return phoneticize;
    }

    public void setPhoneticize(String phoneticize) {
        this.phoneticize = phoneticize;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getJobList() {
        return jobList;
    }

    public void setJobList(List<String> jobList) {
        this.jobList = jobList;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneticize='" + phoneticize + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", jobList=" + jobList +
                ", jobs='" + jobs + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
