package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:成本信息
 * @Date:Created in  17:39 2018\3\20 0020
 * @Modify By:
 */
@Table(name = "tb_id_costinformation")
public class CostInformation implements Serializable{
    @Id
    private Integer id;
    private String costtype;//成本类型
    private String costcode;//成本编号
    private String salary;//薪资
    private String accumulationfund;//公积金号
    private String gscdylbx;//公司承担养老保险
    private String grcdylbx;//个人承担养老保险
    private String gscdgjj;//公司承担公积金
    private String grcdgjj;//个人承担公积金
    private String gscdsybx;//公司承担失业保险
    private String grcdsybx;//个人承担失业保险
    private String gscdylbx2;//公司承担医疗保险
    private String grcdylbx2;//个人承担医疗保险
    private String shengyubx;//生育保险
    private String gsbx;//工伤保险
    private String salarykhh;//工资开户行
    private String salaryaccount;//工资账号
    private String sbjnd;//社保缴纳地
    private String sbcode;//社保账号

    public CostInformation() {
    }

    public CostInformation(Integer id, String costtype, String costcode, String salary, String accumulationfund, String gscdylbx, String grcdylbx, String gscdgjj, String grcdgjj, String gscdsybx, String grcdsybx, String gscdylbx2, String grcdylbx2, String shengyubx, String gsbx, String salarykhh, String salaryaccount, String sbjnd, String sbcode) {
        this.id = id;
        this.costtype = costtype;
        this.costcode = costcode;
        this.salary = salary;
        this.accumulationfund = accumulationfund;
        this.gscdylbx = gscdylbx;
        this.grcdylbx = grcdylbx;
        this.gscdgjj = gscdgjj;
        this.grcdgjj = grcdgjj;
        this.gscdsybx = gscdsybx;
        this.grcdsybx = grcdsybx;
        this.gscdylbx2 = gscdylbx2;
        this.grcdylbx2 = grcdylbx2;
        this.shengyubx = shengyubx;
        this.gsbx = gsbx;
        this.salarykhh = salarykhh;
        this.salaryaccount = salaryaccount;
        this.sbjnd = sbjnd;
        this.sbcode = sbcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCosttype() {
        return costtype;
    }

    public void setCosttype(String costtype) {
        this.costtype = costtype;
    }

    public String getCostcode() {
        return costcode;
    }

    public void setCostcode(String costcode) {
        this.costcode = costcode;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAccumulationfund() {
        return accumulationfund;
    }

    public void setAccumulationfund(String accumulationfund) {
        this.accumulationfund = accumulationfund;
    }

    public String getGscdylbx() {
        return gscdylbx;
    }

    public void setGscdylbx(String gscdylbx) {
        this.gscdylbx = gscdylbx;
    }

    public String getGrcdylbx() {
        return grcdylbx;
    }

    public void setGrcdylbx(String grcdylbx) {
        this.grcdylbx = grcdylbx;
    }

    public String getGscdgjj() {
        return gscdgjj;
    }

    public void setGscdgjj(String gscdgjj) {
        this.gscdgjj = gscdgjj;
    }

    public String getGrcdgjj() {
        return grcdgjj;
    }

    public void setGrcdgjj(String grcdgjj) {
        this.grcdgjj = grcdgjj;
    }

    public String getGscdsybx() {
        return gscdsybx;
    }

    public void setGscdsybx(String gscdsybx) {
        this.gscdsybx = gscdsybx;
    }

    public String getGrcdsybx() {
        return grcdsybx;
    }

    public void setGrcdsybx(String grcdsybx) {
        this.grcdsybx = grcdsybx;
    }

    public String getGscdylbx2() {
        return gscdylbx2;
    }

    public void setGscdylbx2(String gscdylbx2) {
        this.gscdylbx2 = gscdylbx2;
    }

    public String getGrcdylbx2() {
        return grcdylbx2;
    }

    public void setGrcdylbx2(String grcdylbx2) {
        this.grcdylbx2 = grcdylbx2;
    }

    public String getShengyubx() {
        return shengyubx;
    }

    public void setShengyubx(String shengyubx) {
        this.shengyubx = shengyubx;
    }

    public String getGsbx() {
        return gsbx;
    }

    public void setGsbx(String gsbx) {
        this.gsbx = gsbx;
    }

    public String getSalarykhh() {
        return salarykhh;
    }

    public void setSalarykhh(String salarykhh) {
        this.salarykhh = salarykhh;
    }

    public String getSalaryaccount() {
        return salaryaccount;
    }

    public void setSalaryaccount(String salaryaccount) {
        this.salaryaccount = salaryaccount;
    }

    public String getSbjnd() {
        return sbjnd;
    }

    public void setSbjnd(String sbjnd) {
        this.sbjnd = sbjnd;
    }

    public String getSbcode() {
        return sbcode;
    }

    public void setSbcode(String sbcode) {
        this.sbcode = sbcode;
    }

    @Override
    public String toString() {
        return "CostInformation{" +
                "id=" + id +
                ", costtype='" + costtype + '\'' +
                ", costcode='" + costcode + '\'' +
                ", salary='" + salary + '\'' +
                ", accumulationfund='" + accumulationfund + '\'' +
                ", gscdylbx='" + gscdylbx + '\'' +
                ", grcdylbx='" + grcdylbx + '\'' +
                ", gscdgjj='" + gscdgjj + '\'' +
                ", grcdgjj='" + grcdgjj + '\'' +
                ", gscdsybx='" + gscdsybx + '\'' +
                ", grcdsybx='" + grcdsybx + '\'' +
                ", gscdylbx2='" + gscdylbx2 + '\'' +
                ", grcdylbx2='" + grcdylbx2 + '\'' +
                ", shengyubx='" + shengyubx + '\'' +
                ", gsbx='" + gsbx + '\'' +
                ", salarykhh='" + salarykhh + '\'' +
                ", salaryaccount='" + salaryaccount + '\'' +
                ", sbjnd='" + sbjnd + '\'' +
                ", sbcode='" + sbcode + '\'' +
                '}';
    }
}
