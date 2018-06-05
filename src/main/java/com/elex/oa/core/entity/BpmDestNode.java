package com.elex.oa.core.entity;
import java.util.Date;
/**
 *@author hugo.zhao
 *@since 2018/5/5 15:22
*/
public class BpmDestNode {
    private String nodeId;
    private String userIds;
    private Integer priority = Integer.valueOf(0);
    private Date expireTime;

    public BpmDestNode() {
    }

    public BpmDestNode(String nodeId, String userIds, Integer priority, Date exprireTime) {
        this.nodeId = nodeId;
        this.userIds = userIds;
        this.priority = priority;
        this.expireTime = exprireTime;
    }

    public BpmDestNode(String nodeId, String userIds) {
        this.nodeId = nodeId;
        this.userIds = userIds;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getUserIds() {
        return this.userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
