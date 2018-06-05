package com.elex.oa.sys.bo.service.parse.impl;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class TextAreaAttrParse extends AbstractBoAttrParse {
    public TextAreaAttrParse() {
    }

    protected void parseExt(SysBoAttr field, Element el) {
        String datatype = el.attr("datatype");
        if(StringUtil.isEmpty(datatype)) {
            field.setDataType("varchar");
            field.setLength(Integer.valueOf(400));
        } else {
            if("varchar".equals(datatype)) {
                field.setDataType("varchar");
                int json = this.getLength(el.attr("length").trim(), 400);
                field.setLength(Integer.valueOf(json));
            } else {
                field.setDataType(datatype);
            }

            JSONObject json1 = new JSONObject();
            String required = el.attr("required");
            json1.put("required", required);
            field.setExtJson(json1.toJSONString());
        }
    }

    public String getPluginName() {
        return "mini-textarea";
    }

    public String getDescription() {
        return "多行文本";
    }

    public boolean isSingleAttr() {
        return true;
    }
}
