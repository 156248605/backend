package com.elex.oa.org.model;
/**
 *@author hugo.zhao
 *@since 2018/4/24 18:02
*/
public interface ITenant {
    String PUBLIC_TENANT_ID = "0";
    String ADMIN_TENANT_ID = "1";

    String getTenantId();

    String getTenantName();

    String getShortName();

    String getDomain();

    String getStatus();

    String getInstType();
}
