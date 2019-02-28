package com.elex.oa.entity.hr_entity.hr_set;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\2\27 0027 13:58
 * @Version 1.0
 **/
@Table(name = "tb_hr_set_postgradeandpostrank")
public class PostgradeAndPostrank {
    @Id
    private String id;//主键
    private Integer postgradeid;//职等ID
    private Integer postrankid;//职级ID

    public PostgradeAndPostrank() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPostgradeid() {
        return postgradeid;
    }

    public void setPostgradeid(Integer postgradeid) {
        this.postgradeid = postgradeid;
    }

    public Integer getPostrankid() {
        return postrankid;
    }

    public void setPostrankid(Integer postrankid) {
        this.postrankid = postrankid;
    }

    @Override
    public String toString() {
        return "PostgradeAndPostrank{" +
                "id='" + id + '\'' +
                ", postgradeid=" + postgradeid +
                ", postrankid=" + postrankid +
                '}';
    }
}