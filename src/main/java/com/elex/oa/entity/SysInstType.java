package com.elex.oa.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.elex.oa.core.entity.BaseTenantEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Table(
        name = "SYS_INST_TYPE"
)
/*@TableDefine(
        title = "机构类型"
)*/
public class SysInstType extends BaseTenantEntity {
    public static final String INST_TYPE_PLATFORM = "PLATFORM";
/*    @FieldDefine(
            title = "类型"
    )*/
    @Id
    @Column(
            name = "TYPE_ID_"
    )
    protected String typeId;
/*    @FieldDefine(
            title = "类型编码"
    )*/
    @Column(
            name = "TYPE_CODE_"
    )
    protected String typeCode;
/*    @FieldDefine(
            title = "类型名称"
    )*/
    @Column(
            name = "TYPE_NAME_"
    )
    protected String typeName;
/*    @FieldDefine(
            title = "是否启用"
    )*/
    @Column(
            name = "ENABLED_"
    )
    protected String enabled;
/*    @FieldDefine(
            title = "是否系统缺省"
    )*/
    @Column(
            name = "IS_DEFAULT_"
    )
    protected String isDefault;
/*    @FieldDefine(
            title = "描述"
    )*/
    @Column(
            name = "DESCP_"
    )
    protected String descp;
/*    @FieldDefine(
            title = "后台访问首页",
            defaultCol = MBoolean.YES
    )*/
    @Column(
            name = "HOME_URL_"
    )
    @Size(
            max = 200
    )
    protected String homeUrl;

    public SysInstType() {
    }

    public SysInstType(String in_id) {
        this.setPkId(in_id);
    }

    public String getIdentifyLabel() {
        return this.typeId;
    }

    public Serializable getPkId() {
        return this.typeId;
    }

    public void setPkId(Serializable pkId) {
        this.typeId = (String)pkId;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String aValue) {
        this.typeId = aValue;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getEnabled() {
        return this.enabled;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getDescp() {
        return this.descp;
    }

    public String getHomeUrl() {
        return this.homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public boolean equals(Object object) {
        if(!(object instanceof SysInstType)) {
            return false;
        } else {
            SysInstType rhs = (SysInstType)object;
            return (new EqualsBuilder()).append(this.typeId, rhs.typeId).append(this.typeCode, rhs.typeCode).append(this.typeName, rhs.typeName).append(this.enabled, rhs.enabled).append(this.isDefault, rhs.isDefault).append(this.descp, rhs.descp).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.typeId).append(this.typeCode).append(this.typeName).append(this.enabled).append(this.isDefault).append(this.descp).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("typeId", this.typeId).append("typeCode", this.typeCode).append("typeName", this.typeName).append("enabled", this.enabled).append("isDefault", this.isDefault).append("descp", this.descp).toString();
    }
}
