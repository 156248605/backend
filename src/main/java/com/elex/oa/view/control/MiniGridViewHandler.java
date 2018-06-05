package com.elex.oa.view.control;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Resource;

import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class MiniGridViewHandler implements MiniViewHanlder {
    @Resource
    MiniControlParseConfig miniControlParseConfig;

    public MiniGridViewHandler() {
    }

    public String getPluginName() {
        return "rx-grid";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        if(!BeanUtil.isEmpty(el)) {
            Elements eles = el.getAllElements();
            Iterator tableName = eles.iterator();

            Element dataEl;
            while(tableName.hasNext()) {
                dataEl = (Element)tableName.next();
                String jsonData = dataEl.attr("plugins");
                if(StringUtil.isNotEmpty(jsonData) && !"rx-grid".equals(jsonData)) {
                    MiniViewHanlder jsonInit = this.miniControlParseConfig.getElementViewHandler(jsonData);
                    jsonInit.parse(dataEl, params, jsonObj);
                }
            }

            String tableName1 = el.attr("name");
            dataEl = el.select("._initdata").first();
            if(!BeanUtil.isEmpty(dataEl)) {
                JSONArray jsonData1 = new JSONArray();
                if(jsonObj.containsKey("SUB_" + tableName1)) {
                    jsonData1 = jsonObj.getJSONArray("SUB_" + tableName1);
                }

                JSONObject jsonInit1 = new JSONObject();
                JSONObject initJson = jsonObj.getJSONObject("initData");
                if(BeanUtil.isNotEmpty(initJson) && initJson.containsKey(tableName1)) {
                    jsonInit1 = initJson.getJSONObject(tableName1);
                }

                JSONObject obj = new JSONObject();
                obj.put("initData", jsonInit1);
                obj.put("data", jsonData1);
                dataEl.attr("style", "display:none");
                dataEl.html(obj.toString());
            }
        }
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
    }
}
