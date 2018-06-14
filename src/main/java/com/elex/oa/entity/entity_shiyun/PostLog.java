package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

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
    private Integer id;//主键
    private Integer postid;//岗位id
    private String changeinformation;//变更项目
    private List<String> changeinformations;//判断条件
    private String changeinformationvalue;//判断条件
    private String beforeinformation;//变更前内容
    private List<String> beforeinformations;//判断条件
    private String beforeinformationvalue;//判断条件
    private String afterinformation;//变更后内容
    private List<String> afterinformations;//判断条件
    private String afterinformationvalue;//判断条件
    private String changereason;//变更原因
    private List<String> changereasons;//判断条件
    private String changereasonvalue;//判断条件
    private String changedate;//变更日期
    private String changedatevalue1;//判断条件
    private String changedatevalue2;//判断条件
    private Integer transactoruserid;//变更人id
    @Transient
    private String postname;//岗位名称
    private List<String> postnames;//判断条件
    private String postnamevalue;//判断条件
    @Transient
    private String transactortruename;//变更人姓名
    private List<String> transactortruenames;//判断条件
    private String transactortruenamevalue;//判断条件

    public PostLog() {
    }

    public List<String> getChangeinformations() {
        return changeinformations;
    }

    public void setChangeinformations(List<String> changeinformations) {
        this.changeinformations = changeinformations;
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

    public List<String> getPostnames() {
        return postnames;
    }

    public void setPostnames(List<String> postnames) {
        this.postnames = postnames;
    }

    public List<String> getTransactortruenames() {
        return transactortruenames;
    }

    public void setTransactortruenames(List<String> transactortruenames) {
        this.transactortruenames = transactortruenames;
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
