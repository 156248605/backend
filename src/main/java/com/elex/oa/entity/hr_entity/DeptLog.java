package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

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
    private List<String> changeinformations;//判断条件
    private String changeinformationvalue;//判断条件
    private String beforeinformation;//变更前信息
    private List<String> beforeinformations;//判断条件
    private String beforeinformationvalue;//判断条件
    private String afterinformation;//变更后信息
    private List<String> afterinformations;//判断条件
    private String afterinformationvalue;//判断条件
    private String changereason;//变更原因
    private List<String> changereasons;//判断条件
    private String changereasonvalue;//判断条件
    private String changedate;//变更日期
    private String changedatevalue1;//判断条件
    private String changedatevalue2;//判断条件
    private Integer transactoruserid;//变更人ID
    @Transient
    private String deptname;//部门名称
    private List<String> deptnames;//判断条件
    private String deptnamevalue;//判断条件
    @Transient
    private String transactortruename;//变更人姓名
    private List<String> transactortruenames;//判断条件
    private String transactortruenamevalue;//判断条件

    public DeptLog() {
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

    public List<String> getDeptnames() {
        return deptnames;
    }

    public void setDeptnames(List<String> deptnames) {
        this.deptnames = deptnames;
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

    public String getDeptnamevalue() {
        return deptnamevalue;
    }

    public void setDeptnamevalue(String deptnamevalue) {
        this.deptnamevalue = deptnamevalue;
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
                ", changeinformationvalue='" + changeinformationvalue + '\'' +
                ", beforeinformation='" + beforeinformation + '\'' +
                ", beforeinformationvalue='" + beforeinformationvalue + '\'' +
                ", afterinformation='" + afterinformation + '\'' +
                ", afterinformationvalue='" + afterinformationvalue + '\'' +
                ", changereason='" + changereason + '\'' +
                ", changereasonvalue='" + changereasonvalue + '\'' +
                ", changedate='" + changedate + '\'' +
                ", changedatevalue1='" + changedatevalue1 + '\'' +
                ", changedatevalue2='" + changedatevalue2 + '\'' +
                ", transactoruserid=" + transactoruserid +
                ", deptname='" + deptname + '\'' +
                ", deptnamevalue='" + deptnamevalue + '\'' +
                ", transactortruename='" + transactortruename + '\'' +
                ", transactortruenamevalue='" + transactortruenamevalue + '\'' +
                '}';
    }
}
