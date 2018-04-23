package com.elex.oa.entity.activiti;

/**
 *@author hugo.zhao
 *@since 2018/4/12 18:51
*/
public class BpmEventConfig {
    private String eventKey;
    private String eventName;
    private String script;
    private String dbAlias;

    public BpmEventConfig() {
    }

    public BpmEventConfig(String eventKey, String eventName, String script) {
        this.eventKey = eventKey;
        this.eventName = eventName;
        this.script = script;
    }

    public BpmEventConfig(String eventKey, String eventName, String script, String dbAlias) {
        this.eventKey = eventKey;
        this.eventName = eventName;
        this.script = script;
        this.dbAlias = dbAlias;
    }

    public String getEventKey() {
        return this.eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getScript() {
        return this.script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDbAlias() {
        return this.dbAlias;
    }

    public void setDbAlias(String dbAlias) {
        this.dbAlias = dbAlias;
    }
}
