package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:社保公司缴费比例
 * @Date:Created in  10:00 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_ssbgscd")
public class HRsetSsbgscd implements Serializable {
    private static final long serialVersionUID = 3234613233082312783L;
    @Id
    private Integer id;//主键
    private String ssbgscd;//社保公司缴费比例

    public HRsetSsbgscd() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSsbgscd() {
        return ssbgscd;
    }

    public void setSsbgscd(String ssbgscd) {
        this.ssbgscd = ssbgscd;
    }

    @Override
    public String toString() {
        return "HRsetSsbgscd{" +
                "id=" + id +
                ", ssbgscd='" + ssbgscd + '\'' +
                '}';
    }
}
