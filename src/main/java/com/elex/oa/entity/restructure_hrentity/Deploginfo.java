package com.elex.oa.entity.restructure_hrentity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\14 0014 11:31
 * @Version 1.0
 **/
@Table(name = "tb_loginfo_dept")
public class Deploginfo implements Serializable {
    private static final long serialVersionUID = -4639516981098489602L;
    @Id
    private String id;//主键
    private String depcode;//部门编号
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
    private String depname;
    @Transient
    private List<String> depnames;
    @Transient
    private String depnamevalue;
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

    public Deploginfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
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

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public List<String> getDepnames() {
        return depnames;
    }

    public void setDepnames(List<String> depnames) {
        this.depnames = depnames;
    }

    public String getDepnamevalue() {
        return depnamevalue;
    }

    public void setDepnamevalue(String depnamevalue) {
        this.depnamevalue = depnamevalue;
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
        return "Deploginfo{" +'\n'+
                "id='" + id + '\'' +'\n'+
                ", depcode='" + depcode + '\'' +'\n'+
                ", changeinformation='" + changeinformation + '\'' +'\n'+
                ", beforeinformation='" + beforeinformation + '\'' +'\n'+
                ", afterinformation='" + afterinformation + '\'' +'\n'+
                ", changereason='" + changereason + '\'' +'\n'+
                ", changedate='" + changedate + '\'' +'\n'+
                ", transactoruserid='" + transactoruserid + '\'' +'\n'+
                ", changeinformations=" + changeinformations +'\n'+
                ", changeinformationvalue='" + changeinformationvalue + '\'' +'\n'+
                ", depname='" + depname + '\'' +'\n'+
                ", depnames=" + depnames +'\n'+
                ", depnamevalue='" + depnamevalue + '\'' +'\n'+
                ", beforeinformations=" + beforeinformations +'\n'+
                ", beforeinformationvalue='" + beforeinformationvalue + '\'' +'\n'+
                ", afterinformations=" + afterinformations +'\n'+
                ", afterinformationvalue='" + afterinformationvalue + '\'' +'\n'+
                ", changereasons=" + changereasons +'\n'+
                ", changereasonvalue='" + changereasonvalue + '\'' +'\n'+
                ", changedatevalue1='" + changedatevalue1 + '\'' +'\n'+
                ", changedatevalue2='" + changedatevalue2 + '\'' +'\n'+
                ", transactortruename='" + transactortruename + '\'' +'\n'+
                ", transactortruenames=" + transactortruenames +'\n'+
                ", transactortruenamevalue='" + transactortruenamevalue + '\'' +'\n'+
                '}';
    }
}