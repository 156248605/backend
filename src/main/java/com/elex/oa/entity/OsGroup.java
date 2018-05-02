package com.elex.oa.entity;
import com.elex.oa.org.model.IGroup;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *@author hugo.zhao
 *@since 2018/4/25 13:26
*/
public class OsGroup implements IGroup {
    @Id
    @Column(
            name = "GROUP_ID_"
    )
    private String groupId;
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 128
    )
    @NotEmpty
    private String name;
    @Column(
            name = "KEY_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String key;

    @Column(
            name = "RANK_LEVEL_"
    )
    private Integer rankLevel;

    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 40
    )
    @NotEmpty
    private String status;

    @Column(
            name = "DESCP_"
    )
    @Size(
            max = 255
    )
    private String descp;

    @Column(
            name = "SN_"
    )
    private Integer sn;

    @Column(
            name = "PARENT_ID_"
    )
    @Size(
            max = 64
    )
    private String parentId;

    @Column(
            name = "DEPTH_"
    )
    private Integer depth;

    @Column(
            name = "PATH_"
    )
    @Size(
            max = 1024
    )
    private String path;
    @Transient
    private String iconCls = "icon-group";

    @Column(
            name = "CHILDS_"
    )
    private Integer childs;

    @Column(
            name = "FORM_"
    )
    @Size(
            max = 20
    )
    private String form;

    @Column(
            name = "IS_DEFAULT_"
    )
    @Size(
            max = 40
    )
    private String isDefault;

    @Column(
            name = "DIM_ID_"
    )
    private String osDimension;

    @Column(
            name = "SYNC_WX_"
    )
    private int syncWx = 0;

    @Column(
            name = "WX_PARENT_PID_"
    )
    private Integer wxParentPid;
    @Column(
            name = "WX_PID_"
    )
    private Integer wxPid;
    @Transient
    private String dimId;
    @Transient
    private OsGroup parentGroup;
    @Transient
    private String isMain;
    @Transient
    private String orginId;

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

    @Override
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

    public Integer getWxParentPid() {
        return this.wxParentPid;
    }

    public void setWxParentPid(Integer wxParentPid) {
        this.wxParentPid = wxParentPid;
    }

    public Integer getWxPid() {
        return this.wxPid;
    }

    public void setWxPid(Integer wxPid) {
        this.wxPid = wxPid;
    }

    public String getIsLeaf() {
        return this.childs != null && this.childs.intValue() != 0?"false":"true";
    }

    public String getExpanded() {
        return "false";
    }

    public OsGroup() {
    }

    public String getIconCls() {
        return this.iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public OsGroup(String in_groupId) {
        this.setGroupId(in_groupId);
    }

    public OsGroup getParentGroup() {
        return this.parentGroup;
    }

    public void setParentGroup(OsGroup parentGroup) {
        this.parentGroup = parentGroup;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String aValue) {
        this.groupId = aValue;
    }

    public String getDimId() {
        return this.dimId;
    }

    public void setDimId(String dimId) {
        this.dimId = dimId;
    }

    public String getIsMain() {
        return this.isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
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

    @Override
    public String getType() {
        return null;
    }

    public void setKey(String aValue) {
        this.key = aValue;
    }

    public Integer getRankLevel() {
        return this.rankLevel;
    }

    public void setRankLevel(Integer aValue) {
        this.rankLevel = aValue;
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

    public void setDescp(String aValue) {
        this.descp = aValue;
    }

    public Integer getSn() {
        return this.sn;
    }

    public void setSn(Integer aValue) {
        this.sn = aValue;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String aValue) {
        this.parentId = aValue;
    }

    public Integer getDepth() {
        return this.depth;
    }

    public void setDepth(Integer aValue) {
        this.depth = aValue;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String aValue) {
        this.path = aValue;
    }

    public Integer getChilds() {
        return this.childs;
    }

    public void setChilds(Integer childs) {
        this.childs = childs;
    }

    public String getForm() {
        return this.form;
    }

    public void setForm(String aValue) {
        this.form = aValue;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String aValue) {
        this.isDefault = aValue;
    }

    public String getIdentifyLabel() {
        return this.name;
    }

    public Serializable getPkId() {
        return this.groupId;
    }

    public void setPkId(Serializable pkId) {
        this.groupId = (String)pkId;
    }

    public boolean equals(Object object) {
        if(!(object instanceof OsGroup)) {
            return false;
        } else {
            OsGroup rhs = (OsGroup)object;
            return (new EqualsBuilder()).append(this.groupId, rhs.groupId).append(this.name, rhs.name).append(this.key, rhs.key).append(this.rankLevel, rhs.rankLevel).append(this.status, rhs.status).append(this.descp, rhs.descp).append(this.sn, rhs.sn).append(this.parentId, rhs.parentId).append(this.depth, rhs.depth).append(this.path, rhs.path).append(this.form, rhs.form).append(this.isDefault, rhs.isDefault).append(this.tenantId, rhs.tenantId).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

    public String getIdentityType() {
        return "GROUP";
    }

    public String getIdentityName() {
        return this.name;
    }

    public String getOsDimension() {
        return osDimension;
    }

    public void setOsDimension(String osDimension) {
        this.osDimension = osDimension;
    }

    public String getIdentityId() {
        return this.groupId;
    }

    public int getSyncWx() {
        return this.syncWx;
    }

    public void setSyncWx(int syncWx) {
        this.syncWx = syncWx;
    }

    public String getOrginId() {
        return this.orginId;
    }

    public void setOrginId(String orginId) {
        this.orginId = orginId;
    }
}
