package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/4/12 15:52
*/
public enum TaskVar {
    SAME_USER_SET("saveUserSet"),
    START_USER("startUser");

    private String varName;

    public String getVarName() {
        return this.varName;
    }

    private TaskVar(String varName) {
        this.varName = varName;
    }
}
