package com.elex.oa.sys.bo.service.parse.impl;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class UeditorAttrParse extends AbstractBoAttrParse {
    public UeditorAttrParse() {
    }

    public String getPluginName() {
        return "mini-ueditor";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        String datatype = el.attr("datatype");
        if(StringUtil.isEmpty(datatype)) {
            field.setDataType("varchar");
            field.setLength(Integer.valueOf(4000));
        } else {
            if("varchar".equals(datatype)) {
                field.setDataType("varchar");
                field.setLength(Integer.valueOf(Integer.parseInt(el.attr("length").trim())));
            } else {
                field.setDataType("clob");
            }

        }
    }

    public String getDescription() {
        return "富文本框";
    }

    public boolean isSingleAttr() {
        return true;
    }
}

