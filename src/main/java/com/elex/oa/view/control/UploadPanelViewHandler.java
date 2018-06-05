package com.elex.oa.view.control;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import javax.annotation.Resource;
import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.service.IBpmInstCtlService;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class UploadPanelViewHandler implements MiniViewHanlder {
    @Resource
    IBpmInstCtlService bpmInstCtlManager;
    public UploadPanelViewHandler() {
    }

    public String getPluginName() {
        return "upload-panel";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        String userId = ContextUtil.getCurrentUserId();
        String instId = (String)params.get("instId");
        String isDown = el.attr("isDown");
        if(StringUtil.isNotEmpty(isDown)) {
            el.attr("isDown", isDown);
        } else {
            boolean isPrint = this.bpmInstCtlManager.sysFileCtl(userId, instId, "DOWN");
            el.attr("isDown", String.valueOf(isPrint));
        }

        String isPrint1 = el.attr("isPrint");
        if(StringUtil.isNotEmpty(isPrint1)) {
            el.attr("isPrint", isPrint1);
        } else {
            boolean isPrint2 = this.bpmInstCtlManager.sysFileCtl(userId, instId, "PRINT");
            el.attr("isPrint", String.valueOf(isPrint2));
        }

        el.attr("value", val);
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        el.attr("readOnly", "true");
    }
}
