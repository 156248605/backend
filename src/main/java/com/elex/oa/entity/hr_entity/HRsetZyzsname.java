package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:职业证书名称
 * @Date:Created in  13:49 2018\5\12 0012
 * @Modify By:
 */
@Table(name = "tb_hr_set_zyzsname")
public class HRsetZyzsname implements Serializable {
    private static final long serialVersionUID = 6495936487580586504L;
    @Id
    private Integer id;//主键
    private String zyzsname;//职业证书名称

    public HRsetZyzsname() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZyzsname() {
        return zyzsname;
    }

    public void setZyzsname(String zyzsname) {
        this.zyzsname = zyzsname;
    }

    @Override
    public String toString() {
        return "HRsetZyzsname{" +
                "id=" + id +
                ", zyzsname='" + zyzsname + '\'' +
                '}';
    }
}
