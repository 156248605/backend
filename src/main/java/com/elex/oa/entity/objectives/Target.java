package com.elex.oa.entity.objectives;

public class Target {
    private int id;
    private String annual; //年度
    private String department; //部门
    private String goal; //总目标
    private String cumulative; //累计完成
    private String ratio; //完成率

    public Target() {
    }

    public Target(int id, String annual, String department, String goal, String cumulative, String ratio) {
        this.id = id;
        this.annual = annual;
        this.department = department;
        this.goal = goal;
        this.cumulative = cumulative;
        this.ratio = ratio;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getCumulative() {
        return cumulative;
    }

    public void setCumulative(String cumulative) {
        this.cumulative = cumulative;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "Target{" +
                "id=" + id +
                ", annual='" + annual + '\'' +
                ", department='" + department + '\'' +
                ", goal='" + goal + '\'' +
                ", cumulative='" + cumulative + '\'' +
                ", ratio='" + ratio + '\'' +
                '}';
    }
}
