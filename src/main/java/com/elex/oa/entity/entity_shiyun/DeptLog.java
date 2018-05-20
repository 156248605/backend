package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:部门日志实体类
 * @Date:Created in  15:19 2018\5\2 0002
 * @Modify By:
 */
@Table(name = "tb_dept_log")
public class DeptLog implements Serializable{

    private static final long serialVersionUID = -5062564509209282273L;

    @Id
    private Integer id;//主键
    private Integer deptid;//部门ID
    private String changeinformation;//变更项目
    private String beforeinformation;//变更前信息
    private String afterinformation;//变更后信息
    private String changereason;//变更原因
    private String changedate;//变更日期
    private Integer transactoruserid;//变更人ID
    @Transient
    private String deptname;//部门名称
    @Transient
    private String transactortruename;//变更人姓名

    public DeptLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
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

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getTransactortruename() {
        return transactortruename;
    }

    public void setTransactortruename(String transactortruename) {
        this.transactortruename = transactortruename;
    }

    @Override
    public String toString() {
        return "DeptLog{" +
                "id=" + id +
                ", deptid=" + deptid +
                ", changeinformation='" + changeinformation + '\'' +
                ", beforeinformation='" + beforeinformation + '\'' +
                ", afterinformation='" + afterinformation + '\'' +
                ", changereason='" + changereason + '\'' +
                ", changedate='" + changedate + '\'' +
                ", transactoruserid=" + transactoruserid +
                ", deptname='" + deptname + '\'' +
                ", transactortruename='" + transactortruename + '\'' +
                '}';
    }
}
