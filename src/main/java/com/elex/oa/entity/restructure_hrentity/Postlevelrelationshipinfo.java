package com.elex.oa.entity.restructure_hrentity;

import java.io.Serializable;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\28 0028 15:56
 * @Version 1.0
 **/
public class Postlevelrelationshipinfo implements Serializable {
    private static final long serialVersionUID = 3735195294119175880L;
    private String postfamilyid;
    private String postgradeid;
    private String postrankid;
    private String postlevelid;
    private String id;

    private String postfamily;
    private String postgrade;
    private String postrank;
    private String postlevel;

    public Postlevelrelationshipinfo() {
    }

    public Postlevelrelationshipinfo(String postfamilyid) {
        this.postfamilyid = postfamilyid;
    }

    public String getPostfamilyid() {
        return postfamilyid;
    }

    public void setPostfamilyid(String postfamilyid) {
        this.postfamilyid = postfamilyid;
    }

    public String getPostgradeid() {
        return postgradeid;
    }

    public void setPostgradeid(String postgradeid) {
        this.postgradeid = postgradeid;
    }

    public String getPostrankid() {
        return postrankid;
    }

    public void setPostrankid(String postrankid) {
        this.postrankid = postrankid;
    }

    public String getPostlevelid() {
        return postlevelid;
    }

    public void setPostlevelid(String postlevelid) {
        this.postlevelid = postlevelid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostfamily() {
        return postfamily;
    }

    public void setPostfamily(String postfamily) {
        this.postfamily = postfamily;
    }

    public String getPostgrade() {
        return postgrade;
    }

    public void setPostgrade(String postgrade) {
        this.postgrade = postgrade;
    }

    public String getPostrank() {
        return postrank;
    }

    public void setPostrank(String postrank) {
        this.postrank = postrank;
    }

    public String getPostlevel() {
        return postlevel;
    }

    public void setPostlevel(String postlevel) {
        this.postlevel = postlevel;
    }

    @Override
    public String toString() {
        return "Postlevelrelationshipinfo{" +
                "postfamilyid='" + postfamilyid + '\'' +
                ", postgradeid='" + postgradeid + '\'' +
                ", postrankid='" + postrankid + '\'' +
                ", postlevelid='" + postlevelid + '\'' +
                ", id='" + id + '\'' +
                ", postfamily='" + postfamily + '\'' +
                ", postgrade='" + postgrade + '\'' +
                ", postrank='" + postrank + '\'' +
                ", postlevel='" + postlevel + '\'' +
                '}';
    }
}