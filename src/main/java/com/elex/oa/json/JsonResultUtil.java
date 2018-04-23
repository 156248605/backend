package com.elex.oa.json;
/**
 *@author hugo.zhao
 *@since 2018/4/9 15:33
*/
public class JsonResultUtil {
    public JsonResultUtil() {
    }

    public static JsonResult success() {
        JsonResult result = new JsonResult(true);
        result.setMessage("成功");
        return result;
    }

    public static JsonResult success(Object data) {
        JsonResult result = new JsonResult(true);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    public static JsonResult success(String msg, Object data) {
        JsonResult result = new JsonResult(true);
        result.setMessage(msg);
        return result;
    }

    public static JsonResult fail() {
        JsonResult result = new JsonResult(false);
        result.setMessage("失败");
        return result;
    }

    public static JsonResult fail(String msg) {
        JsonResult result = new JsonResult(false);
        result.setMessage(msg);
        return result;
    }

    public static JsonResult fail(String msg, Object obj) {
        JsonResult result = new JsonResult(false);
        result.setMessage(msg);
        result.setData(obj);
        return result;
    }
}
