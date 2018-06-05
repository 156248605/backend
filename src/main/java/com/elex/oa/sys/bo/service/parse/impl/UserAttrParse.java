package com.elex.oa.sys.bo.service.parse.impl;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.org.model.IUser;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class UserAttrParse extends AbstractBoAttrParse {
    public UserAttrParse() {
    }

    public String getPluginName() {
        return "mini-user";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
        this.parseExtJson(field, el);
    }

    private void parseExtJson(SysBoAttr field, Element el) {
        String initloginuser = el.attr("initloginuser");
        String single = el.attr("single");
        JSONObject json = new JSONObject();
        json.put("single", single);
        if("true".equals(initloginuser)) {
            json.put("initloginuser", "true");
        }

        String required = el.attr("required");
        if(StringUtil.isEmpty(required)) {
            required = "false";
        }

        json.put("required", required);
        field.setExtJson(json.toJSONString());
    }

    public boolean isSingleAttr() {
        return false;
    }

    public String getDescription() {
        return "用户选择";
    }

    public JSONObject getInitData(SysBoAttr attr) {
        String initloginuser = attr.getPropByName("initloginuser");
        if("true".equals(initloginuser)) {
            JSONObject obj = new JSONObject();
            IUser curUser = ContextUtil.getCurrentUser();
            AttrParseUtil.addKey(obj, curUser.getUserId());
            AttrParseUtil.addName(obj, curUser.getFullname());
            return obj;
        } else {
            return null;
        }
    }
}

