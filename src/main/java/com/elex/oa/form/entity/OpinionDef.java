package com.elex.oa.form.entity;

public class OpinionDef {
    private String name = "";
    private String label = "";

    public OpinionDef(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public OpinionDef() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
