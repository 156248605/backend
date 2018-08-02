package com.elex.oa.entity.project;

public class IncomeTwo {
    private int id;
    private String projectCode;
    private String inCode; //收入合同编码
    private String execution; //执行日期
    private String category; //执行类别
    private String pAmount; //执行金额
    private String  note; //备注

    public IncomeTwo() {
    }

    public IncomeTwo(int id, String projectCode, String inCode, String execution, String category, String pAmount, String note) {
        this.id = id;
        this.projectCode = projectCode;
        this.inCode = inCode;
        this.execution = execution;
        this.category = category;
        this.pAmount = pAmount;
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

    public String getInCode() {
        return inCode;
    }

    public void setInCode(String inCode) {
        this.inCode = inCode;
    }

    public String getExecution() {
        return execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getpAmount() {
        return pAmount;
    }

    public void setpAmount(String pAmount) {
        this.pAmount = pAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "IncomeTwo{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", inCode='" + inCode + '\'' +
                ", execution='" + execution + '\'' +
                ", category='" + category + '\'' +
                ", pAmount='" + pAmount + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
