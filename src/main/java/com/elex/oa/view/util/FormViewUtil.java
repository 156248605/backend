package com.elex.oa.view.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.saweb.context.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
/**
 *@author hugo.zhao
 *@since 2018/5/7 13:53
*/
public class FormViewUtil {
    public FormViewUtil() {
    }

    public static Map<String, Object> contructParams(HttpServletRequest request) {
        HashMap params = new HashMap();
        params.put("ctxPath", request.getContextPath());
        params.put("curUser", ContextUtil.getCurrentUser());
        String actInstId = request.getParameter("actInstId");
        if(StringUtils.isNotEmpty(actInstId)) {
            params.put("actInstId", actInstId);
        }

        String nodeId = request.getParameter("nodeId");
        if(StringUtils.isNotEmpty(nodeId)) {
            params.put("nodeId", nodeId);
        }

        Date curDate = new Date();
        SimpleDateFormat fullSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat ymdSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ymSdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat hmsSdf = new SimpleDateFormat("HH:mm:ss");
        params.put("curTimeFull", fullSdf.format(curDate));
        params.put("curTimeYmd", ymdSdf.format(curDate));
        params.put("curTimeYm", ymSdf.format(curDate));
        params.put("curTimeHms", hmsSdf.format(curDate));
        return params;
    }

    public static void addHidden(Element el, JSONObject jsonObj, boolean isComplex, boolean replace) {
        String name = el.attr("name");
        String val = FastjsonUtil.getString(jsonObj, name);
        Element elHidden = new Element(Tag.valueOf("input"), "");
        elHidden.addClass("mini-hidden");
        elHidden.attr("value", val);
        elHidden.attr("name", name);
        el.after(elHidden);
        if(isComplex) {
            String text = FastjsonUtil.getString(jsonObj, name + "_NAME".toLowerCase());
            Element elHiddenText = new Element(Tag.valueOf("input"), "");
            elHiddenText.addClass("mini-hidden");
            elHiddenText.attr("value", text);
            elHiddenText.attr("name", name + "_NAME".toLowerCase());
            el.after(elHiddenText);
            if(replace) {
                el.replaceWith((new Element(Tag.valueOf("span"), "")).text(text));
            }
        } else if(replace) {
            el.replaceWith((new Element(Tag.valueOf("span"), "")).text(val));
        }

    }

    public static void addHidden(Element el, String val) {
        String name = el.attr("name");
        Element elHidden = new Element(Tag.valueOf("input"), "");
        elHidden.addClass("mini-hidden");
        elHidden.attr("value", val);
        elHidden.attr("name", name);
        el.after(elHidden);
    }
}
