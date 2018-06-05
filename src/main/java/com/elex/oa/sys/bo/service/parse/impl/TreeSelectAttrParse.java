package com.elex.oa.sys.bo.service.parse.impl;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class TreeSelectAttrParse extends AbstractBoAttrParse {
    public TreeSelectAttrParse() {
    }

    public String getPluginName() {
        return "mini-treeselect";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
    }

    public boolean isSingleAttr() {
        return false;
    }

    public String getDescription() {
        return "树形选择器";
    }
}
