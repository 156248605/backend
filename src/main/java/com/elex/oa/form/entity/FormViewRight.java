package com.elex.oa.form.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(
        name = "BPM_FV_RIGHT"
)
/*@TableDefine(
        title = "表单视图字段权限"
)*/
@JsonIgnoreProperties({"bpmFormView"})
public class FormViewRight{
    public static final String NODE_FORM = "_FORM";
    public static final String NODE_PROCESS = "_PROCESS";
    public static final String NODE_DETAIL = "_DETAIL";
    public static final String NODE_START = "_START";
    public static final String RIGHT_TYPE_FORM = "form";
    public static final String RIGHT_TYPE_TABLE = "table";
    public static final String RIGHT_TYPE_DEALWITH = "dealwith";
    public static final String RIGHT_TYPE_OPINION = "opinion";
    public static final String RIGHT_TYPE_BUTTON = "button";
 /*   @FieldDefine(
            title = "PKID"
    )*/
    @Id
    @Column(
            name = "RIGHT_ID_"
    )
    protected String rightId;
/*    @FieldDefine(
            title = "视图ID"
    )*/
    @Column(
            name = "VIEW_ID_"
    )
    @Size(
            max = 64
    )
    protected String viewId;
/*    @FieldDefine(
            title = "字段路径"
    )*/
    @Column(
            name = "FIELD_NAME_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    protected String fieldName;
 /*   @FieldDefine(
            title = ""
    )*/
    @Column(
            name = "FIELD_LABEL_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    protected String fieldLabel;
/*    @FieldDefine(
            title = "编辑权限"
    )*/
    @Column(
            name = "EDIT_"
    )
    @Size(
            max = 65535
    )
    protected String edit;
/*    @FieldDefine(
            title = "编辑权限描述"
    )*/
    @Column(
            name = "EDIT_DF_"
    )
    @Size(
            max = 65535
    )
    protected String editDf;
/*    @FieldDefine(
            title = "只读权限"
    )*/
    @Column(
            name = "READ_"
    )
    @Size(
            max = 65535
    )
    protected String read;
/*    @FieldDefine(
            title = "只读权限描述"
    )*/
    @Column(
            name = "READ_DF_"
    )
    @Size(
            max = 65535
    )
    protected String readDf;
/*    @FieldDefine(
            title = "隐藏权限"
    )*/
    @Column(
            name = "HIDE_"
    )
    @Size(
            max = 65535
    )
    protected String hide;
/*    @FieldDefine(
            title = "隐藏权限描述"
    )*/
    @Column(
            name = "HIDE_DF_"
    )
    @Size(
            max = 65535
    )
    protected String hideDf;
/*    @FieldDefine(
            title = "序号"
    )*/
    @Column(
            name = "SN_"
    )
    protected Integer sn;
/*    @FieldDefine(
            title = "流程节点ID"
    )*/
    @Column(
            name = "NODE_ID_"
    )
    @Size(
            max = 64
    )
    protected String nodeId;
/*    @FieldDefine(
            title = "流程解决方案Id"
    )*/
    @Column(
            name = "SOL_ID_"
    )
    @Size(
            max = 64
    )
    protected String solId;
/*    @FieldDefine(
            title = "流程定义Id"
    )*/
    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 64
    )
    protected String actDefId;
/*    @FieldDefine(
            title = "意见类型"
    )*/
    @Column(
            name = "TYPE_"
    )
    @Size(
            max = 64
    )
    protected String type = "";
/*    @FieldDefine(
            title = "表单别名"
    )*/
    @Column(
            name = "FORM_ALIAS_"
    )
    @Size(
            max = 100
    )
    protected String formAlias;
/*    @FieldDefine(
            title = "已添加数据处理"
    )*/
    @Column(
            name = "DEALWITH_"
    )
    @Size(
            max = 100
    )
    protected String dealwith;
    @Transient
    protected String parentId;
    @Transient
    protected String iconCls;
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

    public String getViewId() {
        return this.viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public FormViewRight() {
    }

    public FormViewRight(String in_rightId) {
        this.setRightId(in_rightId);
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIconCls() {
        return this.iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getRightId() {
        return this.rightId;
    }

    public void setRightId(String aValue) {
        this.rightId = aValue;
    }

    public String getFormAlias() {
        return this.formAlias;
    }

    public void setFormAlias(String formAlias) {
        this.formAlias = formAlias;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public String getActDefId() {
        return this.actDefId;
    }

    public void setActDefId(String actDefId) {
        this.actDefId = actDefId;
    }

    public void setFieldName(String aValue) {
        this.fieldName = aValue;
    }

    public String getFieldLabel() {
        return this.fieldLabel;
    }

    public void setFieldLabel(String aValue) {
        this.fieldLabel = aValue;
    }

    public String getEdit() {
        return this.edit;
    }

    public void setEdit(String aValue) {
        this.edit = aValue;
    }

    public String getEditDf() {
        return this.editDf;
    }

    public void setEditDf(String aValue) {
        this.editDf = aValue;
    }

    public String getRead() {
        return this.read;
    }

    public void setRead(String aValue) {
        this.read = aValue;
    }

    public String getReadDf() {
        return this.readDf;
    }

    public void setReadDf(String aValue) {
        this.readDf = aValue;
    }

    public String getHide() {
        return this.hide;
    }

    public void setHide(String aValue) {
        this.hide = aValue;
    }

    public String getHideDf() {
        return this.hideDf;
    }

    public void setHideDf(String aValue) {
        this.hideDf = aValue;
    }

    public Integer getSn() {
        return this.sn;
    }

    public void setSn(Integer aValue) {
        this.sn = aValue;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String aValue) {
        this.nodeId = aValue;
    }

    public String getSolId() {
        return this.solId;
    }

    public void setSolId(String aValue) {
        this.solId = aValue;
    }

    public String getIdentifyLabel() {
        return this.rightId;
    }

    public Serializable getPkId() {
        return this.rightId;
    }

    public void setPkId(Serializable pkId) {
        this.rightId = (String)pkId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDealwith() {
        return this.dealwith;
    }

    public void setDealwith(String dealwith) {
        this.dealwith = dealwith;
    }

    public boolean equals(Object object) {
        if(!(object instanceof FormViewRight)) {
            return false;
        } else {
            FormViewRight rhs = (FormViewRight)object;
            return (new EqualsBuilder()).append(this.rightId, rhs.rightId).append(this.fieldName, rhs.fieldName).append(this.fieldLabel, rhs.fieldLabel).append(this.edit, rhs.edit).append(this.editDf, rhs.editDf).append(this.read, rhs.read).append(this.readDf, rhs.readDf).append(this.hide, rhs.hide).append(this.hideDf, rhs.hideDf).append(this.sn, rhs.sn).append(this.nodeId, rhs.nodeId).append(this.solId, rhs.solId).append(this.tenantId, rhs.tenantId).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

}

