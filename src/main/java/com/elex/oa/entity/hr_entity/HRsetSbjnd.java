package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:社保缴纳地
 * @Date:Created in  10:13 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_sbjnd")
public class HRsetSbjnd implements Serializable {
    private static final long serialVersionUID = -5013105551580385796L;
    @Id
    private Integer id;//主键
    private String sbjnd;//社保缴纳地

    public HRsetSbjnd() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSbjnd() {
        return sbjnd;
    }

    public void setSbjnd(String sbjnd) {
        this.sbjnd = sbjnd;
    }

    @Override
    public String toString() {
        return "HRsetSbjnd{" +
                "id=" + id +
                ", sbjnd='" + sbjnd + '\'' +
                '}';
    }
}
