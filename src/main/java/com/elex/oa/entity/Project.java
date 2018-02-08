package com.elex.oa.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name="project")
public class Project {
    @Id
    private Integer id;
	//项目编号
    private String code;
	//项目名称
	private String name;
	//立项时间
	private Date approvalTime;
	//立项时间(字符串形式)
	private String approvalTimeStr;
	//项目来源
	private String projectSource;
	//项目类型
	private Integer projectTypeId;
	//项目类型名称
	private String projectTypeName;
	//立项部门id
	private Integer approvalDepartmentId;
	//立项部门
	private String approvalDepartment;
	//商务经理
	private String businessManager;
	//项目经理
	private String projectManager;
	//项目状态
	private String status;
	//项目概况
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public String getApprovalTimeStr() {
		return approvalTimeStr;
	}

	public void setApprovalTimeStr(String approvalTimeStr) {
		this.approvalTimeStr = approvalTimeStr;
	}

	public String getProjectSource() {
		return projectSource;
	}

	public void setProjectSource(String projectSource) {
		this.projectSource = projectSource;
	}

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getProjectTypeName() {
		return projectTypeName;
	}

	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}

	public Integer getApprovalDepartmentId() {
		return approvalDepartmentId;
	}

	public void setApprovalDepartmentId(Integer approvalDepartmentId) {
		this.approvalDepartmentId = approvalDepartmentId;
	}

	public String getApprovalDepartment() {
		return approvalDepartment;
	}

	public void setApprovalDepartment(String approvalDepartment) {
		this.approvalDepartment = approvalDepartment;
	}

	public String getBusinessManager() {
		return businessManager;
	}

	public void setBusinessManager(String businessManager) {
		this.businessManager = businessManager;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}




