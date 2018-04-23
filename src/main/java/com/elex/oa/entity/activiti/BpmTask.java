package com.elex.oa.entity.activiti;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
@Table(
        name = "ACT_RU_TASK"
)
public class BpmTask {
    public static final String TASK_TYPE_CMM = "CMM";
    public static final String TASK_TYPE_USER = "USER";
    @Id
    @Column(
            name = "ID_"
    )
    protected String id;
    @Column(
            name = "REV_"
    )
    protected Integer rev;

    @Column(
            name = "EXECUTION_ID_"
    )
    @Size(
            max = 64
    )
    protected String executionId;

    @Column(
            name = "NAME_"
    )
    @Size(
            max = 255
    )
    protected String name;

    @Column(
            name = "PARENT_TASK_ID_"
    )
    @Size(
            max = 64
    )
    protected String parentTaskId;

    @Column(
            name = "DESCRIPTION_"
    )
    @Size(
            max = 4000
    )
    protected String description;

    @Column(
            name = "TASK_DEF_KEY_"
    )
    @Size(
            max = 255
    )
    protected String taskDefKey;

    @Column(
            name = "OWNER_"
    )
    @Size(
            max = 255
    )
    protected String owner;

    @Column(
            name = "ASSIGNEE_"
    )
    @Size(
            max = 255
    )
    protected String assignee;

    @Column(
            name = "DELEGATION_"
    )
    @Size(
            max = 64
    )
    protected String delegation;

    @Column(
            name = "PRIORITY_"
    )
    protected Integer priority;

    @Column(
            name = "DUE_DATE_"
    )
    protected Date dueDate;

    @Column(
            name = "CATEGORY_"
    )
    @Size(
            max = 255
    )
    protected String category;

    @Column(
            name = "SUSPENSION_STATE_"
    )
    protected Integer suspensionState;

    @Column(
            name = "FORM_KEY_"
    )
    @Size(
            max = 255
    )
    protected String formKey;

    @Column(
            name = "AGENT_USER_ID_"
    )
    @Size(
            max = 64
    )
    protected String agentUserId;

    @Column(
            name = "SOL_ID_"
    )
    @Size(
            max = 64
    )
    protected String solId;

    @Column(
            name = "PROC_INST_ID_"
    )
    @Size(
            max = 255
    )
    protected String procInstId;

    @Column(
            name = "PROC_DEF_ID_"
    )
    @Size(
            max = 255
    )
    protected String procDefId;
    @Column(
            name = "GEN_CM_TASK_"
    )
    @Size(
            max = 20
    )
    protected String genCmTask;
    @Column(
            name = "RC_TASK_ID_"
    )
    @Size(
            max = 64
    )
    protected String rcTaskId;
    @Column(
            name = "CM_LEVEL_"
    )
    protected Integer cmLevel;
    @Column(
            name = "RUN_PATH_ID_"
    )
    protected String runPathId;
    @Column(
            name = "TASK_TYPE_"
    )
    @Size(
            max = 20
    )
    protected String taskType;
    @Column(
            name = "CM_FUSRID_"
    )
    @Size(
            max = 64
    )
    protected String cmFuserId;
    @Column(
            name = "TOKEN_"
    )
    @Size(
            max = 64
    )
    protected String token;
    @Column(
            name = "URGENT_TIMES_"
    )
    protected Integer urgentTimes;
    @Column(
            name = "ENABLE_MOBILE_"
    )
    protected Integer enableMobile = Integer.valueOf(0);

    @Column(
            name = "TIMEOUT_STATUS_"
    )
    @Size(
            max = 20
    )
    protected String timeoutStatus;
    @Transient
    protected JSONObject rightJson;
    @Transient
    protected String instId = "";
    @Transient
    protected String assigneeNames = "";
    @Transient
    protected String bpmTypeName = "";
    @Transient
    protected String status = "";

    @Column(
            name = "CREATE_TIME_"
    )
    protected Date createTime;

    public Date getCreateTime() {
        return createTime;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBpmTypeName() {
        return this.bpmTypeName;
    }

    public void setBpmTypeName(String bpmTypeName) {
        this.bpmTypeName = bpmTypeName;
    }

    public BpmTask() {
    }

    public BpmTask(String in_id) {
        this.setId(in_id);
    }

    public String getAssigneeNames() {
        return this.assigneeNames;
    }

    public void setAssigneeNames(String assigneeNames) {
        this.assigneeNames = assigneeNames;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String aValue) {
        this.id = aValue;
    }

    public Integer getRev() {
        return this.rev;
    }

    public void setRev(Integer aValue) {
        this.rev = aValue;
    }

    public String getExecutionId() {
        return this.executionId;
    }

    public void setExecutionId(String aValue) {
        this.executionId = aValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String aValue) {
        this.name = aValue;
    }

    public String getParentTaskId() {
        return this.parentTaskId;
    }

    public String getSolId() {
        return this.solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    public void setParentTaskId(String aValue) {
        this.parentTaskId = aValue;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String aValue) {
        this.description = aValue;
    }

    public String getTaskDefKey() {
        return this.taskDefKey;
    }

    public void setTaskDefKey(String aValue) {
        this.taskDefKey = aValue;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String aValue) {
        this.owner = aValue;
    }

    public String getAssignee() {
        return this.assignee;
    }

    public void setAssignee(String aValue) {
        this.assignee = aValue;
    }

    public String getDelegation() {
        return this.delegation;
    }

    public void setDelegation(String aValue) {
        this.delegation = aValue;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer aValue) {
        this.priority = aValue;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date aValue) {
        this.dueDate = aValue;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String aValue) {
        this.category = aValue;
    }

    public Integer getSuspensionState() {
        return this.suspensionState;
    }

    public void setSuspensionState(Integer aValue) {
        this.suspensionState = aValue;
    }

    public String getFormKey() {
        return this.formKey;
    }

    public String getAgentUserId() {
        return this.agentUserId;
    }

    public void setAgentUserId(String agentUserId) {
        this.agentUserId = agentUserId;
    }

    public void setFormKey(String aValue) {
        this.formKey = aValue;
    }

    public String getGenCmTask() {
        return this.genCmTask;
    }

    public void setGenCmTask(String genCmTask) {
        this.genCmTask = genCmTask;
    }

    public String getRcTaskId() {
        return this.rcTaskId;
    }

    public void setRcTaskId(String rcTaskId) {
        this.rcTaskId = rcTaskId;
    }

    public Integer getCmLevel() {
        return this.cmLevel;
    }

    public void setCmLevel(Integer cmLevel) {
        this.cmLevel = cmLevel;
    }

    public String getIdentifyLabel() {
        return this.id;
    }

    public Serializable getPkId() {
        return this.id;
    }

    public void setPkId(Serializable pkId) {
        this.id = (String)pkId;
    }

    public String getProcInstId() {
        return this.procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getProcDefId() {
        return this.procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getCmFuserId() {
        return this.cmFuserId;
    }

    public void setCmFuserId(String cmFuserId) {
        this.cmFuserId = cmFuserId;
    }

    public void setRightJson(JSONObject rightJson) {
        this.rightJson = rightJson;
    }

    public JSONObject getRightJson() {
        return this.rightJson;
    }

    public String getStayTime() {
        if(this.createTime == null) {
            return "";
        } else {
            Date now = new Date();
            long stayHours = (now.getTime() - this.createTime.getTime()) / 3600000L;
            int days = (new Long(stayHours / 24L)).intValue();
            int hours = (new Long(stayHours % 24L)).intValue();
            return days + "天" + hours + "小时";
        }
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUrgentTimes() {
        return this.urgentTimes;
    }

    public void setUrgentTimes(Integer urgentTimes) {
        this.urgentTimes = urgentTimes;
    }

    public Integer getEnableMobile() {
        return this.enableMobile;
    }

    public void setEnableMobile(Integer enableMobile) {
        this.enableMobile = enableMobile;
    }

    public String getRunPathId() {
        return this.runPathId;
    }

    public void setRunPathId(String runPathId) {
        this.runPathId = runPathId;
    }

    public String getTimeoutStatus() {
        return this.timeoutStatus;
    }

    public void setTimeoutStatus(String timeoutStatus) {
        this.timeoutStatus = timeoutStatus;
    }


    public Object clone() {
        BpmTask o = null;

        try {
            o = (BpmTask)super.clone();
        } catch (CloneNotSupportedException var3) {
            var3.printStackTrace();
        }

        return o;
    }



}
