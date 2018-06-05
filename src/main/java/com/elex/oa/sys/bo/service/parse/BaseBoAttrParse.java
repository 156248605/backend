package com.elex.oa.sys.bo.service.parse;

import com.elex.oa.entity.bo.SysBoAttr;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
@Service
public class BaseBoAttrParse extends AbstractBoAttrParse {
    public BaseBoAttrParse() {
    }

    protected void parseExt(SysBoAttr field, Element el) {
    }

    public String getPluginName() {
        return "baseBo";
    }

    public String getDescription() {
        return "";
    }

    public boolean isSingleAttr() {
        return true;
    }
}