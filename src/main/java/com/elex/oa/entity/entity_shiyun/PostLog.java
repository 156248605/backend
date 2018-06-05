package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:岗位日志
 * @Date:Created in  15:44 2018\5\2 0002
 * @Modify By:
 */
@Table(name = "tb_post_log")
public class PostLog implements Serializable{
    private static final long serialVersionUID = 2936731550293683326L;
    @Id
    private Integer id;
    private Integer postid;
    private String changeinformation;
    private String changeinformationvalue;
    private String beforeinformation;
    private String beforeinformationvalue;
    private String afterinformation;
    private String afterinformationvalue;
    private String changereason;
    private String changereasonvalue;
    private String changedate;
    private String changedatevalue1;
    private String changedatevalue2;
    private Integer transactoruserid;
    @Transient
    private String postname;
    private String postnamevalue;
    @Transient
    private String transactortruename;
    private String transactortruenamevalue;

    public PostLog() {
    }

    public String getChangeinformationvalue() {
        return changeinformationvalue;
    }

    public void setChangeinformationvalue(String changeinformationvalue) {
        this.changeinformationvalue = changeinformationvalue;
    }

    public String getBeforeinformationvalue() {
        return beforeinformationvalue;
    }

    public void setBeforeinformationvalue(String beforeinformationvalue) {
        this.beforeinformationvalue = beforeinformationvalue;
    }

    public String getAfterinformationvalue() {
        return afterinformationvalue;
    }

    public void setAfterinformationvalue(String afterinformationvalue) {
        this.afterinformationvalue = afterinformationvalue;
    }

    public String getChangereasonvalue() {
        return changereasonvalue;
    }

    public void setChangereasonvalue(String changereasonvalue) {
        this.changereasonvalue = changereasonvalue;
    }

    public String getChangedatevalue1() {
        return changedatevalue1;
    }

    public void setChangedatevalue1(String changedatevalue1) {
        this.changedatevalue1 = changedatevalue1;
    }

    public String getChangedatevalue2() {
        return changedatevalue2;
    }

    public void setChangedatevalue2(String changedatevalue2) {
        this.changedatevalue2 = changedatevalue2;
    }

    public String getPostnamevalue() {
        return postnamevalue;
    }

    public void setPostnamevalue(String postnamevalue) {
        this.postnamevalue = postnamevalue;
    }

    public String getTransactortruenamevalue() {
        return transactortruenamevalue;
    }

    public void setTransactortruenamevalue(String transactortruenamevalue) {
        this.transactortruenamevalue = transactortruenamevalue;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public String getChangeinformation() {
        return changeinformation;
    }

    public void setChangeinformation(String changeinformation) {
        this.changeinformation = changeinformation;
    }

    public String getBeforeinformation() {
        return beforeinformation;
    }

    public void setBeforeinformation(String beforeinformation) {
        this.beforeinformation = beforeinformation;
    }

    public String getAfterinformation() {
        return afterinformation;
    }

    public void setAfterinformation(String afterinformation) {
        this.afterinformation = afterinformation;
    }

    public String getChangereason() {
        return changereason;
    }

    public void setChangereason(String changereason) {
        this.changereason = changereason;
    }

    public String getChangedate() {
        return changedate;
    }

    public void setChangedate(String changedate) {
        this.changedate = changedate;
    }

    public Integer getTransactoruserid() {
        return transactoruserid;
    }

    public void setTransactoruserid(Integer transactoruserid) {
        this.transactoruserid = transactoruserid;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getTransactortruename() {
        return transactortruename;
    }

    public void setTransactortruename(String transactortruename) {
        this.transactortruename = transactortruename;
    }

    @Override
    public String toString() {
        return "PostLog{" +
                "id=" + id +
                ", postid=" + postid +
                ", changeinformation='" + changeinformation + '\'' +
                ", changeinformationvalue='" + changeinformationvalue + '\'' +
                ", beforeinformation='" + beforeinformation + '\'' +
                ", afterinformation='" + afterinformation + '\'' +
                ", afterinformationvalue='" + afterinformationvalue + '\'' +
                ", changereason='" + changereason + '\'' +
                ", changereasonvalue='" + changereasonvalue + '\'' +
                ", changedate='" + changedate + '\'' +
                ", changedatevalue1='" + changedatevalue1 + '\'' +
                ", changedatevalue2='" + changedatevalue2 + '\'' +
                ", transactoruserid=" + transactoruserid +
                ", postname='" + postname + '\'' +
                ", postnamevalue='" + postnamevalue + '\'' +
                ", transactortruename='" + transactortruename + '\'' +
                ", transactortruenamevalue='" + transactortruenamevalue + '\'' +
                '}';
    }
}
