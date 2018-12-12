package com.elex.oa.entity.restructure_hrentity;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\12 0012 13:19
 * @Version 1.0
 **/
public class Postandpersonalrelationshipinfo implements Serializable {
    private static final long serialVersionUID = 7159058870572464900L;
    private String postcode;//岗位编号
    private String employeenumber;//员工号

    public Postandpersonalrelationshipinfo() {
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }

    @Override
    public String toString() {
        return "Postandpersonalrelationshipinfo{" +
                "postcode='" + postcode + '\'' +
                ", employeenumber='" + employeenumber + '\'' +
                '}';
    }
}