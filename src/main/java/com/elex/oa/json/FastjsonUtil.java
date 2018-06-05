package com.elex.oa.json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
public class FastjsonUtil {
    public FastjsonUtil() {
    }

    public static void copyProperties(JSONObject dest, JSONObject source) {
        Iterator keyIt = source.keySet().iterator();

        while(keyIt.hasNext()) {
            String key = (String)keyIt.next();
            dest.put(key, source.get(key));
        }

    }

    public static Integer getInt(JSONObject json, String name, Integer defaultVal) {
        return json.containsKey(name)?json.getInteger(name):defaultVal;
    }

    public static Integer getInt(JSONObject json, String name) {
        return getInt(json, name, Integer.valueOf(0));
    }

    public static String getString(JSONObject json, String name, String defaultVal) {
        return json.containsKey(name)?json.getString(name):defaultVal;
    }

    public static String getString(JSONObject json, String name) {
        return getString(json, name, "");
    }

    public static Map<String, Object> json2Map(JSONObject jsonData) {
        HashMap map = new HashMap();
        Set keySet = jsonData.keySet();
        Iterator var3 = keySet.iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            map.put(key, jsonData.get(key));
        }

        return map;
    }

    public static Map<String, Object> json2Map(String preKey, JSONObject jsonData) {
        HashMap map = new HashMap();
        Set keySet = jsonData.keySet();
        Iterator var4 = keySet.iterator();

        while(var4.hasNext()) {
            String key = (String)var4.next();
            map.put(preKey + key, jsonData.get(key));
        }

        return map;
    }

    public static Map<String, Object> mapJson2MapProperties(Map<String, JSONObject> mapJsons) {
        HashMap vars = new HashMap();

        String key;
        JSONObject data;
        for(Iterator keyIt = mapJsons.keySet().iterator(); keyIt.hasNext(); vars.putAll(json2Map(key, data))) {
            key = (String)keyIt.next();
            data = (JSONObject)mapJsons.get(key);
            if(StringUtils.isNotEmpty(key)) {
                key = key + "_";
            }
        }

        return vars;
    }

    public static Map<String, Object> jsonArr2Map(JSONArray jsonArr) {
        HashMap map = new HashMap();

        for(int i = 0; i < jsonArr.size(); ++i) {
            JSONObject jsonData = jsonArr.getJSONObject(i);
            Set keySet = jsonData.keySet();
            Iterator var5 = keySet.iterator();

            while(var5.hasNext()) {
                String key = (String)var5.next();
                map.put(key, jsonData.get(key));
            }
        }

        return map;
    }

    public static Map<String, Object> jsonArr2Map(String arrJson) {
        if(org.apache.commons.lang.StringUtils.isEmpty(arrJson)) {
            arrJson = "[]";
        }

        JSONArray jsonArr = JSONArray.parseArray(arrJson);
        return jsonArr2Map(jsonArr);
    }

    public static String toJSON(Object obj, String dateFormat) {
        SerializeConfig mapping = new SerializeConfig();
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
        mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer(dateFormat));
        mapping.put(Timestamp.class, new SimpleDateFormatSerializer(dateFormat));
        String text = JSON.toJSONString(obj, mapping, new SerializerFeature[0]);
        return text;
    }

    public static String toJSON(Object obj) {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        return toJSON(obj, dateFormat);
    }
}
