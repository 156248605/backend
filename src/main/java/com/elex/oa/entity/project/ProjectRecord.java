package com.elex.oa.entity.project;

import java.util.List;
import java.util.Map;

public class ProjectRecord {
    private String projectCode; //项目编号
    private String updateBy; //变更人
    private String updateTime; //变更时间
    private List<Map<String,String>> record; //变更内容

    public ProjectRecord() {
    }

    public ProjectRecord(String projectCode, String updateBy, String updateTime, List<Map<String, String>> record) {
        this.projectCode = projectCode;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.record = record;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Map<String, String>> getRecord() {
        return record;
    }

    public void setRecord(List<Map<String, String>> record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "ProjectRecord{" +
                "projectCode='" + projectCode + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", record=" + record +
                '}';
    }
}
