package com.elex.oa.entity;

import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.alibaba.fastjson.JSONObject;

@Table(
        name = "BPM_SOLUTION"
)
public class BpmSolution {
    public static final String STATUS_CREATED = "CREATED";
    public static final String STATUS_DEPLOYED = "DEPLOYED";
    public static final String FORMAL_YES = "yes";
    public static final String FORMAL_NO = "no";
    public static final Short GRANT_ALL = new Short("0");
    public static final Short GRANT_PART = new Short("1");
    public static final String DATA_SAVE_MODE_ALL = "all";
    public static final String DATA_SAVE_MODE_DB = "db";
    public static final String DATA_SAVE_MODE_JSON = "json";
    public static final int STEP_0 = 0;
    @Id
    @Column(
            name = "SOL_ID_"
    )
    private String solId;

    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 64
    )
    private String actDefId;

    @Column(
            name = "NAME_"
    )
    @Size(
            max = 100
    )
    @NotEmpty
    private String name;

    @Column(
            name = "KEY_"
    )
    @Size(
            max = 100
    )
    @NotEmpty
    private String key;

    @Column(
            name = "DEF_KEY_"
    )
    @Size(
            max = 255
    )
    private String defKey;

    @Column(
            name = "DESCP_"
    )
    @Size(
            max = 4000
    )
    private String descp;

    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String status;

    @Column(
            name = "formal_"
    )
    @Size(
            max = 64
    )
    private String formal;

    @Column(
            name = "STEP_"
    )
    @NotNull
    private Integer step;

    @Column(
            name = "HELP_ID_"
    )
    @Size(
            max = 64
    )
    private String helpId;

    @Column(
            name = "SUPPORT_MOBILE_"
    )
    private int supportMobile = 0;

    @Column(
            name = "IS_USE_BMODEL_"
    )
    @Size(
            max = 30
    )
    private String isUseBmodel;

    @Column(
            name = "GRANT_TYPE_"
    )
    private Short grantType = new Short("0");

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public void setSysTree(SysTree sysTree) {
        this.sysTree = sysTree;
    }

    public SysTree getSysTree() {
        return sysTree;
    }

    @Column(
            name = "TREE_ID_"
    )

    private String treeId;

    @Transient
    private SysTree sysTree;

    @Column(
            name = "SUBJECT_RULE_"
    )
    private String subjectRule;

    @Column(
            name = "TENANT_ID_"
    )
    private String tenantId;

    @Column(
            name = "CREATE_BY_"
    )
    private String createBy;
    @Column(
            name = "UPDATE_BY_"
    )
    private String updateBy;
    @Column(
            name = "CREATE_TIME_"

    )
    private  String createTime;

    @Column(
            name = "UPDATE_TIME_"
    )
    private  String updateTime;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSubjectRule() {
        return subjectRule;
    }

    public void setSubjectRule(String subjectRule) {
        this.subjectRule = subjectRule;
    }

    public String getSolId() {
        return solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    public String getActDefId() {
        return actDefId;
    }

    public void setActDefId(String actDefId) {
        this.actDefId = actDefId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormal() {
        return formal;
    }

    public void setFormal(String formal) {
        this.formal = formal;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
    }

    public int getSupportMobile() {
        return supportMobile;
    }

    public void setSupportMobile(int supportMobile) {
        this.supportMobile = supportMobile;
    }

    public String getIsUseBmodel() {
        return isUseBmodel;
    }

    public void setIsUseBmodel(String isUseBmodel) {
        this.isUseBmodel = isUseBmodel;
    }

    public Short getGrantType() {
        return grantType;
    }

    public void setGrantType(Short grantType) {
        this.grantType = grantType;
    }


    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public JSONObject getRightJson() {
        return rightJson;
    }

    public void setRightJson(JSONObject rightJson) {
        this.rightJson = rightJson;
    }

    public String getBoDefId() {
        return boDefId;
    }

    public void setBoDefId(String boDefId) {
        this.boDefId = boDefId;
    }

    public String getDataSaveMode() {
        return dataSaveMode;
    }

    public void setDataSaveMode(String dataSaveMode) {
        this.dataSaveMode = dataSaveMode;
    }

    @Column(
            name = "TREE_PATH_"
    )
    @Size(
            max = 512
            
    )
    private String treePath;
    @Transient
    private JSONObject rightJson;
    @Column(
            name = "BO_DEF_ID_"
    )
    @Size(
            max = 100
    )
    private String boDefId;
    
    @Column(
            name = "DATA_SAVE_MODE_"
    )
    @Size(
            max = 50
    )
    private String dataSaveMode = "db";



}
