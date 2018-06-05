package com.elex.oa.view.control;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;

import com.elex.oa.json.FastjsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
public abstract class MiniBoxListViewHandler implements MiniViewHanlder {
    public MiniBoxListViewHandler() {
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        String text = FastjsonUtil.getString(jsonObj, name + "_name");
        String from = el.attr("from");
        if("dic".equals(from)) {
            String dataOp = el.attr("dickey");
            if(StringUtils.isNotEmpty(dataOp)) {
                String dicUrl = (String)params.get("ctxPath") + "/sys/core/sysDic/listByKey.do?key=" + dataOp;
                el.attr("url", dicUrl);
            }
        }

        if(!StringUtils.isEmpty(val)) {
            el.attr("value", val);
            el.attr("text", text);
            JSONObject dataOp1 = new JSONObject();
            dataOp1.put("oval", val);
            dataOp1.put("otext", text);
            el.attr("data-options", dataOp1.toJSONString());
        }
    }
}
