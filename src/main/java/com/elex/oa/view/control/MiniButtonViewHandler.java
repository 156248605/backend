package com.elex.oa.view.control;
import java.util.Map;
import org.jsoup.nodes.Element;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MiniButtonViewHandler implements MiniViewHanlder {
    public MiniButtonViewHandler() {
    }

    public String getPluginName() {
        return "mini-button";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        el.removeAttr("href");
        String ckselfdlg = el.attr("ckselfdlg");
        if("true".equals(ckselfdlg)) {
            el.attr("onclick", "_OnSelDialogShow");
        }

    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        el.remove();
    }
}