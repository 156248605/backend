package com.elex.oa.entity.activiti;

/**
 *@author hugo.zhao
 *@since 2018/4/9 9:59
*/
public class BpmNodeStatus {
    private String nodeId;
    private String jumpType;
    private String timeoutStatus;

    public BpmNodeStatus() {
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getJumpType() {
        return this.jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    public String getTimeoutStatus() {
        return this.timeoutStatus;
    }

    public void setTimeoutStatus(String timeoutStatus) {
        this.timeoutStatus = timeoutStatus;
    }



}
