package com.elex.oa.sys.bo.service.parse.impl;


import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class HiddenAttrParse extends AbstractBoAttrParse {
    public HiddenAttrParse() {
    }

    public String getPluginName() {
        return "mini-hidden";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
    }

    public String getDescription() {
        return "隐藏域";
    }

    public boolean isSingleAttr() {
        return true;
    }
}
