package com.elex.oa.view.control;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MiniConditionDivViewHandler implements MiniViewHanlder {
    public MiniConditionDivViewHandler() {
    }

    public String getPluginName() {
        return "mini-condition-div";
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String fieldjson = el.attr("fieldjson");
        if(StringUtils.isEmpty(fieldjson)) {
            el.attr("style", "display:none");
        } else {
            JSONArray jsonArr = JSONArray.parseArray(fieldjson);
            if(jsonArr.size() == 0) {
                el.attr("style", "display:none");
            } else {
                Boolean reVal = null;

                for(int i = 0; i < jsonArr.size(); ++i) {
                    boolean runResult = false;
                    JSONObject obj = jsonArr.getJSONObject(i);
                    String name = obj.getString("name");
                    String op = obj.getString("op");
                    String val = obj.getString("value");
                    if(name != null && op != null && val != null) {
                        String objVal = jsonObj.getString(name);
                        if("==".equals(op)) {
                            runResult = objVal.equals(val);
                        } else if("include".equals(op)) {
                            runResult = objVal.indexOf(val) != -1;
                        } else if("!=".equals(val)) {
                            runResult = !objVal.equals(val);
                        }

                        String logic = obj.getString("logic");
                        if(i <= 0) {
                            reVal = Boolean.valueOf(runResult);
                        } else if("&&".equals(logic)) {
                            reVal = Boolean.valueOf(reVal.booleanValue() && runResult);
                        } else {
                            reVal = Boolean.valueOf(reVal.booleanValue() || runResult);
                        }
                    }
                }

                if(reVal != null && reVal.booleanValue()) {
                    el.attr("style", "display:\'\'");
                } else {
                    el.attr("style", "display:none");
                }

            }
        }
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        this.convertToReadOnly(el, params, jsonObj);
    }
}
