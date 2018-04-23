package com.elex.oa.api;
import java.util.Map;
/**
 *@author hugo.zhao
 *@since 2018/4/13 14:47
*/
public interface ContextHandler {
    String getKey();

    String getName();

    Object getValue(Map<String, Object> var1);
}
