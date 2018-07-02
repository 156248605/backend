package com.elex.oa.entity.project;

public class ProjectIncome {
    private int id;
    private String projectCode;
    private String payAmount; //尾款金额
    private String payDate; //尾款日期
    private String invoiceAmount; //开票合计
    private String invoiceProportion; //开票比例
    private String receivableAmount; //回款合计
    private String receivableProportion; //回款比例
    private String acceptanceAmount; //验收合计
    private String acceptanceProportion; //验收比例

    public ProjectIncome() {
    }

    public ProjectIncome(int id, String projectCode, String payAmount, String payDate, String invoiceAmount, String invoiceProportion, String receivableAmount, String receivableProportion, String acceptanceAmount, String acceptanceProportion) {
        this.id = id;
        this.projectCode = projectCode;
        this.payAmount = payAmount;
        this.payDate = payDate;
        this.invoiceAmount = invoiceAmount;
        this.invoiceProportion = invoiceProportion;
        this.receivableAmount = receivableAmount;
        this.receivableProportion = receivableProportion;
        this.acceptanceAmount = acceptanceAmount;
        this.acceptanceProportion = acceptanceProportion;
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

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceProportion() {
        return invoiceProportion;
    }

    public void setInvoiceProportion(String invoiceProportion) {
        this.invoiceProportion = invoiceProportion;
    }

    public String getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(String receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public String getReceivableProportion() {
        return receivableProportion;
    }

    public void setReceivableProportion(String receivableProportion) {
        this.receivableProportion = receivableProportion;
    }

    public String getAcceptanceAmount() {
        return acceptanceAmount;
    }

    public void setAcceptanceAmount(String acceptanceAmount) {
        this.acceptanceAmount = acceptanceAmount;
    }

    public String getAcceptanceProportion() {
        return acceptanceProportion;
    }

    public void setAcceptanceProportion(String acceptanceProportion) {
        this.acceptanceProportion = acceptanceProportion;
    }

    @Override
    public String toString() {
        return "ProjectIncome{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", payAmount='" + payAmount + '\'' +
                ", payDate='" + payDate + '\'' +
                ", invoiceAmount='" + invoiceAmount + '\'' +
                ", invoiceProportion='" + invoiceProportion + '\'' +
                ", receivableAmount='" + receivableAmount + '\'' +
                ", receivableProportion='" + receivableProportion + '\'' +
                ", acceptanceAmount='" + acceptanceAmount + '\'' +
                ", acceptanceProportion='" + acceptanceProportion + '\'' +
                '}';
    }
}
