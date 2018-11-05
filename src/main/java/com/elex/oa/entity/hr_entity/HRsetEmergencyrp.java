package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:应急联系人关系
 * @Date:Created in  10:18 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_emergencyrp")
public class HRsetEmergencyrp implements Serializable {
    private static final long serialVersionUID = 5233589648116148716L;
    @Id
    private Integer id;//主键
    private String emergencyrp;//应急联系人关系

    public HRsetEmergencyrp() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmergencyrp() {
        return emergencyrp;
    }

    public void setEmergencyrp(String emergencyrp) {
        this.emergencyrp = emergencyrp;
    }

    @Override
    public String toString() {
        return "HRsetEmergencyrp{" +
                "id=" + id +
                ", emergencyrp='" + emergencyrp + '\'' +
                '}';
    }
}
