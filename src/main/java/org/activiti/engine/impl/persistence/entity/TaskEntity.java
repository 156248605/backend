package org.activiti.engine.impl.persistence.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.db.BulkDeleteable;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.HasRevision;
import org.activiti.engine.impl.db.PersistentObject;
import org.activiti.engine.impl.delegate.TaskListenerInvocation;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.SuspensionState;
import org.activiti.engine.impl.persistence.entity.VariableInitializingList;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.persistence.entity.VariableScopeImpl;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;

public class TaskEntity extends VariableScopeImpl implements Task, DelegateTask, Serializable, PersistentObject, HasRevision, BulkDeleteable {
    public static final String DELETE_REASON_COMPLETED = "completed";
    public static final String DELETE_REASON_DELETED = "deleted";
    private static final long serialVersionUID = 1L;
    protected int revision;
    protected String owner;
    protected String assignee;
    protected String initialAssignee;
    protected DelegationState delegationState;
    protected String parentTaskId;
    protected String name;
    protected String description;
    protected int priority = 50;
    protected Date createTime;
    protected Date dueDate;
    protected int suspensionState;
    protected String category;
    protected boolean isIdentityLinksInitialized;
    protected List<IdentityLinkEntity> taskIdentityLinkEntities;
    protected String executionId;
    protected ExecutionEntity execution;
    protected String processInstanceId;
    protected ExecutionEntity processInstance;
    protected String processDefinitionId;
    protected TaskDefinition taskDefinition;
    protected String taskDefinitionKey;
    protected String formKey;
    protected boolean isDeleted;
    protected String eventName;
    protected String tenantId;
    protected List<VariableInstanceEntity> queryVariables;
    protected boolean forcedUpdate;
    protected String solId;
    protected String agentUserId;
    protected Integer enableMobile;
    protected String token;
    protected String runPathId;
    protected Integer urgentTimes;
    protected String timeoutStatus;

    public TaskEntity() {
        this.suspensionState = SuspensionState.ACTIVE.getStateCode();
        this.isIdentityLinksInitialized = false;
        this.taskIdentityLinkEntities = new ArrayList();
        this.tenantId = "";
        this.enableMobile = Integer.valueOf(0);
    }

    public TaskEntity(String taskId) {
        this.suspensionState = SuspensionState.ACTIVE.getStateCode();
        this.isIdentityLinksInitialized = false;
        this.taskIdentityLinkEntities = new ArrayList();
        this.tenantId = "";
        this.enableMobile = Integer.valueOf(0);
        this.id = taskId;
    }

    public static TaskEntity createAndInsert(ActivityExecution execution) {
        TaskEntity task = create(Context.getProcessEngineConfiguration().getClock().getCurrentTime());
        task.insert((ExecutionEntity)execution);
        return task;
    }

    public void insert(ExecutionEntity execution) {
        CommandContext commandContext = Context.getCommandContext();
        DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
        dbSqlSession.insert(this);
        if(execution != null && execution.getTenantId() != null) {
            this.setTenantId(execution.getTenantId());
        }

        if(execution != null) {
            execution.addTask(this);
        }

        commandContext.getHistoryManager().recordTaskCreated(this, execution);
        if(commandContext.getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
            commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED, this));
            commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_INITIALIZED, this));
        }

    }

    public void update() {
        this.setOwner(this.getOwner());
        this.setAssignee(this.getAssignee(), true, false);
        this.setDelegationState(this.getDelegationState());
        this.setName(this.getName());
        this.setDescription(this.getDescription());
        this.setPriority(this.getPriority());
        this.setCategory(this.getCategory());
        this.setCreateTime(this.getCreateTime());
        this.setDueDate(this.getDueDate());
        this.setParentTaskId(this.getParentTaskId());
        this.setFormKey(this.formKey);
        CommandContext commandContext = Context.getCommandContext();
        DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
        dbSqlSession.update(this);
        if(commandContext.getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
            commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_UPDATED, this));
        }

    }

    public static TaskEntity create(Date createTime) {
        TaskEntity task = new TaskEntity();
        task.isIdentityLinksInitialized = true;
        task.createTime = createTime;
        return task;
    }

    public void complete(Map variablesMap, boolean localScope) {
        if(this.getDelegationState() != null && this.getDelegationState().equals(DelegationState.PENDING)) {
            throw new ActivitiException("A delegated task cannot be completed, but should be resolved instead.");
        } else {
            this.fireEvent("complete");
            if(Authentication.getAuthenticatedUserId() != null && this.processInstanceId != null) {
                this.getProcessInstance().involveUser(Authentication.getAuthenticatedUserId(), "participant");
            }

            if(Context.getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
                Context.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEntityWithVariablesEvent(ActivitiEventType.TASK_COMPLETED, this, variablesMap, localScope));
            }

            Context.getCommandContext().getTaskEntityManager().deleteTask(this, "completed", false);
            if(this.executionId != null) {
                ExecutionEntity execution = this.getExecution();
                execution.removeTask(this);
                execution.signal((String)null, (Object)null);
            }

        }
    }

    public void delegate(String userId) {
        this.setDelegationState(DelegationState.PENDING);
        if(this.getOwner() == null) {
            this.setOwner(this.getAssignee());
        }

        this.setAssignee(userId, true, true);
    }

    public void resolve() {
        this.setDelegationState(DelegationState.RESOLVED);
        this.setAssignee(this.owner, true, true);
    }

    public Object getPersistentState() {
        HashMap persistentState = new HashMap();
        persistentState.put("assignee", this.assignee);
        persistentState.put("owner", this.owner);
        persistentState.put("name", this.name);
        persistentState.put("priority", Integer.valueOf(this.priority));
        if(this.executionId != null) {
            persistentState.put("executionId", this.executionId);
        }

        if(this.processDefinitionId != null) {
            persistentState.put("processDefinitionId", this.processDefinitionId);
        }

        if(this.createTime != null) {
            persistentState.put("createTime", this.createTime);
        }

        if(this.description != null) {
            persistentState.put("description", this.description);
        }

        if(this.dueDate != null) {
            persistentState.put("dueDate", this.dueDate);
        }

        if(this.parentTaskId != null) {
            persistentState.put("parentTaskId", this.parentTaskId);
        }

        if(this.delegationState != null) {
            persistentState.put("delegationState", this.delegationState);
        }

        persistentState.put("suspensionState", Integer.valueOf(this.suspensionState));
        if(this.forcedUpdate) {
            persistentState.put("forcedUpdate", Boolean.TRUE);
        }

        return persistentState;
    }

    public int getRevisionNext() {
        return this.revision + 1;
    }

    public void forceUpdate() {
        this.forcedUpdate = true;
    }

    protected VariableScopeImpl getParentVariableScope() {
        return this.getExecution() != null?this.execution:null;
    }

    protected void initializeVariableInstanceBackPointer(VariableInstanceEntity variableInstance) {
        variableInstance.setTaskId(this.id);
        variableInstance.setExecutionId(this.executionId);
        variableInstance.setProcessInstanceId(this.processInstanceId);
    }

    protected List<VariableInstanceEntity> loadVariableInstances() {
        return Context.getCommandContext().getVariableInstanceEntityManager().findVariableInstancesByTaskId(this.id);
    }

    protected VariableInstanceEntity createVariableInstance(String variableName, Object value, ExecutionEntity sourceActivityExecution) {
        VariableInstanceEntity result = super.createVariableInstance(variableName, value, sourceActivityExecution);
        if(Context.getProcessEngineConfiguration() != null && Context.getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
            Context.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createVariableEvent(ActivitiEventType.VARIABLE_CREATED, variableName, value, result.getType(), result.getTaskId(), result.getExecutionId(), this.getProcessInstanceId(), this.getProcessDefinitionId()));
        }

        return result;
    }

    protected void updateVariableInstance(VariableInstanceEntity variableInstance, Object value, ExecutionEntity sourceActivityExecution) {
        super.updateVariableInstance(variableInstance, value, sourceActivityExecution);
        if(Context.getProcessEngineConfiguration() != null && Context.getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
            Context.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createVariableEvent(ActivitiEventType.VARIABLE_UPDATED, variableInstance.getName(), value, variableInstance.getType(), variableInstance.getTaskId(), variableInstance.getExecutionId(), this.getProcessInstanceId(), this.getProcessDefinitionId()));
        }

    }

    public ExecutionEntity getExecution() {
        if(this.execution == null && this.executionId != null) {
            this.execution = Context.getCommandContext().getExecutionEntityManager().findExecutionById(this.executionId);
        }

        return this.execution;
    }

    public void setExecution(DelegateExecution execution) {
        if(execution != null) {
            this.execution = (ExecutionEntity)execution;
            this.executionId = this.execution.getId();
            this.processInstanceId = this.execution.getProcessInstanceId();
            this.processDefinitionId = this.execution.getProcessDefinitionId();
            Context.getCommandContext().getHistoryManager().recordTaskExecutionIdChange(this.id, this.executionId);
        } else {
            this.execution = null;
            this.executionId = null;
            this.processInstanceId = null;
            this.processDefinitionId = null;
        }

    }

    public IdentityLinkEntity addIdentityLink(String userId, String groupId, String type) {
        IdentityLinkEntity identityLinkEntity = new IdentityLinkEntity();
        this.getIdentityLinks().add(identityLinkEntity);
        identityLinkEntity.setTask(this);
        identityLinkEntity.setUserId(userId);
        identityLinkEntity.setGroupId(groupId);
        identityLinkEntity.setType(type);
        identityLinkEntity.insert();
        if(userId != null && this.processInstanceId != null) {
            this.getProcessInstance().involveUser(userId, "participant");
        }

        return identityLinkEntity;
    }

    public void deleteIdentityLink(String userId, String groupId, String type) {
        List identityLinks = Context.getCommandContext().getIdentityLinkEntityManager().findIdentityLinkByTaskUserGroupAndType(this.id, userId, groupId, type);
        ArrayList identityLinkIds = new ArrayList();
        Iterator removedIdentityLinkEntities = identityLinks.iterator();

        while(removedIdentityLinkEntities.hasNext()) {
            IdentityLinkEntity identityLink = (IdentityLinkEntity)removedIdentityLinkEntities.next();
            Context.getCommandContext().getIdentityLinkEntityManager().deleteIdentityLink(identityLink, true);
            identityLinkIds.add(identityLink.getId());
        }

        ArrayList removedIdentityLinkEntities1 = new ArrayList();
        Iterator identityLink1 = this.getIdentityLinks().iterator();

        while(true) {
            IdentityLinkEntity identityLinkEntity;
            do {
                do {
                    do {
                        if(!identityLink1.hasNext()) {
                            this.getIdentityLinks().removeAll(removedIdentityLinkEntities1);
                            return;
                        }

                        identityLinkEntity = (IdentityLinkEntity)identityLink1.next();
                    } while(!"candidate".equals(identityLinkEntity.getType()));
                } while(identityLinkIds.contains(identityLinkEntity.getId()));
            } while((userId == null || !userId.equals(identityLinkEntity.getUserId())) && (groupId == null || !groupId.equals(identityLinkEntity.getGroupId())));

            Context.getCommandContext().getIdentityLinkEntityManager().deleteIdentityLink(identityLinkEntity, true);
            removedIdentityLinkEntities1.add(identityLinkEntity);
        }
    }

    public Set<IdentityLink> getCandidates() {
        HashSet potentialOwners = new HashSet();
        Iterator var2 = this.getIdentityLinks().iterator();

        while(var2.hasNext()) {
            IdentityLinkEntity identityLinkEntity = (IdentityLinkEntity)var2.next();
            if("candidate".equals(identityLinkEntity.getType())) {
                potentialOwners.add(identityLinkEntity);
            }
        }

        return potentialOwners;
    }

    public void addCandidateUser(String userId) {
        this.addIdentityLink(userId, (String)null, "candidate");
    }

    public void addCandidateUsers(Collection<String> candidateUsers) {
        Iterator var2 = candidateUsers.iterator();

        while(var2.hasNext()) {
            String candidateUser = (String)var2.next();
            this.addCandidateUser(candidateUser);
        }

    }

    public void addCandidateGroup(String groupId) {
        this.addIdentityLink((String)null, groupId, "candidate");
    }

    public void addCandidateGroups(Collection<String> candidateGroups) {
        Iterator var2 = candidateGroups.iterator();

        while(var2.hasNext()) {
            String candidateGroup = (String)var2.next();
            this.addCandidateGroup(candidateGroup);
        }

    }

    public void addGroupIdentityLink(String groupId, String identityLinkType) {
        this.addIdentityLink((String)null, groupId, identityLinkType);
    }

    public void addUserIdentityLink(String userId, String identityLinkType) {
        this.addIdentityLink(userId, (String)null, identityLinkType);
    }

    public void deleteCandidateGroup(String groupId) {
        this.deleteGroupIdentityLink(groupId, "candidate");
    }

    public void deleteCandidateUser(String userId) {
        this.deleteUserIdentityLink(userId, "candidate");
    }

    public void deleteGroupIdentityLink(String groupId, String identityLinkType) {
        if(groupId != null) {
            this.deleteIdentityLink((String)null, groupId, identityLinkType);
        }

    }

    public void deleteUserIdentityLink(String userId, String identityLinkType) {
        if(userId != null) {
            this.deleteIdentityLink(userId, (String)null, identityLinkType);
        }

    }

    public List<IdentityLinkEntity> getIdentityLinks() {
        if(!this.isIdentityLinksInitialized) {
            this.taskIdentityLinkEntities = Context.getCommandContext().getIdentityLinkEntityManager().findIdentityLinksByTaskId(this.id);
            this.isIdentityLinksInitialized = true;
        }

        return this.taskIdentityLinkEntities;
    }

    public Map<String, Object> getActivityInstanceVariables() {
        return this.execution != null?this.execution.getVariables():Collections.EMPTY_MAP;
    }

    public void setExecutionVariables(Map<String, Object> parameters) {
        if(this.getExecution() != null) {
            this.execution.setVariables(parameters);
        }

    }

    public String toString() {
        return "Task[id=" + this.id + ", name=" + this.name + "]";
    }

    public void setName(String taskName) {
        this.name = taskName;
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext != null) {
            commandContext.getHistoryManager().recordTaskNameChange(this.id, taskName);
        }

    }

    @Override
    public void setLocalizedName(String s) {

    }

    public void setNameWithoutCascade(String taskName) {
        this.name = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext != null) {
            commandContext.getHistoryManager().recordTaskDescriptionChange(this.id, description);
        }

    }

    @Override
    public void setLocalizedDescription(String s) {

    }

    public void setDescriptionWithoutCascade(String description) {
        this.description = description;
    }

    public void setAssignee(String assignee) {
        this.setAssignee(assignee, false, false);
    }

    public void setAssignee(String assignee, boolean dispatchAssignmentEvent, boolean dispatchUpdateEvent) {
        CommandContext commandContext = Context.getCommandContext();
        if(assignee == null && this.assignee == null) {
            if(commandContext != null) {
                commandContext.getHistoryManager().recordTaskAssigneeChange(this.id, assignee);
            }

        } else {
            this.assignee = assignee;
            if(commandContext != null) {
                commandContext.getHistoryManager().recordTaskAssigneeChange(this.id, assignee);
                if(assignee != null && this.processInstanceId != null) {
                    this.getProcessInstance().involveUser(assignee, "participant");
                }

                if(!StringUtils.equals(this.initialAssignee, assignee)) {
                    this.fireEvent("assignment");
                    this.initialAssignee = assignee;
                }

                if(commandContext.getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
                    if(dispatchAssignmentEvent) {
                        commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEntityEvent(ActivitiEventType.TASK_ASSIGNED, this));
                    }

                    if(dispatchUpdateEvent) {
                        commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_UPDATED, this));
                    }
                }
            }

        }
    }

    public void setAssigneeWithoutCascade(String assignee) {
        this.assignee = assignee;
        this.initialAssignee = assignee;
    }

    public void setOwner(String owner) {
        this.setOwner(owner, false);
    }

    public void setOwner(String owner, boolean dispatchUpdateEvent) {
        if(owner != null || this.owner != null) {
            this.owner = owner;
            CommandContext commandContext = Context.getCommandContext();
            if(commandContext != null) {
                commandContext.getHistoryManager().recordTaskOwnerChange(this.id, owner);
                if(owner != null && this.processInstanceId != null) {
                    this.getProcessInstance().involveUser(owner, "participant");
                }

                if(dispatchUpdateEvent && commandContext.getProcessEngineConfiguration().getEventDispatcher().isEnabled() && dispatchUpdateEvent) {
                    commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_UPDATED, this));
                }
            }

        }
    }

    public void setOwnerWithoutCascade(String owner) {
        this.owner = owner;
    }

    public void setDueDate(Date dueDate) {
        this.setDueDate(dueDate, false);
    }

    public void setDueDate(Date dueDate, boolean dispatchUpdateEvent) {
        this.dueDate = dueDate;
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext != null) {
            commandContext.getHistoryManager().recordTaskDueDateChange(this.id, dueDate);
            if(dispatchUpdateEvent && commandContext.getProcessEngineConfiguration().getEventDispatcher().isEnabled() && dispatchUpdateEvent) {
                commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_UPDATED, this));
            }
        }

    }

    public void setDueDateWithoutCascade(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(int priority) {
        this.setPriority(priority, false);
    }

    public void setPriority(int priority, boolean dispatchUpdateEvent) {
        this.priority = priority;
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext != null) {
            commandContext.getHistoryManager().recordTaskPriorityChange(this.id, priority);
            if(dispatchUpdateEvent && commandContext.getProcessEngineConfiguration().getEventDispatcher().isEnabled() && dispatchUpdateEvent) {
                commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_UPDATED, this));
            }
        }

    }

    public void setCategoryWithoutCascade(String category) {
        this.category = category;
    }

    public void setCategory(String category) {
        this.category = category;
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext != null) {
            commandContext.getHistoryManager().recordTaskCategoryChange(this.id, category);
        }

    }

    public void setPriorityWithoutCascade(int priority) {
        this.priority = priority;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext != null) {
            commandContext.getHistoryManager().recordTaskParentTaskIdChange(this.id, parentTaskId);
        }

    }

    public void setParentTaskIdWithoutCascade(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public void setTaskDefinitionKeyWithoutCascade(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getFormKey() {
        return this.formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext != null) {
            commandContext.getHistoryManager().recordTaskFormKeyChange(this.id, formKey);
        }

    }

    public void setFormKeyWithoutCascade(String formKey) {
        this.formKey = formKey;
    }

    public void fireEvent(String taskEventName) {
        TaskDefinition taskDefinition = this.getTaskDefinition();
        if(taskDefinition != null) {
            List taskEventListeners = this.getTaskDefinition().getTaskListener(taskEventName);
            if(taskEventListeners != null) {
                Iterator var4 = taskEventListeners.iterator();

                while(var4.hasNext()) {
                    TaskListener taskListener = (TaskListener)var4.next();
                    ExecutionEntity execution = this.getExecution();
                    if(execution != null) {
                        this.setEventName(taskEventName);
                    }

                    try {
                        Context.getProcessEngineConfiguration().getDelegateInterceptor().handleInvocation(new TaskListenerInvocation(taskListener, this));
                    } catch (Exception var8) {
                        throw new ActivitiException("Exception while invoking TaskListener: " + var8.getMessage(), var8);
                    }
                }
            }
        }

    }

    protected boolean isActivityIdUsedForDetails() {
        return false;
    }

    protected VariableInstanceEntity getSpecificVariable(String variableName) {
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext == null) {
            throw new ActivitiException("lazy loading outside command context");
        } else {
            VariableInstanceEntity variableInstance = commandContext.getVariableInstanceEntityManager().findVariableInstanceByTaskAndName(this.id, variableName);
            return variableInstance;
        }
    }

    protected List<VariableInstanceEntity> getSpecificVariables(Collection<String> variableNames) {
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext == null) {
            throw new ActivitiException("lazy loading outside command context");
        } else {
            return commandContext.getVariableInstanceEntityManager().findVariableInstancesByTaskAndNames(this.id, variableNames);
        }
    }

    public void setTaskDefinition(TaskDefinition taskDefinition) {
        this.taskDefinition = taskDefinition;
        this.taskDefinitionKey = taskDefinition.getKey();
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext != null) {
            commandContext.getHistoryManager().recordTaskDefinitionKeyChange(this, this.taskDefinitionKey);
        }

    }

    public TaskDefinition getTaskDefinition() {
        if(this.taskDefinition == null && this.taskDefinitionKey != null) {
            ProcessDefinitionEntity processDefinition = Context.getProcessEngineConfiguration().getDeploymentManager().findDeployedProcessDefinitionById(this.processDefinitionId);
            this.taskDefinition = (TaskDefinition)processDefinition.getTaskDefinitions().get(this.taskDefinitionKey);
        }

        return this.taskDefinition;
    }

    public int getRevision() {
        return this.revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public int getPriority() {
        return this.priority;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTimeoutStatus() {
        return this.timeoutStatus;
    }

    public void setTimeoutStatus(String timeoutStatus) {
        this.timeoutStatus = timeoutStatus;
    }

    public String getExecutionId() {
        return this.executionId;
    }

    public String getProcessInstanceId() {
        return this.processInstanceId;
    }

    public String getProcessDefinitionId() {
        return this.processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getSolId() {
        return this.solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    public String getAgentUserId() {
        return this.agentUserId;
    }

    public void setAgentUserId(String agentUserId) {
        this.agentUserId = agentUserId;
    }

    public String getAssignee() {
        return this.assignee;
    }

    public String getTaskDefinitionKey() {
        return this.taskDefinitionKey;
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

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
        CommandContext commandContext = Context.getCommandContext();
        if(commandContext != null) {
            commandContext.getHistoryManager().recordTaskDefinitionKeyChange(this, taskDefinitionKey);
        }

    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public ExecutionEntity getProcessInstance() {
        if(this.processInstance == null && this.processInstanceId != null) {
            this.processInstance = Context.getCommandContext().getExecutionEntityManager().findExecutionById(this.processInstanceId);
        }

        return this.processInstance;
    }

    public void setProcessInstance(ExecutionEntity processInstance) {
        this.processInstance = processInstance;
    }

    public void setExecution(ExecutionEntity execution) {
        this.execution = execution;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getOwner() {
        return this.owner;
    }

    public DelegationState getDelegationState() {
        return this.delegationState;
    }

    public void setDelegationState(DelegationState delegationState) {
        this.delegationState = delegationState;
    }

    public String getDelegationStateString() {
        return this.delegationState != null?this.delegationState.toString():null;
    }

    public void setDelegationStateString(String delegationStateString) {
        this.delegationState = delegationStateString != null?(DelegationState)DelegationState.valueOf(DelegationState.class, delegationStateString):null;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getParentTaskId() {
        return this.parentTaskId;
    }

   /* public Map<String, VariableInstanceEntity> getVariableInstances() {
        this.ensureVariableInstancesInitialized();
        return this.variableInstances;
    }*/

    public int getSuspensionState() {
        return this.suspensionState;
    }

    public void setSuspensionState(int suspensionState) {
        this.suspensionState = suspensionState;
    }

    public String getCategory() {
        return this.category;
    }

    public boolean isSuspended() {
        return this.suspensionState == SuspensionState.SUSPENDED.getStateCode();
    }

    public Map<String, Object> getTaskLocalVariables() {
        HashMap variables = new HashMap();
        if(this.queryVariables != null) {
            Iterator var2 = this.queryVariables.iterator();

            while(var2.hasNext()) {
                VariableInstanceEntity variableInstance = (VariableInstanceEntity)var2.next();
                if(variableInstance.getId() != null && variableInstance.getTaskId() != null) {
                    variables.put(variableInstance.getName(), variableInstance.getValue());
                }
            }
        }

        return variables;
    }

    public Map<String, Object> getProcessVariables() {
        HashMap variables = new HashMap();
        if(this.queryVariables != null) {
            Iterator var2 = this.queryVariables.iterator();

            while(var2.hasNext()) {
                VariableInstanceEntity variableInstance = (VariableInstanceEntity)var2.next();
                if(variableInstance.getId() != null && variableInstance.getTaskId() == null) {
                    variables.put(variableInstance.getName(), variableInstance.getValue());
                }
            }
        }

        return variables;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getEnableMobile() {
        return this.enableMobile;
    }

    public void setEnableMobile(Integer enableMobile) {
        this.enableMobile = enableMobile;
    }

    public List<VariableInstanceEntity> getQueryVariables() {
        if(this.queryVariables == null && Context.getCommandContext() != null) {
            this.queryVariables = new VariableInitializingList();
        }

        return this.queryVariables;
    }

    public void setQueryVariables(List<VariableInstanceEntity> queryVariables) {
        this.queryVariables = queryVariables;
    }

    public String getRunPathId() {
        return this.runPathId;
    }

    public void setRunPathId(String runPathId) {
        this.runPathId = runPathId;
    }
}