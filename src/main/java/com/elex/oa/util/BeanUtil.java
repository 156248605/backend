package com.elex.oa.util;


import org.apache.commons.beanutils.*;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.util.*;

import java.util.Map.Entry;

import java.beans.PropertyDescriptor;

import com.alibaba.druid.pool.DruidDataSource;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtilsBean;


/**
 *@author hugo.zhao
 *@since 2018/4/20 16:47
*/
public class BeanUtil {

    public static ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();

    private static BeanUtilsBean beanUtilsBean;

    public BeanUtil() {
    }

    public static void copyNotNullProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {
        BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
        if(dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        } else if(orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        } else {
           /* if(logger.isDebugEnabled()) {
                logger.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");*/
            }

            int i;
            String name;
            Object value;
            if(orig instanceof DynaBean) {
                DynaProperty[] origDescriptors = ((DynaBean)orig).getDynaClass().getDynaProperties();

                for(i = 0; i < origDescriptors.length; ++i) {
                    name = origDescriptors[i].getName();
                    if(beanUtils.getPropertyUtils().isReadable(orig, name) && beanUtils.getPropertyUtils().isWriteable(dest, name)) {
                        value = ((DynaBean)orig).get(name);
                        beanUtils.copyProperty(dest, name, value);
                    }
                }
            } else if(orig instanceof Map) {
                Iterator var9 = ((Map)orig).entrySet().iterator();

                while(var9.hasNext()) {
                    Entry var11 = (Entry)var9.next();
                    name = (String)var11.getKey();
                    if(beanUtils.getPropertyUtils().isWriteable(dest, name)) {
                        beanUtils.copyProperty(dest, name, var11.getValue());
                    }
                }
            } else {
                PropertyDescriptor[] var10 = beanUtils.getPropertyUtils().getPropertyDescriptors(orig);

                for(i = 0; i < var10.length; ++i) {
                    name = var10[i].getName();
                    if(!"class".equals(name) && beanUtils.getPropertyUtils().isReadable(orig, name) && beanUtils.getPropertyUtils().isWriteable(dest, name)) {
                        try {
                            value = beanUtils.getPropertyUtils().getSimpleProperty(orig, name);
                            if(value != null) {
                                if(value instanceof HashSet) {
                                    HashSet valMap = (HashSet)value;
                                    if(valMap.size() > 0) {
                                        beanUtils.copyProperty(dest, name, value);
                                    }
                                } else {
                                    beanUtils.copyProperty(dest, name, value);
                                }
                            }
                        } catch (NoSuchMethodException var8) {

                        }
                    }
                }
            }

        }


    public static void copyProperties(Object dest, Object orig) {
        try {
            beanUtilsBean.copyProperties(dest, orig);
        } catch (Exception var3) {
            ReflectionUtils.handleReflectionException(var3);
            //logger.error(var3.getMessage());
        }

    }

    public static void copyProperty(Object bean, String name, Object value) {
        try {
            beanUtilsBean.copyProperty(bean, name, value);
        } catch (Exception var4) {
            ReflectionUtils.handleReflectionException(var4);
           // logger.error(var4.getMessage());
        }

    }

    public static BeanUtilsBean getBeanUtils() {
        BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
        return beanUtilsBean;
    }

    public static Object populateEntity(Object entity, Map<String, Object> dataMap) throws IllegalAccessException, InvocationTargetException {
        getBeanUtils().populate(entity, dataMap);
        return entity;
    }



/*    private static Collection deepCopyCollection(Collection source) throws InstantiationException, IllegalAccessException {
        Collection dest = (Collection)source.getClass().newInstance();
        Iterator var2 = source.iterator();

        while(var2.hasNext()) {
            Object o = var2.next();
            dest.add(deepCopyBean(o));
        }

        return dest;
    }*/

/*    private static Object deepCopyArray(Object source) throws InstantiationException, IllegalAccessException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        int length = Array.getLength(source);
        Object dest = Array.newInstance(source.getClass().getComponentType(), length);
        if(length == 0) {
            return dest;
        } else {
            for(int i = 0; i < length; ++i) {
                Array.set(dest, i, deepCopyBean(Array.get(source, i)));
            }

            return dest;
        }
    }*/

/*    private static Map deepCopyMap(Map source) throws InstantiationException, IllegalAccessException {
        Map dest = (Map)source.getClass().newInstance();
        Iterator var2 = source.entrySet().iterator();

        while(var2.hasNext()) {
            Object o = var2.next();
            Entry e = (Entry)o;
            dest.put(deepCopyBean(e.getKey()), deepCopyBean(e.getValue()));
        }

        return dest;
    }*/

    public static Map<String, Object> convertFieldToMap(Object entity) {
        HashMap fieldMap = new HashMap();

        for(Class cls = entity.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            Field[] fs = cls.getDeclaredFields();
            Field[] var4 = fs;
            int var5 = fs.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Field f = var4[var6];

                try {
                    Method e = cls.getDeclaredMethod("get" + StringUtil.makeFirstLetterUpperCase(f.getName()), new Class[0]);
                    Object fieldVal = e.invoke(entity, new Object[0]);
                    fieldMap.put(f.getName(), fieldVal);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }
        }

        return fieldMap;
    }

    public static Object getFieldValueFromObject(Object obj, String attName) {
        if(obj == null) {
            return null;
        } else {
            Object val = null;

            try {
                Method e = obj.getClass().getDeclaredMethod("get" + StringUtil.makeFirstLetterUpperCase(attName), new Class[0]);
                if(e == null) {
                    return null;
                }

                val = e.invoke(obj, new Object[0]);
            } catch (Exception var4) {
            }

            return val;
        }
    }

    public static void setFieldValue(Object instObj, String attName, Object val) {
        if(instObj != null) {
            Class cls = instObj.getClass();
            Field field = null;

            for(Method setMethod = null; cls != Object.class; cls = cls.getSuperclass()) {
                try {
                    setMethod = getSetMethod(cls, attName);
                    if(setMethod != null) {
                        setMethod.invoke(instObj, new Object[]{val});
                        break;
                    }

                    field = cls.getDeclaredField(attName);
                    String e = StringUtil.makeFirstLetterUpperCase(attName);
                    if(field != null) {
                        setMethod = cls.getDeclaredMethod("set" + e, new Class[]{field.getType()});
                        setMethod.invoke(instObj, new Object[]{val});
                        break;
                    }
                } catch (Exception var7) {
                    //logger.error(var7.getMessage());
                }
            }

        }
    }

    private static Method getSetMethod(Class cls, String attName) {
        String attr = StringUtil.makeFirstLetterUpperCase(attName);
        Method[] aryMethod = cls.getDeclaredMethods();
        Method[] var4 = aryMethod;
        int var5 = aryMethod.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Method m = var4[var6];
            if(m.getName().equals("set" + attr)) {
                return m;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        DruidDataSource ds = new DruidDataSource();
        setFieldValue(ds, "username", "root");
        System.err.println("ok");
    }

    public static boolean isEmpty(Object o) {
        if(o == null) {
            return true;
        } else {
            if(o instanceof String) {
                if(((String)o).trim().length() == 0) {
                    return true;
                }
            } else if(o instanceof Collection) {
                if(((Collection)o).size() == 0) {
                    return true;
                }
            } else if(o.getClass().isArray()) {
                if(((Object[])((Object[])o)).length == 0) {
                    return true;
                }
            } else if(o instanceof Map && ((Map)o).size() == 0) {
                return true;
            }

            return false;
        }
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isNumber(Object o) {
        if(o == null) {
            return false;
        } else if(o instanceof Number) {
            return true;
        } else if(o instanceof String) {
            try {
                Double.parseDouble((String)o);
                return true;
            } catch (NumberFormatException var2) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean validClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException var2) {
            return false;
        }
    }

    public static boolean isInherit(Class cls, Class parentClass) {
        return parentClass.isAssignableFrom(cls);
    }

    public static Object convertByActType(String typeName, String value) {
        Object o = null;
        if(typeName.equals("int")) {
            o = Integer.valueOf(Integer.parseInt(value));
        } else if(typeName.equals("short")) {
            o = Short.valueOf(Short.parseShort(value));
        } else if(typeName.equals("long")) {
            o = Long.valueOf(Long.parseLong(value));
        } else if(typeName.equals("float")) {
            o = Float.valueOf(Float.parseFloat(value));
        } else if(typeName.equals("double")) {
            o = Double.valueOf(Double.parseDouble(value));
        } else if(typeName.equals("boolean")) {
            o = Boolean.valueOf(Boolean.parseBoolean(value));
        } else if(typeName.equals("String")) {
            o = value;
        } else {
            o = value;
        }

        return o;
    }

   /* public static <T> List<T> listToTree(List<T> list) {
        LinkedHashMap tempMap = new LinkedHashMap();
        if(isEmpty(list)) {
            return Collections.emptyList();
        } else if(!(list.get(0) instanceof Tree)) {
            throw new RuntimeException("树形转换出现异常。数据必须实现Tree接口！");
        } else {
            ArrayList returnList = new ArrayList();
            Iterator var3 = list.iterator();

            Tree obj;
            while(var3.hasNext()) {
                obj = (Tree)var3.next();
                tempMap.put(obj.getId(), obj);
            }

            var3 = list.iterator();

            while(true) {
                while(var3.hasNext()) {
                    obj = (Tree)var3.next();
                    String parentId = obj.getParentId();
                    if(tempMap.containsKey(parentId) && !obj.getId().equals(parentId)) {
                        if(((Tree)tempMap.get(parentId)).getChildren() == null) {
                            ((Tree)tempMap.get(parentId)).setChildren(new ArrayList());
                        }

                        ((Tree)tempMap.get(parentId)).getChildren().add(obj);
                    } else {
                        returnList.add(obj);
                    }
                }

                return returnList;
            }
        }
    }*/

    public static Method getMethod(Class<?> cls, String methodName, Object[] parameters) throws NoSuchMethodException {
        Method[] methods = cls.getDeclaredMethods();
        Method[] var4 = methods;
        int var5 = methods.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Method method = var4[var6];
            Class[] parameterTypes = method.getParameterTypes();
            int len = parameters == null?0:parameters.length;
            if(methodName.equals(method.getName()) && parameterTypes.length == len) {
                return method;
            }
        }

        throw new NoSuchMethodException();
    }

 /*   static {
        beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
        convertUtilsBean.register(new BeanDateConverter(), Date.class);
        convertUtilsBean.register(new LongConverter((Object)null), Long.class);
    }*/


}
