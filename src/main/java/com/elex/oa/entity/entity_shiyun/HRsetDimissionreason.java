package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:离职原因
 * @Date:Created in  10:56 2018\5\31 0031
 * @Modify By:
 */
@Table(name = "tb_hr_set_dimissionreason")
public class HRsetDimissionreason implements Serializable {
    private static final long serialVersionUID = 7488855666184798957L;
    @Id
    private Integer id;//主键
    private String dimissionreason;//离职原因

    public HRsetDimissionreason() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDimissionreason() {
        return dimissionreason;
    }

    public void setDimissionreason(String dimissionreason) {
        this.dimissionreason = dimissionreason;
    }
}
