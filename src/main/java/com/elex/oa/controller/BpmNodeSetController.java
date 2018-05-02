package com.elex.oa.controller;
import com.alibaba.fastjson.JSONArray;
import com.elex.oa.activiti.service.ActRepService;
import com.elex.oa.entity.ActNodeDef;
import com.elex.oa.entity.ActProcessDef;
import com.elex.oa.entity.BpmDef;
import com.elex.oa.entity.BpmSolution;
import com.elex.oa.entity.activiti.*;
import com.elex.oa.json.JSONUtil;
import com.elex.oa.service.IBpmDefService;
import com.elex.oa.service.IBpmSolutionService;
import com.elex.oa.service.activiti.BpmNodeSetService;
import com.elex.oa.service.activiti.BpmSolVarService;
import com.elex.oa.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import com.elex.oa.api.ContextHandlerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang.StringUtils;

/**
 *@author hugo.zhao
 *@since 2018/4/12 11:12
*/
@Controller
@CrossOrigin
@RequestMapping("/bpmNodeSet")
public class BpmNodeSetController {

     @Autowired
     private BpmNodeSetService bpmNodeSetService;

     @Autowired
     private IBpmSolutionService bpmSolutionService;

     @Autowired
     private IBpmDefService bpmDefService;

     @Autowired
     private ActRepService actRepService;

     @Autowired
     private BpmSolVarService bpmSolVarService;
   /*  @Resource(
             name = "iJson"
     )*/
     @Autowired
     ObjectMapper objectMapper;
     @Autowired
     ContextHandlerFactory contextHandlerFactory;

    @RequestMapping("/getNodeConfig")
    public String getNodeConfig(HttpServletRequest request, Map<String,Object> model) throws Exception{
        String nodeType = request.getParameter("nodeType");
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        String nodeId = request.getParameter("nodeId");
        String mv = null;
        BpmNodeSet bpmNodeSet = this.getBpmNodeSet(solId, actDefId, nodeId, nodeType);
        HashMap seqMap = new HashMap();
        BpmSolution bpmSolution = this.bpmSolutionService.getById(solId);
        BpmDef bpmDef = this.bpmDefService.getValidBpmDef(actDefId, bpmSolution.getDefKey());
        ActProcessDef processDef = this.actRepService.getProcessDef(bpmDef.getActDefId());
        ActNodeDef actNodeDef = (ActNodeDef)processDef.getNodesMap().get(nodeId);
        List configVars = this.bpmSolVarService.getNodeAllVars(solId, actDefId, nodeType, nodeId);
        if("process".equals(nodeType)){
            mv = "/bpmNodeSet/bpmNodeSetProcess";
        }else if("userTask".equals(nodeType)){
            mv = "/bpmNodeSet/bpmNodeSetUserTask";
            model.put("actNodeDef",actNodeDef);
            model.put("boDefId",bpmSolution.getBoDefId());
        }else if("startEvent".equals(nodeType)){
            mv = "/bpmNodeSet/bpmNodeSetStartEvent";
        }else if(!"inclusiveGateway".equals(nodeType) && !"exclusiveGateway".equals(nodeType)){
            if("endEvent".equals(nodeType)) {
                mv = "/bpmNodeSet/bpmNodeSetEndEvent";
            } else {
                mv = "/bpmNodeSet/bpmNodeSetActivity";
            }
        }else {
               mv = "/bpmNodeSet/bpmNodeSetExcGateway";
              this.setGateWay(model, solId, actDefId, actNodeDef, configVars, seqMap);
        }
            this.setSetting(model,bpmNodeSet,seqMap,configVars);
        return mv;
    }
    @RequestMapping("/getTaskNodes")
    @ResponseBody
    public Collection<ActivityNode> getTaskNodes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String actDefId = request.getParameter("actDefId");
        String includeProcess = request.getParameter("includeProcess");
        Collection actNodes = this.actRepService.getUserTasks(actDefId);
        if(StringUtils.isNotEmpty(includeProcess)) {
          //  BpmDef bpmDef = this.bpmDefManager.getByActDefId(actDefId);
            BpmDef bpmDef = this.bpmDefService.getByActDefId(actDefId);
            ActivityNode processNode = new ActivityNode("_PROCESS", bpmDef.getSubject(), "process", "");
            actNodes.add(processNode);
        }
        return actNodes;
    }

    @RequestMapping("/getActivityNodes")
    @ResponseBody
    public Collection<ActivityNode> getActivityNodes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String actDefId = request.getParameter("actDefId");
        Collection actNodes = this.actRepService.getProcessNodes(actDefId);
        ArrayList list = new ArrayList();
        Iterator var6 = actNodes.iterator();
        while(var6.hasNext()) {
            ActivityNode node = (ActivityNode)var6.next();
            if(!node.getType().equals("endEvent") && !node.getType().equals("startEvent")) {
                list.add(node);
            }
        }
         return list;
    }
    private BpmNodeSet getBpmNodeSet(String solId, String actDefId, String nodeId, String nodeType){
        BpmNodeSet bpmNodeSet = bpmNodeSetService.getBySolIdActDefIdNodeId(solId,nodeId,nodeType);
        if(bpmNodeSet != null){
            return bpmNodeSet;
        }else {
            if(nodeType.equals("userTask")){
                bpmNodeSet = this.createBpmNodeSet(solId, actDefId, nodeId, nodeType);
            }else {
                bpmNodeSet = new BpmNodeSet();
                bpmNodeSet.setActDefId(actDefId);
                bpmNodeSet.setNodeType(nodeType);
                bpmNodeSet.setSolId(solId);
                bpmNodeSet.setNodeId(nodeId);
            }
            return bpmNodeSet;
        }
    }

    private BpmNodeSet createBpmNodeSet(String solId, String actDefId, String nodeId, String nodeType){
        String configJsonInit = "{\"configs\":{\"allowConfigButtons\":\"false\",\"allowPathSelect\":\"false\",\"sameUserNext\":\"false\",\"showCheckList\":\"false\",\"allowPrint\":\"false\",\"allowNextExecutor\":\"false\",\"notices\":\"\",\"showOpinion\":\"no\",\"tableRightJson\":\"{}\",\"pagesize\":10,\"preHandle\":\"\",\"afterHandle\":\"\",\"checkTip\":\"\",\"notices_name\":\"\",\"pagesize_name\":\"10\",\"buttons\":[]},\"events\":[{\"eventKey\":\"TASK_CREATED\",\"eventName\":\"[任务创建]-事件脚本\",\"script\":\" \"},{\"eventKey\":\"TASK_COMPLETED\",\"eventName\":\"[任务完成]-事件脚本\",\"script\":\"\"},{\"eventKey\":\"ACTIVITY_STARTED\",\"eventName\":\"[节点活动开始]-事件脚本\",\"script\":\"\"},{\"eventKey\":\"ACTIVITY_COMPLETED\",\"eventName\":\"[节点活动结束]-事件脚本\",\"script\":\"\"}]}";
        this.bpmNodeSetService.saveNodeConfig(solId, actDefId, nodeId, nodeType, configJsonInit, "");
        return this.bpmNodeSetService.getBySolIdActDefIdNodeId(solId, actDefId, nodeId);
    }

    private void setGateWay(Map<String,Object> model , String solId, String actDefId, ActNodeDef actNodeDef, List<BpmSolVar> configVars, Map<String, ActSequenceFlow> seqMap) {
        String nodeId = actNodeDef.getNodeId();
        ArrayList preConfigVars = new ArrayList();
        preConfigVars.addAll(configVars);
        List inNodes = this.actRepService.getIncomeNodes(actDefId, nodeId);
        Iterator outNodeDefs = inNodes.iterator();

        while(outNodeDefs.hasNext()) {
            ActNodeDef inNodeDef = (ActNodeDef)outNodeDefs.next();
           // List nodeDef = this.bpmSolVarManager.getBySolIdActDefIdNodeId(solId, actDefId, inNodeDef.getNodeId());
            List nodeDef = this.bpmSolVarService.getBySolIdActDefIdNodeId(solId, actDefId, inNodeDef.getNodeId());
            preConfigVars.addAll(nodeDef);
        }

        model.put("preConfigVars", preConfigVars);
        List outNodeDefs1 = this.actRepService.getOutcomeNodes(actDefId, nodeId);
        Iterator inNodeDef1 = outNodeDefs1.iterator();

        while(inNodeDef1.hasNext()) {
            ActNodeDef nodeDef1 = (ActNodeDef)inNodeDef1.next();
            ActSequenceFlow flow = new ActSequenceFlow();
            flow.setSourceNodeId(actNodeDef.getNodeId());
            flow.setSourceNodeName(actNodeDef.getNodeName());
            flow.setDestNodeId(nodeDef1.getNodeId());
            flow.setDestNodeName(nodeDef1.getNodeName());
            seqMap.put(nodeDef1.getNodeId(), flow);
        }

        model.put("seqMap", seqMap);
    }

    private void setSetting(Map<String,Object>  mv, BpmNodeSet bpmNodeSet, Map<String, ActSequenceFlow> seqMap, List<BpmSolVar> configVars) throws JsonProcessingException, IOException {
        String nodeType = bpmNodeSet.getNodeType();
        String solId = bpmNodeSet.getSolId();
        String actDefId = bpmNodeSet.getActDefId();
        String nodeId = bpmNodeSet.getNodeId();
        List eventConfigs = this.getEventConfigs(nodeType);
        if(bpmNodeSet != null && StringUtil.isNotEmpty(bpmNodeSet.getSettings())) {
            JsonNode contextVarAry = this.objectMapper.readTree(bpmNodeSet.getSettings());
            JsonNode eventKeys = contextVarAry.get("configs");
            if(eventKeys != null) {
                this.setConditions(seqMap, eventKeys);
                Map configMap = JSONUtil.jsonNode2Map(eventKeys);
               // String conf = this.bpmNodeSetManager.getTableRightJson(solId, actDefId, nodeId, (ObjectNode)eventKeys);
                String conf = this.bpmNodeSetService.getTableRightJson(solId, actDefId, nodeId, (ObjectNode)eventKeys);
                configMap.put("tableRightJson", conf);
                mv.putAll(configMap);
            }

            this.setEventConfig(contextVarAry, eventConfigs);
        }

        JSONArray contextVarAry1 = this.getContextVars();
        mv.put("contextVars", contextVarAry1.toJSONString());
        mv.put("configVars", configVars);
        mv.put("eventConfigs", eventConfigs);
        ArrayList eventKeys1 = new ArrayList();
        Iterator configMap1 = eventConfigs.iterator();

        while(configMap1.hasNext()) {
            BpmEventConfig conf1 = (BpmEventConfig)configMap1.next();
            eventKeys1.add(conf1.getEventKey());
        }

        //mv.addAttribute("eventKeys", this.iJson.toJson(eventKeys1));
        mv.put("eventKeys", null);
    }

    private List<BpmEventConfig> getEventConfigs(String nodeType) {
        ArrayList eventConfigs = new ArrayList();
        int var4;
        int var5;
        BpmEventConfig evt1;
        if("process".equals(nodeType)) {
            ProcessEventType[] evt = ProcessEventType.values();
            var4 = evt.length;

            for(var5 = 0; var5 < var4; ++var5) {
                ProcessEventType ev = evt[var5];
                evt1 = new BpmEventConfig(ev.name(), ev.getEventName(), "");
                eventConfigs.add(evt1);
            }
        } else if("userTask".equals(nodeType)) {
            TaskEventType[] var8 = TaskEventType.values();
            var4 = var8.length;

            for(var5 = 0; var5 < var4; ++var5) {
                TaskEventType var11 = var8[var5];
                evt1 = new BpmEventConfig(var11.name(), var11.getEventName(), "");
                eventConfigs.add(evt1);
            }
        } else {
            BpmEventConfig var9;
            if("endEvent".equals(nodeType)) {
                var9 = new BpmEventConfig(ProcessEventType.PROCESS_COMPLETED.name(), ProcessEventType.PROCESS_COMPLETED.getEventName(), "");
                eventConfigs.add(var9);
            } else if("startEvent".equals(nodeType)) {
                var9 = new BpmEventConfig(ProcessEventType.PROCESS_STARTED.name(), ProcessEventType.PROCESS_STARTED.getEventName(), "");
                eventConfigs.add(var9);
            } else {
                ActivityEventType[] var10 = ActivityEventType.values();
                var4 = var10.length;

                for(var5 = 0; var5 < var4; ++var5) {
                    ActivityEventType var12 = var10[var5];
                    evt1 = new BpmEventConfig(var12.name(), var12.getEventName(), "");
                    eventConfigs.add(evt1);
                }
            }
        }
        return eventConfigs;
    }

    private void setConditions(Map<String, ActSequenceFlow> seqMap, JsonNode configsNode) {
        JsonNode configs = configsNode.get("conditions");
        if(!BeanUtil.isEmpty(configs)) {
            Iterator it = configs.elements();

            while(it.hasNext()) {
                ObjectNode config = (ObjectNode)it.next();
                String tmpNodeId = config.get("nodeId").textValue();
                String tmpCondition = config.get("condition").textValue();
                if(seqMap.get(tmpNodeId) != null) {
                    ((ActSequenceFlow)seqMap.get(tmpNodeId)).setConditionExpression(tmpCondition);
                }
            }
        }
    }

    private void setEventConfig(JsonNode jsonObject, List<BpmEventConfig> eventConfigs) {
        JsonNode eventNode = jsonObject.path("events");
        if(!BeanUtil.isEmpty(eventNode)) {
            Iterator it = eventNode.iterator();

            while(true) {
                while(it.hasNext()) {
                    ObjectNode eventJson = (ObjectNode)it.next();
                    String event = eventJson.get("eventKey").textValue();
                    String script = eventJson.get("script").textValue();
                    String dbAlias = eventJson.get("dbAlias") != null?eventJson.get("dbAlias").textValue():"";
                    Iterator var9 = eventConfigs.iterator();

                    while(var9.hasNext()) {
                        BpmEventConfig evt = (BpmEventConfig)var9.next();
                        if(evt.getEventKey().equals(event)) {
                            evt.setScript(script);
                            evt.setDbAlias(dbAlias);
                            break;
                        }
                    }
                }

                return;
            }
        }
    }

    private JSONArray getContextVars() {
        JSONArray contextVarAry = this.contextHandlerFactory.getJsonHandlers();
        this.addVars(contextVarAry);
        return contextVarAry;
    }

    private void addVars(JSONArray ary) {
        JSONObject ref = new JSONObject();
        ref.put("key", "REF_ID_");
        ref.put("val", "外键字段");
        JSONObject refState = new JSONObject();
        refState.put("key", "#{fk}");
        refState.put("val", "外键占位符");
        ary.add(ref);
        ary.add(refState);
    }


}
