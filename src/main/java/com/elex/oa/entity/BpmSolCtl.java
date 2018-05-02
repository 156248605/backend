package com.elex.oa.entity;

/**
 *@author hugo.zhao
 *@since 2018/4/25 10:54
*/
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Table(
        name = "bpm_sol_ctl"
)
public class BpmSolCtl {
    public static final String CTL_TYPE_FILE = "FILE";
    public static final String CTL_TYPE_READ = "READ";
    public static final String CTL_RIGHT_EDIT = "EDIT";
    public static final String CTL_RIGHT_DOWN = "DOWN";
    public static final String CTL_RIGHT_PRINT = "PRINT";
    @Id
    @Column(
            name = "RIGHT_ID_"
    )
    private String rightId;
    @Column(
            name = "USER_IDS_"
    )
    @Size(
            max = 4000
    )
    private String userIds;
    @Column(
            name = "GROUP_IDS_"
    )
    @Size(
            max = 4000
    )
    private String groupIds;
    @Column(
            name = "ALLOW_STARTOR_"
    )
    @Size(
            max = 20
    )
    private String allowStartor;

    @Column(
            name = "ALLOW_ATTEND_"
    )
    @Size(
            max = 20
    )
    private String allowAttend;

    @Column(
            name = "RIGHT_"
    )
    @Size(
            max = 60
    )
    private String right;
    @Column(
            name = "TYPE_"
    )
    @Size(
            max = 50
    )
    private String type;
    @Column(
            name = "SOL_ID_"
    )
    private String solId;
    @Transient
    private BpmSolution bpmSolution;

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

    public BpmSolCtl() {
    }

    public BpmSolCtl(String in_rightId) {
        this.setRightId(in_rightId);
    }

    public BpmSolution getBpmSolution() {
        return this.bpmSolution;
    }

    public void setBpmSolution(BpmSolution in_bpmSolution) {
        this.bpmSolution = in_bpmSolution;
    }

    public String getRightId() {
        return this.rightId;
    }

    public void setRightId(String aValue) {
        this.rightId = aValue;
    }

    public String getSolId() {
        return solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    public String getUserIds() {
        return this.userIds;
    }

    public void setUserIds(String aValue) {
        this.userIds = aValue;
    }

    public String getGroupIds() {
        return this.groupIds;
    }

    public void setGroupIds(String aValue) {
        this.groupIds = aValue;
    }

    public String getAllowStartor() {
        return this.allowStartor;
    }

    public void setAllowStartor(String aValue) {
        this.allowStartor = aValue;
    }

    public String getAllowAttend() {
        return this.allowAttend;
    }

    public void setAllowAttend(String aValue) {
        this.allowAttend = aValue;
    }

    public String getRight() {
        return this.right;
    }

    public void setRight(String aValue) {
        this.right = aValue;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String aValue) {
        this.type = aValue;
    }

    public String getIdentifyLabel() {
        return this.rightId;
    }

    public Serializable getPkId() {
        return this.rightId;
    }

    public void setPkId(Serializable pkId) {
        this.rightId = (String)pkId;
    }

    public boolean equals(Object object) {
        if(!(object instanceof BpmSolCtl)) {
            return false;
        } else {
            BpmSolCtl rhs = (BpmSolCtl)object;
            return (new EqualsBuilder()).append(this.rightId, rhs.rightId).append(this.userIds, rhs.userIds).append(this.groupIds, rhs.groupIds).append(this.allowStartor, rhs.allowStartor).append(this.allowAttend, rhs.allowAttend).append(this.right, rhs.right).append(this.type, rhs.type).append(this.tenantId, rhs.tenantId).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

}
