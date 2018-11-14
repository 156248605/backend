package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:其他信息
 * @Date:Created in  17:39 2018\3\20 0020
 * @Modify By:
 */
@Table(name = "tb_id_otherformation")
public class OtherInformation implements Serializable{
    @Id
    private Integer id;//主键
    private String privateemail;//私人邮件
    private String companyemail;//公司邮件
    private String emergencycontract;//应急联系人
    private Integer emergencyrpid;//应急联系人关系ID
    private String emergencyphone;//应急联系人电话
    private String address;//应急联系地址
    private String remark;//备注

    public OtherInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrivateemail() {
        return privateemail;
    }

    public void setPrivateemail(String privateemail) {
        this.privateemail = privateemail;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getEmergencycontract() {
        return emergencycontract;
    }

    public void setEmergencycontract(String emergencycontract) {
        this.emergencycontract = emergencycontract;
    }

    public Integer getEmergencyrpid() {
        return emergencyrpid;
    }

    public void setEmergencyrpid(Integer emergencyrpid) {
        this.emergencyrpid = emergencyrpid;
    }

    public String getEmergencyphone() {
        return emergencyphone;
    }

    public void setEmergencyphone(String emergencyphone) {
        this.emergencyphone = emergencyphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OtherInformation{" +"\n"+
                "id=" + id +"\n"+
                ", privateemail='" + privateemail + '\'' +"\n"+
                ", companyemail='" + companyemail + '\'' +"\n"+
                ", emergencycontract='" + emergencycontract + '\'' +"\n"+
                ", emergencyrpid=" + emergencyrpid +"\n"+
                ", emergencyphone='" + emergencyphone + '\'' +"\n"+
                ", address='" + address + '\'' +"\n"+
                ", remark='" + remark + '\'' +"\n"+
                '}';
    }
}
