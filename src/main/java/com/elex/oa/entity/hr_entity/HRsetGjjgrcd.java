package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:公积金个人缴费比例
 * @Date:Created in  10:09 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_gjjgrcd")
public class HRsetGjjgrcd implements Serializable {
    private static final long serialVersionUID = -3808410011841578634L;
    @Id
    private Integer id;//主键
    private String gjjgrcd;//公积金个人缴费比例

    public HRsetGjjgrcd() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGjjgrcd() {
        return gjjgrcd;
    }

    public void setGjjgrcd(String gjjgrcd) {
        this.gjjgrcd = gjjgrcd;
    }

    @Override
    public String toString() {
        return "HRsetGjjgrcd{" +
                "id=" + id +
                ", gjjgrcd='" + gjjgrcd + '\'' +
                '}';
    }
}
