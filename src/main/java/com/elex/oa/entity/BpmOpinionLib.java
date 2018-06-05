package com.elex.oa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.elex.oa.core.entity.BaseTenantEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/*@Entity*/
@Table(
        name = "BPM_OPINION_LIB"
)
/*@TableDefine(
        title = "用户审批意见表"
)*/
public class BpmOpinionLib extends BaseTenantEntity {
    public static final String PUBLIC_OPINION = "0";
/*    @FieldDefine(
            title = "PKID"
    )*/
    @Id
    @Column(
            name = "OP_ID_"
    )
    protected String opId;
/*    @FieldDefine(
            title = "用户Id"
    )*/
    @Column(
            name = "USER_ID_"
    )
    @Size(
            max = 64
    )
    protected String userId;
/*    @FieldDefine(
            title = "审批意见正文"
    )*/
    @Column(
            name = "OP_TEXT_"
    )
    @Size(
            max = 512
    )
    protected String opText;

    public BpmOpinionLib() {
    }

    public BpmOpinionLib(String in_opId) {
        this.setOpId(in_opId);
    }

    public String getOpId() {
        return this.opId;
    }

    public void setOpId(String aValue) {
        this.opId = aValue;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String aValue) {
        this.userId = aValue;
    }

    public String getOpText() {
        return this.opText;
    }

    public void setOpText(String aValue) {
        this.opText = aValue;
    }

    public String getIdentifyLabel() {
        return this.opId;
    }

    public Serializable getPkId() {
        return this.opId;
    }

    public void setPkId(Serializable pkId) {
        this.opId = (String)pkId;
    }

    public boolean equals(Object object) {
        if(!(object instanceof BpmOpinionLib)) {
            return false;
        } else {
            BpmOpinionLib rhs = (BpmOpinionLib)object;
            return (new EqualsBuilder()).append(this.opId, rhs.opId).append(this.userId, rhs.userId).append(this.opText, rhs.opText).append(this.tenantId, rhs.tenantId).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.opId).append(this.userId).append(this.opText).append(this.tenantId).append(this.createBy).append(this.createTime).append(this.updateBy).append(this.updateTime).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("opId", this.opId).append("userId", this.userId).append("opText", this.opText).append("tenantId", this.tenantId).append("createBy", this.createBy).append("createTime", this.createTime).append("updateBy", this.updateBy).append("updateTime", this.updateTime).toString();
    }
}
