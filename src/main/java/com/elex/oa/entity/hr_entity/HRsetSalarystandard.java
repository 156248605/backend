package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:薪资标准
 * @Date:Created in  9:54 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_salarystandard")
public class HRsetSalarystandard implements Serializable {
    private static final long serialVersionUID = -5173023719975173165L;
    @Id
    private Integer id;//主键
    private String salarystandard;//薪资标准

    public HRsetSalarystandard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalarystandard() {
        return salarystandard;
    }

    public void setSalarystandard(String salarystandard) {
        this.salarystandard = salarystandard;
    }

    @Override
    public String toString() {
        return "HRsetSalarystandard{" +
                "id=" + id +
                ", salarystandard='" + salarystandard + '\'' +
                '}';
    }
}
