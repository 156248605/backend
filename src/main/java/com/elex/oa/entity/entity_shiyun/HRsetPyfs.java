package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:培养方式
 * @Date:Created in  10:14 2018\5\12 0012
 * @Modify By:
 */
@Table(name = "tb_hr_set_pyfs")
public class HRsetPyfs implements Serializable {
    private static final long serialVersionUID = 4208057110520009565L;
    @Id
    private Integer id;//主键
    private String pyfs;//培养方式

    public HRsetPyfs() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPyfs() {
        return pyfs;
    }

    public void setPyfs(String pyfs) {
        this.pyfs = pyfs;
    }

    @Override
    public String toString() {
        return "HRsetPyfs{" +
                "id=" + id +
                ", pyfs='" + pyfs + '\'' +
                '}';
    }
}
