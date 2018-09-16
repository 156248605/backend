package com.elex.oa.entity.project;

public class ProjectExpense {
    private int id;
    private String projectCode;
    private String totalAmountL; //金额小写
    private String totalAmountC;    //金额大写

    public ProjectExpense() {
    }

    public ProjectExpense(int id, String projectCode, String totalAmountL, String totalAmountC) {
        this.id = id;
        this.projectCode = projectCode;
        this.totalAmountL = totalAmountL;
        this.totalAmountC = totalAmountC;
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

    public String getTotalAmountL() {
        return totalAmountL;
    }

    public void setTotalAmountL(String totalAmountL) {
        this.totalAmountL = totalAmountL;
    }

    public String getTotalAmountC() {
        return totalAmountC;
    }

    public void setTotalAmountC(String totalAmountC) {
        this.totalAmountC = totalAmountC;
    }

    @Override
    public String toString() {
        return "ProjectExpense{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", totalAmountL='" + totalAmountL + '\'' +
                ", totalAmountC='" + totalAmountC + '\'' +
                '}';
    }
}
