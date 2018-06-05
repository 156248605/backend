package com.elex.oa.identity.service;

/**
 *@author hugo.zhao
 *@since 2018/4/24 17:49
*/
import com.elex.oa.org.model.IdentityInfo;

import java.util.Collection;
public interface IdentityCalService {
    String getTypeKey();

    String getTypeName();

    String getDescp();

    Collection<IdentityInfo> calIdentities(IdentityCalConfig var1);


}
