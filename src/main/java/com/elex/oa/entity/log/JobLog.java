package com.elex.oa.entity.log;


/*工作日志*/
public class JobLog {
    private String id; //编号
    private String code;
    private String department; //填报部门
    private String dateLine; //填报日期
    private String projectCode; //项目编号
    private String projectName; //项目名称
    private String projectManager; //项目经理
    private String startDate; //开始时间
    private String endDate; //结束时间
    private String content; //工作内容

    public JobLog() {
    }

    public JobLog(String id, String code, String department, String dateLine, String projectCode, String projectName, String projectManager, String startDate, String endDate, String content) {
        this.id = id;
        this.code = code;
        this.department = department;
        this.dateLine = dateLine;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.projectManager = projectManager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDateLine() {
        return dateLine;
    }

    public void setDateLine(String dateLine) {
        this.dateLine = dateLine;
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

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "JobLog{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", department='" + department + '\'' +
                ", dateLine='" + dateLine + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
