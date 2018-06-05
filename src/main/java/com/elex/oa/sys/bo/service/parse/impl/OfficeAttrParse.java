package com.elex.oa.sys.bo.service.parse.impl;

import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class OfficeAttrParse extends AbstractBoAttrParse {
    public OfficeAttrParse() {
    }

    protected void parseExt(SysBoAttr field, Element el) {
        field.setDataType("varchar");
        field.setLength(Integer.valueOf(100));
    }

    public String getPluginName() {
        return "mini-office";
    }

    public String getDescription() {
        return "Office控件";
    }

    public boolean isSingleAttr() {
        return true;
    }
}
