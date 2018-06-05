package com.elex.oa.sys.bo.service.parse.impl;

import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class TextBoxListAttrParse extends AbstractBoAttrParse {
    public TextBoxListAttrParse() {
    }

    public String getPluginName() {
        return "mini-textboxlist";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
    }

    public boolean isSingleAttr() {
        return false;
    }

    public String getDescription() {
        return "多选文本框";
    }
}
