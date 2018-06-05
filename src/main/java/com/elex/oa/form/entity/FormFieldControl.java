package com.elex.oa.form.entity;

import java.io.Serializable;

public class FormFieldControl implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String fieldName;
    protected String fieldLabel;
    protected String controlHtml;

    public FormFieldControl() {
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldLabel() {
        return this.fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getControlHtml() {
        return this.controlHtml;
    }

    public void setControlHtml(String controlHtml) {
        this.controlHtml = controlHtml;
    }
}