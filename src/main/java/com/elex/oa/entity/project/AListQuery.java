package com.elex.oa.entity.project;

import java.util.List;

public class AListQuery {
    //审批清单查询
    private String type;
    private String select1;
    private String input1;
    private String select2;
    private String input2;
    private String date3;
    private String date31;
    private String date32;
    private String select4;
    private List<String> list4;
    private String select5;
    private List<String> list5;
    private String select6;
    private String input6;
    private String select7;
    private String input7;
    private String select8;
    private String input8;
    private List<String> codes;

    public AListQuery() {
    }

    public AListQuery(String type, String select1, String input1, String select2, String input2, String date3, String date31, String date32, String select4, List<String> list4, String select5, List<String> list5, String select6, String input6, String select7, String input7, String select8, String input8, List<String> codes) {
        this.type = type;
        this.select1 = select1;
        this.input1 = input1;
        this.select2 = select2;
        this.input2 = input2;
        this.date3 = date3;
        this.date31 = date31;
        this.date32 = date32;
        this.select4 = select4;
        this.list4 = list4;
        this.select5 = select5;
        this.list5 = list5;
        this.select6 = select6;
        this.input6 = input6;
        this.select7 = select7;
        this.input7 = input7;
        this.select8 = select8;
        this.input8 = input8;
        this.codes = codes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getInput2() {
        return input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getDate3() {
        return date3;
    }

    public void setDate3(String date3) {
        this.date3 = date3;
    }

    public String getDate31() {
        return date31;
    }

    public void setDate31(String date31) {
        this.date31 = date31;
    }

    public String getDate32() {
        return date32;
    }

    public void setDate32(String date32) {
        this.date32 = date32;
    }

    public String getSelect4() {
        return select4;
    }

    public void setSelect4(String select4) {
        this.select4 = select4;
    }

    public List<String> getList4() {
        return list4;
    }

    public void setList4(List<String> list4) {
        this.list4 = list4;
    }

    public String getSelect5() {
        return select5;
    }

    public void setSelect5(String select5) {
        this.select5 = select5;
    }

    public List<String> getList5() {
        return list5;
    }

    public void setList5(List<String> list5) {
        this.list5 = list5;
    }

    public String getSelect6() {
        return select6;
    }

    public void setSelect6(String select6) {
        this.select6 = select6;
    }

    public String getInput6() {
        return input6;
    }

    public void setInput6(String input6) {
        this.input6 = input6;
    }

    public String getSelect7() {
        return select7;
    }

    public void setSelect7(String select7) {
        this.select7 = select7;
    }

    public String getInput7() {
        return input7;
    }

    public void setInput7(String input7) {
        this.input7 = input7;
    }

    public String getSelect8() {
        return select8;
    }

    public void setSelect8(String select8) {
        this.select8 = select8;
    }

    public String getInput8() {
        return input8;
    }

    public void setInput8(String input8) {
        this.input8 = input8;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    @Override
    public String toString() {
        return "AListQuery{" +
                "type='" + type + '\'' +
                ", select1='" + select1 + '\'' +
                ", input1='" + input1 + '\'' +
                ", select2='" + select2 + '\'' +
                ", input2='" + input2 + '\'' +
                ", date3='" + date3 + '\'' +
                ", date31='" + date31 + '\'' +
                ", date32='" + date32 + '\'' +
                ", select4='" + select4 + '\'' +
                ", list4=" + list4 +
                ", select5='" + select5 + '\'' +
                ", list5=" + list5 +
                ", select6='" + select6 + '\'' +
                ", input6='" + input6 + '\'' +
                ", select7='" + select7 + '\'' +
                ", input7='" + input7 + '\'' +
                ", select8='" + select8 + '\'' +
                ", input8='" + input8 + '\'' +
                ", codes=" + codes +
                '}';
    }
}
