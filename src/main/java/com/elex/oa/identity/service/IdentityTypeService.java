package com.elex.oa.identity.service;

/**
 *@author hugo.zhao
 *@since 2018/4/24 17:47
*/
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class IdentityTypeService implements InitializingBean {
    private Map<String,IdentityCalService> identityCalServicesMap = new LinkedHashMap<>();
    private List<IdentityCalService> identityCalServices = new ArrayList<>();


    @Override
    public void afterPropertiesSet() throws Exception {
        Iterator var1 = this.identityCalServices.iterator();
        while(var1.hasNext()) {
            IdentityCalService service = (IdentityCalService)var1.next();
            this.identityCalServicesMap.put(service.getTypeKey(), service);
        }
    }

    public IdentityTypeService() {
    }

    public List<IdentityCalService> getIdentityCalServices() {
        return this.identityCalServices;
    }

    public void setIdentityCalServices(List<IdentityCalService> identityCalServices) {
        this.identityCalServices = identityCalServices;
    }

    public Map<String, IdentityCalService> getIdentityCalServicesMap() {
        return this.identityCalServicesMap;
    }

}
