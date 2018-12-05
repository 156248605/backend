package com.elex.oa.entity.business;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\5 0005 16:15
 * @Version 1.0
 **/
@Table(name = "tb_business_attachment")
public class BusinessAttachment implements Serializable {
    private static final long serialVersionUID = 1736648632636016425L;
    @Id
    private String code;//附件编码（主键）
    private String dependence_code;//线索或商机编码
    private String attachment_address;//附件所在地址

    public BusinessAttachment() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDependence_code() {
        return dependence_code;
    }

    public void setDependence_code(String dependence_code) {
        this.dependence_code = dependence_code;
    }

    public String getAttachment_address() {
        return attachment_address;
    }

    public void setAttachment_address(String attachment_address) {
        this.attachment_address = attachment_address;
    }

    @Override
    public String toString() {
        return "BusinessAttachment{" +
                "code='" + code + '\'' +
                ", dependence_code='" + dependence_code + '\'' +
                ", attachment_address='" + attachment_address + '\'' +
                '}';
    }
}