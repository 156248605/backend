package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:开户行
 * @Date:Created in  10:11 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_khh")
public class HRsetKhh implements Serializable {
    private static final long serialVersionUID = -2721752439463276751L;
    @Id
    private Integer id;//主键
    private String khh;//开户行

    public HRsetKhh() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKhh() {
        return khh;
    }

    public void setKhh(String khh) {
        this.khh = khh;
    }

    @Override
    public String toString() {
        return "HRsetKhh{" +
                "id=" + id +
                ", khh='" + khh + '\'' +
                '}';
    }
}
