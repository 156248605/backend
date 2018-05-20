package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:公积金公司缴费比例
 * @Date:Created in  10:06 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_gjjgscd")
public class HRsetGjjgscd implements Serializable {
    private static final long serialVersionUID = 3903560633106927433L;
    @Id
    private Integer id;//主键
    private String gjjgscd;//公司缴费比例

    public HRsetGjjgscd() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGjjgscd() {
        return gjjgscd;
    }

    public void setGjjgscd(String gjjgscd) {
        this.gjjgscd = gjjgscd;
    }

    @Override
    public String toString() {
        return "HRsetGjjgscd{" +
                "id=" + id +
                ", gjjgscd='" + gjjgscd + '\'' +
                '}';
    }
}
