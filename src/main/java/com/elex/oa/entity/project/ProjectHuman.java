package com.elex.oa.entity.project;

public class ProjectHuman {
    private int id;
    private String projectCode;
    private String totalAmountL; //总额小写
    private String totalAmountC; //总额大写

    public ProjectHuman() {
    }

    public ProjectHuman(int id, String projectCode, String totalAmountL, String totalAmountC) {
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
        return "ProjectHuman{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", totalAmountL='" + totalAmountL + '\'' +
                ", totalAmountC='" + totalAmountC + '\'' +
                '}';
    }
}
