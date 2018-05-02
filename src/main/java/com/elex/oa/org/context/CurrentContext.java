package com.elex.oa.org.context;

import com.elex.oa.org.model.ITenant;
import com.elex.oa.org.model.IUser;

/**
 *@author hugo.zhao
 *@since 2018/4/24 17:58
*/
public interface CurrentContext {
    IUser getCurrentUser();

    String getCurrentTenantId();

    ITenant getCurrentTenant();
}
