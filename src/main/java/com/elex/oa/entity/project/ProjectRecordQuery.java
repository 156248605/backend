package com.elex.oa.entity.project;

import java.util.List;

public class ProjectRecordQuery {
    private String codeSelect;
    private String codeInput;
    private String nameSelect;
    private String nameInput;
    private String bySelect;
    private String byInput;
    private String timeSelect;
    private List<String> timeList;

    public ProjectRecordQuery() {
    }

    public ProjectRecordQuery(String codeSelect, String codeInput, String nameSelect, String nameInput, String bySelect, String byInput, String timeSelect, List<String> timeList) {
        this.codeSelect = codeSelect;
        this.codeInput = codeInput;
        this.nameSelect = nameSelect;
        this.nameInput = nameInput;
        this.bySelect = bySelect;
        this.byInput = byInput;
        this.timeSelect = timeSelect;
        this.timeList = timeList;
    }

    public String getCodeSelect() {
        return codeSelect;
    }

    public void setCodeSelect(String codeSelect) {
        this.codeSelect = codeSelect;
    }

    public String getCodeInput() {
        return codeInput;
    }

    public void setCodeInput(String codeInput) {
        this.codeInput = codeInput;
    }

    public String getNameSelect() {
        return nameSelect;
    }

    public void setNameSelect(String nameSelect) {
        this.nameSelect = nameSelect;
    }

    public String getNameInput() {
        return nameInput;
    }

    public void setNameInput(String nameInput) {
        this.nameInput = nameInput;
    }

    public String getBySelect() {
        return bySelect;
    }

    public void setBySelect(String bySelect) {
        this.bySelect = bySelect;
    }

    public String getByInput() {
        return byInput;
    }

    public void setByInput(String byInput) {
        this.byInput = byInput;
    }

    public String getTimeSelect() {
        return timeSelect;
    }

    public void setTimeSelect(String timeSelect) {
        this.timeSelect = timeSelect;
    }

    public List<String> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<String> timeList) {
        this.timeList = timeList;
    }

    @Override
    public String toString() {
        return "ProjectRecordQuery{" +
                "codeSelect='" + codeSelect + '\'' +
                ", codeInput='" + codeInput + '\'' +
                ", nameSelect='" + nameSelect + '\'' +
                ", nameInput='" + nameInput + '\'' +
                ", bySelect='" + bySelect + '\'' +
                ", byInput='" + byInput + '\'' +
                ", timeSelect='" + timeSelect + '\'' +
                ", timeList=" + timeList +
                '}';
    }
}
