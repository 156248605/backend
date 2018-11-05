package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  11:02 2018\5\31 0031
 * @Modify By:
 */
@Table(name = "tb_hr_set_legalholiday")
public class HRsetLegalholiday implements Serializable {
    private static final long serialVersionUID = -8239890826257767360L;
    @Id
    private Integer id;//主键
    private String legalholiday;//法定假

    public HRsetLegalholiday() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLegalholiday() {
        return legalholiday;
    }

    public void setLegalholiday(String legalholiday) {
        this.legalholiday = legalholiday;
    }
}
