package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  16:54 2018\5\11 0011
 * @Modify By:
 */
@Table(name = "tb_hr_set_children")
public class HRsetChildren implements Serializable {
    private static final long serialVersionUID = -1085145499144485920L;
    @Id
    private Integer id;//主键
    private String children;//生育

    public HRsetChildren() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "HRsetChildren{" +
                "id=" + id +
                ", children='" + children + '\'' +
                '}';
    }
}
