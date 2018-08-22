package com.elex.oa.entity.objectives;

public class Goal {
    private int id;
    private String annual; //年份
    private String company; //公司
    private int annualTarget; //年度目标
    private String theTotal; //累计完成
    private String completion; //完成率
    private int january; //一月份
    private int february; //二月份
    private int march; //三月份
    private int april; //四月份
    private int may; //五月份
    private int june; //六月份
    private int july; //七月份
    private int august; //八月份
    private int september; //九月份
    private int october; //十月份
    private int november; //十一月份
    private int december; //十二月份

    public Goal() {
    }

    public Goal(int id, String annual, String company, int annualTarget, String theTotal, String completion, int january, int february, int march, int april, int may, int june, int july, int august, int september, int october, int november, int december) {
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

    public int getAnnualTarget() {
        return annualTarget;
    }

    public void setAnnualTarget(int annualTarget) {
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

    public int getJanuary() {
        return january;
    }

    public void setJanuary(int january) {
        this.january = january;
    }

    public int getFebruary() {
        return february;
    }

    public void setFebruary(int february) {
        this.february = february;
    }

    public int getMarch() {
        return march;
    }

    public void setMarch(int march) {
        this.march = march;
    }

    public int getApril() {
        return april;
    }

    public void setApril(int april) {
        this.april = april;
    }

    public int getMay() {
        return may;
    }

    public void setMay(int may) {
        this.may = may;
    }

    public int getJune() {
        return june;
    }

    public void setJune(int june) {
        this.june = june;
    }

    public int getJuly() {
        return july;
    }

    public void setJuly(int july) {
        this.july = july;
    }

    public int getAugust() {
        return august;
    }

    public void setAugust(int august) {
        this.august = august;
    }

    public int getSeptember() {
        return september;
    }

    public void setSeptember(int september) {
        this.september = september;
    }

    public int getOctober() {
        return october;
    }

    public void setOctober(int october) {
        this.october = october;
    }

    public int getNovember() {
        return november;
    }

    public void setNovember(int november) {
        this.november = november;
    }

    public int getDecember() {
        return december;
    }

    public void setDecember(int december) {
        this.december = december;
    }

    @Override
    public String toString() {
        return "Goal{" +
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
