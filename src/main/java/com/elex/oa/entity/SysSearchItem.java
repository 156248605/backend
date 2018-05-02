package com.elex.oa.entity;

/**
 *@author hugo.zhao
 *@since 2018/4/23 10:50
*/
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
@Table(
        name = "SYS_SEARCH_ITEM"
)

public class SysSearchItem{
    @Transient
    private String id;
    @Id
    @Column(
            name = "ITEM_ID_"
    )
    private String itemId;
    @Column(
            name = "NODE_TYPE_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    private String nodeType;
    @Column(
            name = "NODE_TYPE_LABEL_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    private String nodeTypeLabel;
    @Column(
            name = "PARENT_ID_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String parentId;
    @Column(
            name = "PATH_"
    )
    @Size(
            max = 256
    )
    private String path;
    @Column(
            name = "FIELD_TYPE_"
    )
    @Size(
            max = 20
    )
    private String fieldType;
    @Column(
            name = "SN_"
    )
    private Integer sn;
    @Column(
            name = "LABEL_"
    )
    @Size(
            max = 100
    )
    @NotEmpty
    private String label;
    @Column(
            name = "FIELD_OP_"
    )
    @Size(
            max = 20
    )
    private String fieldOp;
    @Column(
            name = "FIELD_OP_LABEL_"
    )
    @Size(
            max = 20
    )
    private String fieldOpLabel;
    @Column(
            name = "FIELD_TITLE_"
    )
    @Size(
            max = 50
    )
    private String fieldTitle;
    @Column(
            name = "FIELD_NAME_"
    )
    @Size(
            max = 64
    )
    private String fieldName;
    @Column(
            name = "FIELD_VAL_"
    )
    @Size(
            max = 80
    )
    private String fieldVal;
    @Column(
            name = "CTL_TYPE_"
    )
    @Size(
            max = 50
    )
    private String ctlType;
    @Column(
            name = "FORMAT_"
    )
    @Size(
            max = 50
    )
    private String format;
    @Column(
            name = "PRE_HANDLE_"
    )
    @Size(
            max = 65535
    )
    private String preHandle;
    @Column(
            name = "FIELD_ID_"
    )
    private String sysField;
    @Column(
            name = "SEARCH_ID_"
    )
    private String sysSearch;

    public SysSearchItem() {
    }

    public SysSearchItem(String in_itemId) {
        this.setItemId(in_itemId);
    }


    public String getNodeTypeLabel() {
        return this.nodeTypeLabel;
    }

    public void setNodeTypeLabel(String nodeTypeLabel) {
        this.nodeTypeLabel = nodeTypeLabel;
    }

    public String getFieldOpLabel() {
        return this.fieldOpLabel;
    }

    public void setFieldOpLabel(String fieldOpLabel) {
        this.fieldOpLabel = fieldOpLabel;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String aValue) {
        this.itemId = aValue;
    }


    public Integer getSn() {
        return this.sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }


    public String getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(String aValue) {
        this.nodeType = aValue;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String aValue) {
        this.parentId = aValue;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String aValue) {
        this.path = aValue;
    }

    public String getFieldType() {
        return this.fieldType;
    }

    public void setFieldType(String aValue) {
        this.fieldType = aValue;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String aValue) {
        this.label = aValue;
    }

    public String getFieldOp() {
        return this.fieldOp;
    }

    public void setFieldOp(String aValue) {
        this.fieldOp = aValue;
    }

    public String getFieldTitle() {
        return this.fieldTitle;
    }

    public void setFieldTitle(String aValue) {
        this.fieldTitle = aValue;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String aValue) {
        this.fieldName = aValue;
    }

    public String getFieldVal() {
        return this.fieldVal;
    }

    public void setFieldVal(String aValue) {
        this.fieldVal = aValue;
    }

    public String getCtlType() {
        return this.ctlType;
    }

    public void setCtlType(String aValue) {
        this.ctlType = aValue;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String aValue) {
        this.format = aValue;
    }

    public String getPreHandle() {
        return this.preHandle;
    }

    public void setPreHandle(String aValue) {
        this.preHandle = aValue;
    }

    public String getIdentifyLabel() {
        return this.itemId;
    }

    public Serializable getPkId() {
        return this.itemId;
    }

    public void setPkId(Serializable pkId) {
        this.itemId = (String)pkId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getSysField() {
        return sysField;
    }

    public void setSysField(String sysField) {
        this.sysField = sysField;
    }

    public String getSysSearch() {
        return sysSearch;
    }

    public void setSysSearch(String sysSearch) {
        this.sysSearch = sysSearch;
    }
}

