package com.elex.oa.entity.project;

import org.springframework.data.annotation.Id;

public class ProjectCode {
    @Id
    private String id; //编号
    private String projectCode;

    public ProjectCode() {
    }

    public ProjectCode(String id, String projectCode) {
        this.id = id;
        this.projectCode = projectCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Override
    public String toString() {
        return "ProjectCode{" +
                "id='" + id + '\'' +
                ", projectCode='" + projectCode + '\'' +
                '}';
    }
}
