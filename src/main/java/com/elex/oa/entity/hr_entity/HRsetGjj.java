package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:公积金基数
 * @Date:Created in  10:04 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_gjj")
public class HRsetGjj implements Serializable {
    private static final long serialVersionUID = -4107287333689266765L;
    @Id
    private Integer id;//主键
    private String gjj;//公积金基数

    public HRsetGjj() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGjj() {
        return gjj;
    }

    public void setGjj(String gjj) {
        this.gjj = gjj;
    }

    @Override
    public String toString() {
        return "HRsetGjj{" +
                "id=" + id +
                ", gjj='" + gjj + '\'' +
                '}';
    }
}
