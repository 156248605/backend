package com.elex.oa.core.constants;

/**
 *@author hugo.zhao
 *@since 2018/4/27 10:48
*/
public enum WidgetLayout {
    FORM("form"),
    COLUMN("column"),
    TABLE("table"),
    ACCORDION("accordion"),
    FIT("fit"),
    NONE("none");

    private String layout;

    private WidgetLayout(String layout) {
        this.layout = layout;
    }

    public String getLayout() {
        return this.layout;
    }
}
