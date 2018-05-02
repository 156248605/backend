package com.elex.oa.core.constants;

/**
 *@author hugo.zhao
 *@since 2018/4/27 10:46
*/
public enum MBoolean {
    YES("YES"),
    NO("NO"),
    TRUE("TRUE"),
    FALSE("FALSE"),
    DISABLED("DISABLED"),
    ENABLED("ENABLED");

    private String val = "";

    private MBoolean(String val) {
        this.val = val;
    }
}
