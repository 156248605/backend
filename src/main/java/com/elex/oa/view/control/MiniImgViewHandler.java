package com.elex.oa.view.control;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import javax.annotation.Resource;

import com.elex.oa.json.FastjsonUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class MiniImgViewHandler implements MiniViewHanlder {
/*    @Resource
    private OsUserManager osUserManager;*/

    public MiniImgViewHandler() {
    }

    public String getPluginName() {
        return "mini-img";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        el.attr("value", val);
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        el.attr("readOnly", "true");
    }
}