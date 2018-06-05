package com.elex.oa.sys.bo.service.parse;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoAttr;
import org.jsoup.nodes.Element;
public interface IBoAttrParse {
    SysBoAttr parse(String var1, Element var2);

    String getPluginName();

    String getDescription();

    boolean isSingleAttr();

    JSONObject getInitData(SysBoAttr var1);
}

