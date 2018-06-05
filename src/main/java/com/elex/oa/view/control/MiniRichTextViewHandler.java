package com.elex.oa.view.control;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.stereotype.Component;

@Component
public class MiniRichTextViewHandler implements MiniViewHanlder {
    public MiniRichTextViewHandler() {
    }

    public String getPluginName() {
        return "mini-ueditor";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        if(!StringUtils.isEmpty(val)) {
            el.tagName("div");
            if(StringUtils.isNotEmpty(val)) {
                el.html(val);
            }
        }
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        el.replaceWith((new Element(Tag.valueOf("div"), "")).html(el.html()));
    }
}