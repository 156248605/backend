package com.elex.oa.form.entity;

public class RightModel {
    private String permissionType = "all";
    private String ids = "";
    private String names = "";

    public RightModel() {
    }

    public String getPermissionType() {
        return this.permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public String getIds() {
        return this.ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getNames() {
        return this.names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
