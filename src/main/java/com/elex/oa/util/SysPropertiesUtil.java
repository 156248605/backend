package com.elex.oa.util;

import com.elex.oa.entity.SysProperties;
import com.elex.oa.service.ISysPropertiesService;
import com.elex.oa.service.impl.SysPropertiesServiceImpl;
import org.apache.commons.lang.StringUtils;
/**
 *@author hugo.zhao
 *@since 2018/4/18 10:21
*/
public class SysPropertiesUtil {
    public SysPropertiesUtil() {
    }

    private static String getGlobalKey(String key) {
        String proKey = "property_" + key;
        return proKey;
    }

    private static String getTenantKey(String tenantId, String proId) {
        String proKey = "tenant_prop_" + tenantId + "_" + proId;
        return proKey;
    }

    public static SysProperties getGlobalPropertyObj(String key) throws Exception {
         String proKey = getGlobalKey(key);
        SysPropertiesServiceImpl SysPropertiesService = (SysPropertiesServiceImpl)AppBeanUtil.getBean(SysPropertiesServiceImpl.class);
         SysProperties sysProperties;
         sysProperties = SysPropertiesService.getPropertiesByName(key);
         return sysProperties;
    }

    public static String getGlobalProperty(String key) throws Exception {
        SysProperties sysProperties = getGlobalPropertyObj(key);
        return sysProperties == null?"":sysProperties.getVal();
    }

    public static Integer getGlobalPropertyInt(String key) throws Exception {
        String globalProperties = getGlobalProperty(key);
        if(StringUtils.isNotBlank(globalProperties)) {
            Integer rtn = Integer.valueOf(Integer.parseInt(globalProperties));
            if(rtn != null) {
                return rtn;
            }
        }

        return null;
    }

    public static Boolean getGlobalPropertyBool(String key) throws Exception {
        String globalProperties = getGlobalProperty(key);
        if(StringUtils.isNotBlank(globalProperties)) {
            Boolean rtn = Boolean.valueOf(Boolean.parseBoolean(globalProperties));
            if(rtn != null) {
                return rtn;
            }
        }

        return Boolean.valueOf(false);
    }

    public static Long getGlobalPropertyLong(String key) throws Exception {
        String globalProperties = getGlobalProperty(key);
        if(StringUtils.isNotBlank(globalProperties)) {
            Long rtn = Long.valueOf(Long.parseLong(globalProperties));
            if(rtn != null) {
                return rtn;
            }
        }

        return null;
    }

 /*   public static String getTenantProperty(String key, String tenantId) throws Exception {
        SysProperties sysProperties = getGlobalPropertyObj(key);
        if(sysProperties != null) {
                String proKey = getTenantKey(tenantId, sysProperties.getProId());
                SysPrivatePropertiesManager sysPrivatePropertiesManager = (SysPrivatePropertiesManager)AppBeanUtil.getBean(SysPrivatePropertiesManager.class);
                String val = sysPrivatePropertiesManager.getValByProId(sysProperties.getProId(), tenantId);
                val = sysProperties.getVal();
                return val;

        } else {
            return null;
        }
    }*/

/*    public static Long getTenantPropertyLong(String key, String tenantId) throws Exception {
        String tenantProperties = getTenantProperty(key, tenantId);
        if(tenantProperties != null) {
            Long rtn = Long.valueOf(Long.parseLong(tenantProperties));
            if(rtn != null) {
                return rtn;
            }
        }

        return null;
    }*/

 /*   public static Integer getTenantPropertyInteger(String key, String tenantId) throws Exception {
        String tenantProperties = getTenantProperty(key, tenantId);
        if(tenantProperties != null) {
            Integer rtn = Integer.valueOf(Integer.parseInt(tenantProperties));
            if(rtn != null) {
                return rtn;
            }
        }

        return null;
    }

    public static Boolean getTenantPropertyBoolean(String key, String tenantId) throws Exception {
        String tenantProperties = getTenantProperty(key, tenantId);
        if(tenantProperties != null) {
            Boolean rtn = Boolean.valueOf(Boolean.parseBoolean(tenantProperties));
            if(rtn != null) {
                return rtn;
            }
        }

        return null;
    }*/

    public static String getAdminAccount() {
        try {
            String e = getGlobalProperty("adminAccount");
            if(StringUtil.isNotEmpty(e)) {
                return e;
            }
        } catch (Exception var1) {
            var1.printStackTrace();
        }

        return "admin";
    }

    public static String getTenantAdminAccount() {
        try {
            String e = getGlobalProperty("tenantAdminAccount");
            if(StringUtil.isNotEmpty(e)) {
                return e;
            }
        } catch (Exception var1) {
            var1.printStackTrace();
        }

        return "tadmin";
    }
}

