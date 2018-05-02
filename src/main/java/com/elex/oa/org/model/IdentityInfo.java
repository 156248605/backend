package com.elex.oa.org.model;

/**
 *@author hugo.zhao
 *@since 2018/4/24 17:59
*/
public interface IdentityInfo {
    String IDENTIFY_TYPE_USER = "USER";
    String IDENTIFY_TYPE_GROUP = "GROUP";
    String IDENTIFY_TYPE_USERGROUP = "USERGROUP";

    String getIdentityType();

    String getIdentityId();

    String getIdentityName();
}
