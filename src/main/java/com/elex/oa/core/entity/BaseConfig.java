package com.elex.oa.core.entity;

import com.elex.oa.entity.activiti.BpmEventConfig;

import java.util.ArrayList;
import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/5/4 15:38
*/
public class BaseConfig {
    protected String preHandle = null;
    protected String afterHandle = null;
    protected List<BpmEventConfig> events = new ArrayList();

    public BaseConfig() {
    }

    public List<BpmEventConfig> getEvents() {
        return this.events;
    }

    public void setEvents(List<BpmEventConfig> events) {
        this.events = events;
    }

    public String getPreHandle() {
        return this.preHandle;
    }

    public void setPreHandle(String preHandle) {
        this.preHandle = preHandle;
    }

    public String getAfterHandle() {
        return this.afterHandle;
    }

    public void setAfterHandle(String afterHandle) {
        this.afterHandle = afterHandle;
    }

}
