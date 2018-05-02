package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:奖惩信息（实体类）
 * @Date:Created in  19:53 2018\4\17 0017
 * @Modify By:
 */
@Table(name = "tb_hr_randpinformation")
public class RAndPInformation implements Serializable{
    private static final long serialVersionUID = -947956521376331382L;
    @Id
    private Integer id;// 主键
    private Integer userid;//职员的userID
    private Integer isreward;//奖惩区分
    private String grantunit;//授予单位
    private String randpproject;//奖惩名目
    private String randpdate;//奖惩日期
    private String randpsum;//奖惩金额
    private String randpreason;//奖惩原因
    private Integer transactoruserid;//记录人的userID
    @Transient
    private String truename;//职员的姓名
    @Transient
    private String transactortruename;//记录人的姓名

    public RAndPInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getIsreward() {
        return isreward;
    }

    public void setIsreward(Integer isreward) {
        this.isreward = isreward;
    }

    public String getGrantunit() {
        return grantunit;
    }

    public void setGrantunit(String grantunit) {
        this.grantunit = grantunit;
    }

    public String getRandpproject() {
        return randpproject;
    }

    public void setRandpproject(String randpproject) {
        this.randpproject = randpproject;
    }

    public String getRandpdate() {
        return randpdate;
    }

    public void setRandpdate(String randpdate) {
        this.randpdate = randpdate;
    }

    public String getRandpsum() {
        return randpsum;
    }

    public void setRandpsum(String randpsum) {
        this.randpsum = randpsum;
    }

    public String getRandpreason() {
        return randpreason;
    }

    public void setRandpreason(String randpreason) {
        this.randpreason = randpreason;
    }

    public Integer getTransactoruserid() {
        return transactoruserid;
    }

    public void setTransactoruserid(Integer transactoruserid) {
        this.transactoruserid = transactoruserid;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getTransactortruename() {
        return transactortruename;
    }

    public void setTransactortruename(String transactortruename) {
        this.transactortruename = transactortruename;
    }

    @Override
    public String toString() {
        return "RAndPInformation{" +
                "id=" + id +
                ", userid=" + userid +
                ", isreward=" + isreward +
                ", grantunit='" + grantunit + '\'' +
                ", randpproject='" + randpproject + '\'' +
                ", randpdate='" + randpdate + '\'' +
                ", randpsum='" + randpsum + '\'' +
                ", randpreason='" + randpreason + '\'' +
                ", transactoruserid=" + transactoruserid +
                ", truename='" + truename + '\'' +
                ", transactortruename='" + transactortruename + '\'' +
                '}';
    }
}
