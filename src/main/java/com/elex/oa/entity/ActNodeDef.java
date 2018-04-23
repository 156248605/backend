package com.elex.oa.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/8 16:51
*/
public class ActNodeDef {
    private String nodeId;
    private String nodeName;
    private String nodeType;
    private String multiInstance;
    private List<ActNodeDef> outcomeNodes = new ArrayList();

    public ActNodeDef() {
    }
    public String getIconCls() {
        return "icon-" + this.nodeType;
    }

    public ActNodeDef(String nodeId, String nodeName, String nodeType) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
        this.nodeType = nodeType;
    }
    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public List<ActNodeDef> getOutcomeNodes() {
        return this.outcomeNodes;
    }

    public void setOutcomeNodes(List<ActNodeDef> outcomeNodes) {
        this.outcomeNodes = outcomeNodes;
    }

    public String getMultiInstance() {
        return this.multiInstance;
    }

    public void setMultiInstance(String multiInstance) {
        this.multiInstance = multiInstance;
    }


}
