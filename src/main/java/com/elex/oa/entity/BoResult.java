package com.elex.oa.entity;


import com.elex.oa.entity.bo.SysBoEnt;

public class BoResult {
    private String pk = "";
    private String action = "";
    private SysBoEnt boEnt = null;
    private boolean isMain = true;

    public BoResult() {
    }

    public BoResult(String pk, String action, SysBoEnt boEnt) {
        this.pk = pk;
        this.action = action;
        this.boEnt = boEnt;
    }

    public String getAction() {
        return this.action;
    }

    public String getPk() {
        return this.pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SysBoEnt getBoEnt() {
        return this.boEnt;
    }

    public void setBoEnt(SysBoEnt boEnt) {
        this.boEnt = boEnt;
    }

    public boolean isMain() {
        return this.isMain;
    }

    public void setMain(boolean isMain) {
        this.isMain = isMain;
    }

    public static final class ACTION_TYPE {
        public static final String ADD = "add";
        public static final String UPDATE = "upd";
        public static final String DELETE = "del";

        public ACTION_TYPE() {
        }
    }
}
