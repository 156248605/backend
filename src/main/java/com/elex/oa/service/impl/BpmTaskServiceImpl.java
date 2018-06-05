package com.elex.oa.service.impl;

import com.elex.oa.activiti.service.ActRepService;
import com.elex.oa.core.entity.BpmNodeJump;
import com.elex.oa.core.entity.DestNodeUsers;
import com.elex.oa.core.entity.TaskNodeUser;
import com.elex.oa.dao.IBpmInstDao;
import com.elex.oa.entity.ActNodeDef;
import com.elex.oa.entity.ActProcessDef;
import com.elex.oa.entity.activiti.BpmInst;
import com.elex.oa.identity.service.BpmIdentityCalService;
import com.elex.oa.identity.service.impl.BpmIdentityCalServiceImpl;
import com.elex.oa.org.model.IUser;
import com.elex.oa.org.model.IdentityInfo;
import com.elex.oa.org.service.UserService;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.service.IBpmNodeJumpService;
import com.elex.oa.service.IBpmTaskService;

import java.util.*;

import com.elex.oa.util.ProcessVarType;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@author hugo.zhao
 *@since 2018/5/5 13:21
*/
@Service
public class BpmTaskServiceImpl implements IBpmTaskService {

    @Autowired
    private TaskService taskService;

   // private ActTaskService actTaskService;
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ActRepService actRepService;

    @Autowired
    private BpmIdentityCalService bpmIdentityCalService;

    @Autowired
    private IBpmInstDao bpmInstDao;

    @Autowired
    private UserService userService;
    //bpmNodeJumpManager
    @Autowired
    private IBpmNodeJumpService bpmNodeJumpService;

    @Override
    public List<DestNodeUsers> getDestNodeUsers(String taskId) {
        Task task = (Task)((TaskQuery)this.taskService.createTaskQuery().taskId(taskId)).singleResult();
        ActProcessDef actProcessDef = this.actRepService.getProcessDef(task.getProcessDefinitionId());
        ActNodeDef actNodeDef = (ActNodeDef)actProcessDef.getNodesMap().get(task.getTaskDefinitionKey());
        ArrayList destNodeUserList = new ArrayList();
        Map vars = this.runtimeService.getVariables(task.getExecutionId());
        vars.put(ProcessVarType.PRE_NODE_USERID.getKey(), ContextUtil.getCurrentUserId());

        DestNodeUsers nodeUsers;
        for(Iterator var7 = actNodeDef.getOutcomeNodes().iterator(); var7.hasNext(); destNodeUserList.add(nodeUsers)) {
            ActNodeDef outNode = (ActNodeDef)var7.next();
            nodeUsers = new DestNodeUsers(outNode);
            if(nodeUsers.getNodeType() != null && nodeUsers.getNodeType().indexOf("Gateway") != -1) {
                this.genDestNodeUserMap(task.getProcessDefinitionId(), outNode, nodeUsers.getFllowNodeUserMap(), vars);
            } else {
                TaskNodeUser taskNodeUser;
                if("userTask".equals(nodeUsers.getNodeType())) {
                    taskNodeUser = this.calUserNode(task.getProcessDefinitionId(), outNode, vars);
                    nodeUsers.setTaskNodeUser(taskNodeUser);
                } else if("subProcess".equals(nodeUsers.getNodeType())) {
                    Collection taskNodeUser2 = this.actRepService.getTaskOutValidNodes(task.getProcessDefinitionId(), nodeUsers.getNodeId());
                    Iterator var11 = taskNodeUser2.iterator();

                    while(var11.hasNext()) {
                        ActNodeDef def = (ActNodeDef)var11.next();
                        TaskNodeUser taskNodeUser1 = this.calUserNode(task.getProcessDefinitionId(), def, vars);
                        nodeUsers.getFllowNodeUserMap().put(nodeUsers.getNodeId(), taskNodeUser1);
                    }
                } else {
                    taskNodeUser = new TaskNodeUser(outNode);
                    nodeUsers.setTaskNodeUser(taskNodeUser);
                }
            }
        }

        return destNodeUserList;
    }
    @Override
    public List<DestNodeUsers> getDestNodeUsers(String actInstId, String nodeId) {
        ArrayList destNodeUserList = new ArrayList();
        BpmInst bpmInst = this.bpmInstDao.getByActInstId(actInstId);
        ActProcessDef actProcessDef = this.actRepService.getProcessDef(bpmInst.getActDefId());
        ActNodeDef actNodeDef = (ActNodeDef)actProcessDef.getNodesMap().get(nodeId);
        Map vars = this.runtimeService.getVariables(actInstId);
        DestNodeUsers nodeUsers1;
        if("userTask".equals(actNodeDef.getNodeType())) {
            DestNodeUsers nodeUsers = this.getDestNodeUsers(bpmInst.getActDefId(), bpmInst.getActInstId(), nodeId, actNodeDef, vars);
            destNodeUserList.add(nodeUsers);
        } else {
            for(Iterator nodeUsers2 = actNodeDef.getOutcomeNodes().iterator(); nodeUsers2.hasNext(); destNodeUserList.add(nodeUsers1)) {
                ActNodeDef outNode = (ActNodeDef)nodeUsers2.next();
                nodeUsers1 = new DestNodeUsers(outNode);
                if(nodeUsers1.getNodeType() != null && nodeUsers1.getNodeType().indexOf("Gateway") != -1) {
                    this.genDestNodeUserMap(bpmInst.getActDefId(), outNode, nodeUsers1.getFllowNodeUserMap(), vars);
                } else if("userTask".equals(nodeUsers1.getNodeType())) {
                    nodeUsers1 = this.getDestNodeUsers(bpmInst.getActDefId(), bpmInst.getActInstId(), nodeId, outNode, vars);
                } else {
                    TaskNodeUser taskNodeUser = new TaskNodeUser(outNode);
                    nodeUsers1.setTaskNodeUser(taskNodeUser);
                }
            }
        }

        return destNodeUserList;
    }

    private DestNodeUsers getDestNodeUsers(String actDefId, String actInstId, String nodeId, ActNodeDef actNodeDef, Map<String, Object> vars) {
        List nodeJumps = this.bpmNodeJumpService.getByActInstIdNodeId(actInstId, nodeId);
        if(nodeJumps.size() > 0) {
            DestNodeUsers taskNodeUser2 = new DestNodeUsers(actNodeDef);
            HashSet nodeUsers1 = new HashSet();
            StringBuffer userIds = new StringBuffer();
            StringBuffer userNames = new StringBuffer();
            Iterator taskNodeUser1 = nodeJumps.iterator();

            while(taskNodeUser1.hasNext()) {
                BpmNodeJump nodeJump = (BpmNodeJump)taskNodeUser1.next();
                if(!StringUtils.isEmpty(nodeJump.getHandlerId())) {
                    IUser osUser = this.userService.getByUserId(nodeJump.getHandlerId());
                    if(osUser != null && !nodeUsers1.contains(osUser)) {
                        userIds.append(osUser.getUserId()).append(",");
                        userNames.append(osUser.getFullname()).append(",");
                        nodeUsers1.add(osUser);
                    }
                }
            }

            if(userIds.length() > 0) {
                userIds.deleteCharAt(userIds.length() - 1);
                userNames.deleteCharAt(userNames.length() - 1);
                TaskNodeUser taskNodeUser3 = new TaskNodeUser();
                taskNodeUser3.setUserIds(userIds.toString());
                taskNodeUser3.setUserFullnames(userNames.toString());
                taskNodeUser2.setTaskNodeUser(taskNodeUser3);
            }

            return taskNodeUser2;
        } else {
            TaskNodeUser taskNodeUser = this.calUserNode(actDefId, actNodeDef, vars);
            DestNodeUsers nodeUsers = new DestNodeUsers(actNodeDef);
            nodeUsers.setTaskNodeUser(taskNodeUser);
            return nodeUsers;
        }
    }

    public List<DestNodeUsers> getDestNodeUsers(String actDefId, List<ActNodeDef> nodeDefs, Map<String, Object> vars) {
        ArrayList destNodeUserList = new ArrayList();
        Iterator var5 = nodeDefs.iterator();

        while(true) {
            ActNodeDef actNodeDef;
            do {
                if(!var5.hasNext()) {
                    return destNodeUserList;
                }

                actNodeDef = (ActNodeDef)var5.next();
            } while(actNodeDef == null);

            DestNodeUsers nodeUsers = new DestNodeUsers();
            nodeUsers.setNodeId(actNodeDef.getNodeId());
            nodeUsers.setNodeType(actNodeDef.getNodeType());
            nodeUsers.setNodeText(actNodeDef.getNodeName());
            if(actNodeDef.getNodeType() != null && actNodeDef.getNodeType().indexOf("Gateway") != -1) {
                this.genDestNodeUserMap(actDefId, actNodeDef, nodeUsers.getFllowNodeUserMap(), vars);
            } else {
                TaskNodeUser taskNodeUser;
                if("userTask".equals(actNodeDef.getNodeType())) {
                    taskNodeUser = this.calUserNode(actDefId, actNodeDef, vars);
                    nodeUsers.setTaskNodeUser(taskNodeUser);
                } else {
                    taskNodeUser = new TaskNodeUser(actNodeDef);
                    nodeUsers.setTaskNodeUser(taskNodeUser);
                }
            }

            destNodeUserList.add(nodeUsers);
        }
    }

    public List<DestNodeUsers> getDestNodeUsers(String actDefId, String[] destNodeIds, Map<String, Object> vars) {
        ActProcessDef processDef = this.actRepService.getProcessDef(actDefId);
        ArrayList actNodeDefs = new ArrayList();
        String[] var6 = destNodeIds;
        int var7 = destNodeIds.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            String nodeId = var6[var8];
            ActNodeDef actNodeDef = (ActNodeDef)processDef.getNodesMap().get(nodeId);
            if(actNodeDef != null) {
                actNodeDefs.add(actNodeDef);
            }
        }

        return this.getDestNodeUsers(actDefId, (List)actNodeDefs, vars);
    }




    @Override
    public TaskNodeUser calUserNode(String actDefId, ActNodeDef nodeDef, Map<String, Object> vars) {
        TaskNodeUser taskNodeUser = new TaskNodeUser(nodeDef);
        Collection idInfos = this.bpmIdentityCalService.calNodeUsersOrGroups(actDefId, nodeDef.getNodeId(), vars);
        StringBuffer userIdSb = new StringBuffer();
        StringBuffer userNameSb = new StringBuffer();
        StringBuffer groupIdSb = new StringBuffer();
        StringBuffer groupNameSb = new StringBuffer();
        Iterator var10 = idInfos.iterator();

        while(var10.hasNext()) {
            IdentityInfo id = (IdentityInfo)var10.next();
            if(id != null) {
                if("USER".equals(id.getIdentityType())) {
                    userIdSb.append(id.getIdentityId()).append(",");
                    userNameSb.append(id.getIdentityName()).append(",");
                } else {
                    groupIdSb.append(id.getIdentityId()).append(",");
                    groupNameSb.append(id.getIdentityName()).append(",");
                }
            }
        }

        taskNodeUser.setUserIds(this.trimComma(userIdSb));
        taskNodeUser.setUserFullnames(this.trimComma(userNameSb));
        taskNodeUser.setGroupIds(this.trimComma(groupIdSb));
        taskNodeUser.setGroupNames(this.trimComma(groupNameSb));
        return taskNodeUser;
    }

    private void genDestNodeUserMap(String actDefId, ActNodeDef destNodeDef, Map<String, TaskNodeUser> fllowNodeUserMap, Map<String, Object> vars) {
        Iterator var5 = destNodeDef.getOutcomeNodes().iterator();
        while(true) {
            while(var5.hasNext()) {
                ActNodeDef outNode = (ActNodeDef)var5.next();
                TaskNodeUser taskNodeUser;
                if("userTask".equals(outNode.getNodeType())) {
                    taskNodeUser = this.calUserNode(actDefId, outNode, vars);
                    fllowNodeUserMap.put(outNode.getNodeId(), taskNodeUser);
                } else if(outNode.getNodeType() != null && outNode.getNodeType().indexOf("Gateway") != -1) {
                    this.genDestNodeUserMap(actDefId, outNode, fllowNodeUserMap, vars);
                } else {
                    taskNodeUser = new TaskNodeUser(outNode);
                    fllowNodeUserMap.put(outNode.getNodeId(), taskNodeUser);
                }
            }

            return;
        }
    }

    private String trimComma(StringBuffer sb) {
        if(sb.length() == 0) {
            return "";
        } else {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }
}
