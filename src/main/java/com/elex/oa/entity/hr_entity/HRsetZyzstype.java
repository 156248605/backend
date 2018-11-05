package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:职业证书类型
 * @Date:Created in  11:20 2018\5\12 0012
 * @Modify By:
 */
@Table(name = "tb_hr_set_zyzstype")
public class HRsetZyzstype implements Serializable {
    private static final long serialVersionUID = -4683541080894472388L;
    @Id
    private Integer id;//主键
    private String zyzstype;//职业证书类型

    public HRsetZyzstype() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZyzstype() {
        return zyzstype;
    }

    public void setZyzstype(String zyzstype) {
        this.zyzstype = zyzstype;
    }

    @Override
    public String toString() {
        return "HRsetZyzstype{" +
                "id=" + id +
                ", zyzstype='" + zyzstype + '\'' +
                '}';
    }
}
