package com.elex.oa.entity.project;

public class WeeklyPlan { //项目周报
    private int id;
    private String projectCode; //项目编号
    private String startDate; //起始时间
    private String endDate; //截止时间
    private String complete; //完成概况
    private String risk; //风险提醒
    private String projectManager; //项目经理
    private String departmentManager; //部门经理
    //private String leaderShip; //分管领导

    public WeeklyPlan() {
    }

    public WeeklyPlan(int id, String projectCode, String startDate, String endDate, String complete, String risk, String projectManager, String departmentManager) {
        this.id = id;
        this.projectCode = projectCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.complete = complete;
        this.risk = risk;
        this.projectManager = projectManager;
        this.departmentManager = departmentManager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }

    @Override
    public String toString() {
        return "WeeklyPlan{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", complete='" + complete + '\'' +
                ", risk='" + risk + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", departmentManager='" + departmentManager + '\'' +
                '}';
    }
}
