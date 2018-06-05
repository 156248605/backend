package com.elex.oa.view.control;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.jsoup.nodes.Element;
public interface MiniViewHanlder {
    String getPluginName();

    void parse(Element var1, Map<String, Object> var2, JSONObject var3);

    void convertToReadOnly(Element var1, Map<String, Object> var2, JSONObject var3);
}