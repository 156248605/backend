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
    private Integer salarystandardid;//薪资标准ID
    private Integer ssbid;//社保基数ID
    private Integer ssbgscdid;//社保公司缴费比例ID
    private Integer ssbgrcdid;//社保个人缴费比例ID
    private Integer gjjid;//公积金基数ID
    private Integer gjjgscdid;//公积金公司缴费比例ID
    private Integer gjjgrcdid;//公积金个人缴费比例ID
    private Integer khhid;//开户行ID
    private String salaryaccount;//工资账号
    private Integer sbjndid;//社保缴纳地ID
    private String sbcode;//社保账号
    private String gjjcode;//公积金账号

    public CostInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalarystandardid() {
        return salarystandardid;
    }

    public void setSalarystandardid(Integer salarystandardid) {
        this.salarystandardid = salarystandardid;
    }

    public Integer getSsbid() {
        return ssbid;
    }

    public void setSsbid(Integer ssbid) {
        this.ssbid = ssbid;
    }

    public Integer getSsbgscdid() {
        return ssbgscdid;
    }

    public void setSsbgscdid(Integer ssbgscdid) {
        this.ssbgscdid = ssbgscdid;
    }

    public Integer getSsbgrcdid() {
        return ssbgrcdid;
    }

    public void setSsbgrcdid(Integer ssbgrcdid) {
        this.ssbgrcdid = ssbgrcdid;
    }

    public Integer getGjjid() {
        return gjjid;
    }

    public void setGjjid(Integer gjjid) {
        this.gjjid = gjjid;
    }

    public Integer getGjjgscdid() {
        return gjjgscdid;
    }

    public void setGjjgscdid(Integer gjjgscdid) {
        this.gjjgscdid = gjjgscdid;
    }

    public Integer getGjjgrcdid() {
        return gjjgrcdid;
    }

    public void setGjjgrcdid(Integer gjjgrcdid) {
        this.gjjgrcdid = gjjgrcdid;
    }

    public Integer getKhhid() {
        return khhid;
    }

    public void setKhhid(Integer khhid) {
        this.khhid = khhid;
    }

    public String getSalaryaccount() {
        return salaryaccount;
    }

    public void setSalaryaccount(String salaryaccount) {
        this.salaryaccount = salaryaccount;
    }

    public Integer getSbjndid() {
        return sbjndid;
    }

    public void setSbjndid(Integer sbjndid) {
        this.sbjndid = sbjndid;
    }

    public String getSbcode() {
        return sbcode;
    }

    public void setSbcode(String sbcode) {
        this.sbcode = sbcode;
    }

    public String getGjjcode() {
        return gjjcode;
    }

    public void setGjjcode(String gjjcode) {
        this.gjjcode = gjjcode;
    }

    @Override
    public String toString() {
        return "CostInformation{" +
                "id=" + id +
                ", salarystandardid=" + salarystandardid +
                ", ssbid=" + ssbid +
                ", ssbgscdid=" + ssbgscdid +
                ", ssbgrcdid=" + ssbgrcdid +
                ", gjjid=" + gjjid +
                ", gjjgscdid=" + gjjgscdid +
                ", gjjgrcdid=" + gjjgrcdid +
                ", khhid=" + khhid +
                ", salaryaccount='" + salaryaccount + '\'' +
                ", sbjndid=" + sbjndid +
                ", sbcode='" + sbcode + '\'' +
                ", gjjcode='" + gjjcode + '\'' +
                '}';
    }
}
