package com.elex.oa.core.entity;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.activiti.BpmTask;
import com.elex.oa.json.JsonDateSerializer;
import com.elex.oa.util.TaskOptionType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.text.DecimalFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;

@Table(
        name = "BPM_NODE_JUMP"
)

public class BpmNodeJump extends  BaseTenantEntity{
    public static final String STATUS_INIT = "INIT";
    public static final String STATUS_END = "FINISH";
    public static final String JUMP_TYPE_TRANSFER = "TRANSFER";
    public static final String JUMP_TYPE_UNHANDLE = "UNHANDLE";
    @Id
    @Column(
            name = "JUMP_ID_"
    )
    protected String jumpId;

    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 64
    )
    protected String actDefId;

    @Column(
            name = "ACT_INST_ID_"
    )
    @Size(
            max = 64
    )
    protected String actInstId;

    @Column(
            name = "NODE_NAME_"
    )
    @Size(
            max = 255
    )
    protected String nodeName;

    @Column(
            name = "NODE_ID_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    protected String nodeId;
/*    @FieldDefine(
            title = "任务ID"
    )*/
    @Column(
            name = "TASK_ID_"
    )
    @Size(
            max = 64
    )
    protected String taskId;
/*    @FieldDefine(
            title = "完成时间"
    )*/
    @Column(
            name = "COMPLETE_TIME_"
    )
    protected Date completeTime;
/*    @FieldDefine(
            title = "持续时长"
    )*/
    @Column(
            name = "DURATION_"
    )
    protected Integer duration;
/*    @FieldDefine(
            title = "有效审批时长"
    )*/
    @Column(
            name = "DURATION_VAL_"
    )
    protected Integer durationVal;

    @Column(
            name = "HANDLER_ID_"
    )
    @Size(
            max = 64
    )
    protected String handlerId;

    @Column(
            name = "OWNER_ID_"
    )
    @Size(
            max = 64
    )
    protected String ownerId;

    @Column(
            name = "CHECK_STATUS_"
    )
    @Size(
            max = 50
    )
    protected String checkStatus;

    @Column(
            name = "JUMP_TYPE_"
    )
    @Size(
            max = 50
    )
    protected String jumpType;

    @Column(
            name = "REMARK_"
    )
    @Size(
            max = 512
    )
    protected String remark;

    @Column(
            name = "ENABLE_MOBILE_"
    )
    protected Integer enableMobile = Integer.valueOf(0);

    @Column(
            name = "HANDLE_DEP_ID_"
    )
    @Size(
            max = 64
    )
    protected String handleDepId;

    @Column(
            name = "HANDLE_DEP_FULL_"
    )
    @Size(
            max = 300
    )
    protected String handleDepFull;
    @Transient
    protected String subject = "";
    @Transient
    protected String status = "";
    @Transient
    protected String name = "";

    @Column(
            name = "OPINION_NAME_"
    )
    protected String opinionName = "";

    @Transient
    protected String handler;
/*    @FieldDefine(
            title = "手机审批历史部门、照片等信息"
    )*/
    @Transient
    protected JSONObject handlerInfo;
    @Transient
    protected JSONArray attachments = new JSONArray();

    public String getHandleDepId() {
        return this.handleDepId;
    }

    public void setHandleDepId(String handleDepId) {
        this.handleDepId = handleDepId;
    }

    public String getHandleDepFull() {
        return this.handleDepFull;
    }

    public void setHandleDepFull(String handleDepFull) {
        this.handleDepFull = handleDepFull;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckStatusText() {
        if(this.checkStatus != null) {
            try {
                TaskOptionType t = TaskOptionType.valueOf(this.checkStatus);
                if(t != null) {
                    return t.getText();
                }
            } catch (Exception var2) {
                ;
            }
        }

        return "UNHANDLE".equals(this.checkStatus)?"未审批":this.checkStatus;
    }

    public BpmNodeJump() {
    }

    public BpmNodeJump(String in_jumpId) {
        this.setJumpId(in_jumpId);
    }

    public BpmNodeJump(BpmTask bpmTask) {
        this.setActDefId(bpmTask.getProcDefId());
        this.setActInstId(bpmTask.getProcInstId());
        this.setTaskId(bpmTask.getId());
        this.setCreateTime(bpmTask.getCreateTime());
        this.setOwnerId(bpmTask.getOwner());
        this.setNodeName(bpmTask.getName());
        this.setNodeId(bpmTask.getTaskDefKey());
        this.setCheckStatus("UNHANDLE");
        this.setJumpType("UNHANDLE");
        this.setRemark("无");
        this.setEnableMobile(bpmTask.getEnableMobile());
    }

    public String getJumpId() {
        return this.jumpId;
    }

    public void setJumpId(String aValue) {
        this.jumpId = aValue;
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

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String aValue) {
        this.nodeName = aValue;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String aValue) {
        this.nodeId = aValue;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String aValue) {
        this.taskId = aValue;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonSerialize(
            using = JsonDateSerializer.class
    )
    public Date getCompleteTime() {
        return this.completeTime;
    }

    public void setCompleteTime(Date aValue) {
        this.completeTime = aValue;
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

    public String getHandlerId() {
        return this.handlerId;
    }

    public void setHandlerId(String aValue) {
        this.handlerId = aValue;
    }

    public String getCheckStatus() {
        return this.checkStatus;
    }

    public void setCheckStatus(String aValue) {
        this.checkStatus = aValue;
    }

    public String getJumpType() {
        return this.jumpType;
    }

    public void setJumpType(String aValue) {
        this.jumpType = aValue;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String aValue) {
        this.remark = aValue;
    }

    public String getIdentifyLabel() {
        return this.jumpId;
    }

    public Serializable getPkId() {
        return this.jumpId;
    }

    public void setPkId(Serializable pkId) {
        this.jumpId = (String)pkId;
    }

    public String getDurationFormat() {
        if(this.duration == null) {
            return "";
        } else {
            DecimalFormat df = new DecimalFormat("0.00");
            double m;
            if(this.duration.intValue() > 86400000) {
                m = (new Double((double)this.duration.intValue())).doubleValue() / 8.64E7D;
                return df.format(m) + "天";
            } else if(this.duration.intValue() > 3600000) {
                m = (new Double((double)this.duration.intValue())).doubleValue() / 3600000.0D;
                return df.format(m) + "时";
            } else if(this.duration.intValue() > '\uea60') {
                m = (new Double((double)this.duration.intValue())).doubleValue() / 60000.0D;
                return df.format(m) + "分";
            } else {
                m = (new Double((double)this.duration.intValue())).doubleValue() / 1000.0D;
                return df.format(m) + "秒";
            }
        }
    }

    public Integer getEnableMobile() {
        return this.enableMobile;
    }

    public void setEnableMobile(Integer enableMobile) {
        this.enableMobile = enableMobile;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOpinionName() {
        return this.opinionName;
    }

    public void setOpinionName(String opinionName) {
        this.opinionName = opinionName;
    }

    public String getHandler() {
        return this.handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public JSONObject getHandlerInfo() {
        return this.handlerInfo;
    }

    public void setHandlerInfo(JSONObject handlerInfo) {
        this.handlerInfo = handlerInfo;
    }

    public JSONArray getAttachments() {
        return this.attachments;
    }

    public void setAttachments(JSONArray attachments) {
        this.attachments = attachments;
    }

    public boolean equals(Object object) {
        if(!(object instanceof BpmNodeJump)) {
            return false;
        } else {
            BpmNodeJump rhs = (BpmNodeJump)object;
            return (new EqualsBuilder()).append(this.jumpId, rhs.jumpId).append(this.actDefId, rhs.actDefId).append(this.actInstId, rhs.actInstId).append(this.nodeName, rhs.nodeName).append(this.nodeId, rhs.nodeId).append(this.taskId, rhs.taskId).append(this.completeTime, rhs.completeTime).append(this.duration, rhs.duration).append(this.durationVal, rhs.durationVal).append(this.handlerId, rhs.handlerId).append(this.checkStatus, rhs.checkStatus).append(this.jumpType, rhs.jumpType).append(this.remark, rhs.remark).append(this.tenantId, rhs.tenantId).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.jumpId).append(this.actDefId).append(this.actInstId).append(this.nodeName).append(this.nodeId).append(this.taskId).append(this.completeTime).append(this.duration).append(this.durationVal).append(this.handlerId).append(this.checkStatus).append(this.jumpType).append(this.remark).append(this.tenantId).append(this.createBy).append(this.createTime).append(this.updateBy).append(this.updateTime).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("jumpId", this.jumpId).append("actDefId", this.actDefId).append("actInstId", this.actInstId).append("nodeName", this.nodeName).append("nodeId", this.nodeId).append("taskId", this.taskId).append("completeTime", this.completeTime).append("duration", this.duration).append("durationVal", this.durationVal).append("handlerId", this.handlerId).append("checkStatus", this.checkStatus).append("jumpType", this.jumpType).append("remark", this.remark).append("tenantId", this.tenantId).append("createBy", this.createBy).append("createTime", this.createTime).append("updateBy", this.updateBy).append("updateTime", this.updateTime).toString();
    }
}
