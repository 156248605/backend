package com.elex.oa.entity.hr_entity.hr_set;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\2\27 0027 13:56
 * @Version 1.0
 **/
@Table(name = "tb_hr_set_postfamilyandpostgrade")
public class PostfamilyAndPostgrade {
    @Id
    private String id;//主键
    private Integer postfamilyid;//职系ID
    private Integer postgradeid;//职等ID

    public PostfamilyAndPostgrade() {
    }

    public PostfamilyAndPostgrade(String id, Integer postfamilyid, Integer postgradeid) {
        this.id = id;
        this.postfamilyid = postfamilyid;
        this.postgradeid = postgradeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPostfamilyid() {
        return postfamilyid;
    }

    public void setPostfamilyid(Integer postfamilyid) {
        this.postfamilyid = postfamilyid;
    }

    public Integer getPostgradeid() {
        return postgradeid;
    }

    public void setPostgradeid(Integer postgradeid) {
        this.postgradeid = postgradeid;
    }

    @Override
    public String toString() {
        return "PostfamilyAndPostgrade{" +
                "id='" + id + '\'' +
                ", postfamilyid=" + postfamilyid +
                ", postgradeid=" + postgradeid +
                '}';
    }
}