package com.elex.oa.entity.project;

public class MileStonePlan {
    private int id;
    private String projectCode; // 关联项目编号
    private String phase; //阶段
    private String keyWork; //关键工作
    private String startDate; //起始时间
    private String endDate; //截止时间
    private String status; //状态
    private String note; //备注


    public MileStonePlan() {
    }

    public MileStonePlan(int id, String projectCode, String phase, String keyWork, String startDate, String endDate, String status, String note) {
        this.id = id;
        this.projectCode = projectCode;
        this.phase = phase;
        this.keyWork = keyWork;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.note = note;
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

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getKeyWork() {
        return keyWork;
    }

    public void setKeyWork(String keyWork) {
        this.keyWork = keyWork;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "MileStonePlan{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", phase='" + phase + '\'' +
                ", keyWork='" + keyWork + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
