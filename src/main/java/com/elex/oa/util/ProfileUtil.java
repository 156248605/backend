package com.elex.oa.util;

import java.util.*;

/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
public class ProfileUtil {
    public ProfileUtil(){
    }

    public static Map<String, Set<String>> getCurrentProfile() {
        HashMap map = new HashMap();
        Collection list = AppBeanUtil.getBeanList(IProfileService.class);
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            IProfileService service = (IProfileService)var2.next();
            Set set = service.getCurrentProfile();
            if(!BeanUtil.isEmpty(set)) {
                map.put(service.getType(), set);
            }
        }

        return map;
    }



}
