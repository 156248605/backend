package com.elex.oa.view.control;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MiniHiddenViewHandler implements MiniViewHanlder {
    public MiniHiddenViewHandler() {
    }

    public String getPluginName() {
        return "mini-hidden";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        el.attr("type", "hidden");
        String val = FastjsonUtil.getString(jsonObj, name);
        if(!StringUtils.isEmpty(val)) {
            el.attr("value", val);
        }
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
    }
}
