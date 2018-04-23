package com.elex.oa.entity.activiti;

import com.elex.oa.entity.BpmSolution;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *@author hugo.zhao
 *@since 2018/4/12 14:39
*/
@Table(
        name = "BPM_SOL_VAR"
)
public class BpmSolVar {
    public static final String SCOPE_PROCESS = "_PROCESS";
    public static final String TYPE_OBJECT = "Object";
    public static final String TYPE_MAP = "Map";
    public static final String TYPE_COLLECTION = "Collection";
    public static final String TYPE_DATE = "Date";

    public String getSolId() {
        return SolId;
    }

    public void setSolId(String solId) {
        SolId = solId;
    }

    public static final String TYPE_NUMBER = "Number";
    public static final String TYPE_STRING = "String";
    @Id
    @Column(
            name = "VAR_ID_"
    )
    private String varId;
    @Column(
            name = "KEY_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    private String key;
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    private String name;

    @Column(
            name = "TYPE_"
    )
    @Size(
            max = 50
    )
    @NotEmpty
    private String type;

    @Column(
            name = "SCOPE_"
    )
    @Size(
            max = 128
    )
    @NotEmpty
    private String scope;

    @Column(
            name = "NODE_NAME_"
    )
    @Size(
            max = 255
    )
    private String nodeName;

    @Column(
            name = "DEF_VAL_"
    )
    @Size(
            max = 100
    )
    private String defVal;

    @Column(
            name = "IS_REQ_"
    )
    @Size(
            max = 20
    )
    private String isReq;

    @Column(
            name = "EXPRESS_"
    )
    @Size(
            max = 512
    )
    private String express;

    @Column(
            name = "SN_"
    )
    private Integer sn;

    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 64
    )
    private String actDefId;

    @Column(
            name = "SOL_ID_"
    )
    private String SolId;
    @Transient
    private BpmSolution bpmSolution;
    @Transient
    private String parentId;
    @Transient
    private String fieldGroup;

    @Column(
            name = "FORM_FIELD_"
    )
    @Size(
            max = 100
    )
    private String formField;

    public BpmSolVar() {
    }

    public BpmSolVar(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public BpmSolVar(String name, String key, String fieldGroup) {
        this.name = name;
        this.key = key;
        this.fieldGroup = fieldGroup;
    }

    public BpmSolVar(String name, String key, String type, String scope) {
        this.name = name;
        this.key = key;
        this.type = type;
        this.scope = scope;
    }

    public BpmSolVar(String in_varId) {
        this.setVarId(in_varId);
    }

    public BpmSolution getBpmSolution() {
        return this.bpmSolution;
    }

    public void setBpmSolution(BpmSolution in_bpmSolution) {
        this.bpmSolution = in_bpmSolution;
    }

    public String getActDefId() {
        return this.actDefId;
    }

    public void setActDefId(String actDefId) {
        this.actDefId = actDefId;
    }

    public String getVarId() {
        return this.varId;
    }

    public String getFormField() {
        return this.formField;
    }

    public void setFormField(String formField) {
        this.formField = formField;
    }

    public void setVarId(String aValue) {
        this.varId = aValue;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String aValue) {
        this.key = aValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String aValue) {
        this.name = aValue;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String aValue) {
        this.type = aValue;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String aValue) {
        this.scope = aValue;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String aValue) {
        this.nodeName = aValue;
    }

    public String getDefVal() {
        return this.defVal;
    }

    public void setDefVal(String aValue) {
        this.defVal = aValue;
    }

    public String getExpress() {
        return this.express;
    }

    public void setExpress(String aValue) {
        this.express = aValue;
    }

    public Integer getSn() {
        return this.sn;
    }

    public void setSn(Integer aValue) {
        this.sn = aValue;
    }

    public String getIdentifyLabel() {
        return this.varId;
    }

    public String getIsReq() {
        return this.isReq;
    }

    public void setIsReq(String isReq) {
        this.isReq = isReq;
    }

    public String getFieldGroup() {
        return this.fieldGroup;
    }

    public void setFieldGroup(String fieldGroup) {
        this.fieldGroup = fieldGroup;
    }

}
