package com.elex.oa.entity.restructure_hrentity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Table(name = "tb_loginfo_post")
public class Postloginfo implements Serializable {
    private static final long serialVersionUID = -9054723179826757037L;
    @Id
    private String id;//主键
    private String postcode;//岗位编号
    private String changeinformation;//变更内容
    private String beforeinformation;//变更前信息
    private String afterinformation;//变更后信息
    private String changereason;//变更原因
    private String changedate;//变更时间
    private String transactoruserid;//变更人（办理人、登录人）

    @Transient
    private List<String> changeinformations;
    @Transient
    private String changeinformationvalue;
    @Transient
    private String postname;
    @Transient
    private List<String> postnames;
    @Transient
    private String postnamevalue;
    @Transient
    private List<String> beforeinformations;
    @Transient
    private String beforeinformationvalue;
    @Transient
    private List<String> afterinformations;
    @Transient
    private String afterinformationvalue;
    @Transient
    private List<String> changereasons;
    @Transient
    private String changereasonvalue;
    @Transient
    private String changedatevalue1;
    @Transient
    private String changedatevalue2;
    @Transient
    private String transactortruename;
    @Transient
    private List<String> transactortruenames;
    @Transient
    private String transactortruenamevalue;

    public Postloginfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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

    public String getTransactoruserid() {
        return transactoruserid;
    }

    public void setTransactoruserid(String transactoruserid) {
        this.transactoruserid = transactoruserid;
    }

    public List<String> getChangeinformations() {
        return changeinformations;
    }

    public void setChangeinformations(List<String> changeinformations) {
        this.changeinformations = changeinformations;
    }

    public String getChangeinformationvalue() {
        return changeinformationvalue;
    }

    public void setChangeinformationvalue(String changeinformationvalue) {
        this.changeinformationvalue = changeinformationvalue;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public List<String> getPostnames() {
        return postnames;
    }

    public void setPostnames(List<String> postnames) {
        this.postnames = postnames;
    }

    public String getPostnamevalue() {
        return postnamevalue;
    }

    public void setPostnamevalue(String postnamevalue) {
        this.postnamevalue = postnamevalue;
    }

    public List<String> getBeforeinformations() {
        return beforeinformations;
    }

    public void setBeforeinformations(List<String> beforeinformations) {
        this.beforeinformations = beforeinformations;
    }

    public String getBeforeinformationvalue() {
        return beforeinformationvalue;
    }

    public void setBeforeinformationvalue(String beforeinformationvalue) {
        this.beforeinformationvalue = beforeinformationvalue;
    }

    public List<String> getAfterinformations() {
        return afterinformations;
    }

    public void setAfterinformations(List<String> afterinformations) {
        this.afterinformations = afterinformations;
    }

    public String getAfterinformationvalue() {
        return afterinformationvalue;
    }

    public void setAfterinformationvalue(String afterinformationvalue) {
        this.afterinformationvalue = afterinformationvalue;
    }

    public List<String> getChangereasons() {
        return changereasons;
    }

    public void setChangereasons(List<String> changereasons) {
        this.changereasons = changereasons;
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

    public String getTransactortruename() {
        return transactortruename;
    }

    public void setTransactortruename(String transactortruename) {
        this.transactortruename = transactortruename;
    }

    public List<String> getTransactortruenames() {
        return transactortruenames;
    }

    public void setTransactortruenames(List<String> transactortruenames) {
        this.transactortruenames = transactortruenames;
    }

    public String getTransactortruenamevalue() {
        return transactortruenamevalue;
    }

    public void setTransactortruenamevalue(String transactortruenamevalue) {
        this.transactortruenamevalue = transactortruenamevalue;
    }

    @Override
    public String toString() {
        return "Postloginfo{" +
                "id='" + id + '\'' +
                ", postcode='" + postcode + '\'' +
                ", changeinformation='" + changeinformation + '\'' +
                ", beforeinformation='" + beforeinformation + '\'' +
                ", afterinformation='" + afterinformation + '\'' +
                ", changereason='" + changereason + '\'' +
                ", changedate='" + changedate + '\'' +
                ", transactoruserid='" + transactoruserid + '\'' +
                ", changeinformations=" + changeinformations +
                ", changeinformationvalue='" + changeinformationvalue + '\'' +
                ", postname='" + postname + '\'' +
                ", postnames=" + postnames +
                ", postnamevalue='" + postnamevalue + '\'' +
                ", beforeinformations=" + beforeinformations +
                ", beforeinformationvalue='" + beforeinformationvalue + '\'' +
                ", afterinformations=" + afterinformations +
                ", afterinformationvalue='" + afterinformationvalue + '\'' +
                ", changereasons=" + changereasons +
                ", changereasonvalue='" + changereasonvalue + '\'' +
                ", changedatevalue1='" + changedatevalue1 + '\'' +
                ", changedatevalue2='" + changedatevalue2 + '\'' +
                ", transactortruename='" + transactortruename + '\'' +
                ", transactortruenames=" + transactortruenames +
                ", transactortruenamevalue='" + transactortruenamevalue + '\'' +
                '}';
    }
}