package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/4/12 18:54
*/
public enum ProcessEventType {
    PROCESS_STARTED("流程启动时"),
    PROCESS_COMPLETED("流程完成时"),
    PROCESS_CANCELLED("流程取消删除时");

    private String eventName;

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    private ProcessEventType(String eventName) {
        this.eventName = eventName;
    }
}
