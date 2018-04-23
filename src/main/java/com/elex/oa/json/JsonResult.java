package com.elex.oa.json;
import com.alibaba.fastjson.JSONObject;
/**
 *@author hugo.zhao
 *@since 2018/4/9 15:31
*/
public class JsonResult<T> {
    private boolean success = false;
    private String message = "";
    private T data = null;
    private String extPros = null;

    public JsonResult() {
    }

    public JsonResult(boolean success) {
        this.success = success;
    }

    public JsonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public JsonResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getExtPros() {
        return this.extPros;
    }

    public void setExtPros(String extPros) {
        this.extPros = extPros;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
