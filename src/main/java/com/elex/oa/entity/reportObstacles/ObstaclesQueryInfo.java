package com.elex.oa.entity.reportObstacles;

import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\9 0009 10:31
 * @Version 1.0
 **/
public class ObstaclesQueryInfo {
    private String usernameQueryMethod;//用户账号查询方式
    private String usernameValue;//账号值（模糊查询）
    private List<String> usernameList;//账号值（多选查询）

    private String createtimeStart;//新建时间查询头（格式：yyyy/MM/dd HH:mm:ss）
    private String createtimeEnd;//新建时间查询尾（格式：yyyy/MM/dd HH:mm:ss）

    private String operatingSystemQueryMehod;//系统环境查询方式
    private String operatingSystemValue;//系统环境值（模糊查询）
    private List<String> operatingSystemList;//系统环境值（多选查询）

    private String browserQueryMethod;//浏览器版本查询方式
    private String browserValue;//浏览器版本值（模糊查询）
    private List<String> browserList;//浏览器版本值（多选查询）

    private String versionCoreQueryMethod;//OAcore版本查询方式
    private String versionCoreValue;//OAcore版本值（模糊查询）
    private List<String> versionCoreList;//OAcore版本值（多选查询）

    private String versionBackendQueryMethod;//OAbackend版本查询方式
    private String versionBackendValue;//OAbackend版本值（模糊查询）
    private List<String> versionBackendList;//OAbackend版本值（多选查询）

    private String versionFrontQueryMethod;//OAfront版本查询方式
    private String versionFrontValue;//OAfront版本值（模糊查询）
    private List<String> versionFrontList;//OAfront版本值（多选查询）

    private String descriptionQueryMethod;//报障描述查询方式
    private String descriptionValue;//报障描述值（模糊查询）
    private List<String> descriptionList;//报障描述值（多选查询）

    private String locationDescriptionQueryMethod;//定位描述查询方式
    private String locationDescriptionValue;//定位描述值（模糊查询）
    private List<String> locationDescriptionList;//定位描述值（多选查询）

    private String processDescriptionQueryMethod;//处理描述查询方式
    private String processDescriptionValue;//处理描述值（模糊查询）
    private List<String> processDescriptionList;//处理描述值（多选查询）

    private List<String> stateList;//报障状态（多选查询）

    public ObstaclesQueryInfo() {
    }

    public String getUsernameQueryMethod() {
        return usernameQueryMethod;
    }

    public void setUsernameQueryMethod(String usernameQueryMethod) {
        this.usernameQueryMethod = usernameQueryMethod;
    }

    public String getUsernameValue() {
        return usernameValue;
    }

    public void setUsernameValue(String usernameValue) {
        this.usernameValue = usernameValue;
    }

    public List<String> getUsernameList() {
        return usernameList;
    }

    public void setUsernameList(List<String> usernameList) {
        this.usernameList = usernameList;
    }

    public String getCreatetimeStart() {
        return createtimeStart;
    }

    public void setCreatetimeStart(String createtimeStart) {
        this.createtimeStart = createtimeStart;
    }

    public String getCreatetimeEnd() {
        return createtimeEnd;
    }

    public void setCreatetimeEnd(String createtimeEnd) {
        this.createtimeEnd = createtimeEnd;
    }

    public String getOperatingSystemQueryMehod() {
        return operatingSystemQueryMehod;
    }

    public void setOperatingSystemQueryMehod(String operatingSystemQueryMehod) {
        this.operatingSystemQueryMehod = operatingSystemQueryMehod;
    }

    public String getOperatingSystemValue() {
        return operatingSystemValue;
    }

    public void setOperatingSystemValue(String operatingSystemValue) {
        this.operatingSystemValue = operatingSystemValue;
    }

    public List<String> getOperatingSystemList() {
        return operatingSystemList;
    }

    public void setOperatingSystemList(List<String> operatingSystemList) {
        this.operatingSystemList = operatingSystemList;
    }

    public String getBrowserQueryMethod() {
        return browserQueryMethod;
    }

    public void setBrowserQueryMethod(String browserQueryMethod) {
        this.browserQueryMethod = browserQueryMethod;
    }

    public String getBrowserValue() {
        return browserValue;
    }

    public void setBrowserValue(String browserValue) {
        this.browserValue = browserValue;
    }

    public List<String> getBrowserList() {
        return browserList;
    }

    public void setBrowserList(List<String> browserList) {
        this.browserList = browserList;
    }

    public String getVersionCoreQueryMethod() {
        return versionCoreQueryMethod;
    }

    public void setVersionCoreQueryMethod(String versionCoreQueryMethod) {
        this.versionCoreQueryMethod = versionCoreQueryMethod;
    }

    public String getVersionCoreValue() {
        return versionCoreValue;
    }

    public void setVersionCoreValue(String versionCoreValue) {
        this.versionCoreValue = versionCoreValue;
    }

    public List<String> getVersionCoreList() {
        return versionCoreList;
    }

    public void setVersionCoreList(List<String> versionCoreList) {
        this.versionCoreList = versionCoreList;
    }

    public String getVersionBackendQueryMethod() {
        return versionBackendQueryMethod;
    }

    public void setVersionBackendQueryMethod(String versionBackendQueryMethod) {
        this.versionBackendQueryMethod = versionBackendQueryMethod;
    }

    public String getVersionBackendValue() {
        return versionBackendValue;
    }

    public void setVersionBackendValue(String versionBackendValue) {
        this.versionBackendValue = versionBackendValue;
    }

    public List<String> getVersionBackendList() {
        return versionBackendList;
    }

    public void setVersionBackendList(List<String> versionBackendList) {
        this.versionBackendList = versionBackendList;
    }

    public String getVersionFrontQueryMethod() {
        return versionFrontQueryMethod;
    }

    public void setVersionFrontQueryMethod(String versionFrontQueryMethod) {
        this.versionFrontQueryMethod = versionFrontQueryMethod;
    }

    public String getVersionFrontValue() {
        return versionFrontValue;
    }

    public void setVersionFrontValue(String versionFrontValue) {
        this.versionFrontValue = versionFrontValue;
    }

    public List<String> getVersionFrontList() {
        return versionFrontList;
    }

    public void setVersionFrontList(List<String> versionFrontList) {
        this.versionFrontList = versionFrontList;
    }

    public String getDescriptionQueryMethod() {
        return descriptionQueryMethod;
    }

    public void setDescriptionQueryMethod(String descriptionQueryMethod) {
        this.descriptionQueryMethod = descriptionQueryMethod;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }

    public void setDescriptionValue(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public List<String> getDescriptionList() {
        return descriptionList;
    }

    public void setDescriptionList(List<String> descriptionList) {
        this.descriptionList = descriptionList;
    }

    public String getLocationDescriptionQueryMethod() {
        return locationDescriptionQueryMethod;
    }

    public void setLocationDescriptionQueryMethod(String locationDescriptionQueryMethod) {
        this.locationDescriptionQueryMethod = locationDescriptionQueryMethod;
    }

    public String getLocationDescriptionValue() {
        return locationDescriptionValue;
    }

    public void setLocationDescriptionValue(String locationDescriptionValue) {
        this.locationDescriptionValue = locationDescriptionValue;
    }

    public List<String> getLocationDescriptionList() {
        return locationDescriptionList;
    }

    public void setLocationDescriptionList(List<String> locationDescriptionList) {
        this.locationDescriptionList = locationDescriptionList;
    }

    public String getProcessDescriptionQueryMethod() {
        return processDescriptionQueryMethod;
    }

    public void setProcessDescriptionQueryMethod(String processDescriptionQueryMethod) {
        this.processDescriptionQueryMethod = processDescriptionQueryMethod;
    }

    public String getProcessDescriptionValue() {
        return processDescriptionValue;
    }

    public void setProcessDescriptionValue(String processDescriptionValue) {
        this.processDescriptionValue = processDescriptionValue;
    }

    public List<String> getProcessDescriptionList() {
        return processDescriptionList;
    }

    public void setProcessDescriptionList(List<String> processDescriptionList) {
        this.processDescriptionList = processDescriptionList;
    }

    public List<String> getStateList() {
        return stateList;
    }

    public void setStateList(List<String> stateList) {
        this.stateList = stateList;
    }

    @Override
    public String toString() {
        return "ObstaclesQueryInfo{" +
                "usernameQueryMethod='" + usernameQueryMethod + '\'' +
                ", usernameValue='" + usernameValue + '\'' +
                ", usernameList=" + usernameList +
                ", createtimeStart='" + createtimeStart + '\'' +
                ", createtimeEnd='" + createtimeEnd + '\'' +
                ", operatingSystemQueryMehod='" + operatingSystemQueryMehod + '\'' +
                ", operatingSystemValue='" + operatingSystemValue + '\'' +
                ", operatingSystemList=" + operatingSystemList +
                ", browserQueryMethod='" + browserQueryMethod + '\'' +
                ", browserValue='" + browserValue + '\'' +
                ", browserList=" + browserList +
                ", versionCoreQueryMethod='" + versionCoreQueryMethod + '\'' +
                ", versionCoreValue='" + versionCoreValue + '\'' +
                ", versionCoreList=" + versionCoreList +
                ", versionBackendQueryMethod='" + versionBackendQueryMethod + '\'' +
                ", versionBackendValue='" + versionBackendValue + '\'' +
                ", versionBackendList=" + versionBackendList +
                ", versionFrontQueryMethod='" + versionFrontQueryMethod + '\'' +
                ", versionFrontValue='" + versionFrontValue + '\'' +
                ", versionFrontList=" + versionFrontList +
                ", descriptionQueryMethod='" + descriptionQueryMethod + '\'' +
                ", descriptionValue='" + descriptionValue + '\'' +
                ", descriptionList=" + descriptionList +
                ", locationDescriptionQueryMethod='" + locationDescriptionQueryMethod + '\'' +
                ", locationDescriptionValue='" + locationDescriptionValue + '\'' +
                ", locationDescriptionList=" + locationDescriptionList +
                ", processDescriptionQueryMethod='" + processDescriptionQueryMethod + '\'' +
                ", processDescriptionValue='" + processDescriptionValue + '\'' +
                ", processDescriptionList=" + processDescriptionList +
                ", stateList=" + stateList +
                '}';
    }
}