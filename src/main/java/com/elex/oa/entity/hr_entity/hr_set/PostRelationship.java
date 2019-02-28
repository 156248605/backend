package com.elex.oa.entity.hr_entity.hr_set;

import java.io.Serializable;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\22 0022 13:16
 * @Version 1.0
 **/
public class PostRelationship implements Serializable {
    private static final long serialVersionUID = -8794340546164483917L;
    private String postfamily;
    private Integer postfamilyid;
    private String postgrade;
    private Integer postgradeid;
    private String postrank;
    private Integer postrankid;
    private String postlevel;
    private Integer postlevelid;
    private Integer id;

    public PostRelationship() {
    }

    public String getPostfamily() {
        return postfamily;
    }

    public void setPostfamily(String postfamily) {
        this.postfamily = postfamily;
    }

    public Integer getPostfamilyid() {
        return postfamilyid;
    }

    public void setPostfamilyid(Integer postfamilyid) {
        this.postfamilyid = postfamilyid;
    }

    public String getPostgrade() {
        return postgrade;
    }

    public void setPostgrade(String postgrade) {
        this.postgrade = postgrade;
    }

    public Integer getPostgradeid() {
        return postgradeid;
    }

    public void setPostgradeid(Integer postgradeid) {
        this.postgradeid = postgradeid;
    }

    public String getPostrank() {
        return postrank;
    }

    public void setPostrank(String postrank) {
        this.postrank = postrank;
    }

    public Integer getPostrankid() {
        return postrankid;
    }

    public void setPostrankid(Integer postrankid) {
        this.postrankid = postrankid;
    }

    public String getPostlevel() {
        return postlevel;
    }

    public void setPostlevel(String postlevel) {
        this.postlevel = postlevel;
    }

    public Integer getPostlevelid() {
        return postlevelid;
    }

    public void setPostlevelid(Integer postlevelid) {
        this.postlevelid = postlevelid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostRelationship{" +
                "postfamily='" + postfamily + '\'' +
                ", postfamilyid=" + postfamilyid +
                ", postgrade='" + postgrade + '\'' +
                ", postgradeid=" + postgradeid +
                ", postrank='" + postrank + '\'' +
                ", postrankid=" + postrankid +
                ", postlevel='" + postlevel + '\'' +
                ", postlevelid=" + postlevelid +
                ", id=" + id +
                '}';
    }
}