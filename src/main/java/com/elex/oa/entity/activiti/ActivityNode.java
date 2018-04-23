package com.elex.oa.entity.activiti;

/**
 *@author hugo.zhao
 *@since 2018/4/8 17:44
*/
public class ActivityNode {
    public static final String PROCESS_NODE_ID = "_PROCESS";
    public String activityId;
    public String name;
    public String type;
    public String multiInstance = null;
    public String document;
    public String parentActivitiId;

    public String getActivityId() {
        return this.activityId;
    }

    public String getIconCls() {
        return "userTask".equals(this.type)?"icon-group":("startEvent".equals(this.type)?"icon-startEvent":("endEvent".equals(this.type)?"icon-endEvent":("process".equals(this.type)?"icon-flow":"")));
    }

    public ActivityNode() {
    }

    public ActivityNode(String activityId, String name, String type, String document) {
        this.activityId = activityId;
        this.name = name;
        this.type = type;
        this.document = document;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getParentActivitiId() {
        return this.parentActivitiId;
    }

    public void setParentActivitiId(String parentActivitiId) {
        this.parentActivitiId = parentActivitiId;
    }

    public String getMultiInstance() {
        return this.multiInstance;
    }

    public void setMultiInstance(String multiInstance) {
        this.multiInstance = multiInstance;
    }





}
