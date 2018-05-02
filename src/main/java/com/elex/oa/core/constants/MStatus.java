package com.elex.oa.core.constants;

/**
 *@author hugo.zhao
 *@since 2018/4/27 10:47
*/
public enum MStatus {
    INIT("INIT"),
    DISABLED("DISABLED"),
    ENABLED("ENABLED"),
    DELETED("DELETED"),
    NOTAPPROVED("NOTAPPROVED"),
    APPROVEDING("APPROVEDING"),
    APPROVED("APPROVED"),
    REFUSED("REFUSED");

    private String val = "";

    private MStatus(String val) {
        this.val = val;
    }

    public String toString() {
        return this.val;
    }

}
