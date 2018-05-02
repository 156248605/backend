package com.elex.oa.entity.permission;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Job {
    //权限相关 岗位实体类
    @Id
    private String id; // 岗位编号
    private String name; //岗位名称
    private List<Integer> permissions; //该岗位所包含的权限，权限id

    public Job() {
    }

    public Job(String id, String name, List<Integer> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Integer> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
