package com.elex.oa.entity.project;

public class ProjectHumanDetail {
    private int id;
    private String projectCode;
    private String period;  //期间
    private String category;  //类别
    private String particulars; //明细
    private String amount; //金额
    private String note; //备注


    public ProjectHumanDetail() {
    }

    public ProjectHumanDetail(int id, String projectCode, String period, String category, String particulars, String amount, String note) {
        this.id = id;
        this.projectCode = projectCode;
        this.period = period;
        this.category = category;
        this.particulars = particulars;
        this.amount = amount;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ProjectHumanDetail{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", period='" + period + '\'' +
                ", category='" + category + '\'' +
                ", particulars='" + particulars + '\'' +
                ", amount='" + amount + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
