package com.elex.oa.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Table(
        name = "BPM_DEF"
)
public class BpmDef{
    public static final String STATUS_INIT = "INIT";
    public static final String STATUS_DEPLOY = "DEPLOYED";
    @Id
    @Column(
            name = "DEF_ID_"
    )
    protected String defId;
    @Column(
            name = "SUBJECT_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    protected String subject;
    @Column(
            name = "DESCP_"
    )
    @Size(
            max = 1024
    )
    protected String descp;
    @Column(
            name = "KEY_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    protected String key;
    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 255
    )
    protected String actDefId;
    @Column(
            name = "ACT_DEP_ID_"
    )
    @Size(
            max = 255
    )
    protected String actDepId;
    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    protected String status;
    @Column(
            name = "VERSION_"
    )
    protected Integer version;
    @Column(
            name = "IS_MAIN_"
    )
    @Size(
            max = 20
    )
    protected String isMain;
    @Column(
            name = "SETTING_"
    )
    @Size(
            max = 2147483647
    )
    protected String setting;
    @Column(
            name = "MODEL_ID_"
    )
    @Size(
            max = 64
    )
    protected String modelId;
    @Column(
            name = "MAIN_DEF_ID_"
    )
    @Size(
            max = 64
    )
    protected String mainDefId;
    @Column(
            name = "TREE_ID_"
    )
    protected String treeId;

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

    public BpmDef() {
    }

    public BpmDef(String in_defId) {
        this.setDefId(in_defId);
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getDefId() {
        return this.defId;
    }

    public void setDefId(String aValue) {
        this.defId = aValue;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String aValue) {
        this.subject = aValue;
    }

    public String getDescp() {
        return this.descp;
    }

    public void setDescp(String aValue) {
        this.descp = aValue;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String aValue) {
        this.key = aValue;
    }

    public String getActDefId() {
        return this.actDefId;
    }

    public void setActDefId(String aValue) {
        this.actDefId = aValue;
    }

    public String getActDepId() {
        return this.actDepId;
    }

    public void setActDepId(String aValue) {
        this.actDepId = aValue;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String aValue) {
        this.status = aValue;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer aValue) {
        this.version = aValue;
    }

    public String getIsMain() {
        return this.isMain;
    }

    public void setIsMain(String aValue) {
        this.isMain = aValue;
    }

    public String getSetting() {
        return this.setting;
    }

    public void setSetting(String aValue) {
        this.setting = aValue;
    }

    public String getModelId() {
        return this.modelId;
    }

    public void setModelId(String aValue) {
        this.modelId = aValue;
    }

    public String getMainDefId() {
        return this.mainDefId;
    }

    public void setMainDefId(String aValue) {
        this.mainDefId = aValue;
    }

    public String getIdentifyLabel() {
        return this.subject;
    }

    public Serializable getPkId() {
        return this.defId;
    }

    public void setPkId(Serializable pkId) {
        this.defId = (String)pkId;
    }

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

    public boolean equals(Object object) {
        if(!(object instanceof BpmDef)) {
            return false;
        } else {
            BpmDef rhs = (BpmDef)object;
            return (new EqualsBuilder()).append(this.defId, rhs.defId).append(this.subject, rhs.subject).append(this.descp, rhs.descp).append(this.key, rhs.key).append(this.actDefId, rhs.actDefId).append(this.actDepId, rhs.actDepId).append(this.status, rhs.status).append(this.version, rhs.version).append(this.isMain, rhs.isMain).append(this.setting, rhs.setting).append(this.modelId, rhs.modelId).append(this.mainDefId, rhs.mainDefId).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.defId).append(this.subject).append(this.descp).append(this.key).append(this.actDefId).append(this.actDepId).append(this.status).append(this.version).append(this.isMain).append(this.setting).append(this.modelId).append(this.mainDefId).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("defId", this.defId).append("subject", this.subject).append("descp", this.descp).append("key", this.key).append("actDefId", this.actDefId).append("actDepId", this.actDepId).append("status", this.status).append("version", this.version).append("isMain", this.isMain).append("setting", this.setting).append("modelId", this.modelId).append("mainDefId", this.mainDefId).toString();
    }
}
