package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/3/19 15:43
*/
public class IdUtil {
    public static String getId(){
        IdGenerator idGenerator = (IdGenerator)AppBeanUtil.getBean(IdGenerator.class);
        return idGenerator.getSID();
    }
}
