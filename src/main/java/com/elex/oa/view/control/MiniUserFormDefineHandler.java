package com.elex.oa.view.control;

import com.alibaba.fastjson.JSONObject;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.annotation.Resource;

import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.script.GroovyEngine;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

public class MiniUserFormDefineHandler implements MiniViewHanlder {
    private Log logger = LogFactory.getLog(MiniUserFormDefineHandler.class);
    @Resource
    GroovyEngine groovyEngine;

    public MiniUserFormDefineHandler() {
    }

    public String getPluginName() {
        return "mini-textbox";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        if(StringUtils.isNotEmpty(val)) {
            el.attr("value", val);
        }
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        String datatype = el.attr("datatype");
        String title = el.attr("title");
        String format = el.attr("format");

        try {
            if(StringUtils.isNotEmpty(format)) {
                if("Number".equals(datatype)) {
                    DecimalFormat ex = new DecimalFormat(format);
                    val = ex.format(new Double(val));
                } else if("Date".equals(datatype)) {
                    SimpleDateFormat ex1 = new SimpleDateFormat(format);
                    val = ex1.format(val);
                }
            }
        } catch (Exception var10) {
            this.logger.error(var10.getMessage());
        }

        el.replaceWith((new Element(Tag.valueOf("span"), "")).text(val).attr("title", title));
    }
}

