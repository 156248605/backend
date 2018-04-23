package com.elex.oa.entity;

/**
 *@author hugo.zhao
 *@since 2018/4/9 17:09
*/
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;
import java.io.Serializable;

@Table(
        name = "BPM_RU_PATH"
)
public class BpmRuPath {
    @Id
    @Column(
            name = "PATH_ID_"
    )
    protected String pathId;

    @Column(
            name = "INST_ID_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String instId;

    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String actDefId;

    @Column(
            name = "ACT_INST_ID_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String actInstId;

    @Column(
            name = "SOL_ID_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String solId;

    @Column(
            name = "NODE_ID_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    protected String nodeId;

    @Column(
            name = "NODE_NAME_"
    )
    @Size(
            max = 255
    )
    protected String nodeName;

    @Column(
            name = "NODE_TYPE_"
    )
    @Size(
            max = 50
    )
    protected String nodeType;

    @Column(
            name = "START_TIME_"
    )
    protected Date startTime;

    @Column(
            name = "END_TIME_"
    )
    protected Date endTime;

    @Column(
            name = "DURATION_"
    )
    protected Integer duration;

    @Column(
            name = "DURATION_VAL_"
    )
    protected Integer durationVal;

    @Column(
            name = "ASSIGNEE_"
    )
    @Size(
            max = 64
    )
    protected String assignee;

    @Column(
            name = "TO_USER_ID_"
    )
    @Size(
            max = 64
    )
    protected String toUserId;

    @Column(
            name = "IS_MULTIPLE_"
    )
    @Size(
            max = 20
    )
    protected String isMultiple;

    @Column(
            name = "EXECUTION_ID_"
    )
    @Size(
            max = 64
    )
    protected String executionId;

    @Column(
            name = "USER_IDS_"
    )
    @Size(
            max = 300
    )
    protected String userIds;

    @Column(
            name = "PARENT_ID_"
    )
    @Size(
            max = 64
    )
    protected String parentId;

    @Column(
            name = "LEVEL_"
    )
    protected Integer level;

    @Column(
            name = "OUT_TRAN_ID_"
    )
    @Size(
            max = 255
    )
    protected String outTranId;

    @Column(
            name = "TOKEN_"
    )
    @Size(
            max = 255
    )
    protected String token;

    @Column(
            name = "JUMP_TYPE_"
    )
    @Size(
            max = 50
    )
    protected String jumpType;

    @Column(
            name = "NEXT_JUMP_TYPE_"
    )
    @Size(
            max = 50
    )
    protected String nextJumpType;

    @Column(
            name = "OPINION_"
    )
    @Size(
            max = 512
    )
    protected String opinion;

    @Column(
            name = "REF_PATH_ID_"
    )
    @Size(
            max = 64
    )
    protected String refPathId;

    @Column(
            name = "TIMEOUT_STATUS_"
    )
    @Size(
            max = 20
    )
    protected String timeoutStatus;

    @Column(
            name = "TENANT_ID_"
    )
    @Size(
            max = 64
    )
    protected String tenantId = null;

    @Column(
            name = "CREATE_TIME_"
    )
    protected Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Column(
            name = "UPDATE_TIME_"

    )
    protected Date updateTime;
    @Column(
            name = "CREATE_BY_"
    )
    @Size(
            max = 64
    )
    protected String createBy;
    @Column(
            name = "UPDATE_BY_"
    )
    @Size(
            max = 64
    )
    protected String updateBy;
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public BpmRuPath() {
    }

    public BpmRuPath(String in_pathId) {
        this.setPathId(in_pathId);
    }

    public String getPathId() {
        return this.pathId;
    }

    public void setPathId(String aValue) {
        this.pathId = aValue;
    }

    public String getInstId() {
        return this.instId;
    }

    public void setInstId(String aValue) {
        this.instId = aValue;
    }

    public String getActDefId() {
        return this.actDefId;
    }

    public void setActDefId(String aValue) {
        this.actDefId = aValue;
    }

    public String getActInstId() {
        return this.actInstId;
    }

    public void setActInstId(String aValue) {
        this.actInstId = aValue;
    }

    public String getSolId() {
        return this.solId;
    }

    public void setSolId(String aValue) {
        this.solId = aValue;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String aValue) {
        this.nodeId = aValue;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String aValue) {
        this.nodeName = aValue;
    }

    public String getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(String aValue) {
        this.nodeType = aValue;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date aValue) {
        this.startTime = aValue;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date aValue) {
        this.endTime = aValue;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer aValue) {
        this.duration = aValue;
    }

    public Integer getDurationVal() {
        return this.durationVal;
    }

    public void setDurationVal(Integer aValue) {
        this.durationVal = aValue;
    }

    public String getAssignee() {
        return this.assignee;
    }

    public void setAssignee(String aValue) {
        this.assignee = aValue;
    }

    public String getToUserId() {
        return this.toUserId;
    }

    public void setToUserId(String aValue) {
        this.toUserId = aValue;
    }

    public String getIsMultiple() {
        return this.isMultiple;
    }

    public void setIsMultiple(String aValue) {
        this.isMultiple = aValue;
    }

    public String getExecutionId() {
        return this.executionId;
    }

    public void setExecutionId(String aValue) {
        this.executionId = aValue;
    }

    public String getUserIds() {
        return this.userIds;
    }

    public void setUserIds(String aValue) {
        this.userIds = aValue;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String aValue) {
        this.parentId = aValue;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer aValue) {
        this.level = aValue;
    }

    public String getOutTranId() {
        return this.outTranId;
    }

    public void setOutTranId(String aValue) {
        this.outTranId = aValue;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String aValue) {
        this.token = aValue;
    }

    public String getJumpType() {
        return this.jumpType;
    }

    public void setJumpType(String aValue) {
        this.jumpType = aValue;
    }

    public String getNextJumpType() {
        return this.nextJumpType;
    }

    public void setNextJumpType(String aValue) {
        this.nextJumpType = aValue;
    }

    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String aValue) {
        this.opinion = aValue;
    }

    public String getRefPathId() {
        return this.refPathId;
    }

    public void setRefPathId(String aValue) {
        this.refPathId = aValue;
    }

    public String getIdentifyLabel() {
        return this.pathId;
    }

    public Serializable getPkId() {
        return this.pathId;
    }

    public void setPkId(Serializable pkId) {
        this.pathId = (String)pkId;
    }

    public String getTimeoutStatus() {
        return this.timeoutStatus;
    }

    public void setTimeoutStatus(String timeoutStatus) {
        this.timeoutStatus = timeoutStatus;
    }

}
