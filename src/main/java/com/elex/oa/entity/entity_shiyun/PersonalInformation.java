package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:人事档案的人事信息类
 * @Date:Created in  17:24 2018\3\20 0020
 * @Modify By:
 */
@Table(name = "tb_id_personalinformation")
public class PersonalInformation implements Serializable{
    @Id
    private Integer id;
    private Integer userid;//用户ID
    private String truename;//用户姓名
    private Integer depid;//部门ID
    private String depname;//部门名称
    private String employeenumber;//员工号
    private Integer postid;//岗位ID
    private String postname;//岗位名称
    private String sex;//性别
    private String telphone;//办公电话
    private String mobilephone;//移动电话
    private Integer baseinformationid;//基本信息ID
    private BaseInformation baseInformation;//基本信息
    private Integer manageinformationid;//管理信息ID
    private ManageInformation manageInformation;//管理信息
    private Integer costinformationid;//成本信息ID
    private CostInformation costInformation;//成本信息
    private Integer otherinformationid;//其他信息ID
    private OtherInformation otherInformation;//其他信息

    public PersonalInformation() {
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

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Integer getBaseinformationid() {
        return baseinformationid;
    }

    public void setBaseinformationid(Integer baseinformationid) {
        this.baseinformationid = baseinformationid;
    }

    public BaseInformation getBaseInformation() {
        return baseInformation;
    }

    public void setBaseInformation(BaseInformation baseInformation) {
        this.baseInformation = baseInformation;
    }

    public Integer getManageinformationid() {
        return manageinformationid;
    }

    public void setManageinformationid(Integer manageinformationid) {
        this.manageinformationid = manageinformationid;
    }

    public ManageInformation getManageInformation() {
        return manageInformation;
    }

    public void setManageInformation(ManageInformation manageInformation) {
        this.manageInformation = manageInformation;
    }

    public Integer getCostinformationid() {
        return costinformationid;
    }

    public void setCostinformationid(Integer costinformationid) {
        this.costinformationid = costinformationid;
    }

    public CostInformation getCostInformation() {
        return costInformation;
    }

    public void setCostInformation(CostInformation costInformation) {
        this.costInformation = costInformation;
    }

    public Integer getOtherinformationid() {
        return otherinformationid;
    }

    public void setOtherinformationid(Integer otherinformationid) {
        this.otherinformationid = otherinformationid;
    }

    public OtherInformation getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(OtherInformation otherInformation) {
        this.otherInformation = otherInformation;
    }

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "id=" + id +
                ", userid=" + userid +
                ", truename='" + truename + '\'' +
                ", depid=" + depid +
                ", depname='" + depname + '\'' +
                ", employeenumber='" + employeenumber + '\'' +
                ", postid=" + postid +
                ", postname='" + postname + '\'' +
                ", sex='" + sex + '\'' +
                ", telphone='" + telphone + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", baseinformationid=" + baseinformationid +
                ", manageinformationid=" + manageinformationid +
                ", costinformationid=" + costinformationid +
                ", otherinformationid=" + otherinformationid +
                '}';
    }
}
