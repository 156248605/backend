package com.elex.oa.sys.bo.service.parse.impl;

import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class ImgAttrParse extends AbstractBoAttrParse {
    public ImgAttrParse() {
    }

    public String getPluginName() {
        return "mini-img";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
    }

    public String getDescription() {
        return "图片控件";
    }

    public boolean isSingleAttr() {
        return true;
    }
}
