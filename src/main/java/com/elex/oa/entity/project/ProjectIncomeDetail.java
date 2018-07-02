package com.elex.oa.entity.project;

public class ProjectIncomeDetail {
    private int id;
    private String projectCode;
    private String contractCode; //合同编码
    private String contractSituation; //合同概况
    private String contractType; //合同类型
    private String contractAmount; //合同金额
    private String effectiveDate; //生效日期
    private String executionDate; //执行日期
    private String performCategory; //执行类别
    private String performAmount; //执行金额
    private String note; //备注

    public ProjectIncomeDetail() {
    }

    public ProjectIncomeDetail(int id, String projectCode, String contractCode, String contractSituation, String contractType, String contractAmount, String effectiveDate, String executionDate, String performCategory, String performAmount, String note) {
        this.id = id;
        this.projectCode = projectCode;
        this.contractCode = contractCode;
        this.contractSituation = contractSituation;
        this.contractType = contractType;
        this.contractAmount = contractAmount;
        this.effectiveDate = effectiveDate;
        this.executionDate = executionDate;
        this.performCategory = performCategory;
        this.performAmount = performAmount;
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

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractSituation() {
        return contractSituation;
    }

    public void setContractSituation(String contractSituation) {
        this.contractSituation = contractSituation;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(String contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getPerformCategory() {
        return performCategory;
    }

    public void setPerformCategory(String performCategory) {
        this.performCategory = performCategory;
    }

    public String getPerformAmount() {
        return performAmount;
    }

    public void setPerformAmount(String performAmount) {
        this.performAmount = performAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ProjectIncomeDetail{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", contractCode='" + contractCode + '\'' +
                ", contractSituation='" + contractSituation + '\'' +
                ", contractType='" + contractType + '\'' +
                ", contractAmount='" + contractAmount + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", executionDate='" + executionDate + '\'' +
                ", performCategory='" + performCategory + '\'' +
                ", performAmount='" + performAmount + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
