package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_id_post")
public class Post implements Serializable{
    @Id
    private Integer id;
    private String postname; //名称
    private String functionaltype;  //职能类型
    private String postlevel; //职级
    private String postcode; //岗位编码
    private Integer parentpostid; //上级岗位id
    private Post parentpost; //上级岗位
    private String organization; //编制
    private String duty; //责任
    private String entryrequirements; //入职要求
    private String jobdescription; //岗位描述
    private String dutyfile; //岗位详细描述，文件

    public Post() {
    }

    public Post(Integer id, String postname, String functionaltype, String postlevel, String postcode, Integer parentpostid, Post parentpost, String organization, String duty, String entryrequirements, String jobdescription, String dutyfile) {
        this.id = id;
        this.postname = postname;
        this.functionaltype = functionaltype;
        this.postlevel = postlevel;
        this.postcode = postcode;
        this.parentpostid = parentpostid;
        this.parentpost = parentpost;
        this.organization = organization;
        this.duty = duty;
        this.entryrequirements = entryrequirements;
        this.jobdescription = jobdescription;
        this.dutyfile = dutyfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getFunctionaltype() {
        return functionaltype;
    }

    public void setFunctionaltype(String functionaltype) {
        this.functionaltype = functionaltype;
    }

    public String getPostlevel() {
        return postlevel;
    }

    public void setPostlevel(String postlevel) {
        this.postlevel = postlevel;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getParentpostid() {
        return parentpostid;
    }

    public void setParentpostid(Integer parentpostid) {
        this.parentpostid = parentpostid;
    }

    public Post getParentpost() {
        return parentpost;
    }

    public void setParentpost(Post parentpost) {
        this.parentpost = parentpost;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getEntryrequirements() {
        return entryrequirements;
    }

    public void setEntryrequirements(String entryrequirements) {
        this.entryrequirements = entryrequirements;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public String getDutyfile() {
        return dutyfile;
    }

    public void setDutyfile(String dutyfile) {
        this.dutyfile = dutyfile;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postname='" + postname + '\'' +
                ", functionaltype='" + functionaltype + '\'' +
                ", postlevel='" + postlevel + '\'' +
                ", postcode='" + postcode + '\'' +
                ", parentpostid=" + parentpostid +
                ", organization='" + organization + '\'' +
                ", duty='" + duty + '\'' +
                ", entryrequirements='" + entryrequirements + '\'' +
                ", jobdescription='" + jobdescription + '\'' +
                ", dutyfile='" + dutyfile + '\'' +
                '}';
    }
}
