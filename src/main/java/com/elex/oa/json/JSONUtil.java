package com.elex.oa.json;
import com.elex.oa.util.DateUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
/**
 *@author hugo.zhao
 *@since 2018/4/12 19:35
*/
public class JSONUtil {
    public JSONUtil() {
    }
    public static Map<String, Object> json2Map(String json) {
        JSONObject jsonObject = (JSONObject)JSONSerializer.toJSON(json);
        HashMap dataMap = new HashMap();
        Iterator its = jsonObject.keys();

        while(its.hasNext()) {
            String key = (String)its.next();
            Object val = jsonObject.get(key);
            dataMap.put(key, val);
        }

        return dataMap;
    }
    public static Map<String, Object> jsonArr2Map(String json) {
        JSONArray jsonArr = JSONArray.fromObject(json);
        HashMap vars = new HashMap();

        for(int i = 0; i < jsonArr.size(); ++i) {
            JSONObject obj = jsonArr.getJSONObject(i);
            String name = obj.getString("name");
            String value = obj.getString("value");
            String type = obj.getString("type");
            Object val = null;
            if(!"null".equals(value)) {
                if(type.equals("Number")) {
                    val = new Double(value);
                } else if(type.equals("Date")) {
                    val = DateUtil.parseDate(value);
                } else {
                    val = value;
                }

                vars.put(name, val);
            }
        }

        return vars;
    }

    public static Object json2Bean(String json, Class beanClass) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        String[] dateFormats = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpherExt(dateFormats));
        return JSONObject.toBean(jsonObj, beanClass);
    }

    public static Object json2Bean(String json, Class beanClass, String[] excludeFields) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludeFields);
        JSONObject jsonObj = JSONObject.fromObject(json, jsonConfig);
        String[] dateFormats = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpherExt(dateFormats));
        return JSONObject.toBean(jsonObj, beanClass);
    }

    public static String toJson(Object obj) {
        JSONObject jsonObj = JSONObject.fromObject(obj);
        return jsonObj.toString();
    }

    public static String getString(JSONObject obj, String property) {
        Object item = obj.get(property);
        return item != null && !(item instanceof JSONNull)?("null".equals(item.toString())?"":("\"null\"".equals(item.toString())?"":item.toString())):"";
    }

    public static String getString(JSONObject obj, String property, String defaultStr) {
        Object item = obj.get(property);
        return item != null && !(item instanceof JSONNull) && !"null".equals(item.toString())?item.toString():defaultStr;
    }

    public static Integer getInt(JSONObject obj, String property) {
        Object item = obj.get(property);
        return item != null && !"null".equals(item.toString())?new Integer(item.toString()):Integer.valueOf(0);
    }

    public static Integer getInt(JSONObject obj, String property, int defaultVal) {
        Object item = obj.get(property);
        return item != null && !"null".equals(item.toString()) && !"".equals(item.toString())?new Integer(item.toString()):Integer.valueOf(defaultVal);
    }

    public static String copyJsons(String oldJson, String newJson) throws JsonProcessingException, IOException {
        ObjectMapper objMapper = new ObjectMapper();
        ObjectNode oldNode = (ObjectNode)objMapper.readTree(oldJson);
        JsonNode newNode = objMapper.readTree(newJson);
        Iterator keys = newNode.fieldNames();

        while(keys.hasNext()) {
            String fieldName = (String)keys.next();
            JsonNode jsonNode = newNode.get(fieldName);
            oldNode.set(fieldName, jsonNode);
        }

        return oldNode.toString();
    }

    public static Map<String, Object> jsonNode2Map(JsonNode node) {
        Iterator keys = node.fieldNames();
        HashMap map = new HashMap();

        while(keys.hasNext()) {
            String fieldName = (String)keys.next();
            JsonNode jsonNode = node.get(fieldName);
            if(jsonNode instanceof TextNode) {
                map.put(fieldName, jsonNode.asText());
            } else {
                map.put(fieldName, jsonNode.toString());
            }
        }

        return map;
    }

    public static String getJsonString(JsonNode node, String property) {
        if(node == null) {
            return "";
        } else {
            JsonNode pNode = node.get(property);
            return pNode == null?"":pNode.asText();
        }
    }

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        String json = "{\"username\":\"abc\",\"性别\":\"男\",\"company\":\"xxxxxx\"}";
        String jsonV2 = "{\"username\":\"abc\",\"性别\":\"男\",\"company\":\"accc\"}";
        copyJsons(json, jsonV2);
    }





}
