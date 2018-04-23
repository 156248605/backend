package com.elex.oa.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/4/8 16:54
*/
public class ActProcessDef {
    private String processDefId;
    private String processKey;
    private String processName;
    private Map<String, ActNodeDef> nodesMap = new HashMap();

    public ActProcessDef() {
    }

    public String getProcessDefId() {
        return this.processDefId;
    }

    public void setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
    }

    public String getProcessKey() {
        return this.processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

    public Map<String, ActNodeDef> getNodesMap() {
        return this.nodesMap;
    }

    public void setNodesMap(Map<String, ActNodeDef> nodesMap) {
        this.nodesMap = nodesMap;
    }

    public Collection<ActNodeDef> getActNodeDefs() {
        return this.nodesMap.values();
    }

    public String getProcessName() {
        return this.processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
