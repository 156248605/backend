package com.elex.oa.org.impl.profile;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import com.elex.oa.entity.OsGroup;
import com.elex.oa.org.context.IProfileService;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import org.springframework.stereotype.Service;
@Service
public class SubGroupProfileService implements IProfileService {
    @Resource
    private GroupService osGroupManager;

    public SubGroupProfileService() {
    }

    public String getType() {
        return "subGroup";
    }

    public String getName() {
        return "组织或以下";
    }

    public Set<String> getCurrentProfile() {
        String userId = ContextUtil.getCurrentUserId();
        List deps = this.osGroupManager.getBelongDeps(userId);
        if(BeanUtil.isEmpty(deps)) {
            return Collections.emptySet();
        } else {
            HashSet set = new HashSet();
            Iterator var4 = deps.iterator();

            while(var4.hasNext()) {
                OsGroup group = (OsGroup)var4.next();
                String path = group.getPath();
                path = StringUtil.trimPrefix(path, "0.");
                path = StringUtil.trimSuffix(path, ".");
                String[] aryGroup = path.split("[.]");
                String[] var8 = aryGroup;
                int var9 = aryGroup.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    String groupId = var8[var10];
                    set.add(groupId);
                }
            }

            return set;
        }
    }

    public static void main(String[] args) {
    }
}
