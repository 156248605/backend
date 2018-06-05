package com.elex.oa.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.elex.oa.core.entity.BaseTenantEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Table(
        name = "SYS_SUBSYS"
)
/*@TableDefine(
        title = "子系统"
)*/
public class Subsystem extends BaseTenantEntity implements Comparable<Subsystem> {
    public static final String SYS_BASE = "SYS_BASE";
    public static final String SYS_ORG = "SYS_ORG";
/*    @FieldDefine(
            title = "系统ID",
            group = "基本信息"
    )*/
    @Id
    @Column(
            name = "SYS_ID_"
    )
    protected String sysId;
/*    @FieldDefine(
            title = "系统名称",
            group = "基本信息"
    )*/
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 80
    )
    @NotEmpty
    protected String name;
/*    @FieldDefine(
            title = "系统Key",
            group = "基本信息"
    )*/
    @Column(
            name = "KEY_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String key;
/*    @FieldDefine(
            title = "系统Logo",
            group = "基本信息"
    )*/
    @Column(
            name = "LOGO_"
    )
    @Size(
            max = 120
    )
    protected String logo;
/*    @FieldDefine(
            title = "是否缺省",
            group = "基本信息"
    )*/
    @Column(
            name = "IS_DEFAULT_"
    )
    @Size(
            max = 12
    )
    @NotEmpty
    protected String isDefault;
/*    @FieldDefine(
            title = "首页地址",
            group = "基本信息"
    )*/
    @Column(
            name = "HOME_URL_"
    )
    @Size(
            max = 120
    )
    protected String homeUrl;
/*    @FieldDefine(
            title = "图标样式",
            group = "基本信息"
    )*/
    @Column(
            name = "ICON_CLS_"
    )
    @Size(
            max = 50
    )
    protected String iconCls;
/*    @FieldDefine(
            title = "序号",
            group = "基本信息"
    )*/
    @Column(
            name = "SN_"
    )
    protected Integer sn;
/*    @FieldDefine(
            title = "状态",
            group = "基本信息"
    )*/
    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    protected String status;
/*    @FieldDefine(
            title = "描述",
            group = "基本信息"
    )*/
    @Column(
            name = "DESCP_"
    )
    @Size(
            max = 256
    )
    protected String descp;
/*    @FieldDefine(
            title = "是否允许SAAS",
            group = "基本信息"
    )*/
    @Column(
            name = "IS_SAAS_"
    )
    protected String isSaas;
/*    @FieldDefine(
            title = "机构类型编码",
            group = "基本信息"
    )*/
    @Column(
            name = "INST_TYPE_"
    )
    protected String instType;

    public Subsystem() {
    }

    public Subsystem(String in_sysId) {
        this.setSysId(in_sysId);
    }

    public String getSysId() {
        return this.sysId;
    }

    public void setSysId(String aValue) {
        this.sysId = aValue;
    }

    public String getInstType() {
        return this.instType;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String aValue) {
        this.name = aValue;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String aValue) {
        this.key = aValue;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String aValue) {
        this.logo = aValue;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String aValue) {
        this.isDefault = aValue;
    }

    public String getHomeUrl() {
        return this.homeUrl;
    }

    public void setHomeUrl(String aValue) {
        this.homeUrl = aValue;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String aValue) {
        this.status = aValue;
    }

    public String getDescp() {
        return this.descp;
    }

    public String getIconCls() {
        return this.iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public void setDescp(String aValue) {
        this.descp = aValue;
    }

    public String getIdentifyLabel() {
        return this.name;
    }

    public Serializable getPkId() {
        return this.sysId;
    }

    public void setPkId(Serializable pkId) {
        this.sysId = (String)pkId;
    }

    public Integer getSn() {
        return this.sn == null?Integer.valueOf(0):this.sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getIsSaas() {
        return this.isSaas;
    }

    public void setIsSaas(String isSaas) {
        this.isSaas = isSaas;
    }

    public boolean equals(Object object) {
        if(!(object instanceof Subsystem)) {
            return false;
        } else {
            Subsystem rhs = (Subsystem)object;
            return (new EqualsBuilder()).append(this.sysId, rhs.sysId).append(this.name, rhs.name).append(this.key, rhs.key).append(this.logo, rhs.logo).append(this.isDefault, rhs.isDefault).append(this.homeUrl, rhs.homeUrl).append(this.status, rhs.status).append(this.descp, rhs.descp).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.sysId).append(this.name).append(this.key).append(this.logo).append(this.isDefault).append(this.homeUrl).append(this.status).append(this.descp).append(this.createBy).append(this.createTime).append(this.updateBy).append(this.updateTime).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("sysId", this.sysId).append("name", this.name).append("key", this.key).append("logo", this.logo).append("isDefault", this.isDefault).append("homeUrl", this.homeUrl).append("status", this.status).append("descp", this.descp).append("createBy", this.createBy).append("createTime", this.createTime).append("updateBy", this.updateBy).append("updateTime", this.updateTime).toString();
    }

    public int compareTo(Subsystem o) {
        return this.getSn().intValue() - o.getSn().intValue();
    }
}
