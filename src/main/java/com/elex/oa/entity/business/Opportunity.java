package com.elex.oa.entity.business;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
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
    private String createtime;//创建时间
    private String custom;//客户
    private String contact;//联系人
    private String contactphone;//联系人电话
    private String owner;//业主方
    private String custom_decisionmaker;//客户决策人
    private String custom_budget;//客户预算
    private String opportunity_budget;//商机预算
    private String sale_employeenumber;//销售人员工号
    private String scheme_employeenumber;//方案人员工号
    private String state;//状态（跟踪、关闭、转立项）
    private String opportunity_price;//商机价值
    private String participate;//参与人
    private String track_content;//最新商机内容
    private String track_date;//最新商机更新时间
    private String inst_id;//商机转立项存储流程表单instId
    private String project_number;//商机转立项存储流程表单项目编号
    private String in_department;//立项部门
    private String close_time; // 商机关闭时间

    @Transient
    private String username;//登录ID
    @Transient
    private String depname;//部门名称
    @Transient
    private String trackcontent;//跟踪描述内容（最新的）
    @Transient
    private List<TrackInfo> trackInfoList=new ArrayList<>();//跟踪日志
    @Transient
    private String sale_truename;//销售人的姓名
    @Transient
    private String scheme_truename;//方案人的姓名
    @Transient
    private List<BusinessAttachment> businessAttachmentList;//附件
    @Transient
    private String participateName;//参与人姓名

    public Opportunity() {
    }

    public Opportunity(String state) {
        this.state = state;
    }

    public Opportunity(String clueid, String state) {
        this.clueid = clueid;
        this.state = state;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getUsername() {
        return username;
    }

    public String getProject_number() {
        return project_number;
    }

    public void setProject_number(String project_number) {
        this.project_number = project_number;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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

    public String getOpportunity_budget() {
        return opportunity_budget;
    }

    public void setOpportunity_budget(String opportunity_budget) {
        this.opportunity_budget = opportunity_budget;
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

    public String getOpportunity_price() {
        return opportunity_price;
    }

    public void setOpportunity_price(String opportunity_price) {
        this.opportunity_price = opportunity_price;
    }

    public String getParticipate() {
        return participate;
    }

    public void setParticipate(String participate) {
        this.participate = participate;
    }

    public String getParticipateName() {
        return participateName;
    }

    public void setParticipateName(String participateName) {
        this.participateName = participateName;
    }

    public String getTrack_content() {
        return track_content;
    }

    public void setTrack_content(String track_content) {
        this.track_content = track_content;
    }

    public String getTrack_date() {
        return track_date;
    }

    public void setTrack_date(String track_date) {
        this.track_date = track_date;
    }

    public String getInst_id() {
        return inst_id;
    }

    public void setInst_id(String inst_id) {
        this.inst_id = inst_id;
    }

    public String getIn_department() {
        return in_department;
    }

    public void setIn_department(String in_department) {
        this.in_department = in_department;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    @Override
    public String toString() {
        return "Opportunity{" +
                "code='" + code + '\'' +
                ", clueid='" + clueid + '\'' +
                ", opportunityname='" + opportunityname + '\'' +
                ", trackid='" + trackid + '\'' +
                ", resource='" + resource + '\'' +
                ", createtime='" + createtime + '\'' +
                ", custom='" + custom + '\'' +
                ", contact='" + contact + '\'' +
                ", contactphone='" + contactphone + '\'' +
                ", owner='" + owner + '\'' +
                ", custom_decisionmaker='" + custom_decisionmaker + '\'' +
                ", custom_budget='" + custom_budget + '\'' +
                ", opportunity_budget='" + opportunity_budget + '\'' +
                ", sale_employeenumber='" + sale_employeenumber + '\'' +
                ", scheme_employeenumber='" + scheme_employeenumber + '\'' +
                ", state='" + state + '\'' +
                ", opportunity_price='" + opportunity_price + '\'' +
                ", participate='" + participate + '\'' +
                ", track_content='" + track_content + '\'' +
                ", track_date='" + track_date + '\'' +
                ", inst_id='" + inst_id + '\'' +
                ", project_number='" + project_number + '\'' +
                ", in_department='" + in_department + '\'' +
                ", username='" + username + '\'' +
                ", depname='" + depname + '\'' +
                ", trackcontent='" + trackcontent + '\'' +
                ", trackInfoList=" + trackInfoList +
                ", sale_truename='" + sale_truename + '\'' +
                ", scheme_truename='" + scheme_truename + '\'' +
                ", businessAttachmentList=" + businessAttachmentList +
                ", participateName='" + participateName + '\'' +
                ", close_time='" + close_time + '\'' +
                '}';
    }
}
