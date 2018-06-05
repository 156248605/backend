package com.elex.oa.view.control;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class MiniRelatedSolutionHandler implements MiniViewHanlder {
    public MiniRelatedSolutionHandler() {
    }

    public String getPluginName() {
        return "mini-relatedsolution";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        if(StringUtils.isNotEmpty(val)) {
            el.attr("value", val);
        }

    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        el.attr("readOnly", "true");
    }
}
