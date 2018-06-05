package com.elex.oa.org.impl.profile;
import java.util.HashSet;
import java.util.Set;

import com.elex.oa.org.context.IProfileService;
import com.elex.oa.saweb.context.ContextUtil;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService implements IProfileService {
    public UserProfileService() {
    }

    public String getType() {
        return "user";
    }

    public String getName() {
        return "用户";
    }

    public Set<String> getCurrentProfile() {
        String currentUser = ContextUtil.getCurrentUserId();
        HashSet set = new HashSet();
        set.add(currentUser);
        return set;
    }
}
