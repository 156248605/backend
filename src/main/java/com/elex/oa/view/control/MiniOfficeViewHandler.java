package com.elex.oa.view.control;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class MiniOfficeViewHandler implements MiniViewHanlder {
    private Log logger = LogFactory.getLog(MiniOfficeViewHandler.class);

    public MiniOfficeViewHandler() {
    }

    public String getPluginName() {
        return "mini-office";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        if(!StringUtils.isEmpty(val)) {
            el.attr("value", val);
        }
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        el.attr("readonly", "true");
    }
}
