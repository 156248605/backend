package com.elex.oa.view.control;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MiniCheckboxViewHandler implements MiniViewHanlder {
    public MiniCheckboxViewHandler() {
    }

    public String getPluginName() {
        return "mini-checkbox";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        if(!StringUtil.isEmpty(val)) {
            String truevalue = el.attr("truevalue");
            if(val.equals(truevalue)) {
                el.attr("checked", "true");
            } else {
                el.attr("checked", "false");
            }

        }
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        if(StringUtil.isEmpty(val)) {
            val = el.attr("falsevalue");
        }

        el.replaceWith((new Element(Tag.valueOf("span"), "")).text(val));
    }
}