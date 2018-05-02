package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

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
    private String changedinformation;//变更信息
    private Integer changeduserid;//变更人的userid
    @Transient
    private String changedtruename;//变更人姓名
    private String beforeinformation;//变更前内容
    private String afterinformation;//变更后内容
    private String changereason;//变更原因
    private String changedate;//变更时间
    private Integer transactoruserid;//办理人userid
    @Transient
    private String transactortruename;//办理人姓名

    public ChangeInformation() {
    }

    public ChangeInformation(String changedinformation, Integer changeduserid, String beforeinformation, String afterinformation, String changereason, String changedate, Integer transactoruserid) {
        this.changedinformation = changedinformation;
        this.changeduserid = changeduserid;
        this.beforeinformation = beforeinformation;
        this.afterinformation = afterinformation;
        this.changereason = changereason;
        this.changedate = changedate;
        this.transactoruserid = transactoruserid;
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

    public String getChangedinformation() {
        return changedinformation;
    }

    public void setChangedinformation(String changedinformation) {
        this.changedinformation = changedinformation;
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
                ", changedinformation='" + changedinformation + '\'' +
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
