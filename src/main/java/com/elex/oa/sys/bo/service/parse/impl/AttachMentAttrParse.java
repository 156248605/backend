package com.elex.oa.sys.bo.service.parse.impl;


import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AttachMentAttrParse extends AbstractBoAttrParse {
    public AttachMentAttrParse() {
    }
    @Override
    public String getPluginName() {
        return "upload-panel";
    }

    @Override
    public String getDescription() {
        return "附件";
    }

    @Override
    public boolean isSingleAttr() {
        return true;
    }

    @Override
    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
    }
}
