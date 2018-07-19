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
    private String auJob;
    // 联系人编号
    private String auId;
    // 联系人QQ
    private String auQq;
    // 授权联系人
    private String authorize;
    // 联系人电话
    private String auTel;
    // 联系人邮箱
    private String auMail;
    // 联系人微信
    private String auWechat;
    // 联系地址
    private String auAddr;
    // 其他联系人
    private String otherLink;
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
    private String auJobC;
    private String auIdC;
    private String auQqC;
    private String authorizeC;
    private String auTelC;
    private String auMailC;
    private String auWechatC;
    private String auAddrC;
    private String otherLinkC;

    public Partner() {
    }

    public Partner(int onlyIdP, String pnCategory, String pnId, String company, String corp, String comAddr, String comTel, String capital, String staffNum, String sales, String brief, String industry, String area, String auJob, String auId, String auQq, String authorize, String auTel, String auMail, String auWechat, String auAddr, String otherLink, String pnCateC, String companyC, String nameC, String pnIdC, String corpC, String comAddrC, String comTelC, String capitalC, String staffNumC, String salesC, String briefC, String industryC, String areaC, String auJobC, String auIdC, String auQqC, String authorizeC, String auTelC, String auMailC, String auWechatC, String auAddrC, String otherLinkC) {
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
        this.auJob = auJob;
        this.auId = auId;
        this.auQq = auQq;
        this.authorize = authorize;
        this.auTel = auTel;
        this.auMail = auMail;
        this.auWechat = auWechat;
        this.auAddr = auAddr;
        this.otherLink = otherLink;
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
        this.auJobC = auJobC;
        this.auIdC = auIdC;
        this.auQqC = auQqC;
        this.authorizeC = authorizeC;
        this.auTelC = auTelC;
        this.auMailC = auMailC;
        this.auWechatC = auWechatC;
        this.auAddrC = auAddrC;
        this.otherLinkC = otherLinkC;
    }

    public int getOnlyIdP() {
        return onlyIdP;
    }

    public void setOnlyIdP(int onlyIdP) {
        this.onlyIdP = onlyIdP;
    }

    public String getPnCategory() {
        return pnCategory;
    }

    public void setPnCategory(String pnCategory) {
        this.pnCategory = pnCategory;
    }

    public String getPnId() {
        return pnId;
    }

    public void setPnId(String pnId) {
        this.pnId = pnId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getAuJob() {
        return auJob;
    }

    public void setAuJob(String auJob) {
        this.auJob = auJob;
    }

    public String getAuId() {
        return auId;
    }

    public void setAuId(String auId) {
        this.auId = auId;
    }

    public String getAuQq() {
        return auQq;
    }

    public void setAuQq(String auQq) {
        this.auQq = auQq;
    }

    public String getAuthorize() {
        return authorize;
    }

    public void setAuthorize(String authorize) {
        this.authorize = authorize;
    }

    public String getAuTel() {
        return auTel;
    }

    public void setAuTel(String auTel) {
        this.auTel = auTel;
    }

    public String getAuMail() {
        return auMail;
    }

    public void setAuMail(String auMail) {
        this.auMail = auMail;
    }

    public String getAuWechat() {
        return auWechat;
    }

    public void setAuWechat(String auWechat) {
        this.auWechat = auWechat;
    }

    public String getAuAddr() {
        return auAddr;
    }

    public void setAuAddr(String auAddr) {
        this.auAddr = auAddr;
    }

    public String getOtherLink() {
        return otherLink;
    }

    public void setOtherLink(String otherLink) {
        this.otherLink = otherLink;
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

    public String getAuJobC() {
        return auJobC;
    }

    public void setAuJobC(String auJobC) {
        this.auJobC = auJobC;
    }

    public String getAuIdC() {
        return auIdC;
    }

    public void setAuIdC(String auIdC) {
        this.auIdC = auIdC;
    }

    public String getAuQqC() {
        return auQqC;
    }

    public void setAuQqC(String auQqC) {
        this.auQqC = auQqC;
    }

    public String getAuthorizeC() {
        return authorizeC;
    }

    public void setAuthorizeC(String authorizeC) {
        this.authorizeC = authorizeC;
    }

    public String getAuTelC() {
        return auTelC;
    }

    public void setAuTelC(String auTelC) {
        this.auTelC = auTelC;
    }

    public String getAuMailC() {
        return auMailC;
    }

    public void setAuMailC(String auMailC) {
        this.auMailC = auMailC;
    }

    public String getAuWechatC() {
        return auWechatC;
    }

    public void setAuWechatC(String auWechatC) {
        this.auWechatC = auWechatC;
    }

    public String getAuAddrC() {
        return auAddrC;
    }

    public void setAuAddrC(String auAddrC) {
        this.auAddrC = auAddrC;
    }

    public String getOtherLinkC() {
        return otherLinkC;
    }

    public void setOtherLinkC(String otherLinkC) {
        this.otherLinkC = otherLinkC;
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
                ", auJob='" + auJob + '\'' +
                ", auId='" + auId + '\'' +
                ", auQq='" + auQq + '\'' +
                ", authorize='" + authorize + '\'' +
                ", auTel='" + auTel + '\'' +
                ", auMail='" + auMail + '\'' +
                ", auWechat='" + auWechat + '\'' +
                ", auAddr='" + auAddr + '\'' +
                ", otherLink='" + otherLink + '\'' +
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
                ", auJobC='" + auJobC + '\'' +
                ", auIdC='" + auIdC + '\'' +
                ", auQqC='" + auQqC + '\'' +
                ", authorizeC='" + authorizeC + '\'' +
                ", auTelC='" + auTelC + '\'' +
                ", auMailC='" + auMailC + '\'' +
                ", auWechatC='" + auWechatC + '\'' +
                ", auAddrC='" + auAddrC + '\'' +
                ", otherLinkC='" + otherLinkC + '\'' +
                '}';
    }
}
