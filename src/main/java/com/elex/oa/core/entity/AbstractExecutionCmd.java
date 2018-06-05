package com.elex.oa.core.entity;

import com.elex.oa.util.BeanUtil;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
/**
 *@author hugo.zhao
 *@since 2018/5/5 15:28
*/
public class AbstractExecutionCmd implements IExecutionCmd {
    protected String destNodeId;
    protected Map<String, BpmDestNode> nodeUserMap = new HashMap();
    protected String jsonData;
    protected String actInstId;
    protected String nodeId;
    protected String handleNodeId;
    protected String checkFileId;
    protected boolean skipHandler = false;
    protected String fileIds;
    protected String jumpType;
    protected String opinionName;
    protected String opinion;
    protected String opFiles;
    protected Map<String, Object> vars = new HashMap();
    protected Map<String, String> skipCheckTaskUserIds = new HashMap();
    protected String ccUserIds;
    protected String ccGroupIds;
    protected String bpmInstId;
    protected String runPathId;
    protected String timeoutStatus;
    protected Map<String, JSONObject> boDataMaps = new HashMap();
    protected String token;

    public AbstractExecutionCmd() {
    }

    public boolean isSkipHandler() {
        return this.skipHandler;
    }

    public void setSkipHandler(boolean skipHandler) {
        this.skipHandler = skipHandler;
    }

    public String getDestNodeId() {
        return this.destNodeId;
    }

    public void setDestNodeId(String destNodeId) {
        this.destNodeId = destNodeId;
    }

    public Map<String, BpmDestNode> getNodeUserMap() {
        return this.nodeUserMap;
    }

    public Map<String, JSONObject> getBoDataMaps() {
        return this.boDataMaps;
    }

    public void setBoDataMaps(Map<String, JSONObject> boDataMaps) {
        this.boDataMaps = boDataMaps;
    }

    public String getOpFiles() {
        return this.opFiles;
    }

    public void setOpFiles(String opFileId) {
        this.opFiles = opFileId;
    }

    public void setNodeUserMap(Map<String, BpmDestNode> nodeUserMap) {
        this.nodeUserMap = nodeUserMap;
    }

    public String getJsonData() {
        return this.jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getCheckFileId() {
        return this.checkFileId;
    }

    public void setCheckFileId(String checkFileId) {
        this.checkFileId = checkFileId;
    }

    public String getFileIds() {
        return this.fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public String getHandleNodeId() {
        return this.handleNodeId;
    }

    public void setHandleNodeId(String handleNodeId) {
        this.handleNodeId = handleNodeId;
    }

    public String getJumpType() {
        return this.jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Map<String, Object> getVars() {
        return this.vars;
    }

    public void setVars(Map<String, Object> vars) {
        if(!BeanUtil.isEmpty(vars)) {
            this.vars = vars;
        }
    }

    public Map<String, String> getSkipCheckTaskUserIds() {
        return this.skipCheckTaskUserIds;
    }

    public String getCcUserIds() {
        return this.ccUserIds;
    }

    public void setCcUserIds(String ccUserIds) {
        this.ccUserIds = ccUserIds;
    }

    public String getCcGroupIds() {
        return this.ccGroupIds;
    }

    public void setCcGroupIds(String ccGroupIds) {
        this.ccGroupIds = ccGroupIds;
    }

    public String getBpmInstId() {
        return this.bpmInstId;
    }

    public void setBpmInstId(String bpmInstId) {
        this.bpmInstId = bpmInstId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpinionName() {
        return this.opinionName;
    }

    public void setOpinionName(String opinionName) {
        this.opinionName = opinionName;
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
}
