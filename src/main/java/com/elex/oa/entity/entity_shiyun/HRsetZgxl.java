package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  17:58 2018\5\11 0011
 * @Modify By:
 */
@Table(name = "tb_hr_set_zgxl")
public class HRsetZgxl implements Serializable {
    private static final long serialVersionUID = 4206582685434392757L;
    @Id
    private Integer id;//主键
    private String zgxl;//最高学历

    public HRsetZgxl() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZgxl() {
        return zgxl;
    }

    public void setZgxl(String zgxl) {
        this.zgxl = zgxl;
    }

    @Override
    public String toString() {
        return "HRsetZgxl{" +
                "id=" + id +
                ", zgxl='" + zgxl + '\'' +
                '}';
    }
}
