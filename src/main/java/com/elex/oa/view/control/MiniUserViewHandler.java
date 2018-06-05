package com.elex.oa.view.control;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.view.util.FormViewUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class MiniUserViewHandler implements MiniViewHanlder {
    public MiniUserViewHandler() {
    }

    public String getPluginName() {
        return "mini-user";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        String text = FastjsonUtil.getString(jsonObj, name + "_NAME".toLowerCase());
        if(StringUtils.isNotEmpty(val)) {
            el.attr("value", val);
            el.attr("text", text);
        }

    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        FormViewUtil.addHidden(el, jsonObj, true, true);
    }
}