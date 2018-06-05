package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:毕业院校
 * @Date:Created in  18:31 2018\5\11 0011
 * @Modify By:
 */
@Table(name = "tb_hr_set_byyx")
public class HRsetByyx implements Serializable{
    private static final long serialVersionUID = 3956225372397246813L;
    @Id
    private Integer id;//主键
    private String byyx;//毕业院校

    public HRsetByyx() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getByyx() {
        return byyx;
    }

    public void setByyx(String byyx) {
        this.byyx = byyx;
    }

    @Override
    public String toString() {
        return "HRsetByyx{" +
                "id=" + id +
                ", byyx='" + byyx + '\'' +
                '}';
    }
}
