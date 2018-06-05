package com.elex.oa.org.context;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.util.AppBeanUtil;
import com.elex.oa.util.BeanUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ProfileUtil {
    public ProfileUtil() {
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

    public static Map<String, String> getProileTypes() {
        Collection list = AppBeanUtil.getBeanList(IProfileService.class);
        HashMap map = new HashMap();
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            IProfileService service = (IProfileService)var2.next();
            map.put(service.getType(), service.getName());
        }

        return map;
    }

    public static JSONObject getProfileTypeJson() {
        Collection list = AppBeanUtil.getBeanList(IProfileService.class);
        JSONObject json = new JSONObject();
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            IProfileService service = (IProfileService)var2.next();
            json.put(service.getType(), service.getName());
        }

        return json;
    }
}