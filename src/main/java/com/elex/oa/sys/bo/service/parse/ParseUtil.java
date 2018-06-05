package com.elex.oa.sys.bo.service.parse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Iterator;

import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
public class ParseUtil {
    public ParseUtil() {
    }

    public static String parseJosnData(Element el) {
        String jsonStr = el.attr("data");
        if(StringUtil.isEmpty(jsonStr)) {
            return "";
        } else {
            JSONObject rtnJson = new JSONObject();
            JSONArray ary = JSONArray.parseArray(jsonStr);
            Iterator var4 = ary.iterator();

            while(var4.hasNext()) {
                Object obj = var4.next();
                JSONObject jsonObj = (JSONObject)obj;
                rtnJson.put(jsonObj.getString("key"), jsonObj.getString("name"));
            }

            return rtnJson.toJSONString();
        }
    }

    public static void setStringLen(SysBoAttr boAttr, Element el) {
        boAttr.setDataType("varchar");
        String strLen = el.attr("length");
        if(StringUtil.isEmpty(strLen)) {
            strLen = "64";
        }

        boAttr.setLength(Integer.valueOf(Integer.parseInt(strLen)));
    }
}

