package com.elex.oa.saweb.context;

import com.elex.oa.org.context.CurrentContext;
import com.elex.oa.org.model.ITenant;
import com.elex.oa.org.model.IUser;
import org.springframework.stereotype.Component;

/**
 *@author hugo.zhao
 *@since 2018/5/10 15:42
*/
@Component
public class DefaultCurrentContext implements CurrentContext {
    @Override
    public IUser getCurrentUser() {
        return ContextUtil.getCurrentUser();
    }

    @Override
    public String getCurrentTenantId() {
      return   ContextUtil.getCurrentTenantId();
    }

    @Override
    public ITenant getCurrentTenant() {
       return ContextUtil.getTenant();
    }
}
