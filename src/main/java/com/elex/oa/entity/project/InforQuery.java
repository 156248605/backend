package com.elex.oa.entity.project;

import java.util.List;

public class InforQuery {
    private String codeSelect; //项目编号相关搜索
    private String codeInput;
    private String nameSelect; //项目名称
    private String nameInput;
    private String deptSelect; //立项部门
    private String deptInput;
    private String sourceSelect; //项目来源
    private List<String> sourceList;
    private String businessSelect; //商务经理
    private String businessInput;
    private String typeSelect; //项目类型
    private List<String> typeList;
    private String deliverySelect; //交付经理
    private String deliveryInput;
    private String statusSelect; //项目状态
    private List<String> statusList;
    private String phaseSelect; //项目阶段
    private String phaseInput;
    private String type; //查询类别
    private String name;  //查询人

    public InforQuery() {
    }

    public InforQuery(String codeSelect, String codeInput, String nameSelect, String nameInput, String deptSelect, String deptInput, String sourceSelect, List<String> sourceList, String businessSelect, String businessInput, String typeSelect, List<String> typeList, String deliverySelect, String deliveryInput, String statusSelect, List<String> statusList, String phaseSelect, String phaseInput, String type, String name) {
        this.codeSelect = codeSelect;
        this.codeInput = codeInput;
        this.nameSelect = nameSelect;
        this.nameInput = nameInput;
        this.deptSelect = deptSelect;
        this.deptInput = deptInput;
        this.sourceSelect = sourceSelect;
        this.sourceList = sourceList;
        this.businessSelect = businessSelect;
        this.businessInput = businessInput;
        this.typeSelect = typeSelect;
        this.typeList = typeList;
        this.deliverySelect = deliverySelect;
        this.deliveryInput = deliveryInput;
        this.statusSelect = statusSelect;
        this.statusList = statusList;
        this.phaseSelect = phaseSelect;
        this.phaseInput = phaseInput;
        this.type = type;
        this.name = name;
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

    public String getDeptSelect() {
        return deptSelect;
    }

    public void setDeptSelect(String deptSelect) {
        this.deptSelect = deptSelect;
    }

    public String getDeptInput() {
        return deptInput;
    }

    public void setDeptInput(String deptInput) {
        this.deptInput = deptInput;
    }

    public String getSourceSelect() {
        return sourceSelect;
    }

    public void setSourceSelect(String sourceSelect) {
        this.sourceSelect = sourceSelect;
    }

    public List<String> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<String> sourceList) {
        this.sourceList = sourceList;
    }

    public String getBusinessSelect() {
        return businessSelect;
    }

    public void setBusinessSelect(String businessSelect) {
        this.businessSelect = businessSelect;
    }

    public String getBusinessInput() {
        return businessInput;
    }

    public void setBusinessInput(String businessInput) {
        this.businessInput = businessInput;
    }

    public String getTypeSelect() {
        return typeSelect;
    }

    public void setTypeSelect(String typeSelect) {
        this.typeSelect = typeSelect;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public String getDeliverySelect() {
        return deliverySelect;
    }

    public void setDeliverySelect(String deliverySelect) {
        this.deliverySelect = deliverySelect;
    }

    public String getDeliveryInput() {
        return deliveryInput;
    }

    public void setDeliveryInput(String deliveryInput) {
        this.deliveryInput = deliveryInput;
    }

    public String getStatusSelect() {
        return statusSelect;
    }

    public void setStatusSelect(String statusSelect) {
        this.statusSelect = statusSelect;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getPhaseSelect() {
        return phaseSelect;
    }

    public void setPhaseSelect(String phaseSelect) {
        this.phaseSelect = phaseSelect;
    }

    public String getPhaseInput() {
        return phaseInput;
    }

    public void setPhaseInput(String phaseInput) {
        this.phaseInput = phaseInput;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InforQuery{" +
                "codeSelect='" + codeSelect + '\'' +
                ", codeInput='" + codeInput + '\'' +
                ", nameSelect='" + nameSelect + '\'' +
                ", nameInput='" + nameInput + '\'' +
                ", deptSelect='" + deptSelect + '\'' +
                ", deptInput='" + deptInput + '\'' +
                ", sourceSelect='" + sourceSelect + '\'' +
                ", sourceList=" + sourceList +
                ", businessSelect='" + businessSelect + '\'' +
                ", businessInput='" + businessInput + '\'' +
                ", typeSelect='" + typeSelect + '\'' +
                ", typeList=" + typeList +
                ", deliverySelect='" + deliverySelect + '\'' +
                ", deliveryInput='" + deliveryInput + '\'' +
                ", statusSelect='" + statusSelect + '\'' +
                ", statusList=" + statusList +
                ", phaseSelect='" + phaseSelect + '\'' +
                ", phaseInput='" + phaseInput + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
