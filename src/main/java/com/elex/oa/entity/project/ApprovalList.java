package com.elex.oa.entity.project;

public class ApprovalList {
    //审批清单
    private int id;
    private String projectCode; //项目编号
    private String projectName; //项目名称
    private String writeDate; //申请日期
    private String passDate; //审批通过日期
    private String projectSource; //项目来源
    private String projectType; //项目类型
    private String inDepartment; //立项部门
    private String proposer; //申请人
    private String department1;
    private String businessManager; //商务经理
    private String department2;
    private String projectManager; //项目经理
    private String generalSituation; //项目概况
    private String departmentManager; //部门经理
    private String leaderShip; //分管领导
    private String reviewSign; //评审会签
    private String generalManager; //总经理
    private String distribution; //申请人分发
    private String mileStone; //里程碑计划存在标志（y或n）
    private String income; // 收入合同标志（'y'或'n'）
    private String materials; // 物品消耗标志（'y'或'n'）
    private String human; //人力成本标志（'y'或'n'）
    private String expense; //费用报销标志（'y'或'n'）

    public ApprovalList() {
    }

    public ApprovalList(int id, String projectCode, String projectName, String writeDate, String passDate, String projectSource, String projectType, String inDepartment, String proposer, String department1, String businessManager, String department2, String projectManager, String generalSituation, String departmentManager, String leaderShip, String reviewSign, String generalManager, String distribution, String mileStone, String income, String materials, String human, String expense) {
        this.id = id;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.writeDate = writeDate;
        this.passDate = passDate;
        this.projectSource = projectSource;
        this.projectType = projectType;
        this.inDepartment = inDepartment;
        this.proposer = proposer;
        this.department1 = department1;
        this.businessManager = businessManager;
        this.department2 = department2;
        this.projectManager = projectManager;
        this.generalSituation = generalSituation;
        this.departmentManager = departmentManager;
        this.leaderShip = leaderShip;
        this.reviewSign = reviewSign;
        this.generalManager = generalManager;
        this.distribution = distribution;
        this.mileStone = mileStone;
        this.income = income;
        this.materials = materials;
        this.human = human;
        this.expense = expense;
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

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getPassDate() {
        return passDate;
    }

    public void setPassDate(String passDate) {
        this.passDate = passDate;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getInDepartment() {
        return inDepartment;
    }

    public void setInDepartment(String inDepartment) {
        this.inDepartment = inDepartment;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getDepartment1() {
        return department1;
    }

    public void setDepartment1(String department1) {
        this.department1 = department1;
    }

    public String getBusinessManager() {
        return businessManager;
    }

    public void setBusinessManager(String businessManager) {
        this.businessManager = businessManager;
    }

    public String getDepartment2() {
        return department2;
    }

    public void setDepartment2(String department2) {
        this.department2 = department2;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getGeneralSituation() {
        return generalSituation;
    }

    public void setGeneralSituation(String generalSituation) {
        this.generalSituation = generalSituation;
    }

    public String getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }

    public String getLeaderShip() {
        return leaderShip;
    }

    public void setLeaderShip(String leaderShip) {
        this.leaderShip = leaderShip;
    }

    public String getReviewSign() {
        return reviewSign;
    }

    public void setReviewSign(String reviewSign) {
        this.reviewSign = reviewSign;
    }

    public String getGeneralManager() {
        return generalManager;
    }

    public void setGeneralManager(String generalManager) {
        this.generalManager = generalManager;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getMileStone() {
        return mileStone;
    }

    public void setMileStone(String mileStone) {
        this.mileStone = mileStone;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getHuman() {
        return human;
    }

    public void setHuman(String human) {
        this.human = human;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    @Override
    public String toString() {
        return "ApprovalList{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", writeDate='" + writeDate + '\'' +
                ", passDate='" + passDate + '\'' +
                ", projectSource='" + projectSource + '\'' +
                ", projectType='" + projectType + '\'' +
                ", inDepartment='" + inDepartment + '\'' +
                ", proposer='" + proposer + '\'' +
                ", department1='" + department1 + '\'' +
                ", businessManager='" + businessManager + '\'' +
                ", department2='" + department2 + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", generalSituation='" + generalSituation + '\'' +
                ", departmentManager='" + departmentManager + '\'' +
                ", leaderShip='" + leaderShip + '\'' +
                ", reviewSign='" + reviewSign + '\'' +
                ", generalManager='" + generalManager + '\'' +
                ", distribution='" + distribution + '\'' +
                ", mileStone='" + mileStone + '\'' +
                ", income='" + income + '\'' +
                ", materials='" + materials + '\'' +
                ", human='" + human + '\'' +
                ", expense='" + expense + '\'' +
                '}';
    }
}
