package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author:ShiYun;
 * @Description:管理信息
 * @Date:Created in  17:38 2018\3\20 0020
 * @Modify By:
 */
@Table(name = "tb_id_manageinformation")
public class ManageInformation {
    @Id
    private Integer id;
    private String company;
    private String depname;
    private String postname;
    private String principalusertruename;
    private String checkworkattendance;
    private String principalusercode;
    private String workingaddress;
    private String zj;
    private String employeetype;
    private String entrydate;
    private String postlevel;
    private String zhuanzhengdate;
    private String sn;

    public ManageInformation() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getPrincipalusertruename() {
        return principalusertruename;
    }

    public void setPrincipalusertruename(String principalusertruename) {
        this.principalusertruename = principalusertruename;
    }

    public String getCheckworkattendance() {
        return checkworkattendance;
    }

    public void setCheckworkattendance(String checkworkattendance) {
        this.checkworkattendance = checkworkattendance;
    }

    public String getPrincipalusercode() {
        return principalusercode;
    }

    public void setPrincipalusercode(String principalusercode) {
        this.principalusercode = principalusercode;
    }

    public String getWorkingaddress() {
        return workingaddress;
    }

    public void setWorkingaddress(String workingaddress) {
        this.workingaddress = workingaddress;
    }

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj;
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getPostlevel() {
        return postlevel;
    }

    public void setPostlevel(String postlevel) {
        this.postlevel = postlevel;
    }

    public String getZhuanzhengdate() {
        return zhuanzhengdate;
    }

    public void setZhuanzhengdate(String zhuanzhengdate) {
        this.zhuanzhengdate = zhuanzhengdate;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "ManageInformation{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", depname='" + depname + '\'' +
                ", postname='" + postname + '\'' +
                ", principalusertruename='" + principalusertruename + '\'' +
                ", checkworkattendance='" + checkworkattendance + '\'' +
                ", principalusercode='" + principalusercode + '\'' +
                ", workingaddress='" + workingaddress + '\'' +
                ", zj='" + zj + '\'' +
                ", employeetype='" + employeetype + '\'' +
                ", entrydate='" + entrydate + '\'' +
                ", postlevel='" + postlevel + '\'' +
                ", zhuanzhengdate='" + zhuanzhengdate + '\'' +
                ", sn=" + sn +
                '}';
    }
}
