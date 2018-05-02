package com.elex.oa.core.constants;

/**
 *@author hugo.zhao
 *@since 2018/4/27 10:47
*/
public enum FieldPermision {
    EDIT("EDIT"),
    READ("READ"),
    HIDE("HIDE");

    private String permision;

    private FieldPermision(String permision) {
        this.permision = permision;
    }

    public String getPermision() {
        return this.permision;
    }

    public void setPermision(String permision) {
        this.permision = permision;
    }
}
