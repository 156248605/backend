package com.elex.oa.form.entity;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
/**
 *@author hugo.zhao
 *@since 2018/5/7 11:55
*/
public class FormModel {
    private boolean result = true;
    private String msg = "";
    private String type = "";
    private String formKey = "";
    private String content = "";
    private String permission = "";
    private JSONObject jsonData;
    private String description = "";
    private String viewId = "";
    private String boDefId = "";
    private String timeStamp = "";
    private Map<String, Object> params = new HashMap();

    public FormModel() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public JSONObject getJsonData() {
        return this.jsonData;
    }

    public void setJsonData(JSONObject jsonData) {
        this.jsonData = jsonData;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getViewId() {
        return this.viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void addParams(String key, Object val) {
        this.params.put(key, val);
    }

    public String getBoDefId() {
        return this.boDefId;
    }

    public void setBoDefId(String boDefId) {
        this.boDefId = boDefId;
    }

    public String getFormKey() {
        return this.formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
