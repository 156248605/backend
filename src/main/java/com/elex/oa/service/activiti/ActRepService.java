package com.elex.oa.service.activiti;

import com.elex.oa.dao.IActGeByteArrayDao;
import com.elex.oa.entity.ActNodeDef;
import com.elex.oa.entity.ActProcessDef;
import com.elex.oa.entity.activiti.ActivityNode;
import com.elex.oa.service.IActGeBytearrayService;
import com.elex.oa.service.IBpmDefService;
import com.elex.oa.util.StringUtil;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.activiti.engine.ProcessEngineConfiguration;

import java.util.*;

/**
 *@author hugo.zhao
 *@since 2018/4/8 15:36
*/
@Service
public class ActRepService {

    private static String ACT_PROCESS_DEF = "actProcessDef";

    @Autowired
    private IActGeBytearrayService actGeBytearrayService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    private IBpmDefService bpmDefService;

    public ActRepService() {
    }

    public ActNodeDef getNodeAfterStart(String actDefId) {
        ActProcessDef processDef = this.getProcessDef(actDefId);
        Iterator var3 = processDef.getActNodeDefs().iterator();
        while(var3.hasNext()) {
            ActNodeDef nodeDef = (ActNodeDef)var3.next();
            if(nodeDef.getNodeType().equals("startEvent")) {
                List outNodeDefs = nodeDef.getOutcomeNodes();
                if(outNodeDefs.size() > 0) {
                    return (ActNodeDef)outNodeDefs.get(0);
                }
            }
        }
        return  null;
    }
    public String getBpmnXmlByDeployId(String deployId){
        return this.actGeBytearrayService.getDefXmlByDeployId(deployId);

    }
    public ActProcessDef getProcessDef(String actDefId) {
            ProcessDefinitionEntity processDefEntity = (ProcessDefinitionEntity)this.repositoryService.getProcessDefinition(actDefId);
            ActProcessDef processDef = new ActProcessDef();
            processDef.setProcessDefId(actDefId);
            processDef.setProcessKey(processDefEntity.getKey());
            processDef.setProcessName(processDefEntity.getName());
            List actvities = processDefEntity.getActivities();
            Iterator var5 = actvities.iterator();

            while(var5.hasNext()) {
                ActivityImpl actImpl = (ActivityImpl)var5.next();
                this.genActNodes(actImpl, processDef.getNodesMap());
            }

            return processDef;

    }
    public Collection<ActNodeDef> getTaskNodes(String actDefId) {
        ActProcessDef actProcessDef = this.getProcessDef(actDefId);
        ArrayList nodes = new ArrayList();
        Collection actNodeDefs = actProcessDef.getNodesMap().values();
        Iterator var5 = actNodeDefs.iterator();
        while(var5.hasNext()) {
            ActNodeDef def = (ActNodeDef)var5.next();
            if("userTask".equals(def.getNodeType())) {
                nodes.add(def);
            }
        }
        return nodes;
    }
    private ActNodeDef genActNodes(PvmActivity actImpl, Map<String, ActNodeDef> actNodeDefsMap){
        String nodeId = actImpl.getId();
        if(actNodeDefsMap.containsKey(nodeId)){
            return (ActNodeDef)actNodeDefsMap.get(nodeId);
        }else {
            String nodeName = (String)actImpl.getProperty("name");
            String nodeType = (String)actImpl.getProperty("type");
            String multiInstance = (String)actImpl.getProperty("multiInstance");
            String tmpId = nodeId;
            if(nodeId.length() > 5) {
                tmpId = nodeId.substring(nodeId.length() - 5);
            }
            if(StringUtil.isEmpty(nodeName)) {
                if("startEvent".equals(nodeType)) {
                    nodeName = "开始";
                } else if("endEvent".equals(nodeType)) {
                    nodeName = "结束";
                } else if("exclusiveGateway".equals(nodeType)) {
                    nodeName = "单一选择网关(" + tmpId + ")";
                } else if("includeGateway".equals(nodeType)) {
                    nodeName = "条件选择网关(" + tmpId + ")";
                } else if("parallelGateway".equals(nodeType)) {
                    nodeName = "并行(" + tmpId + ")";
                } else if("subProcess".equals(nodeType)) {
                    nodeName = "子流程(" + tmpId + ")";
                }
            }
            ActNodeDef nodeDef = new ActNodeDef(nodeId, nodeName, nodeType);
            nodeDef.setMultiInstance(multiInstance);
            actNodeDefsMap.put(nodeId, nodeDef);
            Iterator var9 = actImpl.getOutgoingTransitions().iterator();
            while(true) {
                while(true) {
                    while(var9.hasNext()) {
                        PvmTransition pt = (PvmTransition)var9.next();
                        PvmActivity outAct = pt.getDestination();
                        String destNodeType = (String)outAct.getProperty("type");
                        if("subProcess".equals(destNodeType)) {
                            List outNodeDef1 = outAct.getActivities();
                            Iterator var14 = outNodeDef1.iterator();

                            while(var14.hasNext()) {
                                PvmActivity av = (PvmActivity)var14.next();
                                String subAvType = (String)av.getProperty("type");
                                if("startEvent".equals(subAvType)) {
                                    Iterator var17 = av.getOutgoingTransitions().iterator();

                                    while(var17.hasNext()) {
                                        PvmTransition startOut = (PvmTransition)var17.next();
                                        ActivityImpl startOutAct = (ActivityImpl)startOut.getDestination();
                                        nodeDef.getOutcomeNodes().add(this.genActNodes(startOutAct, actNodeDefsMap));
                                    }
                                    break;
                                }
                            }
                        } else {
                            ActNodeDef outNodeDef = this.genActNodes(outAct, actNodeDefsMap);
                            nodeDef.getOutcomeNodes().add(outNodeDef);
                        }
                    }
                    return nodeDef;
                }
            }
        }

    }
    public Collection<TaskDefinition> getTaskDefs(String actDefId) {
        ProcessDefinition processDef = this.repositoryService.getProcessDefinition(actDefId);
        ProcessDefinitionEntity entity = (ProcessDefinitionEntity)processDef;
        Map taskMap = entity.getTaskDefinitions();
        return taskMap.values();
    }

    public Collection<ActivityNode> getUserTasks(String actDefId) {
        ArrayList nodes = new ArrayList();
        Collection taskDefs = this.getTaskDefs(actDefId);
        Iterator var4 = taskDefs.iterator();

        while(var4.hasNext()) {
            TaskDefinition taskDef = (TaskDefinition)var4.next();
            String actId = taskDef.getKey();
            String type = "userTask";
            String name = "";
            if(taskDef.getNameExpression() != null) {
                name = taskDef.getNameExpression().getExpressionText();
            }

            ActivityNode node = new ActivityNode(actId, name, type, "");
            node.setParentActivitiId("_PROCESS");
            nodes.add(node);
        }

        return nodes;
    }
    public List<ActNodeDef> getIncomeNodes(String actDefId, String nodeId) {
        ProcessDefinition processDef = this.repositoryService.getProcessDefinition(actDefId);
        ProcessDefinitionEntity entity = (ProcessDefinitionEntity)processDef;
        ActivityImpl actImpl = entity.getProcessDefinition().findActivity(nodeId);
        ArrayList nodes = new ArrayList();
        if(actImpl == null) {
            return nodes;
        } else {
            Iterator var7 = actImpl.getIncomingTransitions().iterator();

            while(var7.hasNext()) {
                PvmTransition pt = (PvmTransition)var7.next();
                PvmActivity inAct = pt.getSource();
                String type = (String)inAct.getProperty("type");
                String name = (String)inAct.getProperty("name");
                nodes.add(new ActNodeDef(inAct.getId(), name, type));
            }

            return nodes;
        }
    }

    public List<ActNodeDef> getOutcomeNodes(String actDefId, String nodeId) {
        ProcessDefinition processDef = this.repositoryService.getProcessDefinition(actDefId);
        ProcessDefinitionEntity entity = (ProcessDefinitionEntity)processDef;
        ActivityImpl actImpl = entity.getProcessDefinition().findActivity(nodeId);
        ArrayList nodes = new ArrayList();
        if(actImpl == null) {
            return nodes;
        } else {
            Iterator var7 = actImpl.getOutgoingTransitions().iterator();

            while(var7.hasNext()) {
                PvmTransition pt = (PvmTransition)var7.next();
                PvmActivity outAct = pt.getDestination();
                String type = (String)outAct.getProperty("type");
                String name = (String)outAct.getProperty("name");
                nodes.add(new ActNodeDef(outAct.getId(), name, type));
            }

            return nodes;
        }
    }





}
