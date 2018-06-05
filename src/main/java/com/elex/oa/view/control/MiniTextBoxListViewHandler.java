package com.elex.oa.view.control;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.stereotype.Component;

@Component
public class MiniTextBoxListViewHandler implements MiniViewHanlder {
    public MiniTextBoxListViewHandler() {
    }

    public String getPluginName() {
        return "mini-textboxlist";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        if(jsonObj != null) {
            String name = el.attr("name");
            String label = FastjsonUtil.getString(jsonObj, name + "_name");
            String val = FastjsonUtil.getString(jsonObj, name);
            if(StringUtils.isNotEmpty(val) && StringUtils.isNotEmpty(label)) {
                el.attr("value", val);
                el.attr("text", label);
            }
        }

    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        if(jsonObj != null) {
            String name = el.attr("name");
            String label = FastjsonUtil.getString(jsonObj, name + "_name");
            el.replaceWith((new Element(Tag.valueOf("span"), "")).text(label));
        }

    }
}
