package com.elex.oa.identity.service;

/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
import java.util.Map;

public class IdentityCalConfig {
    private String processInstId;
    private String processDefId;
    private String nodeId;
    private String isCalUser;
    private Map<String, Object> vars = null;
    private String jsonConfig;
    private String token;

    public IdentityCalConfig() {
    }

    public String getProcessInstId() {
        return this.processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getIsCalUser() {
        return this.isCalUser;
    }

    public void setIsCalUser(String isCalUser) {
        this.isCalUser = isCalUser;
    }

    public Map<String, Object> getVars() {
        return this.vars;
    }

    public void setVars(Map<String, Object> vars) {
        this.vars = vars;
    }

    public String getJsonConfig() {
        return this.jsonConfig;
    }

    public void setJsonConfig(String jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    public String getProcessDefId() {
        return this.processDefId;
    }

    public void setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}