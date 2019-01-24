package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:变更信息实体类
 * @Date:Created in  18:21 2018\4\12 0012
 * @Modify By:
 */
@Table(name = "tb_hr_changeinformation")
public class ChangeInformation implements Serializable{

    private static final long serialVersionUID = -7210880346745130468L;
    @Id
    private Integer id;//主键
    private String changeinformation;//变更信息
    @Transient
    private List<String> changeinformations;
    private String changeinformationvalue;//判断条件
    private Integer changeduserid;//变更人的userid
    @Transient
    private String changedtruename;//变更人姓名
    @Transient
    private List<String> changedtruenames;
    private String changedtruenamevalue;//判断条件
    private String beforeinformation;//变更前内容
    @Transient
    private List<String> beforeinformations;
    private String beforeinformationvalue;//判断条件
    private String afterinformation;//变更后内容
    @Transient
    private List<String> afterinformations;
    private String afterinformationvalue;//判断条件
    private String changereason;//变更原因
    @Transient
    private List<String> changereasons;
    private String changereasonvalue;//判断条件
    private String changedate;//变更时间
    private String changedatevalue1;//判断条件
    private String changedatevalue2;//判断条件
    private Integer transactoruserid;//办理人userid
    @Transient
    private String transactortruename;//办理人姓名
    @Transient
    private List<String> transactortruenames;
    private String transactortruenamevalue;//判断条件

    public ChangeInformation() {
    }

    public ChangeInformation(Integer changeduserid) {
        this.changeduserid = changeduserid;
    }

    public List<String> getChangeinformations() {
        return changeinformations;
    }

    public void setChangeinformations(List<String> changeinformations) {
        this.changeinformations = changeinformations;
    }

    public List<String> getChangedtruenames() {
        return changedtruenames;
    }

    public void setChangedtruenames(List<String> changedtruenames) {
        this.changedtruenames = changedtruenames;
    }

    public List<String> getBeforeinformations() {
        return beforeinformations;
    }

    public void setBeforeinformations(List<String> beforeinformations) {
        this.beforeinformations = beforeinformations;
    }

    public List<String> getAfterinformations() {
        return afterinformations;
    }

    public void setAfterinformations(List<String> afterinformations) {
        this.afterinformations = afterinformations;
    }

    public List<String> getChangereasons() {
        return changereasons;
    }

    public void setChangereasons(List<String> changereasons) {
        this.changereasons = changereasons;
    }

    public List<String> getTransactortruenames() {
        return transactortruenames;
    }

    public void setTransactortruenames(List<String> transactortruenames) {
        this.transactortruenames = transactortruenames;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getChangeinformationvalue() {
        return changeinformationvalue;
    }

    public void setChangeinformationvalue(String changeinformationvalue) {
        this.changeinformationvalue = changeinformationvalue;
    }

    public String getChangedtruenamevalue() {
        return changedtruenamevalue;
    }

    public void setChangedtruenamevalue(String changedtruenamevalue) {
        this.changedtruenamevalue = changedtruenamevalue;
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

    public String getTransactortruenamevalue() {
        return transactortruenamevalue;
    }

    public void setTransactortruenamevalue(String transactortruenamevalue) {
        this.transactortruenamevalue = transactortruenamevalue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChangeinformation() {
        return changeinformation;
    }

    public void setChangeinformation(String changeinformation) {
        this.changeinformation = changeinformation;
    }

    public Integer getChangeduserid() {
        return changeduserid;
    }

    public void setChangeduserid(Integer changeduserid) {
        this.changeduserid = changeduserid;
    }

    public String getChangedtruename() {
        return changedtruename;
    }

    public void setChangedtruename(String changedtruename) {
        this.changedtruename = changedtruename;
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

    public String getTransactortruename() {
        return transactortruename;
    }

    public void setTransactortruename(String transactortruename) {
        this.transactortruename = transactortruename;
    }

    @Override
    public String toString() {
        return "ChangeInformation{" +
                "id=" + id +
                ", changeinformation='" + changeinformation + '\'' +
                ", changeduserid=" + changeduserid +
                ", changedtruename='" + changedtruename + '\'' +
                ", beforeinformation='" + beforeinformation + '\'' +
                ", afterinformation='" + afterinformation + '\'' +
                ", changereason='" + changereason + '\'' +
                ", changedate='" + changedate + '\'' +
                ", transactoruserid=" + transactoruserid +
                ", transactortruename='" + transactortruename + '\'' +
                '}';
    }
}
