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
 *@since 2018/4/12 11:22
*/
@Table(
        name = "BPM_NODE_SET"
)
public class BpmNodeSet {
    public String getSolId() {
        return SolId;
    }

    public void setSolId(String solId) {
        SolId = solId;
    }

    @Id
    @Column(
            name = "SET_ID_"
    )
    private String setId;

    @Column(
            name = "NODE_ID_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    private String nodeId;
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 255
    )
    private String name;

    @Column(
            name = "DESCP_"
    )
    @Size(
            max = 255
    )
    private String descp;

    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 64
    )
    private String actDefId;

    @Column(
            name = "NODE_TYPE_"
    )
    @Size(
            max = 100
    )
    @NotEmpty
    private String nodeType;

    @Column(
            name = "SETTINGS_"
    )
    @Size(
            max = 65535
    )
    private String Settings;

    @Column(
            name = "NODE_CHECK_TIP_"
    )
    @Size(
            max = 1024
    )
    private String nodeCheckTip;
    @Column(
            name = "SOL_ID_"
    )
    private String SolId;
    @Transient
    private BpmSolution bpmSolution;

    @Column(
            name = "CREATE_BY_"
    )
    private String createBy;
    @Column(
            name = "UPDATE_BY_"
    )
    private String updateBy;
    @Column(
            name = "CREATE_TIME_"

    )
    private  String createTime;

    @Column(
            name = "UPDATE_TIME_"
    )
    private  String updateTime;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public BpmNodeSet() {
    }

    public BpmNodeSet(String in_setId) {
        this.setSetId(in_setId);
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

    public String getSetId() {
        return this.setId;
    }

    public void setSetId(String aValue) {
        this.setId = aValue;
    }
    

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String aValue) {
        this.nodeId = aValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String aValue) {
        this.name = aValue;
    }

    public String getDescp() {
        return this.descp;
    }

    public void setDescp(String aValue) {
        this.descp = aValue;
    }

    public String getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(String aValue) {
        this.nodeType = aValue;
    }

    public String getNodeCheckTip() {
        return this.nodeCheckTip;
    }

    public void setNodeCheckTip(String nodeCheckTip) {
        this.nodeCheckTip = nodeCheckTip;
    }

    public String getIdentifyLabel() {
        return this.setId;
    }

    public String getSettings() {
        return Settings;
    }

    public void setSettings(String settings) {
        Settings = settings;
    }
}
