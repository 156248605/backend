package com.elex.oa.entity.project;

public class ProjectIncome {
    private int id;
    private String projectCode;
    private String amount; //合同金额
    private String copies; //合同份数
    private String invoiceAmount; //开票合计
    private String invoiceProportion; //开票比例
    private String receivableAmount; //回款合计
    private String receivableProportion; //回款比例
    private String acceptanceAmount; //验收合计
    private String acceptanceProportion; //验收比例

    public ProjectIncome() {
    }

    public ProjectIncome(int id, String projectCode, String amount, String copies, String invoiceAmount, String invoiceProportion, String receivableAmount, String receivableProportion, String acceptanceAmount, String acceptanceProportion) {
        this.id = id;
        this.projectCode = projectCode;
        this.amount = amount;
        this.copies = copies;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCopies() {
        return copies;
    }

    public void setCopies(String copies) {
        this.copies = copies;
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
                ", amount='" + amount + '\'' +
                ", copies='" + copies + '\'' +
                ", invoiceAmount='" + invoiceAmount + '\'' +
                ", invoiceProportion='" + invoiceProportion + '\'' +
                ", receivableAmount='" + receivableAmount + '\'' +
                ", receivableProportion='" + receivableProportion + '\'' +
                ", acceptanceAmount='" + acceptanceAmount + '\'' +
                ", acceptanceProportion='" + acceptanceProportion + '\'' +
                '}';
    }
}
