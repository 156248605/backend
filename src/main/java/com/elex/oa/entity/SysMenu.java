package com.elex.oa.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.elex.oa.core.constants.MBoolean;
import com.elex.oa.core.entity.BaseTenantEntity;
import com.elex.oa.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
@Table(
        name = "SYS_MENU"
)
public class SysMenu extends BaseTenantEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(
            name = "MENU_ID_"
    )
    protected String menuId;
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 60
    )
    @NotEmpty
    protected String name;
    @Column(
            name = "KEY_"
    )
    @Size(
            max = 80
    )
    @NotEmpty
    protected String key;
    @Column(
            name = "ENTITY_NAME_"
    )
    @Size(
            max = 100
    )
    protected String entityName;
    @Column(
            name = "FORM_"
    )
    @Size(
            max = 80
    )
    protected String form;
    @Column(
            name = "ICON_CLS_"
    )
    @Size(
            max = 32
    )
    protected String iconCls;
    @Column(
            name = "IMG_"
    )
    @Size(
            max = 50
    )
    protected String img;
    @Column(
            name = "PARENT_ID_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String parentId;
    @Column(
            name = "DEPTH_"
    )
    protected Integer depth;
    @Column(
            name = "PATH_"
    )
    @Size(
            max = 256
    )
    protected String path;
    @Column(
            name = "SN_"
    )
    protected Integer sn;
    @Column(
            name = "CHILDS_"
    )
    protected Integer childs = Integer.valueOf(0);
    @Column(
            name = "URL_"
    )
    @Size(
            max = 256
    )
    protected String url;
    @Column(
            name = "SHOW_TYPE_"
    )
    @Size(
            max = 20
    )
    protected String showType;
    @Column(
            name = "IS_BTN_MENU_"
    )
    @Size(
            max = 20
    )
    protected String isBtnMenu;
    @ManyToOne
    @JoinColumn(
            name = "SYS_ID_"
    )
    protected Subsystem subsystem;
    @Column(
            name = "BO_LIST_ID_"
    )
    protected String boListId;
    @Transient
    protected List<SysMenu> childList;

    public SysMenu() {
        this.isBtnMenu = MBoolean.NO.name();
        this.boListId = "";
        this.childList = new ArrayList();
    }

    public SysMenu(String in_menuId) {
        this.isBtnMenu = MBoolean.NO.name();
        this.boListId = "";
        this.childList = new ArrayList();
        this.setMenuId(in_menuId);
    }

    public Subsystem getSubsystem() {
        return this.subsystem;
    }

    public void setSubsystem(Subsystem in_subsystem) {
        this.subsystem = in_subsystem;
    }

    public String getIsBtnMenu() {
        return this.isBtnMenu;
    }

    public void setIsBtnMenu(String isBtnMenu) {
        this.isBtnMenu = isBtnMenu;
    }

    public String getMenuId() {
        return this.menuId;
    }

    public void setMenuId(String aValue) {
        this.menuId = aValue;
    }

    public String getSysId() {
        return this.getSubsystem() == null?null:this.getSubsystem().getSysId();
    }

    public void setSysId(String aValue) {
        if(aValue == null) {
            this.subsystem = null;
        } else if(this.subsystem == null) {
            this.subsystem = new Subsystem(aValue);
        } else {
            this.subsystem.setSysId(aValue);
        }

    }

    public String getName() {
        return this.name;
    }

    public void setName(String aValue) {
        this.name = aValue;
    }

    public String getIconCls() {
        return StringUtils.isEmpty(this.iconCls) && this.depth != null && this.depth.intValue() == 1?"icon-window":this.iconCls;
    }

    public void setIconCls(String aValue) {
        this.iconCls = aValue;
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

    public Integer getSn() {
        return this.sn;
    }

    public void setSn(Integer aValue) {
        this.sn = aValue;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String aValue) {
        this.url = aValue;
    }

    public String getShowType() {
        return this.showType;
    }

    public void setShowType(String aValue) {
        this.showType = aValue;
    }

    public String getIdentifyLabel() {
        return this.menuId;
    }

    public Serializable getPkId() {
        return this.menuId;
    }

    public void setPkId(Serializable pkId) {
        this.menuId = (String)pkId;
    }

    public Integer getChilds() {
        return this.childs;
    }

    public void setChilds(Integer childs) {
        this.childs = childs;
    }

    public String getKey() {
        return StringUtil.isEmpty(this.key)?"":this.key.trim();
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getForm() {
        return this.form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<SysMenu> getChildList() {
        return this.childList;
    }

    public void setChildList(List<SysMenu> childList) {
        this.childList = childList;
    }

    public String getBoListId() {
        return this.boListId;
    }

    public void setBoListId(String boListId) {
        this.boListId = boListId;
    }

    public boolean equals(Object object) {
        if(!(object instanceof SysMenu)) {
            return false;
        } else {
            SysMenu rhs = (SysMenu)object;
            return (new EqualsBuilder()).append(this.menuId, rhs.menuId).append(this.name, rhs.name).append(this.iconCls, rhs.iconCls).append(this.parentId, rhs.parentId).append(this.depth, rhs.depth).append(this.path, rhs.path).append(this.sn, rhs.sn).append(this.url, rhs.url).append(this.showType, rhs.showType).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.menuId).append(this.name).append(this.iconCls).append(this.parentId).append(this.depth).append(this.path).append(this.sn).append(this.url).append(this.showType).append(this.createBy).append(this.createTime).append(this.updateBy).append(this.updateTime).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("menuId", this.menuId).append("name", this.name).append("icon", this.iconCls).append("parentId", this.parentId).append("depth", this.depth).append("path", this.path).append("sn", this.sn).append("url", this.url).append("showType", this.showType).append("createBy", this.createBy).append("createTime", this.createTime).append("updateBy", this.updateBy).append("updateTime", this.updateTime).toString();
    }
}

