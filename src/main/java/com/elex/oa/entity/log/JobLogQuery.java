package com.elex.oa.entity.log;

public class JobLogQuery {
    private String time3; //填报日期
    private String time3a;
    private String time3b;
    private String select4; //项目编号
    private String input4;
    private String select5; //项目名称
    private String input5;
    private String select6; //项目经理
    private String input6;
    private String userName; //当前登录人

    public JobLogQuery() {
    }

    public JobLogQuery(String time3, String time3a, String time3b, String select4, String input4, String select5, String input5, String select6, String input6, String userName) {
        this.time3 = time3;
        this.time3a = time3a;
        this.time3b = time3b;
        this.select4 = select4;
        this.input4 = input4;
        this.select5 = select5;
        this.input5 = input5;
        this.select6 = select6;
        this.input6 = input6;
        this.userName = userName;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getTime3a() {
        return time3a;
    }

    public void setTime3a(String time3a) {
        this.time3a = time3a;
    }

    public String getTime3b() {
        return time3b;
    }

    public void setTime3b(String time3b) {
        this.time3b = time3b;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "JobLogQuery{" +
                "time3='" + time3 + '\'' +
                ", time3a='" + time3a + '\'' +
                ", time3b='" + time3b + '\'' +
                ", select4='" + select4 + '\'' +
                ", input4='" + input4 + '\'' +
                ", select5='" + select5 + '\'' +
                ", input5='" + input5 + '\'' +
                ", select6='" + select6 + '\'' +
                ", input6='" + input6 + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
