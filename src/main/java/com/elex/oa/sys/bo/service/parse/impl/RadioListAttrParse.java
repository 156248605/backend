package com.elex.oa.sys.bo.service.parse.impl;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import com.elex.oa.util.StringUtil;
import net.sf.json.JSONArray;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class RadioListAttrParse extends AbstractBoAttrParse {
    public RadioListAttrParse() {
    }

    public String getPluginName() {
        return "mini-radiobuttonlist";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
        String defaultVal = el.attr("defaultvalue");
        JSONObject json = new JSONObject();
        if(StringUtil.isNotEmpty(defaultVal)) {
            json.put("defaultvalue", defaultVal);
        }

        Attributes attributes = el.attributes();
        String from = attributes.get("from");
        json.put("from", from);
        String required;
        if("dic".equals(from)) {
            required = attributes.get("dickey");
            json.put("dic", required);
        } else if("self".equals(from)) {
            required = attributes.get("data").replaceAll("&quot;", "");
            JSONArray textfield = JSONArray.fromObject(required);
            json.put("data", textfield);
        } else if("sql".equals(from) || "url".equals(from)) {
            required = attributes.get(from);
            String textfield1 = attributes.get("textfield");
            String valuefield = attributes.get("valuefield");
            json.put(from, required);
            json.put("textfield", textfield1);
            json.put("valuefield", valuefield);
        }

        required = el.attr("required");
        json.put("required", required);
        field.setExtJson(json.toJSONString());
    }

    public JSONObject getInitData(SysBoAttr attr) {
        if(StringUtil.isEmpty(attr.getExtJson())) {
            return null;
        } else {
            JSONObject obj = JSONObject.parseObject(attr.getExtJson());
            JSONObject jsonObject = new JSONObject();
            AttrParseUtil.addKey(jsonObject, obj.getString("defaultvalue"));
            return jsonObject;
        }
    }

    public boolean isSingleAttr() {
        return false;
    }

    public String getDescription() {
        return "单选控件";
    }
}
