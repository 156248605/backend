package com.elex.oa.saweb.security.metadata;

public class MenuGroupModel {
    private String menuId;
    private String groupId;
    private String url;
    private String groupKey;

    public String getMenuId() {
        return this.menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGroupKey() {
        return this.groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public MenuGroupModel() {
    }

    public MenuGroupModel(String menuId, String groupId, String url, String groupKey) {
        this.menuId = menuId;
        this.groupId = groupId;
        this.url = url;
        this.groupKey = groupKey;
    }
}