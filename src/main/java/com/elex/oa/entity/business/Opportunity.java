package com.elex.oa.entity.business;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\5 0005 15:36
 * @Version 1.0
 **/
@Table(name = "tb_business_opportunity")
public class Opportunity implements Serializable {
    private static final long serialVersionUID = -6031794208850900550L;
    @Id
    private String code;//商机编码（主键）
    private String clueid;//线索编码
    private String opportunityname;//产品名称
    private String trackid;//跟踪描述编码
    private String resource;//商机来源
    private String discover;//创建时间
    private String custom;//客户
    private String contact;//联系人
    private String contactphone;//联系人电话
    private String owner;//业主方
    private String custom_decisionmaker;//客户决策人
    private String custom_budget;//客户预算
    private String business_budget;//商机预算
    private String sale_employeenumber;//销售人员工号
    private String scheme_employeenumber;//方案人员工号
    private String state;//线索状态（开启、关闭、已转商机）

    private String trackcontent;//跟踪描述内容（最新的）
    private List<TrackInfo> trackInfoList;//跟踪日志
    private String sale_truename;//销售人的姓名
    private String scheme_truename;//方案人的姓名
    private List<BusinessAttachment> businessAttachmentList;//附件

    public Opportunity() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClueid() {
        return clueid;
    }

    public void setClueid(String clueid) {
        this.clueid = clueid;
    }

    public String getOpportunityname() {
        return opportunityname;
    }

    public void setOpportunityname(String opportunityname) {
        this.opportunityname = opportunityname;
    }

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getDiscover() {
        return discover;
    }

    public void setDiscover(String discover) {
        this.discover = discover;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCustom_decisionmaker() {
        return custom_decisionmaker;
    }

    public void setCustom_decisionmaker(String custom_decisionmaker) {
        this.custom_decisionmaker = custom_decisionmaker;
    }

    public String getCustom_budget() {
        return custom_budget;
    }

    public void setCustom_budget(String custom_budget) {
        this.custom_budget = custom_budget;
    }

    public String getBusiness_budget() {
        return business_budget;
    }

    public void setBusiness_budget(String business_budget) {
        this.business_budget = business_budget;
    }

    public String getSale_employeenumber() {
        return sale_employeenumber;
    }

    public void setSale_employeenumber(String sale_employeenumber) {
        this.sale_employeenumber = sale_employeenumber;
    }

    public String getScheme_employeenumber() {
        return scheme_employeenumber;
    }

    public void setScheme_employeenumber(String scheme_employeenumber) {
        this.scheme_employeenumber = scheme_employeenumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTrackcontent() {
        return trackcontent;
    }

    public void setTrackcontent(String trackcontent) {
        this.trackcontent = trackcontent;
    }

    public List<TrackInfo> getTrackInfoList() {
        return trackInfoList;
    }

    public void setTrackInfoList(List<TrackInfo> trackInfoList) {
        this.trackInfoList = trackInfoList;
    }

    public String getSale_truename() {
        return sale_truename;
    }

    public void setSale_truename(String sale_truename) {
        this.sale_truename = sale_truename;
    }

    public String getScheme_truename() {
        return scheme_truename;
    }

    public void setScheme_truename(String scheme_truename) {
        this.scheme_truename = scheme_truename;
    }

    public List<BusinessAttachment> getBusinessAttachmentList() {
        return businessAttachmentList;
    }

    public void setBusinessAttachmentList(List<BusinessAttachment> businessAttachmentList) {
        this.businessAttachmentList = businessAttachmentList;
    }

    @Override
    public String toString() {
        return "Opportunity{" +
                "code='" + code + '\'' +
                ", clueid='" + clueid + '\'' +
                ", opportunityname='" + opportunityname + '\'' +
                ", trackid='" + trackid + '\'' +
                ", resource='" + resource + '\'' +
                ", discover='" + discover + '\'' +
                ", custom='" + custom + '\'' +
                ", contact='" + contact + '\'' +
                ", contactphone='" + contactphone + '\'' +
                ", owner='" + owner + '\'' +
                ", custom_decisionmaker='" + custom_decisionmaker + '\'' +
                ", custom_budget='" + custom_budget + '\'' +
                ", business_budget='" + business_budget + '\'' +
                ", sale_employeenumber='" + sale_employeenumber + '\'' +
                ", scheme_employeenumber='" + scheme_employeenumber + '\'' +
                ", state='" + state + '\'' +
                ", trackcontent='" + trackcontent + '\'' +
                ", trackInfoList=" + trackInfoList +
                ", sale_truename='" + sale_truename + '\'' +
                ", scheme_truename='" + scheme_truename + '\'' +
                ", businessAttachmentList=" + businessAttachmentList +
                '}';
    }
}