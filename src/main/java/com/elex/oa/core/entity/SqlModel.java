package com.elex.oa.core.entity;
import java.util.HashMap;
import java.util.Map;
public class SqlModel {
    private String sql = "";
    private Map<String, Object> params = new HashMap();

    public SqlModel() {
    }

    public SqlModel(String sql) {
        this.sql = sql;
    }

    public SqlModel(String sql, Map<String, Object> params) {
        this.sql = sql;
        this.params = params;
    }

    public String getSql() {
        return this.sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void addParam(String key, Object obj) {
        this.params.put(key, obj);
    }
}
