package com.elex.oa.view.control;
import java.text.DecimalFormat;
import java.util.Map;

import com.elex.oa.util.StringUtil;
import com.elex.oa.view.util.FormViewUtil;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MiniNumberViewHandler implements MiniViewHanlder {
    public MiniNumberViewHandler() {
    }

    public String getPluginName() {
        return "mini-spinner";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String val = this.getVal(jsonObj, el);
        el.attr("value", val);
    }

    private String getVal(JSONObject jsonObj, Element el) {
        String name = el.attr("name");
        Double val = jsonObj.getDouble(name);
        String tmp = "";
        if(val != null) {
            tmp = (new DecimalFormat("#.###")).format(val);
        }

        return tmp;
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        Double orginVal = jsonObj.getDouble(name);
        String val = this.getVal(jsonObj, el);
        if(StringUtil.isEmpty(val)) {
            el.replaceWith((new Element(Tag.valueOf("span"), "")).text(""));
        } else {
            String format = el.attr("fpattern");
            FormViewUtil.addHidden(el, val);
            String display = val;
            if(StringUtil.isNotEmpty(format)) {
                display = (new DecimalFormat(format)).format(orginVal);
            }

            el.replaceWith((new Element(Tag.valueOf("span"), "")).text(display));
        }
    }
}
