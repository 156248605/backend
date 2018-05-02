package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:离职信息
 * @Date:Created in  15:46 2018\4\13 0013
 * @Modify By:
 */
@Table(name = "tb_hr_dimission")
public class DimissionInformation  implements Serializable {

    private static final long serialVersionUID = -7386971547883517342L;

    @Id
    private Integer id;// 主键
    private Integer dimissionuserid;// 离职人员的ID
    private String lastworkingdate;// 最后工作日
    private String dimissiontype;// 离职类型
    private String dimissionreason;// 离职原因
    private Integer transactoruserid;// 办理人的ID
    private String transactiondate;// 办理日期
    private String dimissiondirection;// 离职去向
    @Transient
    private String dimissiontruename;// 离职人员的姓名
    @Transient
    private String employeenumber;// 离职人员的工号
    @Transient
    private String depname;// 离职人员的部门名称
    @Transient
    private String postname;// 离职人员的岗位名称
    @Transient
    private String transactortruename;// 办理人的姓名

    private Integer approvalnumbers;// 待审批单的数量
    private Integer approvalstatue;// 待审批单是否确认处理
    private Integer workingnumbers;// 代办任务的数量
    private Integer workingstatue;// 代办任务是否确认处理
    private Integer filenumbers;// 文件占用数量
    private Integer filestatue;// 文件占用是否确认处理
    private Integer officesupplynumbers;// 办公用品领用数量
    private Integer officesupplystatue;// 办公用品领用是否确认处理

    public DimissionInformation() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDimissionuserid() {
        return dimissionuserid;
    }

    public void setDimissionuserid(Integer dimissionuserid) {
        this.dimissionuserid = dimissionuserid;
    }

    public String getLastworkingdate() {
        return lastworkingdate;
    }

    public void setLastworkingdate(String lastworkingdate) {
        this.lastworkingdate = lastworkingdate;
    }

    public String getDimissiontype() {
        return dimissiontype;
    }

    public void setDimissiontype(String dimissiontype) {
        this.dimissiontype = dimissiontype;
    }

    public String getDimissionreason() {
        return dimissionreason;
    }

    public void setDimissionreason(String dimissionreason) {
        this.dimissionreason = dimissionreason;
    }

    public Integer getTransactoruserid() {
        return transactoruserid;
    }

    public void setTransactoruserid(Integer transactoruserid) {
        this.transactoruserid = transactoruserid;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getDimissiondirection() {
        return dimissiondirection;
    }

    public void setDimissiondirection(String dimissiondirection) {
        this.dimissiondirection = dimissiondirection;
    }

    public String getDimissiontruename() {
        return dimissiontruename;
    }

    public void setDimissiontruename(String dimissiontruename) {
        this.dimissiontruename = dimissiontruename;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
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

    public String getTransactortruename() {
        return transactortruename;
    }

    public void setTransactortruename(String transactortruename) {
        this.transactortruename = transactortruename;
    }

    public Integer getApprovalnumbers() {
        return approvalnumbers;
    }

    public void setApprovalnumbers(Integer approvalnumbers) {
        this.approvalnumbers = approvalnumbers;
    }

    public Integer getApprovalstatue() {
        return approvalstatue;
    }

    public void setApprovalstatue(Integer approvalstatue) {
        this.approvalstatue = approvalstatue;
    }

    public Integer getWorkingnumbers() {
        return workingnumbers;
    }

    public void setWorkingnumbers(Integer workingnumbers) {
        this.workingnumbers = workingnumbers;
    }

    public Integer getWorkingstatue() {
        return workingstatue;
    }

    public void setWorkingstatue(Integer workingstatue) {
        this.workingstatue = workingstatue;
    }

    public Integer getFilenumbers() {
        return filenumbers;
    }

    public void setFilenumbers(Integer filenumbers) {
        this.filenumbers = filenumbers;
    }

    public Integer getFilestatue() {
        return filestatue;
    }

    public void setFilestatue(Integer filestatue) {
        this.filestatue = filestatue;
    }

    public Integer getOfficesupplynumbers() {
        return officesupplynumbers;
    }

    public void setOfficesupplynumbers(Integer officesupplynumbers) {
        this.officesupplynumbers = officesupplynumbers;
    }

    public Integer getOfficesupplystatue() {
        return officesupplystatue;
    }

    public void setOfficesupplystatue(Integer officesupplystatue) {
        this.officesupplystatue = officesupplystatue;
    }
}
