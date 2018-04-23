package com.elex.oa.entity.activiti;

/**
 *@author hugo.zhao
 *@since 2018/4/12 18:32
*/
public class ActSequenceFlow {
    private String sourceNodeId;
    private String sourceNodeName;
    private String destNodeId;
    private String destNodeName;
    private String conditionExpression;

    public ActSequenceFlow() {
    }

    public String getSourceNodeId() {
        return this.sourceNodeId;
    }

    public void setSourceNodeId(String sourceNodeId) {
        this.sourceNodeId = sourceNodeId;
    }

    public String getSourceNodeName() {
        return this.sourceNodeName;
    }

    public void setSourceNodeName(String sourceNodeName) {
        this.sourceNodeName = sourceNodeName;
    }

    public String getDestNodeId() {
        return this.destNodeId;
    }

    public void setDestNodeId(String destNodeId) {
        this.destNodeId = destNodeId;
    }

    public String getDestNodeName() {
        return this.destNodeName;
    }

    public void setDestNodeName(String destNodeName) {
        this.destNodeName = destNodeName;
    }

    public String getConditionExpression() {
        return this.conditionExpression;
    }

    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }
}
