package com.elex.oa.core.script.config;

public class ScriptParam {
    private String paramType;
    private String paramName;
    private String title;

    public ScriptParam() {
    }

    public ScriptParam(String paramType, String paramName, String title) {
        this.paramType = paramType;
        this.paramName = paramName;
        this.title = title;
    }

    public String getParamType() {
        return this.paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamName() {
        return this.paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}