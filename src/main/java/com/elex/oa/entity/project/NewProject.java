package com.elex.oa.entity.project;

public class NewProject {
    //新建项目

    private int id;
    private String projectCode; //项目编号
    private String projectName; //项目名称
    private String inDepartment; //所在部门
    private String writeDate; //填写日期
    private String department1; // 项目经理所在部门
    private String projectManager; //项目经理
    private String projectSource; //项目来源
    private String department2; //商务经理所在部门
    private String businessManager; //商务经理
    private String projectType; //项目类型
    private String generalSituation; //项目概况
    private String proposer; //申请人
    /* private String departmentManager; //部门经理
    private String leaderShip; //分管领导
    private String reviewSign; //评审会签
    private String generalManager; //总经理
    private String distribution; //申请人分发*/

    public NewProject() {
    }

    public NewProject(int id, String projectCode, String projectName, String inDepartment, String writeDate, String department1, String projectManager, String projectSource, String department2, String businessManager, String projectType, String generalSituation, String proposer) {
        this.id = id;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.inDepartment = inDepartment;
        this.writeDate = writeDate;
        this.department1 = department1;
        this.projectManager = projectManager;
        this.projectSource = projectSource;
        this.department2 = department2;
        this.businessManager = businessManager;
        this.projectType = projectType;
        this.generalSituation = generalSituation;
        this.proposer = proposer;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getInDepartment() {
        return inDepartment;
    }

    public void setInDepartment(String inDepartment) {
        this.inDepartment = inDepartment;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getDepartment1() {
        return department1;
    }

    public void setDepartment1(String department1) {
        this.department1 = department1;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    public String getDepartment2() {
        return department2;
    }

    public void setDepartment2(String department2) {
        this.department2 = department2;
    }

    public String getBusinessManager() {
        return businessManager;
    }

    public void setBusinessManager(String businessManager) {
        this.businessManager = businessManager;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getGeneralSituation() {
        return generalSituation;
    }

    public void setGeneralSituation(String generalSituation) {
        this.generalSituation = generalSituation;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    @Override
    public String toString() {
        return "NewProject{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", inDepartment='" + inDepartment + '\'' +
                ", writeDate='" + writeDate + '\'' +
                ", department1='" + department1 + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", projectSource='" + projectSource + '\'' +
                ", department2='" + department2 + '\'' +
                ", businessManager='" + businessManager + '\'' +
                ", projectType='" + projectType + '\'' +
                ", generalSituation='" + generalSituation + '\'' +
                ", proposer='" + proposer + '\'' +
                '}';
    }
}
