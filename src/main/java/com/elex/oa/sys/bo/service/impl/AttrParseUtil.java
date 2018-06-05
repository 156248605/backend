package com.elex.oa.sys.bo.service.impl;

import com.alibaba.fastjson.JSONObject;

public class AttrParseUtil {
    public AttrParseUtil() {
    }

    public static void addKey(JSONObject jsonObj, String val) {
        jsonObj.put("key", val);
    }

    public static String getKey(JSONObject jsonObj) {
        return jsonObj == null?"":(jsonObj.containsKey("key")?jsonObj.getString("key"):"");
    }

    public static String getName(JSONObject jsonObj) {
        return jsonObj == null?"":(jsonObj.containsKey("name")?jsonObj.getString("name"):"");
    }

    public static void addName(JSONObject jsonObj, String val) {
        jsonObj.put("name", val);
    }
}
