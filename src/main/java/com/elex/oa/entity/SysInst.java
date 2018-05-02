package com.elex.oa.entity;

import com.elex.oa.org.model.ITenant;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *@author hugo.zhao
 *@since 2018/4/26 11:47
*/
@Table(
        name = "SYS_INST"
)
public class SysInst implements ITenant {
    @Id
    @Column(
            name = "INST_ID_"
    )
    protected String instId;
    @Column(
            name = "NAME_CN_"
    )
    @Size(
            max = 256
    )
    @NotEmpty
    protected String nameCn;
    @Column(
            name = "NAME_EN_"
    )
    @Size(
            max = 256
    )
    @NotEmpty
    protected String nameEn;
    @Column(
            name = "BUS_LICE_NO_"
    )
    @Size(
            max = 50
    )
    @NotEmpty
    protected String busLiceNo;
    @Column(
            name = "INST_NO_"
    )
    @Size(
            max = 50
    )
    @NotEmpty
    protected String instNo;

    @Column(
            name = "DOMAIN_"
    )
    @Size(
            max = 100
    )
    @NotEmpty
    protected String domain;
    @Column(
            name = "BUS_LICE_FILE_ID_"
    )
    @Size(
            max = 64
    )
    protected String busLiceFileId;
    @Column(
            name = "REG_CODE_FILE_ID_"
    )
    @Size(
            max = 64
    )
    protected String regCodeFileId;

    @Column(
            name = "NAME_CN_S_"
    )
    @Size(
            max = 80
    )
    protected String nameCnS;

    @Column(
            name = "NAME_EN_S_"
    )
    @Size(
            max = 80
    )
    protected String nameEnS;

    @Column(
            name = "LEGAL_MAN_"
    )
    @Size(
            max = 64
    )
    protected String legalMan;

    @Column(
            name = "DESCP_"
    )
    @Size(
            max = 2147483647
    )
    protected String descp;

    @Column(
            name = "ADDRESS_"
    )
    @Size(
            max = 128
    )
    protected String address;

    @Column(
            name = "PHONE_"
    )
    @Size(
            max = 30
    )
    protected String phone;

    @Column(
            name = "EMAIL_"
    )
    @Size(
            max = 255
    )
    protected String email;

    @Column(
            name = "FAX_"
    )
    @Size(
            max = 30
    )
    protected String fax;

    @Column(
            name = "CONTRACTOR_"
    )
    @Size(
            max = 30
    )
    protected String contractor;

    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 30
    )
    protected String status;

    @Column(
            name = "DS_NAME_"
    )
    @Size(
            max = 30
    )
    protected String dsName = "";

    @Column(
            name = "DS_ALIAS_"
    )
    @Size(
            max = 30
    )
    protected String dsAlias = "";

    @Column(
            name = "INST_TYPE_"
    )
    @Size(
            max = 50
    )
    protected String instType = "";

    @Column(
            name = "HOME_URL_"
    )
    @Size(
            max = 50
    )
    protected String homeUrl;
    @Column(
            name = "PARENT_ID_"
    )
    @Size(
            max = 64
    )
    protected String parentId;
    @Column(
            name = "PATH_"
    )
    @Size(
            max = 1024
    )
    protected String path;
    @Transient
    protected String validCode;

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

    public String getValidCode() {
        return this.validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public SysInst() {
    }

    public SysInst(String in_instId) {
        this.setInstId(in_instId);
    }

    public String getInstId() {
        return this.instId;
    }

    public void setInstId(String aValue) {
        this.instId = aValue;
    }

    public String getInstType() {
        return this.instType;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    public String getHomeUrl() {
        return this.homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public String getNameCn() {
        return this.nameCn;
    }

    public void setNameCn(String aValue) {
        this.nameCn = aValue;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNameEn(String aValue) {
        this.nameEn = aValue;
    }

    public String getInstNo() {
        return this.instNo;
    }

    public void setInstNo(String aValue) {
        this.instNo = aValue;
    }

    public String getNameCnS() {
        return this.nameCnS;
    }

    public void setNameCnS(String aValue) {
        this.nameCnS = aValue;
    }

    public String getNameEnS() {
        return this.nameEnS;
    }

    public void setNameEnS(String aValue) {
        this.nameEnS = aValue;
    }

    public String getLegalMan() {
        return this.legalMan;
    }

    public void setLegalMan(String aValue) {
        this.legalMan = aValue;
    }

    public String getDescp() {
        return this.descp;
    }

    public void setDescp(String aValue) {
        this.descp = aValue;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String aValue) {
        this.address = aValue;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String aValue) {
        this.phone = aValue;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String aValue) {
        this.fax = aValue;
    }

    public String getContractor() {
        return this.contractor;
    }

    public void setContractor(String aValue) {
        this.contractor = aValue;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBusLiceNo() {
        return this.busLiceNo;
    }

    public void setBusLiceNo(String busLiceNo) {
        this.busLiceNo = busLiceNo;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getBusLiceFileId() {
        return this.busLiceFileId;
    }

    public void setBusLiceFileId(String busLiceFileId) {
        this.busLiceFileId = busLiceFileId;
    }

    public String getRegCodeFileId() {
        return this.regCodeFileId;
    }

    public void setRegCodeFileId(String regCodeFileId) {
        this.regCodeFileId = regCodeFileId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String aValue) {
        this.status = aValue;
    }

    public String getIdentifyLabel() {
        return this.nameCnS;
    }

    public Serializable getPkId() {
        return this.instId;
    }

    public void setPkId(Serializable pkId) {
        this.instId = (String)pkId;
    }

    public String getDsName() {
        return this.dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getDsAlias() {
        return this.dsAlias;
    }

    public void setDsAlias(String dsAlias) {
        this.dsAlias = dsAlias;
    }

    public boolean equals(Object object) {
        if(!(object instanceof SysInst)) {
            return false;
        } else {
            SysInst rhs = (SysInst)object;
            return (new EqualsBuilder()).append(this.instId, rhs.instId).append(this.nameCn, rhs.nameCn).append(this.nameEn, rhs.nameEn).append(this.instNo, rhs.instNo).append(this.nameCnS, rhs.nameCnS).append(this.nameEnS, rhs.nameEnS).append(this.legalMan, rhs.legalMan).append(this.descp, rhs.descp).append(this.address, rhs.address).append(this.phone, rhs.phone).append(this.fax, rhs.fax).append(this.contractor, rhs.contractor).append(this.status, rhs.status).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

    public String getTenantId() {
        return this.instId;
    }

    public String getTenantName() {
        return this.nameCn;
    }

    public String getShortName() {
        return this.nameCnS;
    }


}
