package com.elex.oa.entity.project;

public class IncomeOne {
    private int id;
    private String projectCode; //项目编号
    private String code; //合同编号
    private String name; //合同名称
    private String situation; //合同概况
    private String type; //合同类型
    private String amount; //合同金额
    private String effective; //生效日期
    private String payAmount; //尾款金额
    private String payDate; //尾款日期

    public IncomeOne() {
    }

    public IncomeOne(int id, String projectCode, String code, String name, String situation, String type, String amount, String effective, String payAmount, String payDate) {
        this.id = id;
        this.projectCode = projectCode;
        this.code = code;
        this.name = name;
        this.situation = situation;
        this.type = type;
        this.amount = amount;
        this.effective = effective;
        this.payAmount = payAmount;
        this.payDate = payDate;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "IncomeOne{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", situation='" + situation + '\'' +
                ", type='" + type + '\'' +
                ", amount='" + amount + '\'' +
                ", effective='" + effective + '\'' +
                ", payAmount='" + payAmount + '\'' +
                ", payDate='" + payDate + '\'' +
                '}';
    }
}
