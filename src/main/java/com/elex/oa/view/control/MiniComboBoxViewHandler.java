package com.elex.oa.view.control;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import com.elex.oa.form.impl.formhandler.FormUtil;
import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MiniComboBoxViewHandler extends MiniBoxListViewHandler {
    public MiniComboBoxViewHandler() {
    }

    public String getPluginName() {
        return "mini-combobox";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        super.parse(el, params, jsonObj);
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        String from = el.attr("from");
        if("sql".equals(from)) {
            String sqlKey = el.attr("sql");
            String sqlParent = el.attr("sql_parent");
            String sqlParams = el.attr("sql_params");
            String parentVal = jsonObj.getString(sqlParent);
            if(StringUtils.isNotEmpty(sqlKey)) {
                String url = (String)params.get("ctxPath") + "/sys/db/sysSqlCustomQuery/queryForJson_" + sqlKey + ".do";
                if(StringUtil.isNotEmpty(sqlParent)) {
                    String options;
                    if(StringUtil.isNotEmpty(parentVal)) {
                        try {
                            options = "{" + sqlParams + ":\"" + parentVal + "\"}";
                            parentVal = URLEncoder.encode(options, "utf-8");
                            String json = "params=" + parentVal;
                            url = url + "?" + json;
                            el.attr("url", url);
                        } catch (UnsupportedEncodingException var14) {
                            var14.printStackTrace();
                        }
                    } else {
                        options = el.attr("data-options");
                        JSONObject json1 = JSONObject.parseObject(options);
                        json1.put("url_customSql", url);
                        el.attr("data-options", json1.toJSONString());
                    }
                } else {
                    el.attr("url", url);
                }
            }
        }

        if(!StringUtils.isEmpty(val)) {
            el.attr("value", val);
        }
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
    /*    String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        if(jsonObj.containsKey(name + "_name")) {
            val = FastjsonUtil.getString(jsonObj, name + "_name");
        }

        if(StringUtil.isEmpty(val)) {
            String value = FastjsonUtil.getString(jsonObj, name);
            String from = el.attr("from");
            if("self".equals(from)) {
                String json = el.attr("data");
                JSONArray jsonData = JSONArray.parseArray(json);

                for(int i = 0; i < jsonData.size(); ++i) {
                    JSONObject object = (JSONObject)jsonData.get(i);
                    String key = object.getString("key");
                    if(key.equals(value)) {
                        val = object.getString("name");
                        break;
                    }
                }
            }
        }

        FormUtil.convertFieldToReadOnly(el, params, jsonObj, val);*/
    }
}
