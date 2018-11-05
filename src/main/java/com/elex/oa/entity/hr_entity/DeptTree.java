package com.elex.oa.entity.hr_entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:部门树或岗位树
 * @Date:Created in  10:16 2018\4\16 0016
 * @Modify By:
 */
public class DeptTree {
    private String title;
    private String name = this.title;
    private String code;
    private Integer id;
    private Boolean expand = true;
    private List<DeptTree> children = new ArrayList<DeptTree>();

    public DeptTree() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public List<DeptTree> getChildren() {
        return children;
    }

    public void setChildren(List<DeptTree> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DeptTree{" +
                "title='" + title + '\'' +
                ", expand=" + expand +
                ", children=" + children +
                '}'+'\n';
    }
}
