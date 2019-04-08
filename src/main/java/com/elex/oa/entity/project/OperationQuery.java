package com.elex.oa.entity.project;

import java.util.List;

public class OperationQuery {
    private String select1; //项目编号部分
    private String input1;
    private String select2; //项目名称部分
    private String input2;
    private String select3; //立项部门
    private String input3;
    private String select4;
    private List<String> list4;
    private String select5;
    private String input5;
    private String select6;
    private List<String> list6;
    private String select7;
    private String input7;
    private String select8;
    private List<String> list8;
    private String phaseSelect;
    private List<String> phaseList;
    private String type;
    private String name;
    private List<String> codes; //项目编号


    public OperationQuery() {
    }

    public OperationQuery(String select1, String input1, String select2, String input2, String select3, String input3, String select4, List<String> list4, String select5, String input5, String select6, List<String> list6, String select7, String input7, String select8, List<String> list8, String phaseSelect, List<String> phaseList, String type, String name, List<String> codes) {
        this.select1 = select1;
        this.input1 = input1;
        this.select2 = select2;
        this.input2 = input2;
        this.select3 = select3;
        this.input3 = input3;
        this.select4 = select4;
        this.list4 = list4;
        this.select5 = select5;
        this.input5 = input5;
        this.select6 = select6;
        this.list6 = list6;
        this.select7 = select7;
        this.input7 = input7;
        this.select8 = select8;
        this.list8 = list8;
        this.phaseSelect = phaseSelect;
        this.phaseList = phaseList;
        this.type = type;
        this.name = name;
        this.codes = codes;
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

    public String getInput5() {
        return input5;
    }

    public void setInput5(String input5) {
        this.input5 = input5;
    }

    public String getSelect6() {
        return select6;
    }

    public void setSelect6(String select6) {
        this.select6 = select6;
    }

    public List<String> getList6() {
        return list6;
    }

    public void setList6(List<String> list6) {
        this.list6 = list6;
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

    public List<String> getList8() {
        return list8;
    }

    public void setList8(List<String> list8) {
        this.list8 = list8;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public List<String> getPhaseList() {
        return phaseList;
    }

    public void setPhaseList(List<String> phaseList) {
        this.phaseList = phaseList;
    }

    public String getPhaseSelect() {
        return phaseSelect;
    }

    public void setPhaseSelect(String phaseSelect) {
        this.phaseSelect = phaseSelect;
    }

    @Override
    public String toString() {
        return "OperationQuery{" +
                "select1='" + select1 + '\'' +
                ", input1='" + input1 + '\'' +
                ", select2='" + select2 + '\'' +
                ", input2='" + input2 + '\'' +
                ", select3='" + select3 + '\'' +
                ", input3='" + input3 + '\'' +
                ", select4='" + select4 + '\'' +
                ", list4=" + list4 +
                ", select5='" + select5 + '\'' +
                ", input5='" + input5 + '\'' +
                ", select6='" + select6 + '\'' +
                ", list6=" + list6 +
                ", select7='" + select7 + '\'' +
                ", input7='" + input7 + '\'' +
                ", select8='" + select8 + '\'' +
                ", list8=" + list8 +
                ", phaseSelect='" + phaseSelect + '\'' +
                ", phaseList=" + phaseList +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", codes=" + codes +
                '}';
    }
}
