package com.elex.oa.entity.eqpt;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Partner {

    // 唯一Id
    private int onlyIdP;
    // 业务伙伴类型
    @Excel(name = "业务类型")
    private String pnCategory;
    // 公司
    @Excel(name = "公司名称")
    private String company;
    // 相关项目
    @Excel(name = "项目名称")
    private String pjName;
    // 联系人职务
    private String job;
    // 联系人编号
    private String linkId;
    // 联系人QQ
    private String qqNum;
    // 联系人姓名
    @Excel(name = "联系人姓名")
    private String name;
    // 联系人电话
    private String tel;
    // 联系人邮箱
    private String email;
    // 联系人微信
    private String wechatNum;
    // 联系地址
    private String address;
    // 查询条件
    private String pnCateC;
    private String companyC;
    private String pjNameC;
    private String nameC;

    public Partner() {
    }

    public Partner(int onlyIdP, String pnCategory, String company, String pjName, String job, String linkId, String qqNum, String name, String tel, String email, String wechatNum, String address, String pnCateC, String companyC, String pjNameC, String nameC) {
        this.onlyIdP = onlyIdP;
        this.pnCategory = pnCategory;
        this.company = company;
        this.pjName = pjName;
        this.job = job;
        this.linkId = linkId;
        this.qqNum = qqNum;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.wechatNum = wechatNum;
        this.address = address;
        this.pnCateC = pnCateC;
        this.companyC = companyC;
        this.pjNameC = pjNameC;
        this.nameC = nameC;
    }

    public String getPnCategory() {
        return pnCategory;
    }

    public void setPnCategory(String pnCategory) {
        this.pnCategory = pnCategory;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPjName() {
        return pjName;
    }

    public void setPjName(String pjName) {
        this.pjName = pjName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOnlyIdP() {
        return onlyIdP;
    }

    public void setOnlyIdP(int onlyIdP) {
        this.onlyIdP = onlyIdP;
    }

    public String getPnCateC() {
        return pnCateC;
    }

    public void setPnCateC(String pnCateC) {
        this.pnCateC = pnCateC;
    }

    public String getCompanyC() {
        return companyC;
    }

    public void setCompanyC(String companyC) {
        this.companyC = companyC;
    }

    public String getPjNameC() {
        return pjNameC;
    }

    public void setPjNameC(String pjNameC) {
        this.pjNameC = pjNameC;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "onlyIdP=" + onlyIdP +
                ", pnCategory='" + pnCategory + '\'' +
                ", company='" + company + '\'' +
                ", pjName='" + pjName + '\'' +
                ", job='" + job + '\'' +
                ", linkId='" + linkId + '\'' +
                ", qqNum='" + qqNum + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", wechatNum='" + wechatNum + '\'' +
                ", address='" + address + '\'' +
                ", pnCateC='" + pnCateC + '\'' +
                ", companyC='" + companyC + '\'' +
                ", pjNameC='" + pjNameC + '\'' +
                ", nameC='" + nameC + '\'' +
                '}';
    }
}
