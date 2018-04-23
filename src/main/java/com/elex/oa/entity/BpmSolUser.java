package com.elex.oa.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
@Table(
        name = "BPM_SOL_USER"
)
public class BpmSolUser {
    public static final String LOGIC_AND = "AND";
    public static final String LOGIC_OR = "OR";
    public static final String LOGIC_NOT = "NOT";
    @Id
    @Column(
            name = "ID_"
    )
    private String id;
    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 64
    )
    private String actDefId;
    @Column(
            name = "NODE_ID_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    private String nodeId;

    @Column(
            name = "NODE_NAME_"
    )
    @Size(
            max = 255
    )
    private String nodeName;

    @Column(
            name = "USER_TYPE_"
    )
    @Size(
            max = 50
    )
    @NotEmpty
    private String userType;

    @Column(
            name = "USER_TYPE_NAME_"
    )
    @Size(
            max = 100
    )
    private String userTypeName;

    @Column(
            name = "CONFIG_DESCP_"
    )
    @Size(
            max = 512
    )
    private String configDescp;

    @Column(
            name = "CONFIG_"
    )
    @Size(
            max = 512
    )
    private String config;

    @Column(
            name = "IS_CAL_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    private String isCal;

    @Column(
            name = "CAL_LOGIC_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    private String calLogic;

    @Column(
            name = "SN_"
    )
    private Integer sn;

    @Column(
            name = "SOL_ID_"
    )
    private String solId;

    @Column(
            name = "CATEGORY_"
    )
    private String category;

    @Column(
            name = "GROUP_ID_"
    )
    private String groupId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

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

    @Column(

            name = "TENANT_ID_"
    )
    private String tenantId;

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

    public String getActDefId() {
        return this.actDefId;
    }

    public void setActDefId(String actDefId) {
        this.actDefId = actDefId;
    }

    public BpmSolUser() {
    }

    public BpmSolUser(String in_id) {
        this.setId(in_id);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String aValue) {
        this.id = aValue;
    }



    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String aValue) {
        this.nodeId = aValue;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String aValue) {
        this.nodeName = aValue;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String aValue) {
        this.userType = aValue;
    }

    public String getUserTypeName() {
        return this.userTypeName;
    }

    public void setUserTypeName(String aValue) {
        this.userTypeName = aValue;
    }

    public String getConfigDescp() {
        return this.configDescp;
    }

    public void setConfigDescp(String aValue) {
        this.configDescp = aValue;
    }

    public String getConfig() {
        return this.config;
    }

    public void setConfig(String aValue) {
        this.config = aValue;
    }

    public String getIsCal() {
        return this.isCal;
    }

    public void setIsCal(String aValue) {
        this.isCal = aValue;
    }

    public String getCalLogic() {
        return this.calLogic;
    }

    public void setCalLogic(String aValue) {
        this.calLogic = aValue;
    }

    public Integer getSn() {
        return this.sn;
    }

    public void setSn(Integer aValue) {
        this.sn = aValue;
    }

    public String getIdentifyLabel() {
        return this.id;
    }

    public Serializable getPkId() {
        return this.id;
    }

    public void setPkId(Serializable pkId) {
        this.id = (String)pkId;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public String getSolId() {
        return solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }
}
