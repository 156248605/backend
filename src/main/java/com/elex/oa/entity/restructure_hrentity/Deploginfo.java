package com.elex.oa.entity.restructure_hrentity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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

    @Override
    public String toString() {
        return "Deploginfo{" +
                "id='" + id + '\'' +
                ", depcode='" + depcode + '\'' +
                ", changeinformation='" + changeinformation + '\'' +
                ", beforeinformation='" + beforeinformation + '\'' +
                ", afterinformation='" + afterinformation + '\'' +
                ", changereason='" + changereason + '\'' +
                ", changedate='" + changedate + '\'' +
                ", transactoruserid='" + transactoruserid + '\'' +
                '}';
    }
}