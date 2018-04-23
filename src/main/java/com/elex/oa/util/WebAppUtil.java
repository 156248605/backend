package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/4/18 10:12
*/
import com.elex.oa.bean.PropertyPlaceholderConfigurerExt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import javax.servlet.ServletContext;
import java.io.File;
public class WebAppUtil implements ApplicationContextAware {
    private static PropertyPlaceholderConfigurerExt configProperties = null;
    private static ApplicationContext applicationContext = null;
    private static ServletContext servletContext;

    public WebAppUtil() {
    }

    public void setApplicationContext(ApplicationContext ctx) {
        applicationContext = ctx;
        configProperties = (PropertyPlaceholderConfigurerExt)ctx.getBean(PropertyPlaceholderConfigurerExt.class);
    }

    public static void init(ServletContext sContext) {
        servletContext = sContext;
        String ctxPath = servletContext.getRealPath("/");
        String path = ctxPath + "WEB-INF" + File.separator + "dll" + File.separator + "jec-core.jar";
        String outPut = ctxPath + "WEB-INF" + File.separator + "classes";

        try {
            FileUtil.unzip(path, outPut, false);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static String getAppAbsolutePath() {
        return servletContext.getRealPath("/");
    }

    public static String getClassPath() {
        return WebAppUtil.class.getResource("/").getPath();
    }

    public static Object getBean(String beanId) {
        try {
            return applicationContext.getBean(beanId);
        } catch (Exception var2) {
            return null;
        }
    }

    public static <T> T getBean(Class<T> beanClass) {
        try {
            return applicationContext.getBean(beanClass);
        } catch (Exception var2) {
            return null;
        }
    }

    public static void publishEvent(ApplicationEvent event) {
        if(applicationContext != null) {
            applicationContext.publishEvent(event);
        }

    }

    public static String getUploadPath() {
        try {
            return SysPropertiesUtil.getGlobalProperty("upload.dir");
        } catch (Exception var1) {
            var1.printStackTrace();
            return "";
        }
    }

    public static String getUeditorUploadPath() throws Exception {
        return getUploadPath() + "/ueditor";
    }

    public static String getOrgMgrDomain() {
        try {
            return SysPropertiesUtil.getGlobalProperty("org.mgr.domain");
        } catch (Exception var1) {
            var1.printStackTrace();
            return "";
        }
    }

    public static String getDbType() {
        return getProperty("db.type");
    }

    public static String getMailFrom() {
        return getProperty("mail.from");
    }

    public static String getMailTo() {
        return getProperty("mail.to");
    }

    public static String getInstallHost() {
        try {
            return SysPropertiesUtil.getGlobalProperty("install.host");
        } catch (Exception var1) {
            return "";
        }
    }

    public static boolean getIsSaasMode() throws Exception {
        return SysPropertiesUtil.getGlobalPropertyBool("install.saas").booleanValue();
    }

    public static String getAppName() {
        try {
            return SysPropertiesUtil.getGlobalProperty("app.name");
        } catch (Exception var1) {
            var1.printStackTrace();
            return "JSAAS业务流程平台";
        }
    }

  /*  public static boolean isSaasMgrUser() {
        return getOrgMgrDomain().equals(ContextUtil.getTenant().getDomain());
    }*/

    public static boolean isEanbleSaaS() throws Exception {
        return getIsSaasMode();
    }

    public static String getAnonymusUploadDir() throws Exception {
        return SysPropertiesUtil.getGlobalProperty("upload.dir.anony");
    }

    public static String getProperty(String key) {
        String str = configProperties.getProperty(key);
        return str == null?"":str;
    }

    public static String getProperty(String key, String defaultVal) {
        String str = configProperties.getProperty(key);
        return StringUtil.isEmpty(str)?defaultVal:str;
    }

    public static Boolean getBoolProperty(String key) {
        String str = getProperty(key);
        return "".equals(str)?Boolean.valueOf(false):Boolean.valueOf(Boolean.parseBoolean(str));
    }

    public static Long getLongProperty(String key) {
        String str = getProperty(key);
        return "".equals(str)?Long.valueOf(0L):Long.valueOf(Long.parseLong(str));
    }

    public static Integer getIntProperty(String key) {
        String str = getProperty(key);
        return "".equals(str)?Integer.valueOf(0):Integer.valueOf(Integer.parseInt(str));
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
