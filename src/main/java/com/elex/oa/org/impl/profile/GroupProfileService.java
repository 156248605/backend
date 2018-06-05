package com.elex.oa.org.impl.profile;


import com.elex.oa.entity.OsGroup;
import com.elex.oa.org.context.IProfileService;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.util.BeanUtil;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

@Service
public class GroupProfileService implements IProfileService {
    @Resource
    private GroupService osGroupManager;

    public GroupProfileService() {
    }

    public String getType() {
        return "group";
    }

    public String getName() {
        return "用户组";
    }

    public Set<String> getCurrentProfile() {
        String userId = ContextUtil.getCurrentUserId();
        List list = this.osGroupManager.getBelongGroups(userId);
        if(BeanUtil.isEmpty(list)) {
            return Collections.emptySet();
        } else {
            HashSet set = new HashSet();
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                OsGroup group = (OsGroup)var4.next();
                set.add(group.getGroupId());
            }

            return set;
        }
    }
}
