package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:部门类型
 * @Date:Created in  18:31 2018\5\11 0011
 * @Modify By:
 */
@Table(name = "tb_hr_set_deptype")
public class HRsetDeptype implements Serializable{
    private static final long serialVersionUID = 1370860857091229936L;
    @Id
    private Integer id;//主键
    private String deptype;//部门类型

    public HRsetDeptype() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptype() {
        return deptype;
    }

    public void setDeptype(String deptype) {
        this.deptype = deptype;
    }

    @Override
    public String toString() {
        return "HRsetDeptype{" +
                "id=" + id +
                ", deptype='" + deptype + '\'' +
                '}';
    }
}
