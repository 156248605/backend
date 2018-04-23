package com.elex.oa.json;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/4/20 16:01
*/
public interface IJson {
    String toJson(Object var1);

    List<Map<String, Object>> toList(String var1);

    <T> List<T> toList(String var1, Class<T> var2);

    Map<String, Object> toMap(String var1);

    <T> T toObject(String var1, Class<T> var2);

    String toPageJson(List<?> var1, Integer var2);

    String toDataJson(Object var1);

    String toResultJson(Object var1);
}
