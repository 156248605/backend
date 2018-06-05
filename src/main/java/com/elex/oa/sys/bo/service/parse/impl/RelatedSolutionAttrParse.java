package com.elex.oa.sys.bo.service.parse.impl;


import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class RelatedSolutionAttrParse extends AbstractBoAttrParse {
    public RelatedSolutionAttrParse() {
    }

    public String getPluginName() {
        return "mini-relatedsolution";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        field.setDataType("clob");
    }

    public boolean isSingleAttr() {
        return true;
    }

    public String getDescription() {
        return "关联流程";
    }
}
