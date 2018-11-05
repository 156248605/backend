package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:所学专业
 * @Date:Created in  9:51 2018\5\12 0012
 * @Modify By:
 */
@Table(name = "tb_hr_set_sxzy")
public class HRsetSxzy implements Serializable{
    private static final long serialVersionUID = 7384077624716004723L;
    @Id
    private Integer id;//主键
    private String sxzy;//所学专业

    public HRsetSxzy() {
    }

    public HRsetSxzy(Integer id, String sxzy) {
        this.id = id;
        this.sxzy = sxzy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSxzy() {
        return sxzy;
    }

    public void setSxzy(String sxzy) {
        this.sxzy = sxzy;
    }

    @Override
    public String toString() {
        return "HRsetSxzy{" +
                "id=" + id +
                ", sxzy='" + sxzy + '\'' +
                '}';
    }
}
