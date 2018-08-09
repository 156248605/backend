package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_id_department")
public class Dept implements Serializable{
    @Id
    private Integer id;// 主键
    private Dept parentdep;// 上级部门
    private Integer parentdepid;// 上级部门的ID
    private String companyname;// 公司名称
    private String depname;// 部门名称
    private String depcode;// 部门编号
    private String functionaltype;// 职能类型
    private Integer functionaltypeid;// 职能类型ID
    private User principaluser;// 部门正职
    private Integer principaluserid;// 部门正职的ID
    private User deputyuser;// 部门副职
    private Integer deputyuserid;// 部门副职的ID
    private User secretaryuser;// 部门秘书
    private Integer secretaryuserid;// 部门秘书的ID
    private Integer numbers;// 部门人数
    private String dutydescription;// 部门职责
    private String depdescription;//部门概述
    private Integer state;//状态
    private Integer ordercode;//顺序码
    private Integer deptypeid;//部门类型ID
    private String deptype;//部门类型

    public Dept() {
    }

    public String getDeptype() {
        return deptype;
    }

    public void setDeptype(String deptype) {
        this.deptype = deptype;
    }

    public Integer getDeptypeid() {
        return deptypeid;
    }

    public void setDeptypeid(Integer deptypeid) {
        this.deptypeid = deptypeid;
    }

    public Integer getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(Integer ordercode) {
        this.ordercode = ordercode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dept getParentdep() {
        return parentdep;
    }

    public void setParentdep(Dept parentdep) {
        this.parentdep = parentdep;
    }

    public Integer getParentdepid() {
        return parentdepid;
    }

    public void setParentdepid(Integer parentdepid) {
        this.parentdepid = parentdepid;
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

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }

    public String getFunctionaltype() {
        return functionaltype;
    }

    public void setFunctionaltype(String functionaltype) {
        this.functionaltype = functionaltype;
    }

    public Integer getFunctionaltypeid() {
        return functionaltypeid;
    }

    public void setFunctionaltypeid(Integer functionaltypeid) {
        this.functionaltypeid = functionaltypeid;
    }

    public User getPrincipaluser() {
        return principaluser;
    }

    public void setPrincipaluser(User principaluser) {
        this.principaluser = principaluser;
    }

    public Integer getPrincipaluserid() {
        return principaluserid;
    }

    public void setPrincipaluserid(Integer principaluserid) {
        this.principaluserid = principaluserid;
    }

    public User getDeputyuser() {
        return deputyuser;
    }

    public void setDeputyuser(User deputyuser) {
        this.deputyuser = deputyuser;
    }

    public Integer getDeputyuserid() {
        return deputyuserid;
    }

    public void setDeputyuserid(Integer deputyuserid) {
        this.deputyuserid = deputyuserid;
    }

    public User getSecretaryuser() {
        return secretaryuser;
    }

    public void setSecretaryuser(User secretaryuser) {
        this.secretaryuser = secretaryuser;
    }

    public Integer getSecretaryuserid() {
        return secretaryuserid;
    }

    public void setSecretaryuserid(Integer secretaryuserid) {
        this.secretaryuserid = secretaryuserid;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", parentdepid=" + parentdepid +
                ", companyname='" + companyname + '\'' +
                ", depname='" + depname + '\'' +
                ", depcode='" + depcode + '\'' +
                ", functionaltype='" + functionaltype + '\'' +
                ", principaluser=" + principaluser +
                ", principaluserid=" + principaluserid +
                ", deputyuser=" + deputyuser +
                ", deputyuserid=" + deputyuserid +
                ", secretaryuser=" + secretaryuser +
                ", secretaryuserid=" + secretaryuserid +
                ", numbers=" + numbers +
                ", dutydescription='" + dutydescription + '\'' +
                '}';
    }
}
