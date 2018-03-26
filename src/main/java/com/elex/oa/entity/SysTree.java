package com.elex.oa.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *@author hugo.zhao
 *@since 2018/3/13 17:07
*/
@Table(name="sys_tree")
public class SysTree {
    public static final String SHOW_TYPE_FLAT = "FLAT";
    public static final String SHOW_TYPE_TREE = "TREE";
    public static final String CAT_FORM_DIC = "_FORM_DIC";
    public static final String CAT_PERSON_FILE = "_PERSON_FILE";
    public static final String CAT_FORMVIEW_TEMPLATE = "_FORMVIEW_TEMPLATE";
    public static final String CAT_BPM_SOLUTION = "CAT_BPM_SOLUTION";
    public static final String CAT_FORMSOLUTION = "CAT_FORMSOLUTION";
    public static final String CAT_CUSTOMATTRIBUTE = "CAT_CUSTOMATTRIBUTE";
    @Id
    @Column(
            name = "TREE_ID_"
    )
    private String treeId;
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 128
    )
    @NotEmpty
    private String name;
    @Column(
            name = "PATH_"
    )
    @Size(
            max = 1024
    )
    private String path;
    @Column(
            name = "DEPTH_"
    )
    private Integer depth;

    @Column(
            name = "PARENT_ID_"
    )
    @Size(
            max = 64
    )
    private String parentId;

    @Column(
            name = "KEY_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String key;

    @Column(
            name = "CODE_"
    )
    @Size(
            max = 50
    )
    private String code;

    @Column(
            name = "DESCP_"
    )
    @Size(
            max = 512
    )
    private String descp;

    @Column(
            name = "USER_ID_"
    )
    @Size(
            max = 64
    )
    private String userId;

    @Column(
            name = "CAT_KEY_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String catKey;

    @Column(
            name = "SN_"
    )
    private Integer sn;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Column(
            name = "DATA_SHOW_TYPE_"
    )
    private String dataShowType;
    @Column(
            name = "TENANT_ID_"
    )
    private String tenantId;

    @Transient
    private boolean used = true;

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCatKey() {
        return catKey;
    }

    public void setCatKey(String catKey) {
        this.catKey = catKey;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getDataShowType() {
        return dataShowType;
    }

    public void setDataShowType(String dataShowType) {
        this.dataShowType = dataShowType;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    @Transient

    private String right;

    public SysTree() {
    }

    public SysTree(String in_treeId) {
        this.setTreeId(in_treeId);
    }


}
