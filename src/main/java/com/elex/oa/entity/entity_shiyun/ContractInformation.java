package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:合同信息
 * @Date:Created in  16:20 2018\4\9 0009
 * @Modify By:
 */
@Table(name = "tb_hr_contract")
public class ContractInformation implements Serializable{
    @Id
    private Integer id;
    private String contractcode;//合同编号
    private Integer userid;//用户ID
    private String employeenumber;//工号
    private String truename;//姓名
    private String startdate;//生效期
    private String enddate;//失效期
    private String contractlife;//合同年限
    private String contracttype;//合同类型
    private String istrainingagreement;//是否有培训协议
    private String remark;//备注
    private List<RenewContractRecord> renewcontracts = new ArrayList<RenewContractRecord>();// 合同续签集合

    public ContractInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractcode() {
        return contractcode;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getContractlife() {
        return contractlife;
    }

    public void setContractlife(String contractlife) {
        this.contractlife = contractlife;
    }

    public String getContracttype() {
        return contracttype;
    }

    public void setContracttype(String contracttype) {
        this.contracttype = contracttype;
    }

    public String getIstrainingagreement() {
        return istrainingagreement;
    }

    public void setIstrainingagreement(String istrainingagreement) {
        this.istrainingagreement = istrainingagreement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RenewContractRecord> getRenewcontracts() {
        return renewcontracts;
    }

    public void setRenewcontracts(List<RenewContractRecord> renewcontracts) {
        this.renewcontracts = renewcontracts;
    }

    @Override
    public String toString() {
        return "ContractInformation{" +
                "id=" + id +
                ", contractcode='" + contractcode + '\'' +
                ", userid=" + userid +
                ", employeenumber='" + employeenumber + '\'' +
                ", truename='" + truename + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", contractlife='" + contractlife + '\'' +
                ", contracttype='" + contracttype + '\'' +
                ", istrainingagreement='" + istrainingagreement + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
