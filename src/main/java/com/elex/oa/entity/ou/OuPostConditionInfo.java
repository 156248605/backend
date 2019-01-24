package com.elex.oa.entity.ou;

import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\24 0024 15:07
 * @Version 1.0
 **/
public class OuPostConditionInfo {
    private String postcodeQueryMethod;//岗位编号（查询方式）
    private String postcodeValue;//岗位编号（模糊查询）
    private List<String> postcodeList;//岗位编号（多选查询）

    private String postnameQueryMethod;//岗位名称（查询方式）
    private String postnameValue;//岗位名称（模糊查询）
    private List<String> postnameList;//岗位名称（多选查询）

    private String postlevelQueryMethod;//岗级（查询方式）
    private String postlevelValue;//岗级（模糊查询）
    private List<String> postlevelList;//岗级（多选查询）

    private List<String> stateList;//岗位状态（多选查询）

    public OuPostConditionInfo() {
    }

    public String getPostcodeQueryMethod() {
        return postcodeQueryMethod;
    }

    public void setPostcodeQueryMethod(String postcodeQueryMethod) {
        this.postcodeQueryMethod = postcodeQueryMethod;
    }

    public String getPostcodeValue() {
        return postcodeValue;
    }

    public void setPostcodeValue(String postcodeValue) {
        this.postcodeValue = postcodeValue;
    }

    public List<String> getPostcodeList() {
        return postcodeList;
    }

    public void setPostcodeList(List<String> postcodeList) {
        this.postcodeList = postcodeList;
    }

    public String getPostnameQueryMethod() {
        return postnameQueryMethod;
    }

    public void setPostnameQueryMethod(String postnameQueryMethod) {
        this.postnameQueryMethod = postnameQueryMethod;
    }

    public String getPostnameValue() {
        return postnameValue;
    }

    public void setPostnameValue(String postnameValue) {
        this.postnameValue = postnameValue;
    }

    public List<String> getPostnameList() {
        return postnameList;
    }

    public void setPostnameList(List<String> postnameList) {
        this.postnameList = postnameList;
    }

    public String getPostlevelQueryMethod() {
        return postlevelQueryMethod;
    }

    public void setPostlevelQueryMethod(String postlevelQueryMethod) {
        this.postlevelQueryMethod = postlevelQueryMethod;
    }

    public String getPostlevelValue() {
        return postlevelValue;
    }

    public void setPostlevelValue(String postlevelValue) {
        this.postlevelValue = postlevelValue;
    }

    public List<String> getPostlevelList() {
        return postlevelList;
    }

    public void setPostlevelList(List<String> postlevelList) {
        this.postlevelList = postlevelList;
    }

    public List<String> getStateList() {
        return stateList;
    }

    public void setStateList(List<String> stateList) {
        this.stateList = stateList;
    }

    @Override
    public String toString() {
        return "OuPostConditionInfo{" +
                "postcodeQueryMethod='" + postcodeQueryMethod + '\'' +
                ", postcodeValue='" + postcodeValue + '\'' +
                ", postcodeList=" + postcodeList +
                ", postnameQueryMethod='" + postnameQueryMethod + '\'' +
                ", postnameValue='" + postnameValue + '\'' +
                ", postnameList=" + postnameList +
                ", postlevelQueryMethod='" + postlevelQueryMethod + '\'' +
                ", postlevelValue='" + postlevelValue + '\'' +
                ", postlevelList=" + postlevelList +
                ", stateList=" + stateList +
                '}';
    }
}