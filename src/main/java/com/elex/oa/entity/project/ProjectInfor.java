package com.elex.oa.entity.project;

public class ProjectInfor {
    private int id;
    private String projectCode; //项目编号
    private String projectName; //项目名称
    private String inDepartment; //立项部门
    private String projectStatus; //项目状态
    private String deptManager; //立项部门的部门经理
    private String proposer; //申请人
    private String writeDate; //立项日期
    private String businessManager; //商务经理
    private String businessManagerId; //商务经理编号
    private String businessManagerCode; //商务经理编号，用于查询
    private String projectSource; //项目来源
    private String projectManager; //项目经理
    private String projectManagerId; //项目经理编号
    private String projectManagerCode; //项目经理编号，用于查询
    private String projectType; //项目类型
    private String projectPhase; //项目阶段
    private String startTime; //项目开始时间
    private String endTime; //项目结束时间
    private String capitalization; //资本化
    private String classified; //涉密
    private String generalSituation;// 项目概况
    private String projectMembers; //项目成员
    private String projectMemberId; //项目成员id
    private String projectMemberCode; //项目成语id,用于查询

    public String getRelatedMmemberCode() {
        return relatedMmemberCode;
    }

    public void setRelatedMmemberCode(String relatedMmemberCode) {
        this.relatedMmemberCode = relatedMmemberCode;
    }

    private String relatedMembers; //相关人员
    private String relatedMemberId; //相关人员id
    private String relatedMemberCode; //相关人员id,用于查询
    private String partyName; //甲方名称
    private String partyAddress; //甲方地址
    private String partyPhone; //甲方电话
    private String partyFax; //甲方传真
    private String headName; //负责人姓名
    private String headPosition; //负责人职务
    private String headMobile; //负责人手机
    private String headEmail; //负责人邮件
    private String contactName; //联系人姓名
    private String contactPosition; //联系人职务
    private String contactMobile; //联系人手机
    private String contactEmail; //联系人邮件
    private String departmentManager; //项目经理的部门经理
    private String weeklyReport;//本周日报
    private String nextPlan;//下周计划
    private String weeklyReportStart;//周计划开始时间
    private String weeklyReportEnd;//周计划结束时间
    private String opportunityCode;//商机转项目编号
    private String money;   // 项目金额
    private String relatedMmemberCode;
    public Integer getAmendId() {
        return amendId;
    }

    public void setAmendId(Integer amendId) {
        this.amendId = amendId;
    }
    private Integer amendId;

    public ProjectInfor() {
    }


//    public ProjectInfor(int id, String projectCode, String projectName, String inDepartment, String projectStatus, String deptManager, String proposer, String writeDate, String businessManager, String businessManagerId, String businessManagerCode, String projectSource, String projectManager, String projectManagerId, String projectManagerCode, String projectType, String projectPhase, String startTime, String endTime, String capitalization, String classified, String generalSituation, String projectMembers, String projectMemberId, String projectMemberCode, String relatedMembers, String relatedMemberId, String relatedMemberCode, String partyName, String partyAddress, String partyPhone, String partyFax, String headName, String headPosition, String headMobile, String headEmail, String contactName, String contactPosition, String contactMobile, String contactEmail, String departmentManager, String weeklyReport, String nextPlan, String weeklyReportStart, String weeklyReportEnd, String opportunityCode, Integer amendId) {
//        this.id = id;
//        this.projectCode = projectCode;
//        this.projectName = projectName;
//        this.inDepartment = inDepartment;
//        this.projectStatus = projectStatus;
//        this.deptManager = deptManager;
//        this.proposer = proposer;
//        this.writeDate = writeDate;
//        this.businessManager = businessManager;
//        this.businessManagerId = businessManagerId;
//        this.businessManagerCode = businessManagerCode;
//        this.projectSource = projectSource;
//        this.projectManager = projectManager;
//        this.projectManagerId = projectManagerId;
//        this.projectManagerCode = projectManagerCode;
//        this.projectType = projectType;
//        this.projectPhase = projectPhase;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.capitalization = capitalization;
//        this.classified = classified;
//        this.generalSituation = generalSituation;
//        this.projectMembers = projectMembers;
//        this.projectMemberId = projectMemberId;
//        this.projectMemberCode = projectMemberCode;
//        this.relatedMembers = relatedMembers;
//        this.relatedMemberId = relatedMemberId;
//        this.relatedMemberCode = relatedMemberCode;
//        this.partyName = partyName;
//        this.partyAddress = partyAddress;
//        this.partyPhone = partyPhone;
//        this.partyFax = partyFax;
//        this.headName = headName;
//        this.headPosition = headPosition;
//        this.headMobile = headMobile;
//        this.headEmail = headEmail;
//        this.contactName = contactName;
//        this.contactPosition = contactPosition;
//        this.contactMobile = contactMobile;
//        this.contactEmail = contactEmail;
//        this.departmentManager = departmentManager;
//        this.weeklyReport = weeklyReport;
//        this.nextPlan = nextPlan;
//        this.weeklyReportStart = weeklyReportStart;
//        this.weeklyReportEnd = weeklyReportEnd;
//        this.opportunityCode = opportunityCode;
//        this.amendId = amendId;
//    }

    public ProjectInfor(int id, String projectCode, String projectName, String inDepartment, String projectStatus, String deptManager, String proposer, String writeDate, String businessManager, String businessManagerId, String businessManagerCode, String projectSource, String projectManager, String projectManagerId, String projectManagerCode, String projectType, String projectPhase, String startTime, String endTime, String capitalization, String classified, String generalSituation, String projectMembers, String projectMemberId, String projectMemberCode, String relatedMembers, String relatedMemberId, String relatedMemberCode, String partyName, String partyAddress, String partyPhone, String partyFax, String headName, String headPosition, String headMobile, String headEmail, String contactName, String contactPosition, String contactMobile, String contactEmail, String departmentManager, String weeklyReport, String nextPlan, String weeklyReportStart, String weeklyReportEnd, String opportunityCode, Integer amendId, String money) {
        this.id = id;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.inDepartment = inDepartment;
        this.projectStatus = projectStatus;
        this.deptManager = deptManager;
        this.proposer = proposer;
        this.writeDate = writeDate;
        this.businessManager = businessManager;
        this.businessManagerId = businessManagerId;
        this.businessManagerCode = businessManagerCode;
        this.projectSource = projectSource;
        this.projectManager = projectManager;
        this.projectManagerId = projectManagerId;
        this.projectManagerCode = projectManagerCode;
        this.projectType = projectType;
        this.projectPhase = projectPhase;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capitalization = capitalization;
        this.classified = classified;
        this.generalSituation = generalSituation;
        this.projectMembers = projectMembers;
        this.projectMemberId = projectMemberId;
        this.projectMemberCode = projectMemberCode;
        this.relatedMembers = relatedMembers;
        this.relatedMemberId = relatedMemberId;
        this.relatedMemberCode = relatedMemberCode;
        this.partyName = partyName;
        this.partyAddress = partyAddress;
        this.partyPhone = partyPhone;
        this.partyFax = partyFax;
        this.headName = headName;
        this.headPosition = headPosition;
        this.headMobile = headMobile;
        this.headEmail = headEmail;
        this.contactName = contactName;
        this.contactPosition = contactPosition;
        this.contactMobile = contactMobile;
        this.contactEmail = contactEmail;
        this.departmentManager = departmentManager;
        this.weeklyReport = weeklyReport;
        this.nextPlan = nextPlan;
        this.weeklyReportStart = weeklyReportStart;
        this.weeklyReportEnd = weeklyReportEnd;
        this.opportunityCode = opportunityCode;
        this.amendId = amendId;

        this.money = money;
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

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
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

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
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

    public String getBusinessManagerCode() {
        return businessManagerCode;
    }

    public void setBusinessManagerCode(String businessManagerCode) {
        this.businessManagerCode = businessManagerCode;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
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

    public String getProjectManagerCode() {
        return projectManagerCode;
    }

    public void setProjectManagerCode(String projectManagerCode) {
        this.projectManagerCode = projectManagerCode;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectPhase() {
        return projectPhase;
    }

    public void setProjectPhase(String projectPhase) {
        this.projectPhase = projectPhase;
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

    public String getGeneralSituation() {
        return generalSituation;
    }

    public void setGeneralSituation(String generalSituation) {
        this.generalSituation = generalSituation;
    }

    public String getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(String projectMembers) {
        this.projectMembers = projectMembers;
    }

    public String getProjectMemberId() {
        return projectMemberId;
    }

    public void setProjectMemberId(String projectMemberId) {
        this.projectMemberId = projectMemberId;
    }

    public String getProjectMemberCode() {
        return projectMemberCode;
    }

    public void setProjectMemberCode(String projectMemberCode) {
        this.projectMemberCode = projectMemberCode;
    }

    public String getRelatedMembers() {
        return relatedMembers;
    }

    public void setRelatedMembers(String relatedMembers) {
        this.relatedMembers = relatedMembers;
    }

    public String getRelatedMemberId() {
        return relatedMemberId;
    }

    public void setRelatedMemberId(String relatedMemberId) {
        this.relatedMemberId = relatedMemberId;
    }

    public String getRelatedMemberCode() {
        return relatedMemberCode;
    }

    public void setRelatedMemberCode(String relatedMemberCode) {
        this.relatedMemberCode = relatedMemberCode;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyAddress() {
        return partyAddress;
    }

    public void setPartyAddress(String partyAddress) {
        this.partyAddress = partyAddress;
    }

    public String getPartyPhone() {
        return partyPhone;
    }

    public void setPartyPhone(String partyPhone) {
        this.partyPhone = partyPhone;
    }

    public String getPartyFax() {
        return partyFax;
    }

    public void setPartyFax(String partyFax) {
        this.partyFax = partyFax;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(String headPosition) {
        this.headPosition = headPosition;
    }

    public String getHeadMobile() {
        return headMobile;
    }

    public void setHeadMobile(String headMobile) {
        this.headMobile = headMobile;
    }

    public String getHeadEmail() {
        return headEmail;
    }

    public void setHeadEmail(String headEmail) {
        this.headEmail = headEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }

    public String getWeeklyReport() {
        return weeklyReport;
    }

    public void setWeeklyReport(String weeklyReport) {
        this.weeklyReport = weeklyReport;
    }

    public String getNextPlan() {
        return nextPlan;
    }

    public void setNextPlan(String nextPlan) {
        this.nextPlan = nextPlan;
    }

    public String getWeeklyReportStart() {
        return weeklyReportStart;
    }

    public void setWeeklyReportStart(String weeklyReportStart) {
        this.weeklyReportStart = weeklyReportStart;
    }

    public String getWeeklyReportEnd() {
        return weeklyReportEnd;
    }

    public void setWeeklyReportEnd(String weeklyReportEnd) {
        this.weeklyReportEnd = weeklyReportEnd;
    }

    public String getOpportunityCode() {
        return opportunityCode;
    }

    public void setOpportunityCode(String opportunityCode) {
        this.opportunityCode = opportunityCode;
    }

    public String getMoney(){ return  money; }

    public void setMoney(String money) { this.money = money; }

    @Override
    public String toString() {
        return "ProjectInfor{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", inDepartment='" + inDepartment + '\'' +
                ", projectStatus='" + projectStatus + '\'' +
                ", deptManager='" + deptManager + '\'' +
                ", proposer='" + proposer + '\'' +
                ", writeDate='" + writeDate + '\'' +
                ", businessManager='" + businessManager + '\'' +
                ", businessManagerId='" + businessManagerId + '\'' +
                ", businessManagerCode='" + businessManagerCode + '\'' +
                ", projectSource='" + projectSource + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", projectManagerId='" + projectManagerId + '\'' +
                ", projectManagerCode='" + projectManagerCode + '\'' +
                ", projectType='" + projectType + '\'' +
                ", projectPhase='" + projectPhase + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", capitalization='" + capitalization + '\'' +
                ", classified='" + classified + '\'' +
                ", generalSituation='" + generalSituation + '\'' +
                ", projectMembers='" + projectMembers + '\'' +
                ", projectMemberId='" + projectMemberId + '\'' +
                ", projectMemberCode='" + projectMemberCode + '\'' +
                ", relatedMembers='" + relatedMembers + '\'' +
                ", relatedMemberId='" + relatedMemberId + '\'' +
                ", relatedMemberCode='" + relatedMemberCode + '\'' +
                ", partyName='" + partyName + '\'' +
                ", partyAddress='" + partyAddress + '\'' +
                ", partyPhone='" + partyPhone + '\'' +
                ", partyFax='" + partyFax + '\'' +
                ", headName='" + headName + '\'' +
                ", headPosition='" + headPosition + '\'' +
                ", headMobile='" + headMobile + '\'' +
                ", headEmail='" + headEmail + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPosition='" + contactPosition + '\'' +
                ", contactMobile='" + contactMobile + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", departmentManager='" + departmentManager + '\'' +
                ", weeklyReport='" + weeklyReport + '\'' +
                ", nextPlan='" + nextPlan + '\'' +
                ", weeklyReportStart='" + weeklyReportStart + '\'' +
                ", money='" + money + '\'' +
                ", weeklyReportEnd='" + weeklyReportEnd + '\'' +
                ", opportunityCode='" + opportunityCode + '\'' +
                ", amendId=" + amendId +
                '}';
    }
}
