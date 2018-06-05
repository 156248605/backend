package com.elex.oa.core.entity;

import com.elex.oa.entity.ActNodeDef;
import com.elex.oa.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 *@author hugo.zhao
 *@since 2018/5/5 13:34
*/
public class TaskNodeUser {
    private String taskId;
    private String nodeId;
    private String nodeText;
    private String nodeType;
    private String multiInstance = null;
    private String userIds;
    private String userFullnames;
    private String userIdsAndText;
    private String groupIds;
    private String groupNames;
    private String refUserIds;
    private String refUserFullnames;
    private String exeUserIds;
    private String exeUserFullnames;
    private boolean isRunning = false;
    private boolean isCurUserTask = false;

    public TaskNodeUser() {
    }

    public String getUserJsons() {
        if(!StringUtil.isEmpty(this.userIds) && !StringUtils.isEmpty(this.userFullnames)) {
            String[] uIds = this.userIds.split("[,]");
            String[] uNames = this.userFullnames.split("[,]");
            JSONArray arr = JSONArray.parseArray("[]");

            for(int i = 0; i < uIds.length; ++i) {
                JSONObject jsonObj = JSONObject.parseObject("{}");
                jsonObj.put("id", uIds[i]);
                jsonObj.put("text", uNames[i]);
                arr.add(jsonObj);
            }

            return arr.toJSONString().replace("\"", "\'");
        } else {
            return "[]";
        }
    }

    public TaskNodeUser(ActNodeDef actNodeDef) {
        this.nodeId = actNodeDef.getNodeId();
        this.nodeText = actNodeDef.getNodeName();
        this.nodeType = actNodeDef.getNodeType();
    }

    public String getNodeId() {
        return this.nodeId;
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

    public String getUserIds() {
        return this.userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getUserFullnames() {
        return this.userFullnames;
    }

    public void setUserFullnames(String userFullnames) {
        this.userFullnames = userFullnames;
    }

    public String getUserIdsAndText() {
        return this.userIdsAndText;
    }

    public void setUserIdsAndText(String userIdsAndText) {
        this.userIdsAndText = userIdsAndText;
    }

    public String getGroupIds() {
        return this.groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getGroupNames() {
        return this.groupNames;
    }

    public void setGroupNames(String groupNames) {
        this.groupNames = groupNames;
    }

    public String getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getMultiInstance() {
        return this.multiInstance;
    }

    public void setMultiInstance(String multiInstance) {
        this.multiInstance = multiInstance;
    }

    public String getRefUserIds() {
        return this.refUserIds;
    }

    public void setRefUserIds(String refUserIds) {
        this.refUserIds = refUserIds;
    }

    public String getRefUserFullnames() {
        return this.refUserFullnames;
    }

    public void setRefUserFullnames(String refUserFullnames) {
        this.refUserFullnames = refUserFullnames;
    }

    public String getExeUserIds() {
        return this.exeUserIds;
    }

    public void setExeUserIds(String exeUserIds) {
        this.exeUserIds = exeUserIds;
    }

    public String getExeUserFullnames() {
        return this.exeUserFullnames;
    }

    public void setExeUserFullnames(String exeUserFullnames) {
        this.exeUserFullnames = exeUserFullnames;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isCurUserTask() {
        return this.isCurUserTask;
    }

    public void setCurUserTask(boolean isCurUserTask) {
        this.isCurUserTask = isCurUserTask;
    }
}
