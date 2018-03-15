package com.elex.oa.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *@author hugo.zhao
 *@since 2018/3/13 16:52
*/
@Table(name="sys_dic")
public class SysDic {
    @Id
    @Column(
            name = "DIC_ID_"
    )
    private String dicId;

    @Column(
            name = "KEY_"
    )
    @Size(
            max = 64
    )
    protected String key;
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String name;
    @Column(
            name = "VALUE_"
    )
    @Size(
            max = 100
    )
    @NotEmpty
    private String value;
    @Column(
            name = "DESCP_"
    )
    @Size(
            max = 256
    )
    private String descp;

    @Column(
            name = "SN_"
    )
    private Integer sn;
    @Column(
            name = "PATH_"
    )
    @Size(
            max = 256
    )
    private String path;

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

/*    public SysTree getSysTree() {
        return sysTree;
    }*/

/*    public void setSysTree(SysTree sysTree) {
        this.sysTree = sysTree;
    }*/

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Column(
            name = "PARENT_ID_"
    )

    @Size(
            max = 64
    )
    private String parentId;

    @Column(
            name = "TYPE_ID_"
    )
    private String typeId;
    @Transient
    private SysTree sysTree;

    public SysTree getSysTree() {
        return sysTree;
    }

    public void setSysTree(SysTree sysTree) {
        this.sysTree = sysTree;
    }
}
