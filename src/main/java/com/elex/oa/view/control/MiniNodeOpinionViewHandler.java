package com.elex.oa.view.control;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.elex.oa.core.engine.FreemarkEngine;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MiniNodeOpinionViewHandler implements MiniViewHanlder {
    @Resource
    FreemarkEngine freemarkEngine;

    public MiniNodeOpinionViewHandler() {
    }

    public String getPluginName() {
        return "mini-nodeopinion";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String name = el.attr("name");
        name = name.replace("FORM_OPINION_", "");
        String html = this.getOpinion(name, jsonObj);
        if(StringUtil.isNotEmpty(html)) {
            el.before(html);
        }

    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        el.remove();
    }

    private String getOpinion(String name, JSONObject jsonObj) {
        JSONObject data = jsonObj.getJSONObject("form_opinion_");
        if(BeanUtil.isEmpty(data)) {
            return "";
        } else {
            JSONArray jsonAry = data.getJSONArray(name);
            if(BeanUtil.isEmpty(jsonAry)) {
                return "";
            } else {
                HashMap model = new HashMap();
                model.put("opinions", jsonAry);
                String str = "";

                try {
                    str = this.freemarkEngine.mergeTemplateIntoString("form/render/opinion.ftl", model);
                } catch (Exception var8) {
                    var8.printStackTrace();
                }

                return str;
            }
        }
    }
}

