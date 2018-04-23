package com.elex.oa.entity.core;
import com.alibaba.fastjson.JSONObject;
/**
 *@author hugo.zhao
 *@since 2018/4/13 14:52
*/
public class KeyValEnt<T> {
    private String key = "";
    private T val = null;

    public KeyValEnt() {
    }

    public KeyValEnt(String key, T val) {
        this.key = key;
        this.val = val;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getVal() {
        return this.val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
