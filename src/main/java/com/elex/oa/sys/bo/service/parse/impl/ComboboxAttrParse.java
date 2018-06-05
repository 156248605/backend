package com.elex.oa.sys.bo.service.parse.impl;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import com.elex.oa.util.StringUtil;
import net.sf.json.JSONArray;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class ComboboxAttrParse extends AbstractBoAttrParse {
    public ComboboxAttrParse() {
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
        String defaultVal = el.attr("defaultvalue");
        JSONObject json = new JSONObject();
        if(StringUtil.isNotEmpty(defaultVal)) {
            json.put("defaultvalue", defaultVal);
        }

        String from = el.attr("from");
        json.put("from", from);
        String required;
        if("dic".equals(from)) {
            required = el.attr("dickey");
            json.put("dic", required);
        } else if("self".equals(from)) {
            required = el.attr("data").replaceAll("&quot;", "");
            JSONArray textfield = JSONArray.fromObject(required);
            json.put("data", textfield);
        } else if("sql".equals(from) || "url".equals(from)) {
            required = el.attr(from);
            String textfield1 = el.attr("textfield");
            String valuefield = el.attr("valuefield");
            String sql_params = el.attr("sql_params");
            String sql_parent = el.attr("sql_parent");
            json.put(from, required);
            json.put("textfield", textfield1);
            json.put("valuefield", valuefield);
            json.put("sql_params", sql_params);
            if("sql".equals(from)) {
                json.put("sql_params", sql_params);
                json.put("sql_parent", sql_parent);
            }
        }

        required = el.attr("required");
        if(StringUtil.isEmpty(required)) {
            required = "false";
        }

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

    public String getPluginName() {
        return "mini-combobox";
    }

    public boolean isSingleAttr() {
        return false;
    }

    public String getDescription() {
        return "下拉框";
    }

}
