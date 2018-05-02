package com.elex.oa.entity;

/**
 *@author hugo.zhao
 *@since 2018/4/25 15:13
*/
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Table(
        name = "SYS_ACCOUNT"
)
public class SysAccount{
    @Id
    @Column(
            name = "ACCOUNT_ID_"
    )
    protected String accountId;
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 50
    )
    @NotEmpty
    protected String name;
    @Column(
            name = "PWD_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String pwd;

    @Column(
            name = "ENC_TYPE_"
    )
    @Size(
            max = 20
    )
    protected String encType;

    @Column(
            name = "DOMAIN_"
    )
    @Size(
            max = 20
    )
    protected String domain;
    @Column(
            name = "FULLNAME_"
    )
    @Size(
            max = 256
    )
    @NotEmpty
    protected String fullname;

    @Column(
            name = "USER_ID_"
    )
    @Size(
            max = 64
    )
    protected String userId;

    @Column(
            name = "REMARK_"
    )
    @Size(
            max = 200
    )
    protected String remark;

    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    protected String status;

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

    public SysAccount() {
    }

    public SysAccount(String in_accountId) {
        this.setAccountId(in_accountId);
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String aValue) {
        this.accountId = aValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String aValue) {
        this.name = aValue;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String aValue) {
        this.pwd = aValue;
    }

    public String getEncType() {
        return this.encType;
    }

    public void setEncType(String aValue) {
        this.encType = aValue;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String aValue) {
        this.fullname = aValue;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String aValue) {
        this.userId = aValue;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String aValue) {
        this.remark = aValue;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String aValue) {
        this.status = aValue;
    }

    public String getIdentifyLabel() {
        return this.accountId;
    }

    public Serializable getPkId() {
        return this.accountId;
    }

    public void setPkId(Serializable pkId) {
        this.accountId = (String)pkId;
    }

    public boolean equals(Object object) {
        if(!(object instanceof SysAccount)) {
            return false;
        } else {
            SysAccount rhs = (SysAccount)object;
            return (new EqualsBuilder()).append(this.accountId, rhs.accountId).append(this.tenantId, rhs.tenantId).isEquals();
        }
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.accountId).append(this.name).append(this.pwd).append(this.encType).append(this.createTime).append(this.fullname).append(this.userId).append(this.tenantId).append(this.remark).append(this.status).append(this.updateBy).append(this.updateTime).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("accountId", this.accountId).append("name", this.name).append("pwd", this.pwd).append("encType", this.encType).append("createTime", this.createTime).append("fullname", this.fullname).append("userId", this.userId).append("tenantId", this.tenantId).append("remark", this.remark).append("status", this.status).append("createBy", this.createBy).append("updateBy", this.updateBy).append("updateTime", this.updateTime).toString();
    }
}

