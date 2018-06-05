package com.elex.oa.service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;

import com.elex.oa.entity.bo.SysBoDef;
import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.WebAppUtil;
import org.apache.commons.lang.StringUtils;
public class BoDataUtil {
    public BoDataUtil() {
    }

    public static IFormDataHandler getDataHandler(String dataSaveMode) {
        return "all".equals(dataSaveMode)?(IFormDataHandler) WebAppUtil.getBean("allFormDataHandler"):("db".equals(dataSaveMode)?(IFormDataHandler)WebAppUtil.getBean("dbFormDataHandler"):(IFormDataHandler)WebAppUtil.getBean("jsonFormDataHandler"));
    }

    public static Map<String, Object> getModelFieldsFromBoJsons(JSONObject boJsons) {
        //SysBoDefManager boDefManager = (SysBoDefManager)WebAppUtil.getBean(SysBoDefManager.class);
        ISysBoDefService sysBoDefService = (ISysBoDefService)WebAppUtil.getBean(ISysBoDefService.class);
        HashMap maps = new HashMap();
        if(boJsons == null) {
            return maps;
        } else {
            JSONArray jsonArr = boJsons.getJSONArray("vars");
            String boDefId;
            if(jsonArr != null) {
                for(int boArr = 0; boArr < jsonArr.size(); ++boArr) {
                    JSONObject i = jsonArr.getJSONObject(boArr);
                    String varObj = i.getString("key");
                    boDefId = i.getString("val");
                    if(!StringUtils.isEmpty(varObj) && !StringUtils.isEmpty(boDefId)) {
                        maps.put(varObj, boDefId);
                    }
                }
            }

            JSONArray var12 = boJsons.getJSONArray("bos");
            if(BeanUtil.isEmpty(var12)) {
                return maps;
            } else {
                for(int var13 = 0; var13 < var12.size(); ++var13) {
                    JSONObject var14 = var12.getJSONObject(var13);
                    boDefId = var14.getString("boDefId");
                    SysBoDef boDef = sysBoDefService.getById(boDefId);
                    JSONObject boData = var14.getJSONObject("data");
                    String pre = boDef.getAlais() + ".";
                    Map boFields = FastjsonUtil.json2Map(pre, boData);
                    maps.putAll(boFields);
                }

                return maps;
            }
        }
    }
}
