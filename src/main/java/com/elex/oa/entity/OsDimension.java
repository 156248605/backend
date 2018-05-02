package com.elex.oa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
@Table(
        name = "OS_DIMENSION"
)
public class OsDimension {
    public static final String DIM_POS = "_POS";
    public static final String DIM_JOB = "_JOB";
    public static final String DIM_JOB_ID = "3";
    public static final String DIM_ADMIN = "_ADMIN";
    public static final String DIM_ADMIN_ID = "1";
    public static final String DIM_ROLE = "_ROLE";
    public static final String DIM_ROLE_ID = "2";
    public static final String SHOW_TYPE_TREE = "TREE";
    public static final String SHOW_TYPE_FLAT = "FLAT";

    @Id
    @Column(
            name = "DIM_ID_"
    )
    private String dimId;
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 40
    )
    @NotEmpty
    private String name;
    @Column(
            name = "DIM_KEY_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String dimKey;

    @Column(
            name = "IS_COMPOSE_"
    )
    @Size(
            max = 10
    )
    @NotEmpty
    private String isCompose;
    @Column(
            name = "IS_SYSTEM_"
    )
    @Size(
            max = 10
    )
    @NotEmpty
    private String isSystem;
    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 40
    )
    @NotEmpty
    private String status;
    @Column(
            name = "SN_"
    )
    private Integer sn;
    @Column(
            name = "SHOW_TYPE_"
    )
    @Size(
            max = 40
    )
    @NotEmpty
    private String showType;
    @Column(
            name = "IS_GRANT_"
    )
    @Size(
            max = 10
    )
    private String isGrant;
    @Column(
            name = "DESC_"
    )
    @Size(
            max = 255
    )
    private String desc;

    @Transient
    private String iconCls = "icon-group";

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

    public OsDimension() {
    }

    public String getIconCls() {
        return this.iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public OsDimension(String in_dimId) {
        this.setDimId(in_dimId);
    }

    public String getDimId() {
        return this.dimId;
    }

    public void setDimId(String aValue) {
        this.dimId = aValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String aValue) {
        this.name = aValue;
    }

    public String getDimKey() {
        return this.dimKey;
    }

    public void setDimKey(String aValue) {
        this.dimKey = aValue;
    }

    public String getIsCompose() {
        return this.isCompose;
    }

    public void setIsCompose(String aValue) {
        this.isCompose = aValue;
    }

    public String getIsSystem() {
        return this.isSystem;
    }

    public void setIsSystem(String aValue) {
        this.isSystem = aValue;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String aValue) {
        this.status = aValue;
    }

    public Integer getSn() {
        return this.sn;
    }

    public void setSn(Integer aValue) {
        this.sn = aValue;
    }

    public String getShowType() {
        return this.showType;
    }

    public void setShowType(String aValue) {
        this.showType = aValue;
    }

    public String getIsGrant() {
        return this.isGrant;
    }

    public void setIsGrant(String aValue) {
        this.isGrant = aValue;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String aValue) {
        this.desc = aValue;
    }

    public String getIdentifyLabel() {
        return this.name;
    }

    public Serializable getPkId() {
        return this.dimId;
    }

    public void setPkId(Serializable pkId) {
        this.dimId = (String)pkId;
    }

    public boolean equals(Object object) {
        if(!(object instanceof OsDimension)) {
            return false;
        } else {
            OsDimension rhs = (OsDimension)object;
            return (new EqualsBuilder()).append(this.dimId, rhs.dimId).append(this.name, rhs.name).append(this.dimKey, rhs.dimKey).append(this.isCompose, rhs.isCompose).append(this.isSystem, rhs.isSystem).append(this.status, rhs.status).append(this.sn, rhs.sn).append(this.showType, rhs.showType).append(this.isGrant, rhs.isGrant).append(this.desc, rhs.desc).append(this.tenantId, rhs.tenantId).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }


}

