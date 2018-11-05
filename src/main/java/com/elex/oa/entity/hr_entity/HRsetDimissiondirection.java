package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  10:54 2018\5\31 0031
 * @Modify By:
 */
@Table(name = "tb_hr_set_dimissiondirection")
public class HRsetDimissiondirection implements Serializable{
    private static final long serialVersionUID = -9059020967784127888L;
    @Id
    private Integer id;//主键
    private String dimissiondirection;//离职去向

    public HRsetDimissiondirection() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDimissiondirection() {
        return dimissiondirection;
    }

    public void setDimissiondirection(String dimissiondirection) {
        this.dimissiondirection = dimissiondirection;
    }
}
