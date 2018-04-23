package com.elex.oa.util;

import java.util.HashMap;
import java.lang.reflect.Method;
import java.util.Map;
import java.io.File;
/**
 *@author hugo.zhao
 *@since 2018/4/9 17:36
*/
public class ClassLoadUtil {

    private static Map<String, Class<?>> classMap;

    public ClassLoadUtil() {
    }

    private static native Class<?>[] getClass(String[] var0, boolean var1);

    private static void getClass(String className) {
        String[] classAry = new String[1];
        String packageName = className.replace(".", "/");
        String classPath = getClassPath() + packageName + "," + packageName;
        classAry[0] = classPath;
        Class[] result = getClass(classAry, false);
        if(result != null && result.length != 0) {
            Class[] var5 = result;
            int var6 = result.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Class c = var5[var7];
                classMap.put(c.getName(), c);
            }

        }
    }

    public static Object execute(String className, String methodName, Object... parameters) throws Exception {
        Class cls = (Class)classMap.get(className);
        if(cls == null) {
            getClass(className);
            cls = (Class)classMap.get(className);
        }

        Method method = BeanUtil.getMethod(cls, methodName, parameters);
        Object result = method.invoke(cls, parameters);
        return result;
    }

    public static Object execute(String className, String methodName) throws Exception {
        return execute(className, methodName, (Object[])null);
    }

    private static String getClassPath() {
        String classPath = ClassLoadUtil.class.getResource("/").getPath();
        if(File.separator.equals("\\")) {
            classPath = classPath.substring(1);
        }

        return classPath;
    }

    static {
        String arch = System.getProperty("os.arch");
        String os = System.getProperty("os.name").toLowerCase();
        String path = ClassLoadUtil.class.getResource("/").getPath();
        path = path.replace("classes/", "dll/");
        if(os.contains("win")) {
            if(arch.contains("64")) {
                System.load(path + "ClassLoader64.dll");
            } else {
                System.load(path + "ClassLoader.dll");
            }
        } else if(!os.contains("mac")) {
            System.load(path + "ClassLoader.so");
        }

        classMap = new HashMap();
    }


}
