package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  11:04 2018\5\31 0031
 * @Modify By:
 */
@Table(name = "tb_hr_set_customwelfareleave")
public class HRsetCustomwelfareleave implements Serializable {
    private static final long serialVersionUID = -4917166875058998820L;
    @Id
    private Integer id;//主键
    private String customwelfareleave;//自定义福利假

    public HRsetCustomwelfareleave() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomwelfareleave() {
        return customwelfareleave;
    }

    public void setCustomwelfareleave(String customwelfareleave) {
        this.customwelfareleave = customwelfareleave;
    }
}
