package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:上家公司
 * @Date:Created in  13:45 2018\5\12 0012
 * @Modify By:
 */
@Table(name = "tb_hr_set_parentcompanyname")
public class HRsetParentcompany implements Serializable{
    private static final long serialVersionUID = 2213751112308515435L;
    @Id
    private Integer id;//主键
    private String parentcompanyname;//上家公司

    public HRsetParentcompany() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentcompanyname() {
        return parentcompanyname;
    }

    public void setParentcompanyname(String parentcompanyname) {
        this.parentcompanyname = parentcompanyname;
    }

    @Override
    public String toString() {
        return "HRsetParentcompany{" +
                "id=" + id +
                ", parentcompanyname='" + parentcompanyname + '\'' +
                '}';
    }
}
