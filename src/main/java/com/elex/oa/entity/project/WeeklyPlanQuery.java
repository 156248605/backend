package com.elex.oa.entity.project;

import java.util.List;

public class WeeklyPlanQuery {
    //周计划查询条件封装类
    private int type;
    private String name;
    private String approval; //审批判断
    private String select1; //项目编号
    private String input1;
    private String select2; //项目名称
    private String input2;
    private String select3; // 立项部门的
    private String input3;
    private String date4; //立项时间
    private String date4a;
    private String date4b;
    private String select5; //商务经理
    private String input5;
    private String select6; //项目来源的
    private List<String> list6;
    private String select7; //商务经理的
    private String input7;
    private String select8; //项目类型的
    private List<String> list8;
    private String date9; //起始时间
    private String date9a;
    private String date9b;
    private String date10; //截止时间
    private String date10a;
    private String date10b;

    public WeeklyPlanQuery() {
    }

    public WeeklyPlanQuery(int type, String name, String approval, String select1, String input1, String select2, String input2, String select3, String input3, String date4, String date4a, String date4b, String select5, String input5, String select6, List<String> list6, String select7, String input7, String select8, List<String> list8, String date9, String date9a, String date9b, String date10, String date10a, String date10b) {
        this.type = type;
        this.name = name;
        this.approval = approval;
        this.select1 = select1;
        this.input1 = input1;
        this.select2 = select2;
        this.input2 = input2;
        this.select3 = select3;
        this.input3 = input3;
        this.date4 = date4;
        this.date4a = date4a;
        this.date4b = date4b;
        this.select5 = select5;
        this.input5 = input5;
        this.select6 = select6;
        this.list6 = list6;
        this.select7 = select7;
        this.input7 = input7;
        this.select8 = select8;
        this.list8 = list8;
        this.date9 = date9;
        this.date9a = date9a;
        this.date9b = date9b;
        this.date10 = date10;
        this.date10a = date10a;
        this.date10b = date10b;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
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

    public String getDate4() {
        return date4;
    }

    public void setDate4(String date4) {
        this.date4 = date4;
    }

    public String getDate4a() {
        return date4a;
    }

    public void setDate4a(String date4a) {
        this.date4a = date4a;
    }

    public String getDate4b() {
        return date4b;
    }

    public void setDate4b(String date4b) {
        this.date4b = date4b;
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

    public String getDate9() {
        return date9;
    }

    public void setDate9(String date9) {
        this.date9 = date9;
    }

    public String getDate9a() {
        return date9a;
    }

    public void setDate9a(String date9a) {
        this.date9a = date9a;
    }

    public String getDate9b() {
        return date9b;
    }

    public void setDate9b(String date9b) {
        this.date9b = date9b;
    }

    public String getDate10() {
        return date10;
    }

    public void setDate10(String date10) {
        this.date10 = date10;
    }

    public String getDate10a() {
        return date10a;
    }

    public void setDate10a(String date10a) {
        this.date10a = date10a;
    }

    public String getDate10b() {
        return date10b;
    }

    public void setDate10b(String date10b) {
        this.date10b = date10b;
    }

    @Override
    public String toString() {
        return "WeeklyPlanQuery{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", approval='" + approval + '\'' +
                ", select1='" + select1 + '\'' +
                ", input1='" + input1 + '\'' +
                ", select2='" + select2 + '\'' +
                ", input2='" + input2 + '\'' +
                ", select3='" + select3 + '\'' +
                ", input3='" + input3 + '\'' +
                ", date4='" + date4 + '\'' +
                ", date4a='" + date4a + '\'' +
                ", date4b='" + date4b + '\'' +
                ", select5='" + select5 + '\'' +
                ", input5='" + input5 + '\'' +
                ", select6='" + select6 + '\'' +
                ", list6=" + list6 +
                ", select7='" + select7 + '\'' +
                ", input7='" + input7 + '\'' +
                ", select8='" + select8 + '\'' +
                ", list8=" + list8 +
                ", date9='" + date9 + '\'' +
                ", date9a='" + date9a + '\'' +
                ", date9b='" + date9b + '\'' +
                ", date10='" + date10 + '\'' +
                ", date10a='" + date10a + '\'' +
                ", date10b='" + date10b + '\'' +
                '}';
    }
}
