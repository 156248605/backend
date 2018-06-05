package com.elex.oa.view.control;
import java.util.Map;

import com.elex.oa.form.impl.formhandler.FormUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;
import com.alibaba.fastjson.JSONObject;
public class MiniDivViewHandler implements MiniViewHanlder {
    public MiniDivViewHandler() {
    }

    public String getPluginName() {
        return "mini-div";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String dataOptions = el.attr("data-options");
        if(StringUtils.isNotEmpty(dataOptions)) {
           // FormUtil.convertFieldToReadOnly(el, params, jsonObj, el.html());
        }

    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
    }
}