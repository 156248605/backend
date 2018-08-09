package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private List<String> contractcodes;
    private String contractcodevalue;//判断条件
    private Integer userid;//用户ID
    private String employeenumber;//工号
    private List<String> employeenumbers;
    private List<Integer> userids;//判断条件
    private String employeenumbervalue;//判断条件
    private String truename;//姓名
    private List<String> truenames;
    private String truenamevalue;//判断条件
    private String startdate;//生效期
    private String startdatevalue1;//判断条件
    private String startdatevalue2;//判断条件
    private String enddate;//失效期
    private String enddatevalue1;//判断条件
    private String enddatevalue2;//判断条件
    private String contractage;//合同年限
    private List<String> contractages;
    private String contractagevalue;//判断条件
    private Integer contracttypeid;//合同类型编号
    private String contracttype;//合同类型
    private List<String> contracttypes;
    private String contracttypevalue;//判断条件
    private String attachment;//附件
    private String remark;//备注
    private String remarkvalue;//判断条件
    private String curdate;//判断条件
    private List<ContractInformation> historyContract = new ArrayList<ContractInformation>();// 合同续签集合
    private Integer transactoruserid;//最后办理人
    private String transactortruename;//最后办理人姓名
    private String transdate;//最后办理日期
    private String state;

    public ContractInformation() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.curdate = sdf.format(new Date());
    }



    public List<String> getContractcodes() {
        return contractcodes;
    }

    public void setContractcodes(List<String> contractcodes) {
        this.contractcodes = contractcodes;
    }

    public List<String> getEmployeenumbers() {
        return employeenumbers;
    }

    public void setEmployeenumbers(List<String> employeenumbers) {
        this.employeenumbers = employeenumbers;
    }

    public List<String> getTruenames() {
        return truenames;
    }

    public void setTruenames(List<String> truenames) {
        this.truenames = truenames;
    }

    public List<String> getContractages() {
        return contractages;
    }

    public void setContractages(List<String> contractages) {
        this.contractages = contractages;
    }

    public List<String> getContracttypes() {
        return contracttypes;
    }

    public void setContracttypes(List<String> contracttypes) {
        this.contracttypes = contracttypes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getTransactoruserid() {
        return transactoruserid;
    }

    public void setTransactoruserid(Integer transactoruserid) {
        this.transactoruserid = transactoruserid;
    }

    public String getTransactortruename() {
        return transactortruename;
    }

    public void setTransactortruename(String transactortruename) {
        this.transactortruename = transactortruename;
    }

    public String getTransdate() {
        return transdate;
    }

    public void setTransdate(String transdate) {
        this.transdate = transdate;
    }

    public String getCurdate() {
        return curdate;
    }

    public void setCurdate(String curdate) {
        this.curdate = curdate;
    }

    public List<Integer> getUserids() {
        return userids;
    }

    public void setUserids(List<Integer> userids) {
        this.userids = userids;
    }

    public Integer getContracttypeid() {
        return contracttypeid;
    }

    public void setContracttypeid(Integer contracttypeid) {
        this.contracttypeid = contracttypeid;
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

    public String getContractcodevalue() {
        return contractcodevalue;
    }

    public void setContractcodevalue(String contractcodevalue) {
        this.contractcodevalue = contractcodevalue;
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

    public String getTruenamevalue() {
        return truenamevalue;
    }

    public void setTruenamevalue(String truenamevalue) {
        this.truenamevalue = truenamevalue;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
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

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
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

    public String getContractage() {
        return contractage;
    }

    public void setContractage(String contractage) {
        this.contractage = contractage;
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

    public String getContracttypevalue() {
        return contracttypevalue;
    }

    public void setContracttypevalue(String contracttypevalue) {
        this.contracttypevalue = contracttypevalue;
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

    public String getRemarkvalue() {
        return remarkvalue;
    }

    public void setRemarkvalue(String remarkvalue) {
        this.remarkvalue = remarkvalue;
    }

    public List<ContractInformation> getHistoryContract() {
        return historyContract;
    }

    public void setHistoryContract(List<ContractInformation> historyContract) {
        this.historyContract = historyContract;
    }

    @Override
    public String toString() {
        return "ContractInformation{" +
                "id=" + id +
                ", contractcode='" + contractcode + '\'' +
                ", contractcodevalue='" + contractcodevalue + '\'' +
                ", userid=" + userid +
                ", employeenumber='" + employeenumber + '\'' +
                ", employeenumbervalue='" + employeenumbervalue + '\'' +
                ", truename='" + truename + '\'' +
                ", truenamevalue='" + truenamevalue + '\'' +
                ", startdate='" + startdate + '\'' +
                ", startdatevalue1='" + startdatevalue1 + '\'' +
                ", startdatevalue2='" + startdatevalue2 + '\'' +
                ", enddate='" + enddate + '\'' +
                ", enddatevalue1='" + enddatevalue1 + '\'' +
                ", enddatevalue2='" + enddatevalue2 + '\'' +
                ", contractage='" + contractage + '\'' +
                ", contractagevalue='" + contractagevalue + '\'' +
                ", contracttype='" + contracttype + '\'' +
                ", contracttypevalue='" + contracttypevalue + '\'' +
                ", attachment='" + attachment + '\'' +
                ", remark='" + remark + '\'' +
                ", remarkvalue='" + remarkvalue + '\'' +
                '}';
    }
}
