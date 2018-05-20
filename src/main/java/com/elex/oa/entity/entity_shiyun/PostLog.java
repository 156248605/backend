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
    private String beforeinformation;
    private String afterinformation;
    private String changereason;
    private String changedate;
    private Integer transactoruserid;
    @Transient
    private String postname;
    @Transient
    private String transactortruename;

    public PostLog() {
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
                ", beforeinformation='" + beforeinformation + '\'' +
                ", afterinformation='" + afterinformation + '\'' +
                ", changereason='" + changereason + '\'' +
                ", changedate='" + changedate + '\'' +
                ", transactoruserid=" + transactoruserid +
                ", postname='" + postname + '\'' +
                ", transactortruename='" + transactortruename + '\'' +
                '}';
    }
}
