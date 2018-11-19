package com.elex.oa.entity.hr_entity;

import java.io.Serializable;

/**
 * @ClassName: HRset
 * @Description: HR设置
 * @Author shiyun
 * @Date 2018\11\6 0006 10:39
 * @Version 1.0
 **/
public class HRset implements Serializable {

    private static final long serialVersionUID = 4194437220453549386L;
    private Integer id;//HR设置主键
    private String datatype;//HR字段类型
    private String datacode;//HR字段编码(可以为空)
    private String datavalue;//HR字段值

    public HRset() {
    }

    public HRset(Integer id) {
        this.id = id;
    }

    public HRset(String datatype) {
        this.datatype = datatype;
    }

    public HRset(Integer id, String datavalue) {
        this.id = id;
        this.datavalue = datavalue;
    }

    public HRset(String datatype, String datavalue) {
        this.datatype = datatype;
        this.datavalue = datavalue;
    }

    public HRset(String datatype, String datacode, String datavalue) {
        this.datatype = datatype;
        this.datacode = datacode;
        this.datavalue = datavalue;
    }

    public HRset(Integer id, String datatype, String datavalue) {
        this.id = id;
        this.datatype = datatype;
        this.datavalue = datavalue;
    }

    public HRset(Integer id, String datatype, String datacode, String datavalue) {
        this.id = id;
        this.datatype = datatype;
        this.datacode = datacode;
        this.datavalue = datavalue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getDatacode() {
        return datacode;
    }

    public void setDatacode(String datacode) {
        this.datacode = datacode;
    }

    public String getDatavalue() {
        return datavalue;
    }

    public void setDatavalue(String datavalue) {
        this.datavalue = datavalue;
    }

    @Override
    public String toString() {
        return "HRset{" +
                "id=" + id +
                ", datatype='" + datatype + '\'' +
                ", datavalue='" + datavalue + '\'' +
                '}';
    }
}