package com.elex.oa.core.entity;

/**
 *@author hugo.zhao
 *@since 2018/5/5 11:12
*/
public class StartEventConfig extends BaseConfig {
    protected String allowPathSelect = "false";
    protected String allowNextExecutor = "false";

    public StartEventConfig() {
    }

    public String getAllowPathSelect() {
        return this.allowPathSelect;
    }

    public void setAllowPathSelect(String allowPathSelect) {
        this.allowPathSelect = allowPathSelect;
    }

    public String getAllowNextExecutor() {
        return this.allowNextExecutor;
    }

    public void setAllowNextExecutor(String allowNextExecutor) {
        this.allowNextExecutor = allowNextExecutor;
    }
}
