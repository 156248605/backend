package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/4/12 18:55
*/
public enum TaskEventType {
    TASK_CREATED("任务创建"),
    TASK_COMPLETED("任务完成"),
    ACTIVITY_STARTED("节点活动开始"),
    ACTIVITY_COMPLETED("节点活动结束");

    private String eventName;

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    private TaskEventType(String eventName) {
        this.eventName = eventName;
    }
}
