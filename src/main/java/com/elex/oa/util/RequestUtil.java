package com.elex.oa.util;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.alibaba.fastjson.JSONObject;
/**
 *@author hugo.zhao
 *@since 2018/4/19 16:18
*/

public class RequestUtil {
    public RequestUtil() {
    }

    public static String getString(HttpServletRequest request, String key) {
        return getString(request, key, "");
    }

    public static String getString(HttpServletRequest request, String key, String defaultValue) {
        String val = request.getParameter(key);
        return StringUtil.isNotEmpty(val)?val:defaultValue;
    }

    public static int getInt(HttpServletRequest request, String key) {
        return getInt(request, key, 0);
    }

    public static int getInt(HttpServletRequest request, String key, int defaultValue) {
        String str = request.getParameter(key);
        return StringUtils.isEmpty(str)?defaultValue:Integer.parseInt(str);
    }

    public static long getLong(HttpServletRequest request, String key) {
        return getLong(request, key, 0L);
    }

    public static Long[] getLongAry(HttpServletRequest request, String key) {
        String[] aryKeys = request.getParameterValues(key);
        if(aryKeys != null && aryKeys.length != 0) {
            Long[] aryLong = new Long[aryKeys.length];

            for(int i = 0; i < aryKeys.length; ++i) {
                aryLong[i] = Long.valueOf(Long.parseLong(aryKeys[i]));
            }

            return aryLong;
        } else {
            return null;
        }
    }

    public static Long[] getLongAryByStr(HttpServletRequest request, String key) {
        String sysUserId = request.getParameter(key);
        String[] aryId = sysUserId.split(",");
        Long[] lAryId = new Long[aryId.length];

        for(int i = 0; i < aryId.length; ++i) {
            lAryId[i] = Long.valueOf(Long.parseLong(aryId[i]));
        }

        return lAryId;
    }

    public static String[] getStringAryByStr(HttpServletRequest request, String key) {
        String ids = request.getParameter(key);
        String[] aryId = ids.split(",");
        return aryId;
    }

    public static Integer[] getIntAry(HttpServletRequest request, String key) {
        String[] aryKey = request.getParameterValues(key);
        Integer[] aryInt = new Integer[aryKey.length];

        for(int i = 0; i < aryKey.length; ++i) {
            aryInt[i] = Integer.valueOf(Integer.parseInt(aryKey[i]));
        }

        return aryInt;
    }

    public static Float[] getFloatAry(HttpServletRequest request, String key) {
        String[] aryKey = request.getParameterValues(key);
        Float[] fAryId = new Float[aryKey.length];

        for(int i = 0; i < aryKey.length; ++i) {
            fAryId[i] = Float.valueOf(Float.parseFloat(aryKey[i]));
        }

        return fAryId;
    }

    public static long getLong(HttpServletRequest request, String key, long defaultValue) {
        String str = request.getParameter(key);
        return StringUtils.isEmpty(str)?defaultValue:Long.parseLong(str);
    }

    public static float getFloat(HttpServletRequest request, String key) {
        return getFloat(request, key, 0.0F);
    }

    public static float getFloat(HttpServletRequest request, String key, float defaultValue) {
        String str = request.getParameter(key);
        return StringUtils.isEmpty(str)?defaultValue:Float.parseFloat(request.getParameter(key));
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        return getBoolean(request, key, false);
    }

    public static boolean getBoolean(HttpServletRequest request, String key, boolean defaultValue) {
        String str = request.getParameter(key);
        return StringUtils.isEmpty(str)?defaultValue:(StringUtils.isNumeric(str)?Integer.parseInt(str) == 1:Boolean.parseBoolean(str));
    }

   /* public static Short getShort(HttpServletRequest request, String key) {
        return getShort(request, key, Short.valueOf(0));
    }*/

    public static Short getShort(HttpServletRequest request, String key, Short defaultValue) {
        String str = request.getParameter(key);
        return StringUtils.isEmpty(str)?defaultValue:Short.valueOf(Short.parseShort(str));
    }

    public static Date getDate(HttpServletRequest request, String key) throws ParseException {
        String str = request.getParameter(key);
        return StringUtils.isEmpty(str)?null:DateUtil.parseDate(str);
    }

    public static JSONObject getJSONObject(HttpServletRequest request, String key) {
        return getJSONObject(request, key, (JSONObject)null);
    }

    public static JSONObject getJSONObject(HttpServletRequest request, String key, JSONObject defaultValue) {
        String val = request.getParameter(key);
        return StringUtil.isNotEmpty(val)?JSONObject.parseObject(val):defaultValue;
    }

    public static String getUrl(HttpServletRequest request) {
        StringBuilder urlThisPage = new StringBuilder();
        urlThisPage.append(request.getRequestURI());
        Enumeration e = request.getParameterNames();
        urlThisPage.append("?");

        while(e.hasMoreElements()) {
            String para = (String)e.nextElement();
            String values = request.getParameter(para);
            urlThisPage.append(para);
            urlThisPage.append("=");
            urlThisPage.append(values);
            urlThisPage.append("&");
        }

        return urlThisPage.substring(0, urlThisPage.length() - 1);
    }

    public static String getUrl(HttpServletRequest request, String excludeParams) {
        if(StringUtils.isEmpty(excludeParams)) {
            return getUrl(request);
        } else {
            StringBuilder urlThisPage = new StringBuilder();
            urlThisPage.append(request.getRequestURI());
            if(request.getParameterNames().hasMoreElements()) {
                return urlThisPage.toString();
            } else {
                Enumeration e = request.getParameterNames();
                urlThisPage.append("?");

                while(e.hasMoreElements()) {
                    String para = (String)e.nextElement();
                    if(excludeParams.indexOf(para) == -1) {
                        String values = request.getParameter(para);
                        urlThisPage.append(para);
                        urlThisPage.append("=");
                        urlThisPage.append(values);
                        urlThisPage.append("&");
                    }
                }

                return urlThisPage.substring(0, urlThisPage.length() - 1);
            }
        }
    }

    public static String getRequestURI(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        if(StringUtils.isNotEmpty(contextPath)) {
            int index = url.indexOf(contextPath);
            if(index != -1) {
                return url.substring(contextPath.length());
            }
        }

        return url;
    }

    public static String getPrePage(HttpServletRequest request) {
        return request.getHeader("REFERER");
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                if(cookie.getName().equals(cookieName) && StringUtils.isNotBlank(cookie.getValue())) {
                    return cookie.getValue();
                }
            }
        }

        return "";
    }

    public static Boolean isCookieName(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                if(cookie.getName().equals(cookieName)) {
                    return Boolean.valueOf(true);
                }
            }
        }

        return Boolean.valueOf(false);
    }

    public static Map<String, Object> getParameterValueMap(HttpServletRequest request, boolean remainArray) {
        HashMap map = new HashMap();
        Enumeration params = request.getParameterNames();

        while(params.hasMoreElements()) {
            String key = params.nextElement().toString();
            String[] values = request.getParameterValues(key);
            if(values != null) {
                String rtn;
                if(values.length == 1) {
                    rtn = values[0];
                    if(rtn != null) {
                        rtn = rtn.trim();
                        if(!rtn.equals("") && !rtn.equals("")) {
                            map.put(key, rtn);
                        }
                    }
                } else {
                    rtn = getByAry(values);
                    if(rtn.length() > 0) {
                        if(remainArray) {
                            map.put(key, rtn.split(","));
                        } else {
                            map.put(key, rtn);
                        }
                    }
                }
            }
        }

        return map;
    }

    private static String getByAry(String[] aryTmp) {
        String rtn = "";

        for(int i = 0; i < aryTmp.length; ++i) {
            String str = aryTmp[i].trim();
            if(!str.equals("")) {
                rtn = rtn + str + ",";
            }
        }

        if(rtn.length() > 0) {
            rtn = rtn.substring(0, rtn.length() - 1);
        }

        return rtn;
    }
}

