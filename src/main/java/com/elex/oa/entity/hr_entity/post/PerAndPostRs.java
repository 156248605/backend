package com.elex.oa.entity.hr_entity.post;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:人事与岗位关系表
 * @Date:Created in  20:17 2018\5\12 0012
 * @Modify By:
 */
@Table(name = "tb_hr_per_and_post_rs")
public class PerAndPostRs implements Serializable {
    private static final long serialVersionUID = 5950896329294267472L;
    private Integer perid;//主键1（人事ID）
    private Integer postid;//主键2（岗位ID）

    public PerAndPostRs() {
    }

    public PerAndPostRs(Integer perid, Integer postid) {
        this.perid = perid;
        this.postid = postid;
    }

    public Integer getPerid() {
        return perid;
    }

    public void setPerid(Integer perid) {
        this.perid = perid;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    @Override
    public String toString() {
        return "PerAndPostRs{" +
                "perid=" + perid +
                ", postid=" + postid +
                '}';
    }
}
