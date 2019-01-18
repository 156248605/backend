package com.elex.oa.entity.reportObstacles;

import com.elex.oa.entity.business.BusinessAttachment;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\4 0004 11:47
 * @Version 1.0
 **/
@Table(name = "tb_reportobstacles_info")
public class ObstaclesInfo {
    @Id
    private String id;//主键
    private String username;//登录ID
    private String createtime;//创建时间（格式：yyyy/MM/dd HH:mm:ss）
    private String operatingSystem;//操作系统版本
    private String browser;//浏览器版本
    private String versionCore;//OAcore版本
    private String versionBackend;//OAbackend版本
    private String versionFront;//OAfront版本
    private String description;//描述
    private String state;//报障显示状态
    private String location_description;//报障定位描述
    private String process_description;//报障处理描述
    @Transient
    private List<BusinessAttachment> attachmentList = new ArrayList<>();//附件

    public ObstaclesInfo() {
    }

    public String getLocation_description() {
        return location_description;
    }

    public void setLocation_description(String location_description) {
        this.location_description = location_description;
    }

    public String getProcess_description() {
        return process_description;
    }

    public void setProcess_description(String process_description) {
        this.process_description = process_description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getVersionCore() {
        return versionCore;
    }

    public void setVersionCore(String versionCore) {
        this.versionCore = versionCore;
    }

    public String getVersionBackend() {
        return versionBackend;
    }

    public void setVersionBackend(String versionBackend) {
        this.versionBackend = versionBackend;
    }

    public String getVersionFront() {
        return versionFront;
    }

    public void setVersionFront(String versionFront) {
        this.versionFront = versionFront;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BusinessAttachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<BusinessAttachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ObstaclesInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", createtime='" + createtime + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", browser='" + browser + '\'' +
                ", versionCore='" + versionCore + '\'' +
                ", versionBackend='" + versionBackend + '\'' +
                ", versionFront='" + versionFront + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}