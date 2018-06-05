package com.elex.oa.sys.bo.service.parse.impl;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class ButtonEditAttrParse extends AbstractBoAttrParse {
    public ButtonEditAttrParse() {
    }

    public String getPluginName() {
        return "mini-buttonedit";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
        JSONObject jo = new JSONObject();
        String json = el.attr("data-options");
        if(StringUtil.isNotEmpty(json)) {
            jo = JSONObject.parseObject(json);
        }

        String required = el.attr("required");
        if(StringUtil.isEmpty(required)) {
            required = "false";
        }

        jo.put("required", required);
        field.setExtJson(jo.toString());
    }

    public boolean isSingleAttr() {
        return false;
    }

    public String getDescription() {
        return "按钮选择框";
    }
}
