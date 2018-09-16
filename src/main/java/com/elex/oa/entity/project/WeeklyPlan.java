package com.elex.oa.entity.project;

public class WeeklyPlan { //项目周报
    private int id;
    private String projectCode; //项目编号
    private String startDate; //实施启动时间
    private String endDate; //预计完成时间
    private String timeSchedule; //时间进度
    private String fulfillment; //完成进度
    private String punctuality; //是否按期
    private String projectPhase; //项目阶段
    private String completion; //完成概况
    private String nextPlan; //下周计划
    private String risk; //风险提醒
    private String fillDate; //填写日期
    private String projectManager; //项目经理
    private String departmentManager; //部门经理
    //private String leaderShip; //分管领导

    public WeeklyPlan() {
    }

    public WeeklyPlan(int id, String projectCode, String startDate, String endDate, String timeSchedule, String fulfillment, String punctuality, String projectPhase, String completion, String nextPlan, String risk, String fillDate, String projectManager, String departmentManager) {
        this.id = id;
        this.projectCode = projectCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeSchedule = timeSchedule;
        this.fulfillment = fulfillment;
        this.punctuality = punctuality;
        this.projectPhase = projectPhase;
        this.completion = completion;
        this.nextPlan = nextPlan;
        this.risk = risk;
        this.fillDate = fillDate;
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

    public String getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedule(String timeSchedule) {
        this.timeSchedule = timeSchedule;
    }

    public String getFulfillment() {
        return fulfillment;
    }

    public void setFulfillment(String fulfillment) {
        this.fulfillment = fulfillment;
    }

    public String getPunctuality() {
        return punctuality;
    }

    public void setPunctuality(String punctuality) {
        this.punctuality = punctuality;
    }

    public String getProjectPhase() {
        return projectPhase;
    }

    public void setProjectPhase(String projectPhase) {
        this.projectPhase = projectPhase;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getNextPlan() {
        return nextPlan;
    }

    public void setNextPlan(String nextPlan) {
        this.nextPlan = nextPlan;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getFillDate() {
        return fillDate;
    }

    public void setFillDate(String fillDate) {
        this.fillDate = fillDate;
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
                ", timeSchedule='" + timeSchedule + '\'' +
                ", fulfillment='" + fulfillment + '\'' +
                ", punctuality='" + punctuality + '\'' +
                ", projectPhase='" + projectPhase + '\'' +
                ", completion='" + completion + '\'' +
                ", nextPlan='" + nextPlan + '\'' +
                ", risk='" + risk + '\'' +
                ", fillDate='" + fillDate + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", departmentManager='" + departmentManager + '\'' +
                '}';
    }
}
