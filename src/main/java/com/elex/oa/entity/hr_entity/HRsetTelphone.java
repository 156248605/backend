package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:办公电话
 * @Date:Created in  10:15 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_telphone")
public class HRsetTelphone implements Serializable {
    private static final long serialVersionUID = 1751965915108764673L;
    @Id
    private Integer id;//主键
    private String telphone;//办公电话

    public HRsetTelphone() {
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

    @Override
    public String toString() {
        return "HRsetTelphone{" +
                "id=" + id +
                ", telphone='" + telphone + '\'' +
                '}';
    }
}
