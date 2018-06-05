package com.elex.oa.core.entity;

import com.alibaba.fastjson.JSONObject;

public interface IRightModel {
    void setRightJson(JSONObject var1);

    JSONObject getRightJson();

    String getSolId();
}
