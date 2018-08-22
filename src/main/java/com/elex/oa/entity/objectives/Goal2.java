package com.elex.oa.entity.objectives;

public class Goal2 {
    private int id;
    private String annual; //年份
    private String company; //公司
    private double annualTarget; //年度目标
    private String theTotal; //累计完成
    private String completion; //完成率
    private double january; //一月份
    private double february; //二月份
    private double march; //三月份
    private double april; //四月份
    private double may; //五月份
    private double june; //六月份
    private double july; //七月份
    private double august; //八月份
    private double september; //九月份
    private double october; //十月份
    private double november; //十一月份
    private double december; //十二月份

    public Goal2() {
    }

    public Goal2(int id, String annual, String company, double annualTarget, String theTotal, String completion, double january, double february, double march, double april, double may, double june, double july, double august, double september, double october, double november, double december) {
        this.id = id;
        this.annual = annual;
        this.company = company;
        this.annualTarget = annualTarget;
        this.theTotal = theTotal;
        this.completion = completion;
        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getAnnualTarget() {
        return annualTarget;
    }

    public void setAnnualTarget(double annualTarget) {
        this.annualTarget = annualTarget;
    }

    public String getTheTotal() {
        return theTotal;
    }

    public void setTheTotal(String theTotal) {
        this.theTotal = theTotal;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public double getJanuary() {
        return january;
    }

    public void setJanuary(double january) {
        this.january = january;
    }

    public double getFebruary() {
        return february;
    }

    public void setFebruary(double february) {
        this.february = february;
    }

    public double getMarch() {
        return march;
    }

    public void setMarch(double march) {
        this.march = march;
    }

    public double getApril() {
        return april;
    }

    public void setApril(double april) {
        this.april = april;
    }

    public double getMay() {
        return may;
    }

    public void setMay(double may) {
        this.may = may;
    }

    public double getJune() {
        return june;
    }

    public void setJune(double june) {
        this.june = june;
    }

    public double getJuly() {
        return july;
    }

    public void setJuly(double july) {
        this.july = july;
    }

    public double getAugust() {
        return august;
    }

    public void setAugust(double august) {
        this.august = august;
    }

    public double getSeptember() {
        return september;
    }

    public void setSeptember(double september) {
        this.september = september;
    }

    public double getOctober() {
        return october;
    }

    public void setOctober(double october) {
        this.october = october;
    }

    public double getNovember() {
        return november;
    }

    public void setNovember(double november) {
        this.november = november;
    }

    public double getDecember() {
        return december;
    }

    public void setDecember(double december) {
        this.december = december;
    }

    @Override
    public String toString() {
        return "Goal2{" +
                "id=" + id +
                ", annual='" + annual + '\'' +
                ", company='" + company + '\'' +
                ", annualTarget=" + annualTarget +
                ", theTotal='" + theTotal + '\'' +
                ", completion='" + completion + '\'' +
                ", january=" + january +
                ", february=" + february +
                ", march=" + march +
                ", april=" + april +
                ", may=" + may +
                ", june=" + june +
                ", july=" + july +
                ", august=" + august +
                ", september=" + september +
                ", october=" + october +
                ", november=" + november +
                ", december=" + december +
                '}';
    }
}
