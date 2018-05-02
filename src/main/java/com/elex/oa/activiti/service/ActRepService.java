package com.elex.oa.activiti.service;
import com.elex.oa.entity.ActNodeDef;
import com.elex.oa.entity.ActProcessDef;
import com.elex.oa.entity.activiti.ActivityNode;
import com.elex.oa.service.IBpmDefService;
import com.elex.oa.service.impl.ActGeBytearrayImpl;
import com.elex.oa.util.ActivityNodeType;
import com.elex.oa.util.StringUtil;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *@author hugo.zhao
 *@since 2018/4/18 20:15
*/
@Service
public class ActRepService {
    private static String ACT_PROCESS_DEF = "actProcessDef";

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    IBpmDefService bpmDefManager;

    @Autowired
    ActGeBytearrayImpl actGeBytearray;

    public ActRepService() {
    }
/*    public void clearProcessDefinitionCache(String actDefId) {
        this.actProcessDefCache.delByKey(ACT_PROCESS_DEF + actDefId);
        ActivitiDefCache.clearByDefId(actDefId);
    }*/

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
        return null;
    }

    public String getBpmnXmlByDeployId(String deployId) {
        return this.actGeBytearray.getDefXmlByDeployId(deployId);
    }

    public void doWriteXml(String deployId, String defXml) {
        this.actGeBytearray.writeDefXml(deployId, defXml);
    }

    public void doModifyXmlAndClearCache(String actDefId, String deployId, String defXml) {
        this.doWriteXml(deployId, defXml);
       // this.clearProcessDefinitionCache(actDefId);
    }

    public String getEditorJsonByModelId(String modelId) {
        try {
            return new String(this.repositoryService.getModelEditorSource(modelId), "utf-8");
        } catch (Exception var3) {
            var3.fillInStackTrace();
            return "No Setting!";
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

    public ActNodeDef getSubProcessFirstTaskNodeDef(String actDefId, String subProcessNodeId) {
        ProcessDefinitionEntity processDefEntity = (ProcessDefinitionEntity)this.repositoryService.getProcessDefinition(actDefId);
        ActivityImpl subProcessAct = processDefEntity.findActivity(subProcessNodeId);
        if(subProcessAct == null) {
            return null;
        } else {
            Iterator var5 = subProcessAct.getActivities().iterator();

            while(true) {
                ActivityImpl act;
                String type;
                do {
                    if(!var5.hasNext()) {
                        return null;
                    }

                    act = (ActivityImpl)var5.next();
                    type = (String)act.getProperty("type");
                } while(!"startEvent".equals(type));

                Iterator var8 = act.getOutgoingTransitions().iterator();

                while(var8.hasNext()) {
                    PvmTransition tan = (PvmTransition)var8.next();
                    ActivityImpl tmpAct = (ActivityImpl)tan.getDestination();
                    String tmpType = (String)tmpAct.getProperty("type");
                    if("userTask".equals(tmpType)) {
                        String nodeId = tmpAct.getId();
                        String name = (String)tmpAct.getProperty("name");
                        ActNodeDef nodeDef = new ActNodeDef(nodeId, name, tmpType);
                        return nodeDef;
                    }
                }
            }
        }
    }

    public ActProcessDef getProcessDef(String actDefId) {
        //ActProcessDef processDef = (ActProcessDef)this.actProcessDefCache.getByKey(ACT_PROCESS_DEF + actDefId);
       /* if(processDef != null) {
            return processDef;
        }else { */
        ProcessDefinitionEntity processDefEntity = (ProcessDefinitionEntity)this.repositoryService.getProcessDefinition(actDefId);
        ActProcessDef  processDef = new ActProcessDef();
            processDef.setProcessDefId(actDefId);
            processDef.setProcessKey(processDefEntity.getKey());
            processDef.setProcessName(processDefEntity.getName());
            List actvities = processDefEntity.getActivities();
            Iterator var5 = actvities.iterator();

            while(var5.hasNext()) {
                ActivityImpl actImpl = (ActivityImpl)var5.next();
                this.genActNodes(actImpl, processDef.getNodesMap());
            }

            //this.actProcessDefCache.add(ACT_PROCESS_DEF + actDefId, processDef);
            return processDef;
    }

    public ActivityImpl getActivityImplByActDefIdNodeId(String actDefId, String nodeId) {
        ProcessDefinitionEntity processDefEntity = (ProcessDefinitionEntity)this.repositoryService.getProcessDefinition(actDefId);
        return processDefEntity.findActivity(nodeId);
    }

    public Collection<ActNodeDef> getTaskOutValidNodes(String actDefId, String nodeId) {
        ProcessDefinitionEntity processDefEntity = (ProcessDefinitionEntity)this.repositoryService.getProcessDefinition(actDefId);
        ActivityImpl curAct = processDefEntity.findActivity(nodeId);
        LinkedHashMap nodeDefMap = new LinkedHashMap();
        if(curAct == null) {
            return nodeDefMap.values();
        } else {
            Iterator var6 = curAct.getActivities().iterator();

            while(var6.hasNext()) {
                ActivityImpl act = (ActivityImpl)var6.next();
                this.genTaskOutNodes(act, nodeDefMap);
            }

            return nodeDefMap.values();
        }
    }

    private void genTaskOutNodes(ActivityImpl act, Map<String, ActNodeDef> nodeDefMap) {
        if(act != null) {
            String actNodeId = act.getId();
            String nodeType = (String)act.getProperty("type");
            String nodeName = (String)act.getProperty("name");
            String multiInstance = (String)act.getProperty("multiInstance");
            if(!"exclusiveGateway".equals(nodeType) && !"includeGateway".equals(nodeType) && !"parallelGateway".equals(nodeType)) {
                if("subProcess".equals(nodeType)) {
                    List nodeDef1 = act.getActivities();
                    Iterator pt1 = nodeDef1.iterator();

                    while(pt1.hasNext()) {
                        ActivityImpl subAct1 = (ActivityImpl)pt1.next();
                        String subNodeType = (String)subAct1.getProperty("type");
                        if(subNodeType.equals("startEvent")) {
                            Iterator var11 = subAct1.getOutgoingTransitions().iterator();

                            while(var11.hasNext()) {
                                PvmTransition startOut = (PvmTransition)var11.next();
                                this.genTaskOutNodes((ActivityImpl)startOut.getDestination(), nodeDefMap);
                            }

                            return;
                        }
                    }
                } else if("userTask".equals(nodeType)) {
                    ActNodeDef nodeDef2 = new ActNodeDef(actNodeId, nodeName, nodeType);
                    nodeDef2.setMultiInstance(multiInstance);
                    nodeDefMap.put(actNodeId, nodeDef2);
                }
            } else {
                Iterator nodeDef = act.getOutgoingTransitions().iterator();

                while(nodeDef.hasNext()) {
                    PvmTransition pt = (PvmTransition)nodeDef.next();
                    PvmActivity subAct = pt.getDestination();
                    this.genTaskOutNodes((ActivityImpl)subAct, nodeDefMap);
                }
            }

        }
    }

    public ActNodeDef getActNodeDef(String actDefId, String nodeId) {
        ActProcessDef actProcessDef = this.getProcessDef(actDefId);
        return (ActNodeDef)actProcessDef.getNodesMap().get(nodeId);
    }

    private ActNodeDef genActNodes(PvmActivity actImpl, Map<String, ActNodeDef> actNodeDefsMap) {
        String nodeId = actImpl.getId();
        if(actNodeDefsMap.containsKey(nodeId)) {
            return (ActNodeDef)actNodeDefsMap.get(nodeId);
        } else {
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

    public ActNodeDef getStartNode(String actDefId) {
        ActProcessDef processDef = this.getProcessDef(actDefId);
        Iterator var3 = processDef.getActNodeDefs().iterator();

        ActNodeDef actNodeDef;
        do {
            if(!var3.hasNext()) {
                return null;
            }

            actNodeDef = (ActNodeDef)var3.next();
        } while(!ActivityNodeType.startEvent.name().equals(actNodeDef.getNodeType()));

        return actNodeDef;
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

    public List<ActivityImpl> getActivityNodeImpls(String actDefId) {
        ProcessDefinition processDef = this.repositoryService.getProcessDefinition(actDefId);
        ProcessDefinitionEntity entity = (ProcessDefinitionEntity)processDef;
        return entity.getProcessDefinition().getActivities();
    }

    public Collection<ActivityNode> getProcessNodes(String actDefId) {
        ProcessDefinition processDef = this.repositoryService.getProcessDefinition(actDefId);
        ProcessDefinitionEntity entity = (ProcessDefinitionEntity)processDef;
        LinkedHashMap nodesMap = new LinkedHashMap();
        ActivityNode processNode = new ActivityNode("_PROCESS", entity.getName(), "process", entity.getDescription());
        nodesMap.put(entity.getKey(), processNode);
        List acts = entity.getProcessDefinition().getActivities();
        Iterator var7 = acts.iterator();

        while(var7.hasNext()) {
            ActivityImpl actImpl = (ActivityImpl)var7.next();
            String actId = actImpl.getId();
            String type = (String)actImpl.getProperty("type");
            String document = (String)actImpl.getProperty("document");
            String name = (String)actImpl.getProperty("name");
            String tmpId = actId;
            if(actId.length() > 5) {
                tmpId = actId.substring(actId.length() - 5);
            }

            if(StringUtil.isEmpty(name)) {
                if("startEvent".equals(type)) {
                    name = "开始";
                } else if("endEvent".equals(type)) {
                    name = "结束";
                } else if("exclusiveGateway".equals(type)) {
                    name = "单一选择网关(" + tmpId + ")";
                } else if("inclusiveGateway".equals(type)) {
                    name = "条件选择网关(" + tmpId + ")";
                } else if("parallelGateway".equals(type)) {
                    name = "并行(" + tmpId + ")";
                }
            }

            ActivityNode node = new ActivityNode(actId, name, type, document);
            String multiInstance = (String)actImpl.getProperty("multiInstance");
            node.setMultiInstance(multiInstance);
            node.setParentActivitiId(processNode.getActivityId());
            nodesMap.put(actId, node);
            this.getSubActivities(actImpl, nodesMap);
        }

        return nodesMap.values();
    }

    public Collection<ActNodeDef> getValidNodes(String actDefId, String excludeNodeId) {
        ActProcessDef def = this.getProcessDef(actDefId);
        Collection nodeList = def.getActNodeDefs();
        ArrayList validNodes = new ArrayList();
        Iterator var6 = nodeList.iterator();

        while(true) {
            ActNodeDef nodeDef;
            do {
                do {
                    if(!var6.hasNext()) {
                        return validNodes;
                    }

                    nodeDef = (ActNodeDef)var6.next();
                } while(ActivityNodeType.startEvent.name().equals(nodeDef.getNodeType()));
            } while(excludeNodeId != null && excludeNodeId.equals(nodeDef.getNodeId()));

            validNodes.add(nodeDef);
        }
    }

    public List<ActNodeDef> getStartFlowUserNodes(String actDefId, boolean isSkipFirst) {
        ActNodeDef startNodeDef = this.getStartNode(actDefId);
        Object destNodes = new ArrayList();
        if(!isSkipFirst) {
            destNodes = startNodeDef.getOutcomeNodes();
        } else if(startNodeDef.getOutcomeNodes().size() > 0) {
            ActNodeDef startFlowNode = (ActNodeDef)startNodeDef.getOutcomeNodes().get(0);
            Iterator var6 = startFlowNode.getOutcomeNodes().iterator();

            while(var6.hasNext()) {
                ActNodeDef flowNodeDef = (ActNodeDef)var6.next();
                if(ActivityNodeType.userTask.name().equals(flowNodeDef.getNodeType())) {
                    ((List)destNodes).add(flowNodeDef);
                } else {
                    ((List)destNodes).addAll(this.getFlowUserTask(flowNodeDef));
                }
            }
        }

        return (List)destNodes;
    }

    private List<ActNodeDef> getFlowUserTask(ActNodeDef nodeDef) {
        ArrayList list = new ArrayList();
        Iterator var3 = nodeDef.getOutcomeNodes().iterator();

        while(var3.hasNext()) {
            ActNodeDef fNode = (ActNodeDef)var3.next();
            if(ActivityNodeType.userTask.name().equals(fNode.getNodeType())) {
                list.add(fNode);
            } else if(fNode.getNodeType() != null && fNode.getNodeType().indexOf("Gateway") != -1) {
                list.addAll(this.getFlowUserTask(fNode));
            }
        }

        return list;
    }

    public Collection<ActivityNode> getActNodes(String actDefId) {
        ProcessDefinition processDef = this.repositoryService.getProcessDefinition(actDefId);
        ProcessDefinitionEntity entity = (ProcessDefinitionEntity)processDef;
        HashMap nodesMap = new HashMap();
        List acts = entity.getProcessDefinition().getActivities();
        Iterator var6 = acts.iterator();

        while(var6.hasNext()) {
            ActivityImpl actImpl = (ActivityImpl)var6.next();
            String actId = actImpl.getId();
            String type = (String)actImpl.getProperty("type");
            String document = (String)actImpl.getProperty("document");
            String name = (String)actImpl.getProperty("name");
            if(StringUtil.isEmpty(name)) {
                name = type;
            }

            ActivityNode node = new ActivityNode(actId, name, type, document);
            nodesMap.put(actId, node);
            this.getSubActivities(actImpl, nodesMap);
        }

        return nodesMap.values();
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

    public void getSubActivities(ActivityImpl act, Map<String, ActivityNode> nodesMap) {
        if(act.getActivities() != null && act.getActivities().size() != 0) {
            String parentActId = act.getId();
            Iterator var4 = act.getActivities().iterator();

            while(var4.hasNext()) {
                ActivityImpl actImpl = (ActivityImpl)var4.next();
                String actId = actImpl.getId();
                String type = (String)actImpl.getProperty("type");
                String document = (String)actImpl.getProperty("document");
                String name = (String)actImpl.getProperty("name");
                if(StringUtil.isEmpty(name)) {
                    name = type;
                }

                ActivityNode node = new ActivityNode(actId, name, type, document);
                node.setParentActivitiId(parentActId);
                nodesMap.put(actId, node);
                this.getSubActivities(actImpl, nodesMap);
            }

        }
    }



}
