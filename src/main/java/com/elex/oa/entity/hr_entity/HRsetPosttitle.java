package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:职称
 * @Date:Created in  11:18 2018\5\12 0012
 * @Modify By:
 */
@Table(name = "tb_hr_set_posttitle")
public class HRsetPosttitle implements Serializable {
    private static final long serialVersionUID = -7981148844603770069L;
    @Id
    private Integer id;//主键
    private String posttitle;//职称

    public HRsetPosttitle() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    @Override
    public String toString() {
        return "HRsetPosttitle{" +
                "id=" + id +
                ", posttitle='" + posttitle + '\'' +
                '}';
    }
}
