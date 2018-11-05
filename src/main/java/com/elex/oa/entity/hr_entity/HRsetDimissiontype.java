package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:离职类型
 * @Date:Created in  10:51 2018\5\31 0031
 * @Modify By:
 */
@Table(name = "tb_hr_set_dimission")
public class HRsetDimissiontype implements Serializable{
    private static final long serialVersionUID = -1598524683569873858L;
    @Id
    private Integer id;//主键
    private String dimissiontype;//离职类型

    public HRsetDimissiontype() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDimissiontype() {
        return dimissiontype;
    }

    public void setDimissiontype(String dimissiontype) {
        this.dimissiontype = dimissiontype;
    }
}
