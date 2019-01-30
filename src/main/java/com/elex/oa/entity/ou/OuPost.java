package com.elex.oa.entity.ou;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\22 0022 15:06
 * @Version 1.0
 **/
@Table(name = "tb_ou_post")
public class OuPost {
    @Id
    private String id;//主键
    private String postcode;//岗位编号
    private String postname;//岗位名称
    private Integer postlevelid;//岗级ID
    @Transient
    private String postlevel;//岗级
    private String state;//状态

    public OuPost() {
    }

    public OuPost(String postcode) {
        this.postcode = postcode;
    }

    public String getPostlevel() {
        return postlevel;
    }

    public void setPostlevel(String postlevel) {
        this.postlevel = postlevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public Integer getPostlevelid() {
        return postlevelid;
    }

    public void setPostlevelid(Integer postlevelid) {
        this.postlevelid = postlevelid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "OuPost{" +
                "id='" + id + '\'' +
                ", postcode='" + postcode + '\'' +
                ", postname='" + postname + '\'' +
                ", postlevelid=" + postlevelid +
                ", state='" + state + '\'' +
                '}';
    }
}