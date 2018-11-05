package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:社保基数
 * @Date:Created in  9:57 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_ssb")
public class HRsetSsb implements Serializable {
    private static final long serialVersionUID = 1975923225630879782L;
    @Id
    private Integer id;//主键
    private String ssb;//社保基数

    public HRsetSsb() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSsb() {
        return ssb;
    }

    public void setSsb(String ssb) {
        this.ssb = ssb;
    }

    @Override
    public String toString() {
        return "HRsetSsb{" +
                "id=" + id +
                ", ssb='" + ssb + '\'' +
                '}';
    }
}
