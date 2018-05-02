package com.elex.oa.entity.entity_shiyun;

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
    private Integer id;
    private String telphone;
    private String mobilephone;
    private String companyemail;
    private String privateemail;
    private String emergencycontract;
    private String emergencyphone;
    private String address;
    private String remark;

    public OtherInformation() {
    }

    public OtherInformation(Integer id, String telphone, String mobilephone, String companyemail, String privateemail, String emergencycontract, String emergencyphone, String address, String remark) {
        this.id = id;
        this.telphone = telphone;
        this.mobilephone = mobilephone;
        this.companyemail = companyemail;
        this.privateemail = privateemail;
        this.emergencycontract = emergencycontract;
        this.emergencyphone = emergencyphone;
        this.address = address;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getPrivateemail() {
        return privateemail;
    }

    public void setPrivateemail(String privateemail) {
        this.privateemail = privateemail;
    }

    public String getEmergencycontract() {
        return emergencycontract;
    }

    public void setEmergencycontract(String emergencycontract) {
        this.emergencycontract = emergencycontract;
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
        return "OtherInformation{" +
                "id=" + id +
                ", telphone='" + telphone + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", companyemail='" + companyemail + '\'' +
                ", privateemail='" + privateemail + '\'' +
                ", emergencycontract='" + emergencycontract + '\'' +
                ", emergencyphone='" + emergencyphone + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
