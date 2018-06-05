package com.elex.oa.core.entity;

/**
 *@author hugo.zhao
 *@since 2018/5/4 15:39
*/
public class ProcessConfig extends BaseConfig {
    public static final String DATA_SAVE_MODE_ALL = "all";
    public static final String DATA_SAVE_MODE_DB = "db";
    public static final String DATA_SAVE_MODE_JSON = "json";
    public static final String PROCESS_NODE_ID = "_PROCESS";
    protected String isSkipFirst = "false";
    protected String isShowStartUsers;
    protected String subRule;
    protected String noRule;
    protected String allowFreeJump = "false";
    protected String showCheckList = "false";
    protected String couldChooseConfig;
    protected String mustChooseConfig;
    protected String couldChooseConfigName;
    protected String mustChooseConfigName;
    protected String notices = "";
    protected String tableRightJson = "";
    protected String processEndHandle;

    public ProcessConfig() {
    }

    public String getIsSkipFirst() {
        return this.isSkipFirst;
    }

    public void setIsSkipFirst(String isSkipFirst) {
        this.isSkipFirst = isSkipFirst;
    }

    public String getSubRule() {
        return this.subRule;
    }

    public void setSubRule(String subRule) {
        this.subRule = subRule;
    }

    public String getIsShowStartUsers() {
        return this.isShowStartUsers;
    }

    public void setIsShowStartUsers(String isShowStartUsers) {
        this.isShowStartUsers = isShowStartUsers;
    }

    public String getAllowFreeJump() {
        return this.allowFreeJump;
    }

    public void setAllowFreeJump(String allowFreeJump) {
        this.allowFreeJump = allowFreeJump;
    }

    public String getProcessEndHandle() {
        return this.processEndHandle;
    }

    public void setProcessEndHandle(String processEndHandle) {
        this.processEndHandle = processEndHandle;
    }

    public String getShowCheckList() {
        return this.showCheckList;
    }

    public void setShowCheckList(String showCheckList) {
        this.showCheckList = showCheckList;
    }

    public String getCouldChooseConfig() {
        return this.couldChooseConfig;
    }

    public void setCouldChooseConfig(String couldChooseConfig) {
        this.couldChooseConfig = couldChooseConfig;
    }

    public String getMustChooseConfig() {
        return this.mustChooseConfig;
    }

    public void setMustChooseConfig(String mustChooseConfig) {
        this.mustChooseConfig = mustChooseConfig;
    }

    public String getCouldChooseConfigName() {
        return this.couldChooseConfigName;
    }

    public void setCouldChooseConfigName(String couldChooseConfigName) {
        this.couldChooseConfigName = couldChooseConfigName;
    }

    public String getMustChooseConfigName() {
        return this.mustChooseConfigName;
    }

    public void setMustChooseConfigName(String mustChooseConfigName) {
        this.mustChooseConfigName = mustChooseConfigName;
    }

    public String getNotices() {
        return this.notices;
    }

    public void setNotices(String notices) {
        this.notices = notices;
    }

    public String getTableRightJson() {
        return this.tableRightJson;
    }

    public void setTableRightJson(String tableRightJson) {
        this.tableRightJson = tableRightJson;
    }

    public String getNoRule() {
        return this.noRule;
    }

    public void setNoRule(String noRule) {
        this.noRule = noRule;
    }
}
