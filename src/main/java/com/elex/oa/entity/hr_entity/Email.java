package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description;邮件实体类
 * @Date:Create in 2018/3/1 16:35
 * @Modify By:
 */
@Table(name = "tb_email_detail")
public class Email implements Serializable{
    @Id
    private Integer id;
    private Integer cfgid;
    private String fromm;
    private String too;
    private String cc;
    private String bcc;
    private String subject;
    private String attachment;
    private String bt;
    private String date;
    private String type;
    private String uid;

    public Email() {
    }

    public Email(Integer id, Integer cfgid, String fromm, String too, String cc, String bcc, String subject, String attachment, String bt, String date, String type, String uid) {
        this.id = id;
        this.cfgid = cfgid;
        this.fromm = fromm;
        this.too = too;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.attachment = attachment;
        this.bt = bt;
        this.date = date;
        this.type = type;
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCfgid() {
        return cfgid;
    }

    public void setCfgid(Integer cfgid) {
        this.cfgid = cfgid;
    }

    public String getFromm() {
        return fromm;
    }

    public void setFromm(String fromm) {
        this.fromm = fromm;
    }

    public String getToo() {
        return too;
    }

    public void setToo(String too) {
        this.too = too;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", cfgid=" + cfgid +
                ", fromm='" + fromm + '\'' +
                ", too='" + too + '\'' +
                ", cc='" + cc + '\'' +
                ", bcc='" + bcc + '\'' +
                ", subject='" + subject + '\'' +
                ", attachment='" + attachment + '\'' +
                ", bt='" + bt + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
