package com.elex.oa.entity.project;

public class ProjectMaterial {
    private int id;
    private String projectCode; //项目编号
    private String totalAmountL; //总额小写
    private String totalAmountC; //总额大写

    public ProjectMaterial() {
    }

    public ProjectMaterial(int id, String projectCode, String totalAmountL, String totalAmountC) {
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
        return "ProjectMaterial{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", totalAmountL='" + totalAmountL + '\'' +
                ", totalAmountC='" + totalAmountC + '\'' +
                '}';
    }
}
