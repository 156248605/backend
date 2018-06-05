package com.elex.oa.service.activiti;

import com.elex.oa.core.entity.ProcessConfig;
import com.elex.oa.core.entity.StartEventConfig;
import com.elex.oa.dao.IBpmNodeSetDao;
import com.elex.oa.dao.IBpmSolutionDao;
import com.elex.oa.entity.BpmDef;
import com.elex.oa.entity.BpmFormView;
import com.elex.oa.entity.BpmSolution;
import com.elex.oa.entity.activiti.BpmEventConfig;
import com.elex.oa.entity.activiti.BpmNodeSet;
import com.elex.oa.entity.activiti.BpmSolFv;
import com.elex.oa.entity.bo.SysBoEnt;
import com.elex.oa.json.JSONUtil;
import com.elex.oa.service.IBpmDefService;
import com.elex.oa.service.IBpmFormViewService;
import com.elex.oa.service.IBpmSolFvService;
import com.elex.oa.service.ISysBoEntService;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Iterator;
import java.util.List;

/**
 *@author hugo.zhao
 *@since ` 11:47
*/
@Service
public class BpmNodeSetService {
    @Autowired
    private IBpmNodeSetDao bpmNodeSetDao;

    @Autowired
    private IBpmSolutionDao bpmSolutionDao;

    @Autowired
    private IBpmSolFvService bpmSolFvService;

    @Autowired
    private IBpmFormViewService bpmFormViewService;

    @Autowired
    private ISysBoEntService sysBoEntService;

    @Autowired
    private IBpmDefService bpmDefService;

    @Autowired
    private ObjectMapper objectMapper;


    public BpmNodeSet getBySolIdActDefIdNodeId(String solId, String actDefId, String nodeId){
        BpmNodeSet bpmNodeSet = new BpmNodeSet();
        bpmNodeSet.setSettings(solId);
        bpmNodeSet.setActDefId(actDefId);
        bpmNodeSet.setNodeId(nodeId);
        return bpmNodeSetDao.selectOne(bpmNodeSet);
    }

    public void saveNodeConfig(String solId, String actDefId, String nodeId, String nodeType, String configJson, String tipText) {
        BpmNodeSet bpmNodeSet = this.getBySolIdActDefIdNodeId(solId, actDefId, nodeId);
        if(bpmNodeSet == null) {
            BpmSolution bpmSolution = (BpmSolution)this.bpmSolutionDao.selectByPrimaryKey(solId);
            bpmNodeSet = new BpmNodeSet();
            bpmNodeSet.setBpmSolution(bpmSolution);
            bpmNodeSet.setNodeId(nodeId);
            bpmNodeSet.setNodeType(nodeType);
            bpmNodeSet.setActDefId(actDefId);
            bpmNodeSet.setSettings(configJson);
            bpmNodeSet.setNodeCheckTip(tipText);
            bpmNodeSetDao.insert(bpmNodeSet);
        } else {
            bpmNodeSet.setNodeCheckTip(tipText);
            bpmNodeSet.setSettings(configJson);
            bpmNodeSetDao.updateByPrimaryKey(bpmNodeSet);
        }
    }

    public String getTableRightJson(String solId, String actDefId, String nodeId, ObjectNode configsNode) {
        BpmSolFv solFv = this.bpmSolFvService.getBySolIdActDefIdNodeId(solId, actDefId, nodeId);
        if(solFv == null) {
            return "{}";
        } else {
            String formAlias = "";
            String CondForms = solFv.getCondForms();
            JSONArray arr = JSONArray.parseArray(CondForms);
            JSONObject rtnJson = new JSONObject();
            if(arr.size() == 0) {
                CondForms = this.bpmSolFvService.getBySolIdActDefIdNodeId(solId, actDefId, "_PROCESS").getCondForms();
                arr = JSONArray.parseArray(CondForms);
            }

            for(int i = 0; i < arr.size(); ++i) {
                JSONObject ob = arr.getJSONObject(i);
                formAlias = ob.getString("formAlias");
                BpmFormView formView = this.bpmFormViewService.getLatestByKey(formAlias, solFv.getTenantId());
                if(formView == null) {
                    return "{}";
                }

                String boDefId = formView.getBoDefId();
                if(BeanUtil.isEmpty(boDefId)) {
                    return "{}";
                }

                String rightJson = JSONUtil.getJsonString(configsNode, "tableRightJson");
                //SysBoEnt boEnt = this.sysBoEntManager.getByBoDefId(boDefId, false);
                SysBoEnt boEnt = this.sysBoEntService.getByBoDefId(boDefId);
                List subList = boEnt.getBoEntList();
                if(BeanUtil.isEmpty(subList)) {
                    return "{}";
                }

                if(StringUtil.isEmpty(rightJson)) {
                    rightJson = "{}";
                }

                JSONObject orginJson = JSONObject.parseObject(rightJson);
                Iterator var18 = subList.iterator();

                while(var18.hasNext()) {
                    SysBoEnt ent = (SysBoEnt)var18.next();
                    String name = ent.getName();
                    JSONObject json;
                    if(!orginJson.containsKey(name)) {
                        json = new JSONObject();
                        json.put("type", "all");
                        json.put("comment", ent.getComment());
                        rtnJson.put(name, json);
                    } else {
                        json = orginJson.getJSONObject(name);
                        json.put("comment", ent.getComment());
                        rtnJson.put(name, json);
                    }
                }
            }

            return rtnJson.toJSONString();
        }
    }


    public ProcessConfig getProcessConfig(String solId) {
        BpmSolution bpmSolution = this.bpmSolutionDao.selectByPrimaryKey(solId);
        String actDefId = bpmSolution.getActDefId();
        if(StringUtil.isEmpty(actDefId)) {
            BpmDef bpmDef = this.bpmDefService.getLatestBpmByKey(bpmSolution.getDefKey(), "1");
            if(bpmDef != null) {
                actDefId = bpmDef.getActDefId();
            }
        }
        return this.getProcessConfig(solId, actDefId);
    }

    public ProcessConfig getProcessConfig(String solId, String actDefId) {
        String nodeId = "_PROCESS";
        BpmNodeSet bpmNodeSet = this.getBySolIdActDefIdNodeId(solId, actDefId, nodeId);
        ProcessConfig config = new ProcessConfig();
        if(bpmNodeSet == null) {
            return config;
        } else {
            try {
                JsonNode ex = this.objectMapper.readTree(bpmNodeSet.getSettings());
                ObjectNode configsNode = (ObjectNode)ex.get("configs");
                config.setIsSkipFirst(JSONUtil.getJsonString(configsNode, "isSkipFirst"));
                config.setIsShowStartUsers(JSONUtil.getJsonString(configsNode, "isShowStartUsers"));
                config.setSubRule(JSONUtil.getJsonString(configsNode, "subRule"));
                config.setNoRule(JSONUtil.getJsonString(configsNode, "noRule"));
                config.setAllowFreeJump(JSONUtil.getJsonString(configsNode, "allowFreeJump"));
                config.setShowCheckList(JSONUtil.getJsonString(configsNode, "showCheckList"));
                config.setCouldChooseConfig(JSONUtil.getJsonString(configsNode, "couldSelectNode"));
                config.setCouldChooseConfigName(JSONUtil.getJsonString(configsNode, "couldSelectNodeName"));
                config.setMustChooseConfig(JSONUtil.getJsonString(configsNode, "mustSelectNode"));
                config.setMustChooseConfigName(JSONUtil.getJsonString(configsNode, "mustSelectNodeName"));
                config.setNotices(JSONUtil.getJsonString(configsNode, "notices"));
                if(configsNode.get("preHandle") != null) {
                    config.setPreHandle(JSONUtil.getJsonString(configsNode, "preHandle"));
                }

                if(configsNode.get("afterHandle") != null) {
                    config.setAfterHandle(JSONUtil.getJsonString(configsNode, "afterHandle"));
                }

                if(configsNode.get("processEndHandle") != null) {
                    config.setProcessEndHandle(JSONUtil.getJsonString(configsNode, "processEndHandle"));
                }

                String tableRightJson = this.getTableRightJson(solId, actDefId, nodeId, configsNode);
                config.setTableRightJson(tableRightJson);
                JsonNode events = ex.get("events");
                if(events != null) {
                    Iterator eventIt = events.elements();

                    while(eventIt.hasNext()) {
                        JsonNode objNode = (JsonNode)eventIt.next();
                        BpmEventConfig eventConfig = this.objectMapper.readValue(objNode.toString(), BpmEventConfig.class);
                        config.getEvents().add(eventConfig);
                    }
                }
            } catch (Exception var13) {
                var13.printStackTrace();
               /* this.logger.error("solId:" + solId + " nodeId:" + nodeId + " json is error:" + var13.getMessage());
                this.logger.error("json is:" + bpmNodeSet.getSettings());*/
            }
            return config;
        }
    }

    public StartEventConfig getStartEventConfig(String solId, String actDefId, String nodeId) {
        BpmNodeSet bpmNodeSet = this.getBySolIdActDefIdNodeId(solId, actDefId, nodeId);
        StartEventConfig config = new StartEventConfig();
        if(bpmNodeSet == null) {
            return config;
        } else {
            try {
                JsonNode ex = this.objectMapper.readTree(bpmNodeSet.getSettings());
                ObjectNode configsNode = (ObjectNode)ex.get("configs");
                config.setAllowNextExecutor(JSONUtil.getJsonString(configsNode, "allowNextExecutor"));
                config.setAllowPathSelect(JSONUtil.getJsonString(configsNode, "allowPathSelect"));
                if(configsNode.get("preHandle") != null) {
                    config.setPreHandle(JSONUtil.getJsonString(configsNode, "preHandle"));
                }

                if(configsNode.get("afterHandle") != null) {
                    config.setAfterHandle(JSONUtil.getJsonString(configsNode, "afterHandle"));
                }

                JsonNode events = ex.get("events");
                Iterator eventIt = events.elements();

                while(eventIt.hasNext()) {
                    JsonNode objNode = (JsonNode)eventIt.next();
                    BpmEventConfig eventConfig = (BpmEventConfig)this.objectMapper.readValue(objNode.toString(), BpmEventConfig.class);
                    config.getEvents().add(eventConfig);
                }
            } catch (Exception var12) {
              /*  this.logger.error("solId:" + solId + " nodeId:" + nodeId + " json is error:" + var12.getMessage());
                this.logger.error("json is:" + bpmNodeSet.getSettings());*/
            }
            return config;
        }
    }



}
