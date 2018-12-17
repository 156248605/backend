package com.elex.oa.entity.restructure_hrentity;

import org.springframework.data.annotation.Persistent;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
@Table(name = "tb_id_dep_info")
public class Depinfo implements Serializable{
    private static final long serialVersionUID = 8597283980607439617L;
    @Id
    private String depcode;// 部门编号(主键)
    private String companyname;// 公司名称
    private String depname;// 部门名称
    private String functionaltypeid;// 职能类型ID
    private String deptypeid;//部门类型ID
    private String parent_depcode;//上级部门编号
    private String principaluserid;// 部门正职的ID
    private String deputyuserid;// 部门副职的ID
    private String secretaryuserid;// 部门秘书的ID
    private String dutydescription;// 部门职责
    private String depdescription;//部门概述
    private String state;//状态
    private String ordercode;//顺序码
    private String node_level;//层级

    @Transient
    private String functionaltype;
    @Transient
    private String deptype;
    @Transient
    private Depinfo parentdep;
    @Transient
    private Personalinfo principaluser;
    @Transient
    private Personalinfo deputyuser;
    @Transient
    private Personalinfo secretaryuser;


    public Depinfo() {
    }

    public Depinfo(String depcode) {
        this.depcode = depcode;
    }

    public Depinfo(String depcode, String parent_depcode) {
        this.depcode = depcode;
        this.parent_depcode = parent_depcode;
    }

    public Depinfo(String depcode, String parent_depcode, String state) {
        this.depcode = depcode;
        this.parent_depcode = parent_depcode;
        this.state = state;
    }

    public Depinfo(String depcode, String parent_depcode, String state, String ordercode) {
        this.depcode = depcode;
        this.parent_depcode = parent_depcode;
        this.state = state;
        this.ordercode = ordercode;
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getFunctionaltypeid() {
        return functionaltypeid;
    }

    public void setFunctionaltypeid(String functionaltypeid) {
        this.functionaltypeid = functionaltypeid;
    }

    public String getDeptypeid() {
        return deptypeid;
    }

    public void setDeptypeid(String deptypeid) {
        this.deptypeid = deptypeid;
    }

    public String getParent_depcode() {
        return parent_depcode;
    }

    public void setParent_depcode(String parent_depcode) {
        this.parent_depcode = parent_depcode;
    }

    public String getPrincipaluserid() {
        return principaluserid;
    }

    public void setPrincipaluserid(String principaluserid) {
        this.principaluserid = principaluserid;
    }

    public String getDeputyuserid() {
        return deputyuserid;
    }

    public void setDeputyuserid(String deputyuserid) {
        this.deputyuserid = deputyuserid;
    }

    public String getSecretaryuserid() {
        return secretaryuserid;
    }

    public void setSecretaryuserid(String secretaryuserid) {
        this.secretaryuserid = secretaryuserid;
    }

    public String getDutydescription() {
        return dutydescription;
    }

    public void setDutydescription(String dutydescription) {
        this.dutydescription = dutydescription;
    }

    public String getDepdescription() {
        return depdescription;
    }

    public void setDepdescription(String depdescription) {
        this.depdescription = depdescription;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getNode_level() {
        return node_level;
    }

    public void setNode_level(String node_level) {
        this.node_level = node_level;
    }

    public String getFunctionaltype() {
        return functionaltype;
    }

    public void setFunctionaltype(String functionaltype) {
        this.functionaltype = functionaltype;
    }

    public String getDeptype() {
        return deptype;
    }

    public void setDeptype(String deptype) {
        this.deptype = deptype;
    }

    public Depinfo getParentdep() {
        return parentdep;
    }

    public void setParentdep(Depinfo parentdep) {
        this.parentdep = parentdep;
    }

    public Personalinfo getPrincipaluser() {
        return principaluser;
    }

    public void setPrincipaluser(Personalinfo principaluser) {
        this.principaluser = principaluser;
    }

    public Personalinfo getDeputyuser() {
        return deputyuser;
    }

    public void setDeputyuser(Personalinfo deputyuser) {
        this.deputyuser = deputyuser;
    }

    public Personalinfo getSecretaryuser() {
        return secretaryuser;
    }

    public void setSecretaryuser(Personalinfo secretaryuser) {
        this.secretaryuser = secretaryuser;
    }

    @Override
    public String toString() {
        return "DepInfo{" +
                "depcode='" + depcode + '\'' +
                ", companyname='" + companyname + '\'' +
                ", depname='" + depname + '\'' +
                ", functionaltypeid='" + functionaltypeid + '\'' +
                ", deptypeid='" + deptypeid + '\'' +
                ", parent_depcode='" + parent_depcode + '\'' +
                ", principaluserid='" + principaluserid + '\'' +
                ", deputyuserid='" + deputyuserid + '\'' +
                ", secretaryuserid='" + secretaryuserid + '\'' +
                ", dutydescription='" + dutydescription + '\'' +
                ", depdescription='" + depdescription + '\'' +
                ", state='" + state + '\'' +
                ", ordercode='" + ordercode + '\'' +
                ", node_level='" + node_level + '\'' +
                '}';
    }
}
