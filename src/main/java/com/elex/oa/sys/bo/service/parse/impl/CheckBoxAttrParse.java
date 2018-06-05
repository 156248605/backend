package com.elex.oa.sys.bo.service.parse.impl;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class CheckBoxAttrParse  extends AbstractBoAttrParse{
    public CheckBoxAttrParse() {
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
        Attributes attributes = el.attributes();
        String checked = attributes.get("checked");
        String truevalue = attributes.get("truevalue");
        String falsevalue = attributes.get("falsevalue");
        JSONObject json = new JSONObject();
        json.put("checked", checked);
        json.put("truevalue", truevalue);
        json.put("falsevalue", falsevalue);
        field.setExtJson(json.toJSONString());
    }

    public String getPluginName() {
        return "mini-checkbox";
    }

    public String getDescription() {
        return "复选框";
    }

    public boolean isSingleAttr() {
        return true;
    }
}
