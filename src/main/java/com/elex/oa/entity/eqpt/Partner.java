package com.elex.oa.entity.eqpt;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Partner {

    // 唯一Id
    private int onlyIdP;
    // 业务伙伴类型
    @Excel(name = "业务伙伴类别")
    private String pnCategory;
    // 业务伙伴编号
    @Excel(name = "业务伙伴编号")
    private String pnId;
    // 业务伙伴名称
    @Excel(name = "业务伙伴名称")
    private String company;
    // 法人代表
    @Excel(name = "法人代表")
    private String corp;
    // 联系地址
    @Excel(name = "联系地址")
    private String comAddr;
    // 公司电话
    @Excel(name = "公司电话")
    private String comTel;
    // 注册资本
    @Excel(name = "注册资本")
    private String capital;
    // 员工人数
    @Excel(name = "员工人数")
    private String staffNum;
    // 年销售额
    @Excel(name = "年销售额")
    private String sales;
    // 公司简介
    @Excel(name = "公司简介")
    private String brief;
    // 行业
    @Excel(name = "行业")
    private String industry;
    // 区域
    @Excel(name = "区域")
    private String area;
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
    private String nameC;
    private String pnIdC;
    private String corpC;
    private String comAddrC;
    private String comTelC;
    private String capitalC;
    private String staffNumC;
    private String salesC;
    private String briefC;
    private String industryC;
    private String areaC;

    public Partner() {
    }

    public Partner(int onlyIdP, String pnCategory, String pnId, String company, String corp, String comAddr, String comTel, String capital, String staffNum, String sales, String brief, String industry, String area, String job, String linkId, String qqNum, String name, String tel, String email, String wechatNum, String address, String pnCateC, String companyC, String pjNameC, String nameC, String pnIdC, String corpC, String comAddrC, String comTelC, String capitalC, String staffNumC, String salesC, String briefC, String industryC, String areaC) {
        this.onlyIdP = onlyIdP;
        this.pnCategory = pnCategory;
        this.pnId = pnId;
        this.company = company;
        this.corp = corp;
        this.comAddr = comAddr;
        this.comTel = comTel;
        this.capital = capital;
        this.staffNum = staffNum;
        this.sales = sales;
        this.brief = brief;
        this.industry = industry;
        this.area = area;
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
        this.nameC = nameC;
        this.pnIdC = pnIdC;
        this.corpC = corpC;
        this.comAddrC = comAddrC;
        this.comTelC = comTelC;
        this.capitalC = capitalC;
        this.staffNumC = staffNumC;
        this.salesC = salesC;
        this.briefC = briefC;
        this.industryC = industryC;
        this.areaC = areaC;
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

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public String getPnId() {
        return pnId;
    }

    public void setPnId(String pnId) {
        this.pnId = pnId;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getComAddr() {
        return comAddr;
    }

    public void setComAddr(String comAddr) {
        this.comAddr = comAddr;
    }

    public String getComTel() {
        return comTel;
    }

    public void setComTel(String comTel) {
        this.comTel = comTel;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPnIdC() {
        return pnIdC;
    }

    public void setPnIdC(String pnIdC) {
        this.pnIdC = pnIdC;
    }

    public String getCorpC() {
        return corpC;
    }

    public void setCorpC(String corpC) {
        this.corpC = corpC;
    }

    public String getComAddrC() {
        return comAddrC;
    }

    public void setComAddrC(String comAddrC) {
        this.comAddrC = comAddrC;
    }

    public String getComTelC() {
        return comTelC;
    }

    public void setComTelC(String comTelC) {
        this.comTelC = comTelC;
    }

    public String getCapitalC() {
        return capitalC;
    }

    public void setCapitalC(String capitalC) {
        this.capitalC = capitalC;
    }

    public String getStaffNumC() {
        return staffNumC;
    }

    public void setStaffNumC(String staffNumC) {
        this.staffNumC = staffNumC;
    }

    public String getSalesC() {
        return salesC;
    }

    public void setSalesC(String salesC) {
        this.salesC = salesC;
    }

    public String getBriefC() {
        return briefC;
    }

    public void setBriefC(String briefC) {
        this.briefC = briefC;
    }

    public String getIndustryC() {
        return industryC;
    }

    public void setIndustryC(String industryC) {
        this.industryC = industryC;
    }

    public String getAreaC() {
        return areaC;
    }

    public void setAreaC(String areaC) {
        this.areaC = areaC;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "onlyIdP=" + onlyIdP +
                ", pnCategory='" + pnCategory + '\'' +
                ", pnId='" + pnId + '\'' +
                ", company='" + company + '\'' +
                ", corp='" + corp + '\'' +
                ", comAddr='" + comAddr + '\'' +
                ", comTel='" + comTel + '\'' +
                ", capital='" + capital + '\'' +
                ", staffNum='" + staffNum + '\'' +
                ", sales='" + sales + '\'' +
                ", brief='" + brief + '\'' +
                ", industry='" + industry + '\'' +
                ", area='" + area + '\'' +
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
                ", nameC='" + nameC + '\'' +
                ", pnIdC='" + pnIdC + '\'' +
                ", corpC='" + corpC + '\'' +
                ", comAddrC='" + comAddrC + '\'' +
                ", comTelC='" + comTelC + '\'' +
                ", capitalC='" + capitalC + '\'' +
                ", staffNumC='" + staffNumC + '\'' +
                ", salesC='" + salesC + '\'' +
                ", briefC='" + briefC + '\'' +
                ", industryC='" + industryC + '\'' +
                ", areaC='" + areaC + '\'' +
                '}';
    }
}
