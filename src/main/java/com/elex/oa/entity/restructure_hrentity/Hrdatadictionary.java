package com.elex.oa.entity.restructure_hrentity;

import java.io.Serializable;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\27 0027 13:35
 * @Version 1.0
 **/
public class Hrdatadictionary implements Serializable {
    private static final long serialVersionUID = -236702607049440411L;
    private String datacode;//HR字段编码(主键)
    private String datatype;//HR字段类型
    private String datavalue;//HR字段值

    public Hrdatadictionary() {
    }

    public Hrdatadictionary(String datacode, String datatype, String datavalue) {
        this.datacode = datacode;
        this.datatype = datatype;
        this.datavalue = datavalue;
    }

    public String getDatacode() {
        return datacode;
    }

    public void setDatacode(String datacode) {
        this.datacode = datacode;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getDatavalue() {
        return datavalue;
    }

    public void setDatavalue(String datavalue) {
        this.datavalue = datavalue;
    }

    @Override
    public String toString() {
        return "HRDataDictionary{" +
                "datacode='" + datacode + '\'' +
                ", datatype='" + datatype + '\'' +
                ", datavalue='" + datavalue + '\'' +
                '}';
    }
}