package com.elex.oa.core.entity;

import com.elex.oa.entity.ActNodeDef;

import java.util.HashMap;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/5/5 13:32
*/
public class DestNodeUsers {
    private String nodeId;
    private String nodeText;
    private String nodeType;
    private TaskNodeUser taskNodeUser;
    private Map<String, TaskNodeUser> fllowNodeUserMap = new HashMap();

    public DestNodeUsers() {
    }

    public DestNodeUsers(ActNodeDef nodeDef) {
        this.nodeId = nodeDef.getNodeId();
        this.nodeText = nodeDef.getNodeName();
        this.nodeType = nodeDef.getNodeType();
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public String getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeText() {
        return this.nodeText;
    }

    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }

    public Map<String, TaskNodeUser> getFllowNodeUserMap() {
        return this.fllowNodeUserMap;
    }

    public void setFllowNodeUserMap(Map<String, TaskNodeUser> fllowNodeUserMap) {
        this.fllowNodeUserMap = fllowNodeUserMap;
    }

    public TaskNodeUser getTaskNodeUser() {
        return this.taskNodeUser;
    }

    public void setTaskNodeUser(TaskNodeUser taskNodeUser) {
        this.taskNodeUser = taskNodeUser;
    }
}
