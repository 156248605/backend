package com.elex.oa.entity.project;

public class ProjectMaterialDetail {
    private int id;
    private String projectCode;
    private String outOrder; //出库单号
    private String goodCode; //物品编码
    private String goodName; //物品名称
    private String number; //数量
    private String unitPrice; //单价
    private String amount; //金额

    public ProjectMaterialDetail() {
    }

    public ProjectMaterialDetail(int id, String projectCode, String outOrder, String goodCode, String goodName, String number, String unitPrice, String amount) {
        this.id = id;
        this.projectCode = projectCode;
        this.outOrder = outOrder;
        this.goodCode = goodCode;
        this.goodName = goodName;
        this.number = number;
        this.unitPrice = unitPrice;
        this.amount = amount;
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

    public String getOutOrder() {
        return outOrder;
    }

    public void setOutOrder(String outOrder) {
        this.outOrder = outOrder;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ProjectMaterialDetail{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", outOrder='" + outOrder + '\'' +
                ", goodCode='" + goodCode + '\'' +
                ", goodName='" + goodName + '\'' +
                ", number='" + number + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
