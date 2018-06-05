package com.elex.oa.identity.service;
import com.elex.oa.entity.BpmSolUser;
import com.elex.oa.org.model.IdentityInfo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 *@author hugo.zhao
 *@since 2018/5/5 14:16
*/
public interface BpmIdentityCalService {
    String SIMULATE_CAL = "SIMULATE_CAL";

    Collection<IdentityInfo> calNodeUsersOrGroups(String var1, String var2, Map<String, Object> var3);

    Set<IdentityInfo> calNodeUsersOrGroups(String var1, String var2, List<BpmSolUser> var3, Map<String, Object> var4);
}
