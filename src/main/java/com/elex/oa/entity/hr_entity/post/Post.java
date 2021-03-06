package com.elex.oa.entity.hr_entity.post;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_id_post")
public class Post implements Serializable{
    @Id
    private Integer id;
    private String postname; //名称
    private String functionaltype;  //职能类型
    private Integer functionaltypeid;//职能类型ID
    private String postfamily;//职系
    private Integer postfamilyid;//
    private String postgrade;//职等
    private Integer postgradeid;//
    private String postrank;//职级
    private Integer postrankid;//
    private String postlevel; //岗级
    private Integer postlevelid;//岗级ID
    private String postcode; //岗位编码
    private Integer parentpostid; //上级岗位id
    private Post parentpost; //上级岗位
    private String organization; //编制
    private String duty; //责任
    private String entryrequirements; //入职要求
    private String jobdescription; //岗位描述
    private String dutyfile; //岗位详细描述，文件
    private Integer state;//状态
    private Integer ordercode;//顺序码

    public Post() {
    }

    public Post(Integer id, String postname, Integer ordercode) {
        this.id = id;
        this.postname = postname;
        this.ordercode = ordercode;
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

    public Integer getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(Integer ordercode) {
        this.ordercode = ordercode;
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

    public Integer getFunctionaltypeid() {
        return functionaltypeid;
    }

    public void setFunctionaltypeid(Integer functionaltypeid) {
        this.functionaltypeid = functionaltypeid;
    }

    public Integer getPostlevelid() {
        return postlevelid;
    }

    public void setPostlevelid(Integer postlevelid) {
        this.postlevelid = postlevelid;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
