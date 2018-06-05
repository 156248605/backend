package com.elex.oa.sys.bo.service.parse.impl;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class NumberAttrParse extends AbstractBoAttrParse {
    public NumberAttrParse() {
    }

    public String getPluginName() {
        return "mini-spinner";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        field.setDataType("number");
        field.setLength(Integer.valueOf(14));
        field.setDecimalLength(Integer.valueOf(4));
        JSONObject json = new JSONObject();
        String dataOptions = el.attr("data-options");
        if(StringUtil.isNotEmpty(dataOptions)) {
            json.put("conf", dataOptions);
        }

        String required = el.attr("required");
        if(StringUtil.isNotEmpty(required)) {
            json.put("required", required);
        }

        if(json.size() > 1) {
            String jsonStr = json.toJSONString();
            field.setExtJson(jsonStr);
        }

    }

    public String getDescription() {
        return "数字控件";
    }

    public boolean isSingleAttr() {
        return true;
    }
}

