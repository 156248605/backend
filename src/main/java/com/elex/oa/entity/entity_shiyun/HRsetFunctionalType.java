package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:部门的HR设置（职能类型）
 * @Date:Created in  16:48 2018\5\10 0010
 * @Modify By:
 */
@Table(name = "tb_hr_dep_functionaltype")
public class HRsetFunctionalType implements Serializable {
    private static final long serialVersionUID = 7332865637624679110L;
    @Id
    private Integer id;//主键
    private String functionaltype;//职能类型

    public HRsetFunctionalType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFunctionaltype() {
        return functionaltype;
    }

    public void setFunctionaltype(String functionaltype) {
        this.functionaltype = functionaltype;
    }

    @Override
    public String toString() {
        return "DepFunctionalType{" +
                "id=" + id +
                ", functionaltype='" + functionaltype + '\'' +
                '}';
    }
}
