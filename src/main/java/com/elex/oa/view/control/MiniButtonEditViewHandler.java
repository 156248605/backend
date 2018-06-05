package com.elex.oa.view.control;

import java.util.Map;

import com.elex.oa.form.impl.formhandler.FormUtil;
import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.view.util.FormViewUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MiniButtonEditViewHandler implements MiniViewHanlder {
    public MiniButtonEditViewHandler() {
    }

    public String getPluginName() {
        return "mini-buttonedit";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        String val_name = FastjsonUtil.getString(jsonObj, name + "_name");
        if(StringUtils.isNotEmpty(val)) {
            el.attr("value", val);
            el.attr("text", val_name);
        }

        String ckselfdlg = el.attr("ckselfdlg");
        if("true".equals(ckselfdlg)) {
            el.attr("onbuttonclick", "_OnEditSelDialogShow");
        }

        el.attr("showClose", "true");
        el.attr("oncloseclick", "_OnButtonEditClear");
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        /*FormViewUtil.addHidden(el, jsonObj, true, false);
        FormUtil.convertFieldToReadOnly(el, params, jsonObj, el.attr("text"));*/
    }
}

