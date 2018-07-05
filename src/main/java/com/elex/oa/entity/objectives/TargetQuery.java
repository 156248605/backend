package com.elex.oa.entity.objectives;

import java.util.List;

public class TargetQuery {
    private String select1;
    private String input1;
    private String select2;
    private List<String> list2;
    private String select3;
    private String input3;
    private String select4;
    private String input4;
    private String select5;
    private String input5;

    public TargetQuery() {
    }

    public TargetQuery(String select1, String input1, String select2, List<String> list2, String select3, String input3, String select4, String input4, String select5, String input5) {
        this.select1 = select1;
        this.input1 = input1;
        this.select2 = select2;
        this.list2 = list2;
        this.select3 = select3;
        this.input3 = input3;
        this.select4 = select4;
        this.input4 = input4;
        this.select5 = select5;
        this.input5 = input5;
    }

    public String getSelect1() {
        return select1;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public String getInput1() {
        return input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getSelect2() {
        return select2;
    }

    public void setSelect2(String select2) {
        this.select2 = select2;
    }

    public List<String> getList2() {
        return list2;
    }

    public void setList2(List<String> list2) {
        this.list2 = list2;
    }

    public String getSelect3() {
        return select3;
    }

    public void setSelect3(String select3) {
        this.select3 = select3;
    }

    public String getInput3() {
        return input3;
    }

    public void setInput3(String input3) {
        this.input3 = input3;
    }

    public String getSelect4() {
        return select4;
    }

    public void setSelect4(String select4) {
        this.select4 = select4;
    }

    public String getInput4() {
        return input4;
    }

    public void setInput4(String input4) {
        this.input4 = input4;
    }

    public String getSelect5() {
        return select5;
    }

    public void setSelect5(String select5) {
        this.select5 = select5;
    }

    public String getInput5() {
        return input5;
    }

    public void setInput5(String input5) {
        this.input5 = input5;
    }

    @Override
    public String toString() {
        return "TargetQuery{" +
                "select1='" + select1 + '\'' +
                ", input1='" + input1 + '\'' +
                ", select2='" + select2 + '\'' +
                ", list2=" + list2 +
                ", select3='" + select3 + '\'' +
                ", input3='" + input3 + '\'' +
                ", select4='" + select4 + '\'' +
                ", input4='" + input4 + '\'' +
                ", select5='" + select5 + '\'' +
                ", input5='" + input5 + '\'' +
                '}';
    }
}
