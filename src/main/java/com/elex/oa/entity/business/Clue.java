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
 * @Date 2018\12\5 0005 15:37
 * @Version 1.0
 **/
@Table(name = "tb_business_clue")
public class Clue implements Serializable {
    private static final long serialVersionUID = 2126575563557586730L;
    @Id
    private String code;//线索编码（主键）
    private String cluename;//线索名称
    private String trackid;//跟踪描述编码
    private String resource;//线索来源
    private String createtime;//创建时间
    private String custom;//客户
    private String contact;//联系人
    private String contactphone;//联系人电话
    private String owner;//业主方
    private String sale_employeenumber;//销售人员工号
    private String scheme_employeenumber;//方案人员工号
    private String state;//状态（跟踪、关闭、转商机）
    private String clue_price;//线索价值
    private String participate;//参与人
    private String track_content;//最新线索内容
    private String track_date;//最新线索更新时间
    private String in_department;//立项部门
    private String close_time;// 线索关闭时间

    @Transient
    private String username;//登录ID（分权查询用的）
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
    private String participateName;//参与人的姓名

    public Clue() {
    }

    public Clue(String state) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCluename() {
        return cluename;
    }

    public void setCluename(String cluename) {
        this.cluename = cluename;
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

    public String getClue_price() {
        return clue_price;
    }

    public void setClue_price(String clue_price) {
        this.clue_price = clue_price;
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
        return "Clue{" +
                "code='" + code + '\'' +
                ", cluename='" + cluename + '\'' +
                ", trackid='" + trackid + '\'' +
                ", resource='" + resource + '\'' +
                ", createtime='" + createtime + '\'' +
                ", custom='" + custom + '\'' +
                ", contact='" + contact + '\'' +
                ", contactphone='" + contactphone + '\'' +
                ", owner='" + owner + '\'' +
                ", sale_employeenumber='" + sale_employeenumber + '\'' +
                ", scheme_employeenumber='" + scheme_employeenumber + '\'' +
                ", state='" + state + '\'' +
                ", clue_price='" + clue_price + '\'' +
                ", participate='" + participate + '\'' +
                ", track_content='" + track_content + '\'' +
                ", track_date='" + track_date + '\'' +
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