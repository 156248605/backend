package com.elex.oa.sys.bo.service.parse.impl;

import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class MonthAttrParse extends AbstractBoAttrParse {
    public MonthAttrParse() {
    }

    public String getPluginName() {
        return "mini-month";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        field.setDataType("varchar");
        field.setLength(Integer.valueOf(10));
        this.parseExtJson(field, el);
    }

    private void parseExtJson(SysBoAttr field, Element el) {
        String format = el.attr("format");
        String initcurtime = el.attr("initcurtime");
        JSONObject json = new JSONObject();
        if(StringUtil.isNotEmpty(format)) {
            json.put("format", format);
        }

        if(StringUtil.isNotEmpty(initcurtime)) {
            json.put("initcurtime", initcurtime);
        }

        field.setExtJson(json.toJSONString());
    }

    public String getDescription() {
        return "月份选择";
    }

    public boolean isSingleAttr() {
        return true;
    }

    public JSONObject getInitData(SysBoAttr attr) {
        String initcurtime = attr.getPropByName("initcurtime");
        String format = attr.getPropByName("format");
        if(StringUtil.isEmpty(initcurtime)) {
            return null;
        } else {
            JSONObject jsonObject = new JSONObject();
            if(StringUtils.isEmpty(format)) {
                format = "yyyy-MM";
            }

            if("true".equals(initcurtime)) {
                Date curDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                String value = sdf.format(curDate);
                AttrParseUtil.addKey(jsonObject, value);
            }

            return jsonObject;
        }
    }
}
