package com.elex.oa.service.activiti;

import com.elex.oa.dao.IBpmNodeSetDao;
import com.elex.oa.dao.IBpmSolutionDao;
import com.elex.oa.entity.BpmFormView;
import com.elex.oa.entity.BpmSolution;
import com.elex.oa.entity.activiti.BpmNodeSet;
import com.elex.oa.entity.activiti.BpmSolFv;
import com.elex.oa.entity.bo.SysBoEnt;
import com.elex.oa.json.JSONUtil;
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





}
