package com.elex.oa.service.impl;


import com.elex.oa.api.ContextHandlerFactory;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.entity.bo.SysBoEnt;
import com.elex.oa.script.GroovyEngine;
import com.elex.oa.service.IDataSettingHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;

import com.elex.oa.service.ISysBoEntService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DataSettingHandler implements IDataSettingHandler {

    @Resource
    ISysBoEntService sysBoEntService;
    @Resource
    ContextHandlerFactory contextHandlerFactory;
    @Resource
    GroovyEngine groovyEngine;
    private Pattern patten = Pattern.compile("\\[(.*?)\\]", 10);

    public DataSettingHandler() {
    }

    public void handSetting(JSONObject jsonData, String boDefId, JSONObject setting, boolean isSave, Map<String, Object> vars) {
        if(setting != null) {
            if(vars == null) {
                vars = new HashMap();
            }

            ((Map)vars).put("jsonData", jsonData);
            String boAttSettings;
            if(isSave) {
                boAttSettings = setting.getString("saveScript");
                if(StringUtils.isNotEmpty(boAttSettings)) {
                    this.groovyEngine.executeScripts(boAttSettings, (Map)vars);
                }
            } else {
                boAttSettings = setting.getString("initScript");
                if(StringUtils.isNotEmpty(boAttSettings)) {
                    this.groovyEngine.executeScripts(boAttSettings, (Map)vars);
                }
            }

            JSONArray var21 = setting.getJSONArray("boAttSettings");
            if(var21 != null && var21.size() != 0) {
                JSONObject boAttsObj = null;

                for(int sysBoEnts = 0; sysBoEnts < var21.size(); ++sysBoEnts) {
                    JSONObject tmpObj = var21.getJSONObject(sysBoEnts);
                    String ent = tmpObj.getString("boDefId");
                    if(boDefId.equals(ent)) {
                        boAttsObj = tmpObj;
                        break;
                    }
                }

                if(boAttsObj != null) {
                    if(isSave) {
                        boAttsObj = boAttsObj.getJSONObject("save");
                    } else {
                        boAttsObj = boAttsObj.getJSONObject("init");
                    }

                    if(boAttsObj != null) {
                        //List var22 = this.sysBoEntManager.getListByBoDefId(boDefId, true);
                        List var22 = this.sysBoEntService.getListByBoDefId(boDefId, true);
                        Iterator var23 = var22.iterator();

                        while(true) {
                            JSONObject boJson;
                            SysBoEnt var24;
                            do {
                                if(!var23.hasNext()) {
                                    return;
                                }

                                var24 = (SysBoEnt)var23.next();
                                boJson = boAttsObj.getJSONObject(var24.getName());
                            } while(boJson == null);

                            Set boFieldSet = boJson.keySet();
                            boolean isMain = "main".equals(var24.getRelationType());
                            JSONObject jsonSubRow = new JSONObject();
                            JSONObject jsonSub = null;
                            if(!isMain) {
                                if(!jsonData.containsKey("initData")) {
                                    jsonSub = new JSONObject();
                                    jsonData.put("initData", jsonSub);
                                } else {
                                    jsonSub = jsonData.getJSONObject("initData");
                                }
                            }

                            Map attrs = this.getAttrMap(var24);
                            Iterator it = boFieldSet.iterator();

                            while(it.hasNext()) {
                                String key = (String)it.next();
                                if(attrs.containsKey(key)) {
                                    SysBoAttr attr = (SysBoAttr)attrs.get(key);
                                    JSONObject jsonSetting = boJson.getJSONObject(key);
                                    if(isMain) {
                                        this.handJson(jsonData, jsonSetting, attr, (Map)vars);
                                    } else {
                                        this.handJson(jsonSubRow, jsonSetting, attr, (Map)vars);
                                        jsonSub.put(var24.getName(), jsonSubRow);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void handJson(JSONObject rowData, JSONObject jsonSetting, SysBoAttr attr, Map<String, Object> vars) {
        String valType = jsonSetting.getString("valType");
        if("manual".equals(valType)) {
            this.handManual(rowData, jsonSetting, attr);
        } else if("constant".equals(valType)) {
            this.handConstant(rowData, jsonSetting, attr, vars);
        } else if("script".equals(valType)) {
            this.handScript(rowData, jsonSetting, attr, vars);
        }

    }

    void handManual(JSONObject rowData, JSONObject jsonSetting, SysBoAttr attr) {
        String name = attr.getName();
        String valName = "val" + "_NAME".toLowerCase();
        rowData.put(name, jsonSetting.getString("val"));
        if(!attr.single()) {
            rowData.put(name + "_NAME".toLowerCase(), jsonSetting.getString(valName));
        }

    }

    void handScript(JSONObject rowData, JSONObject jsonSetting, SysBoAttr attr, Map<String, Object> vars) {
        String name = attr.getName();
        String valName = "val" + "_NAME".toLowerCase();
        String script = jsonSetting.getString("val");
        Object rtn = this.executeScript(rowData, script, vars);
        rowData.put(name, rtn);
        if(!attr.single()) {
            String nameScript = jsonSetting.getString(valName);
            Object rtnName = this.executeScript(rowData, nameScript, vars);
            rowData.put(name + "_NAME".toLowerCase(), rtnName);
        }

    }

    private Object executeScript(JSONObject rowData, String script, Map<String, Object> vars) {
        String val;
        for(Matcher m = this.patten.matcher(script); m.find(); script = script.replace(m.group(0), val)) {
            String rtn = m.group(1);
            val = rowData.getString(rtn);
        }

        Object rtn1 = this.groovyEngine.executeScripts(script, vars);
        return rtn1;
    }

    void handConstant(JSONObject rowData, JSONObject jsonSetting, SysBoAttr attr, Map<String, Object> vars) {
        String name = attr.getName();
        String valName = "val" + "_NAME".toLowerCase();
        String constantKey = jsonSetting.getString("val");
        Object val = this.contextHandlerFactory.getValByKey(constantKey, vars);
        rowData.put(name, val);
        if(!attr.single()) {
            String constantNameKey = jsonSetting.getString(valName);
            Object val_name = this.contextHandlerFactory.getValByKey(constantNameKey, vars);
            rowData.put(name + "_NAME".toLowerCase(), val_name);
        }

    }

    private Map<String, SysBoAttr> getAttrMap(SysBoEnt ent) {
        HashMap attrMap = new HashMap();
        List attrs = ent.getSysBoAttrs();
        Iterator var4 = attrs.iterator();

        while(var4.hasNext()) {
            SysBoAttr attr = (SysBoAttr)var4.next();
            attrMap.put(attr.getName(), attr);
        }

        return attrMap;
    }

    public static void main(String[] args) {
        String str = "aaa[userid],[name],[name]dddd";
        Pattern p = Pattern.compile("\\[(.*?)\\]", 10);

        for(Matcher m = p.matcher(str); m.find(); str = str.replace(m.group(0), "")) {
            System.out.println(str);
        }

        System.out.println(str);
    }
}
