package com.elex.oa.org.model;

/**
 *@author hugo.zhao
 *@since 2018/4/24 18:01
*/
public interface IGroup extends IdentityInfo {
    String getTenantId();

    String getKey();

    String getType();
}
