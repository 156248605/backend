package com.elex.oa.entity.restructure_hrentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\27 0027 13:09
 * @Version 1.0
 **/
public class Contractinfo implements Serializable {
    private static final long serialVersionUID = -1857987343062377115L;
    private String contractcode;//��ͬ���(����)
    private String employeenumber;//����
    private String startdate;//��Ч��
    private String enddate;//ʧЧ��
    private String contracttypeid;//��ͬ���ͱ��
    private String attachment;//����
    private String remark;//��ע
    private String transactoruserid;//��������(������Ա����)
    private String transdate;//����������
    private String contractage;//��ͬ����

    private List<String> contractcodes;//(������ѯ��)
    private String contractcodevalue;//�ж�����
    private List<String> employeenumbers;//(������ѯ��)
    private List<Integer> userids;//�ж�����
    private String employeenumbervalue;//�ж�����
    private String truename;//����
    private List<String> truenames;//(������ѯ��)
    private String truenamevalue;//�ж�����
    private String startdatevalue1;//�ж�����
    private String startdatevalue2;//�ж�����
    private String enddatevalue1;//�ж�����
    private String enddatevalue2;//�ж�����
    private List<String> contractages;//(������ѯ��)
    private String contractagevalue;//�ж�����
    private String contracttype;//��ͬ����
    private List<String> contracttypes;//(������ѯ��)
    private String contracttypevalue;//�ж�����
    private String remarkvalue;//�ж�����
    private String curdate;//�ж�����
    private List<Contractinfo> historyContract = new ArrayList<Contractinfo>();// ��ͬ��ǩ����
    private String transactortruename;//������������
    private String state;//

    public Contractinfo() {
    }

    public Contractinfo(String contractcode) {
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

    public List<Contractinfo> getHistoryContract() {
        return historyContract;
    }

    public void setHistoryContract(List<Contractinfo> historyContract) {
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