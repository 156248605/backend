package com.elex.oa.entity.permission;

import org.springframework.data.annotation.Id;

public class Permission {
    //权限实体类
    @Id
    private Integer id; //权限id
    private Integer parentId; //父级权限id
    private String name; //权限名称
    private String nameE; //权限英文名称，方便查询

    public Permission() {
    }

    public Permission(Integer id, Integer parentId, String name, String nameE) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.nameE = nameE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", nameE='" + nameE + '\'' +
                '}';
    }
}
