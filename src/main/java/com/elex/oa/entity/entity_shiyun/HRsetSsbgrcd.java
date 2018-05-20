package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:社保个人缴费比例
 * @Date:Created in  10:02 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_ssbgrcd")
public class HRsetSsbgrcd implements Serializable {
    private static final long serialVersionUID = 786097584301214799L;
    @Id
    private Integer id;//主键
    private String ssbgrcd;//社保个人缴费比例

    public HRsetSsbgrcd() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSsbgrcd() {
        return ssbgrcd;
    }

    public void setSsbgrcd(String ssbgrcd) {
        this.ssbgrcd = ssbgrcd;
    }

    @Override
    public String toString() {
        return "HRsetSsbgrcd{" +
                "id=" + id +
                ", ssbgrcd='" + ssbgrcd + '\'' +
                '}';
    }
}
