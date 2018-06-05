package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:HR设置（政治面貌）
 * @Date:Created in  17:36 2018\5\11 0011
 * @Modify By:
 */
@Table(name = "tb_hr_set_zzmm")
public class HRsetZzmm implements Serializable{
    private static final long serialVersionUID = 8614566581762814416L;
    @Id
    private Integer id;//主键
    private String zzmm;//政治面貌

    public HRsetZzmm() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    @Override
    public String toString() {
        return "HRsetZzmm{" +
                "id=" + id +
                ", zzmm='" + zzmm + '\'' +
                '}';
    }
}
