package com.elex.oa.view.control;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.util.DateUtil;
import com.elex.oa.util.StringUtil;
import com.elex.oa.view.util.FormViewUtil;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import com.alibaba.fastjson.JSONObject;
public abstract class MiniDateViewHandler implements MiniViewHanlder {
    public MiniDateViewHandler() {
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = "";
        String format = el.attr("format");
        Object obj = jsonObj.get(name);
        if(obj instanceof Date) {
            if(StringUtil.isEmpty(format)) {
                format = "yyyy-MM-dd";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            val = dateFormat.format(obj);
        } else {
            val = FastjsonUtil.getString(jsonObj, name);
            if(val.indexOf("T") > -1) {
                val = this.getValue(format, val);
            }
        }

        el.attr("value", val);
    }

    protected String getValue(String formatStr, String val) {
        val = val.replace("T", " ");
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = DateUtil.parseDate(val, "yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        FormViewUtil.addHidden(el, el.val());
        el.replaceWith((new Element(Tag.valueOf("span"), "")).text(el.val()));
    }
}
