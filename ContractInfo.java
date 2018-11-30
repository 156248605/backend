package com.elex.oa.entity.restructure_hrentity;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\27 0027 13:09
 * @Version 1.0
 **/
public class ContractInfo implements Serializable {
    private static final long serialVersionUID = -1857987343062377115L;
    private String contractcode;//合同编号(主键)
    private String employeenumber;//工号
    private String startdate;//生效期
    private String enddate;//失效期
    private String contracttypeid;//合同类型编号
    private String attachment;//附件
    private String remark;//备注
    private String transactoruserid;//最后办理人(办理人员工号)
    private String transdate;//最后办理日期
    private String contractage;//合同年限

    private List<String> contractcodes;//(条件查询用)
    private String contractcodevalue;//判断条件
    private List<String> employeenumbers;//(条件查询用)
    private List<Integer> userids;//判断条件
    private String employeenumbervalue;//判断条件
    private String truename;//姓名
    private List<String> truenames;//(条件查询用)
    private String truenamevalue;//判断条件
    private String startdatevalue1;//判断条件
    private String startdatevalue2;//判断条件
    private String enddatevalue1;//判断条件
    private String enddatevalue2;//判断条件
    private List<String> contractages;//(条件查询用)
    private String contractagevalue;//判断条件
    private String contracttype;//合同类型
    private List<String> contracttypes;//(条件查询用)
    private String contracttypevalue;//判断条件
    private String remarkvalue;//判断条件
    private String curdate;//判断条件
    private List<ContractInfo> historyContract = new ArrayList<ContractInfo>();// 合同续签集合
    private String transactortruename;//最后办理人姓名
    private String state;//

    public ContractInfo() {
    }

    public ContractInfo(String contractcode) {
        this.contractcode = contractcode;
    }

    public String getContractcode() {
        return contractcode;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
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

    public String getContracttypeid() {
        return contracttypeid;
    }

    public void setContracttypeid(String contracttypeid) {
        this.contracttypeid = contracttypeid;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTransactoruserid() {
        return transactoruserid;
    }

    public void setTransactoruserid(String transactoruserid) {
        this.transactoruserid = transactoruserid;
    }

    public String getTransdate() {
        return transdate;
    }

    public void setTransdate(String transdate) {
        this.transdate = transdate;
    }

    public String getContractage() {
        return contractage;
    }

    public void setContractage(String contractage) {
        this.contractage = contractage;
    }

    public List<String> getContractcodes() {
        return contractcodes;
    }

    public void setContractcodes(List<String> contractcodes) {
        this.contractcodes = contractcodes;
    }

    public String getContractcodevalue() {
        return contractcodevalue;
    }

    public void setContractcodevalue(String contractcodevalue) {
        this.contractcodevalue = contractcodevalue;
    }

    public List<String> getEmployeenumbers() {
        return employeenumbers;
    }

    public void setEmployeenumbers(List<String> employeenumbers) {
        this.employeenumbers = employeenumbers;
    }

    public List<Integer> getUserids() {
        return userids;
    }

    public void setUserids(List<Integer> userids) {
        this.userids = userids;
    }

    public String getEmployeenumbervalue() {
        return employeenumbervalue;
    }

    public void setEmployeenumbervalue(String employeenumbervalue) {
        this.employeenumbervalue = employeenumbervalue;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public List<String> getTruenames() {
        return truenames;
    }

    public void setTruenames(List<String> truenames) {
        this.truenames = truenames;
    }

    public String getTruenamevalue() {
        return truenamevalue;
    }

    public void setTruenamevalue(String truenamevalue) {
        this.truenamevalue = truenamevalue;
    }

    public String getStartdatevalue1() {
        return startdatevalue1;
    }

    public void setStartdatevalue1(String startdatevalue1) {
        this.startdatevalue1 = startdatevalue1;
    }

    public String getStartdatevalue2() {
        return startdatevalue2;
    }

    public void setStartdatevalue2(String startdatevalue2) {
        this.startdatevalue2 = startdatevalue2;
    }

    public String getEnddatevalue1() {
        return enddatevalue1;
    }

    public void setEnddatevalue1(String enddatevalue1) {
        this.enddatevalue1 = enddatevalue1;
    }

    public String getEnddatevalue2() {
        return enddatevalue2;
    }

    public void setEnddatevalue2(String enddatevalue2) {
        this.enddatevalue2 = enddatevalue2;
    }

    public List<String> getContractages() {
        return contractages;
    }

    public void setContractages(List<String> contractages) {
        this.contractages = contractages;
    }

    public String getContractagevalue() {
        return contractagevalue;
    }

    public void setContractagevalue(String contractagevalue) {
        this.contractagevalue = contractagevalue;
    }

    public String getContracttype() {
        return contracttype;
    }

    public void setContracttype(String contracttype) {
        this.contracttype = contracttype;
    }

    public List<String> getContracttypes() {
        return contracttypes;
    }

    public void setContracttypes(List<String> contracttypes) {
        this.contracttypes = contracttypes;
    }

    public String getContracttypevalue() {
        return contracttypevalue;
    }

    public void setContracttypevalue(String contracttypevalue) {
        this.contracttypevalue = contracttypevalue;
    }

    public String getRemarkvalue() {
        return remarkvalue;
    }

    public void setRemarkvalue(String remarkvalue) {
        this.remarkvalue = remarkvalue;
    }

    public String getCurdate() {
        return curdate;
    }

    public void setCurdate(String curdate) {
        this.curdate = curdate;
    }

    public List<ContractInfo> getHistoryContract() {
        return historyContract;
    }

    public void setHistoryContract(List<ContractInfo> historyContract) {
        this.historyContract = historyContract;
    }

    public String getTransactortruename() {
        return transactortruename;
    }

    public void setTransactortruename(String transactortruename) {
        this.transactortruename = transactortruename;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ContractInfo{" +
                "contractcode='" + contractcode + '\'' +
                ", employeenumber='" + employeenumber + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", contracttypeid='" + contracttypeid + '\'' +
                ", attachment='" + attachment + '\'' +
                ", remark='" + remark + '\'' +
                ", transactoruserid='" + transactoruserid + '\'' +
                ", transdate='" + transdate + '\'' +
                ", contractage='" + contractage + '\'' +
                ", contractcodes=" + contractcodes +
                ", contractcodevalue='" + contractcodevalue + '\'' +
                ", employeenumbers=" + employeenumbers +
                ", userids=" + userids +
                ", employeenumbervalue='" + employeenumbervalue + '\'' +
                ", truename='" + truename + '\'' +
                ", truenames=" + truenames +
                ", truenamevalue='" + truenamevalue + '\'' +
                ", startdatevalue1='" + startdatevalue1 + '\'' +
                ", startdatevalue2='" + startdatevalue2 + '\'' +
                ", enddatevalue1='" + enddatevalue1 + '\'' +
                ", enddatevalue2='" + enddatevalue2 + '\'' +
                ", contractages=" + contractages +
                ", contractagevalue='" + contractagevalue + '\'' +
                ", contracttype='" + contracttype + '\'' +
                ", contracttypes=" + contracttypes +
                ", contracttypevalue='" + contracttypevalue + '\'' +
                ", remarkvalue='" + remarkvalue + '\'' +
                ", curdate='" + curdate + '\'' +
                ", historyContract=" + historyContract +
                ", transactortruename='" + transactortruename + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}