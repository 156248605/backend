package com.elex.oa.form.manager;

import com.elex.oa.form.entity.RightModel;

import java.util.Map;
import java.util.Set;
public class PermissionUtil {
    public PermissionUtil() {
    }

    public static boolean hasRight(RightModel rightModel, Map<String, Set<String>> profileMap) {
        if("all".equals(rightModel.getPermissionType())) {
            return true;
        } else {
            Set set = (Set)profileMap.get(rightModel.getPermissionType());
            String[] aryId = rightModel.getIds().split(",");

            for(int i = 0; i < aryId.length; ++i) {
                if(set.contains(aryId[i])) {
                    return true;
                }
            }

            return false;
        }
    }
}

