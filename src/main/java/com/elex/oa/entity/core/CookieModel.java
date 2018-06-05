package com.elex.oa.entity.core;

public class CookieModel {
    private String name = "";
    private String value = "";
    private String path = "";
    private boolean httpOnly = false;

    public CookieModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isHttpOnly() {
        return this.httpOnly;
    }

    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    public String toString() {
        return "CookieModel [name=" + this.name + ", value=" + this.value + "]";
    }
}