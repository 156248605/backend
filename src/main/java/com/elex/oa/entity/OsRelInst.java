package com.elex.oa.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
@Table(
        name = "OS_REL_INST"
)
@JsonIgnoreProperties({"osRelType"})
public class OsRelInst{
    @Id
    @Column(
            name = "INST_ID_"
    )
    protected String instId;
    @Column(
            name = "REL_TYPE_"
    )
    @Size(
            max = 40
    )
    protected String relType;
    @Column(
            name = "REL_TYPE_KEY_"
    )
    @Size(
            max = 40
    )
    protected String relTypeKey;

    @Column(
            name = "IS_MAIN_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    protected String isMain;
    @Column(
            name = "PARTY1_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String party1;

    @Column(
            name = "PARTY2_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String party2;

    @Column(
            name = "DIM1_"
    )
    @Size(
            max = 64
    )
    protected String dim1;
    @Column(
            name = "DIM2_"
    )
    @Size(
            max = 64
    )
    protected String dim2;

    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 40
    )
    @NotEmpty
    protected String status;

    @Column(
            name = "ALIAS_"
    )
    @Size(
            max = 80
    )
    protected String alias;

    @Column(
            name = "PATH_"
    )
    @Size(
            max = 80
    )
    protected String path;

    @ManyToOne
    @JoinColumn(
            name = "REL_TYPE_ID_"
    )
    protected OsRelType osRelType;
    @Transient
    protected String relTypeName;
    @Transient
    protected String osRelTypeId;
    @Transient
    protected String relTypeCat;
    @Transient
    protected String groupName;
    @Transient
    protected String partyName2;
    @Transient
    protected String partyName1;
    @Transient
    protected String groupKey;

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

    public OsRelInst() {
    }

    public OsRelInst(String in_instId) {
        this.setInstId(in_instId);
    }

    public OsRelType getOsRelType() {
        return this.osRelType;
    }

    public void setOsRelType(OsRelType in_osRelType) {
        this.osRelType = in_osRelType;
    }

    public String getOsRelTypeId() {
        return StringUtils.isNotEmpty(this.osRelTypeId)?this.osRelTypeId:(this.osRelType != null?this.osRelType.getId():null);
    }

    public void setOsRelTypeId(String osRelTypeId) {
        this.osRelTypeId = osRelTypeId;
    }

    public String getPartyName2() {
        return this.partyName2;
    }

    public void setPartyName2(String partyName2) {
        this.partyName2 = partyName2;
    }

    public String getRelType() {
        return this.relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public String getIsMain() {
        return this.isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getInstId() {
        return this.instId;
    }

    public String getRelTypeName() {
        return StringUtils.isNotEmpty(this.relTypeName)?this.relTypeName:(this.osRelType != null?this.osRelType.getName():null);
    }

    public void setRelTypeName(String relTypeName) {
        this.relTypeName = relTypeName;
    }

    public void setInstId(String aValue) {
        this.instId = aValue;
    }

    public String getRelTypeId() {
        return this.getOsRelType() == null?null:this.getOsRelType().getId();
    }

    public void setRelTypeId(String aValue) {
        if(aValue == null) {
            this.osRelType = null;
        } else if(this.osRelType == null) {
            this.osRelType = new OsRelType(aValue);
        } else {
            this.osRelType.setId(aValue);
        }

    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRelTypeKey() {
        return this.relTypeKey;
    }

    public void setRelTypeKey(String aValue) {
        this.relTypeKey = aValue;
    }

    public String getParty1() {
        return this.party1;
    }

    public void setParty1(String aValue) {
        this.party1 = aValue;
    }

    public String getParty2() {
        return this.party2;
    }

    public void setParty2(String aValue) {
        this.party2 = aValue;
    }

    public String getDim1() {
        return this.dim1;
    }

    public void setDim1(String aValue) {
        this.dim1 = aValue;
    }

    public String getDim2() {
        return this.dim2;
    }

    public void setDim2(String aValue) {
        this.dim2 = aValue;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String aValue) {
        this.status = aValue;
    }

    public String getPartyName1() {
        return this.partyName1;
    }

    public void setPartyName1(String partyName1) {
        this.partyName1 = partyName1;
    }

    public String getIdentifyLabel() {
        return this.instId;
    }

    public Serializable getPkId() {
        return this.instId;
    }

    public void setPkId(Serializable pkId) {
        this.instId = (String)pkId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupKey() {
        return this.groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getRelTypeCat() {
        return StringUtils.isNotEmpty(this.relTypeCat)?this.relTypeCat:(this.osRelType != null?this.osRelType.getRelType():null);
    }

    public void setRelTypeCat(String relTypeCat) {
        this.relTypeCat = relTypeCat;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean equals(Object object) {
        if(!(object instanceof OsRelInst)) {
            return false;
        } else {
            OsRelInst rhs = (OsRelInst)object;
            return (new EqualsBuilder()).append(this.instId, rhs.instId).append(this.relTypeKey, rhs.relTypeKey).append(this.party1, rhs.party1).append(this.party2, rhs.party2).append(this.dim1, rhs.dim1).append(this.dim2, rhs.dim2).append(this.status, rhs.status).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).append(this.tenantId, rhs.tenantId).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.instId).append(this.relTypeKey).append(this.party1).append(this.party2).append(this.dim1).append(this.dim2).append(this.status).append(this.createBy).append(this.createTime).append(this.updateBy).append(this.updateTime).append(this.tenantId).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("instId", this.instId).append("relType", this.relTypeKey).append("party1", this.party1).append("party2", this.party2).append("dim1", this.dim1).append("dim2", this.dim2).append("status", this.status).append("createBy", this.createBy).append("createTime", this.createTime).append("updateBy", this.updateBy).append("updateTime", this.updateTime).append("tenantId", this.tenantId).toString();
    }
}

