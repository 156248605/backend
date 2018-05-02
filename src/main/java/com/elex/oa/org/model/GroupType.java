package com.elex.oa.org.model;

/**
 *@author hugo.zhao
 *@since 2018/4/24 18:00
*/
public class GroupType {
    public static final String GROUP_TYPE_ORG = "org";
    public static final String GROUP_TYPE_ROLE = "role";
    private String alias = "";
    private String name = "";

    public GroupType() {
    }

    public GroupType(String alias, String name) {
        this.alias = alias;
        this.name = name;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
