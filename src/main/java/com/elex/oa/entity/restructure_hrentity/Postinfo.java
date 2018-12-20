package com.elex.oa.entity.restructure_hrentity;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\27 0027 18:35
 * @Version 1.0
 **/
public class Postinfo implements Serializable {
    private static final long serialVersionUID = -8477580653993244148L;
    private String postcode; //岗位编码(主键)
    private String parent_postcode; //上级岗位编码
    private String postname; //名称
    private String functionaltypeid;//职能类型ID
    private String postfamilyid;//
    private String postgradeid;//
    private String postrankid;//
    private String postlevelid;//岗级ID
    private String organization; //编制
    private String jobdescription; //岗位描述
    private String duty; //责任
    private String entryrequirements; //入职要求
    private String dutyfile; //岗位详细描述，文件(岗位说明书)
    private String state;//状态
    private String ordercode;//顺序码
    private String node_level;//层级

    @Transient
    private Postinfo parentpost;
    @Transient
    private String postrank;
    @Transient
    private String functionaltype;
    @Transient
    private String childrenPostnames;//下级岗位名称分隔符';'

    public Postinfo() {
    }

    public Postinfo(String postcode) {
        this.postcode = postcode;
    }

    public Postinfo(String postcode, String parent_postcode) {
        this.postcode = postcode;
        this.parent_postcode = parent_postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getParent_postcode() {
        return parent_postcode;
    }

    public void setParent_postcode(String parent_postcode) {
        this.parent_postcode = parent_postcode;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getFunctionaltypeid() {
        return functionaltypeid;
    }

    public void setFunctionaltypeid(String functionaltypeid) {
        this.functionaltypeid = functionaltypeid;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
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

    public String getDutyfile() {
        return dutyfile;
    }

    public void setDutyfile(String dutyfile) {
        this.dutyfile = dutyfile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getNode_level() {
        return node_level;
    }

    public void setNode_level(String node_level) {
        this.node_level = node_level;
    }

    public Postinfo getParentpost() {
        return parentpost;
    }

    public void setParentpost(Postinfo parentpost) {
        this.parentpost = parentpost;
    }

    public String getPostrank() {
        return postrank;
    }

    public void setPostrank(String postrank) {
        this.postrank = postrank;
    }

    public String getFunctionaltype() {
        return functionaltype;
    }

    public void setFunctionaltype(String functionaltype) {
        this.functionaltype = functionaltype;
    }

    public String getChildrenPostnames() {
        return childrenPostnames;
    }

    public void setChildrenPostnames(String childrenPostnames) {
        this.childrenPostnames = childrenPostnames;
    }

    @Override
    public String toString() {
        return "Postinfo{" +
                "postcode='" + postcode + '\'' +
                ", parent_postcode='" + parent_postcode + '\'' +
                ", postname='" + postname + '\'' +
                ", functionaltypeid='" + functionaltypeid + '\'' +
                ", postfamilyid='" + postfamilyid + '\'' +
                ", postgradeid='" + postgradeid + '\'' +
                ", postrankid='" + postrankid + '\'' +
                ", postlevelid='" + postlevelid + '\'' +
                ", organization='" + organization + '\'' +
                ", jobdescription='" + jobdescription + '\'' +
                ", duty='" + duty + '\'' +
                ", entryrequirements='" + entryrequirements + '\'' +
                ", dutyfile='" + dutyfile + '\'' +
                ", state='" + state + '\'' +
                ", ordercode='" + ordercode + '\'' +
                ", node_level='" + node_level + '\'' +
                '}';
    }
}