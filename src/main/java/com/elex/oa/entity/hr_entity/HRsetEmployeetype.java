package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:员工类型
 * @Date:Created in  9:50 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_employeetype")
public class HRsetEmployeetype implements Serializable {
    private static final long serialVersionUID = -593487000286210376L;
    @Id
    private Integer id;//主键
    private String employeetype;//员工类型

    public HRsetEmployeetype() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype;
    }

    @Override
    public String toString() {
        return "HRsetEmployeetype{" +
                "id=" + id +
                ", employeetype='" + employeetype + '\'' +
                '}';
    }
}
