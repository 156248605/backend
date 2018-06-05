package com.elex.oa.view.control;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.stereotype.Component;

@Component
public class MiniTextareaViewHandler implements MiniViewHanlder {
    public MiniTextareaViewHandler() {
    }

    public String getPluginName() {
        return "mini-textarea";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        if(!StringUtils.isEmpty(val)) {
            el.html(val);
        }
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        val = val.replaceAll("\n", "<br>");
        el.replaceWith((new Element(Tag.valueOf("div"), "")).html(val).attr("style", "line-height:22px;font-size:10pt;"));
    }
}
