package com.elex.oa.view.control;

import com.alibaba.fastjson.JSONObject;
import java.text.DecimalFormat;
import java.util.Map;
import javax.annotation.Resource;

import com.elex.oa.form.impl.formhandler.FormUtil;
import com.elex.oa.script.GroovyEngine;
import com.elex.oa.service.ISysSeqIdService;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class MiniTextBoxViewHandler implements MiniViewHanlder {
    private Log logger = LogFactory.getLog(MiniTextBoxViewHandler.class);
    @Resource
    ISysSeqIdService sysSeqIdManager;
    @Resource
    GroovyEngine groovyEngine;

    public MiniTextBoxViewHandler() {
    }

    public String getPluginName() {
        return "mini-textbox";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String val = this.getVal(el, jsonObj, false);
        if(StringUtils.isNotEmpty(val)) {
            el.attr("value", val);
        }
    }

    private String getVal(Element el, JSONObject jsonObj, boolean display) {
        String name = el.attr("name");
        String format = el.attr("format");
        String dataType = el.attr("datatype");
        if(!"number".equals(dataType)) {
            return jsonObj.getString(name) == null?"":jsonObj.getString(name);
        } else {
            Object val = jsonObj.get(name);
            if(BeanUtil.isEmpty(val)) {
                return "";
            } else {
                try {
                    Double e = new Double(val.toString());
                    if(e == null) {
                        return "";
                    } else if(display && StringUtil.isNotEmpty(format)) {
                        String value = (new DecimalFormat(format)).format(e);
                        return value.startsWith(".")?"0" + value:value;
                    } else {
                        return (new DecimalFormat("#.##")).format(e);
                    }
                } catch (Exception var10) {
                    var10.printStackTrace();
                    return "";
                }
            }
        }
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
      /*  String val = this.getVal(el, jsonObj, false);
        FormViewUtil.addHidden(el, val);
        String display = this.getVal(el, jsonObj, true);
        FormUtil.convertFieldToReadOnly(el, params, jsonObj, display);*/
    }
}
