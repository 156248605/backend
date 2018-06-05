package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:HR设置（岗位级别）
 * @Date:Created in  17:58 2018\5\10 0010
 * @Modify By:
 */
@Table(name = "tb_hr_post_level")
public class HRsetPostlevel implements Serializable {
    private static final long serialVersionUID = 691106279220123474L;
    @Id
    private Integer id;//主键
    private String postlevel;//岗位级别

    public HRsetPostlevel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostlevel() {
        return postlevel;
    }

    public void setPostlevel(String postlevel) {
        this.postlevel = postlevel;
    }

    @Override
    public String toString() {
        return "HRsetPostlevel{" +
                "id=" + id +
                ", postlevel='" + postlevel + '\'' +
                '}';
    }
}
