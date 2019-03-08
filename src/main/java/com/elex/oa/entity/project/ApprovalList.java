package com.elex.oa.entity.project;

public class ApprovalList {
    //审批清单
    private int id;
    private String projectCode; //项目编号
    private String projectName; //项目名称
    private String writeDate; //申请日期
    private String projectStatus; //项目状态
    private String projectSource; //项目来源
    private String projectType; //项目类型
    private String inDepartment; //立项部门
    private String deptManager; //申请人的部门经理
    private String proposer; //申请人
    private String startTime; //开始时间
    private String endTime; //结束时间
    private String businessManager; //商务经理
    private String businessManagerId; //商务经理编码
    private String projectPhase; //项目阶段
    private String capitalization; //资本化
    private String classified; //涉密
    private String relCode; //关联编码
    private String projectManager; //交付经理
    private String projectManagerId; //交付经理编码
    private String generalSituation; //项目概况
    private String departmentManager; //部门经理


    public ApprovalList() {
    }

    public ApprovalList(int id, String projectCode, String projectName, String writeDate, String projectStatus, String projectSource, String projectType, String inDepartment, String deptManager, String proposer, String startTime, String endTime, String businessManager, String businessManagerId, String projectPhase, String capitalization, String classified, String relCode, String projectManager, String projectManagerId, String generalSituation, String departmentManager) {
        this.id = id;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.writeDate = writeDate;
        this.projectStatus = projectStatus;
        this.projectSource = projectSource;
        this.projectType = projectType;
        this.inDepartment = inDepartment;
        this.deptManager = deptManager;
        this.proposer = proposer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.businessManager = businessManager;
        this.businessManagerId = businessManagerId;
        this.projectPhase = projectPhase;
        this.capitalization = capitalization;
        this.classified = classified;
        this.relCode = relCode;
        this.projectManager = projectManager;
        this.projectManagerId = projectManagerId;
        this.generalSituation = generalSituation;
        this.departmentManager = departmentManager;
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

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
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

    public String getDeptManager() {
        return deptManager;
    }

    public void setDeptManager(String deptManager) {
        this.deptManager = deptManager;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBusinessManager() {
        return businessManager;
    }

    public void setBusinessManager(String businessManager) {
        this.businessManager = businessManager;
    }

    public String getBusinessManagerId() {
        return businessManagerId;
    }

    public void setBusinessManagerId(String businessManagerId) {
        this.businessManagerId = businessManagerId;
    }

    public String getProjectPhase() {
        return projectPhase;
    }

    public void setProjectPhase(String projectPhase) {
        this.projectPhase = projectPhase;
    }

    public String getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(String capitalization) {
        this.capitalization = capitalization;
    }

    public String getClassified() {
        return classified;
    }

    public void setClassified(String classified) {
        this.classified = classified;
    }

    public String getRelCode() {
        return relCode;
    }

    public void setRelCode(String relCode) {
        this.relCode = relCode;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(String projectManagerId) {
        this.projectManagerId = projectManagerId;
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

    @Override
    public String toString() {
        return "ApprovalList{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", writeDate='" + writeDate + '\'' +
                ", projectStatus='" + projectStatus + '\'' +
                ", projectSource='" + projectSource + '\'' +
                ", projectType='" + projectType + '\'' +
                ", inDepartment='" + inDepartment + '\'' +
                ", deptManager='" + deptManager + '\'' +
                ", proposer='" + proposer + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", businessManager='" + businessManager + '\'' +
                ", businessManagerId='" + businessManagerId + '\'' +
                ", projectPhase='" + projectPhase + '\'' +
                ", capitalization='" + capitalization + '\'' +
                ", classified='" + classified + '\'' +
                ", relCode='" + relCode + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", projectManagerId='" + projectManagerId + '\'' +
                ", generalSituation='" + generalSituation + '\'' +
                ", departmentManager='" + departmentManager + '\'' +
                '}';
    }
}
