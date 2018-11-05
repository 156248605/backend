package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  11:00 2018\5\31 0031
 * @Modify By:
 */
@Table(name = "tb_hr_set_punishment")
public class HRsetPunishment implements Serializable {
    private static final long serialVersionUID = 8889426379168891683L;
    @Id
    private Integer id;//主键
    private String punishment;//惩罚

    public HRsetPunishment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }
}
