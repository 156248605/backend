package com.elex.oa.service;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.BoResult;

public interface IFormDataHandler {
    JSONObject getInitData(String var1);

    JSONObject getData(String var1, String var2);

    BoResult saveData(String var1, String var2, JSONObject var3);
}