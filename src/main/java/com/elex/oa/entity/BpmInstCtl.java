package com.elex.oa.entity;
import com.elex.oa.core.entity.BaseTenantEntity;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import com.elex.oa.entity.activiti.BpmInst;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Table(
        name = "BPM_INST_CTL"
)
/*@TableDefine(
        title = ""
)*/
public class BpmInstCtl extends BaseTenantEntity {
    public static final String CTL_TYPE_FILE = "FILE";
    public static final String CTL_TYPE_READ = "READ";
    public static final String CTL_RIGHT_EDIT = "EDIT";
    public static final String CTL_RIGHT_DOWN = "DOWN";
    public static final String CTL_RIGHT_PRINT = "PRINT";

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    /*    @FieldDefine(
                title = "PKID"
        )*/
    @Id
    @Column(
            name = "CTL_ID"
    )
    protected String ctlId;
/*    @FieldDefine(
            title = "${column.remarks}"
    )*/
    @Column(
            name = "TYPE_"
    )
    @Size(
            max = 50
    )
    protected String type;
/*    @FieldDefine(
            title = "${column.remarks}"
    )*/
    @Column(
            name = "RIGHT_"
    )
    @Size(
            max = 60
    )
    protected String right;
/*    @FieldDefine(
            title = "${column.remarks}"
    )*/
    @Column(
            name = "ALLOW_ATTEND_"
    )
    @Size(
            max = 20
    )
    protected String allowAttend;
/*    @FieldDefine(
            title = "${column.remarks}"
    )*/
    @Column(
            name = "ALLOW_STARTOR_"
    )
    @Size(
            max = 20
    )
    protected String allowStartor;
/*    @FieldDefine(
            title = "${column.remarks}"
    )*/
    @Column(
            name = "GROUP_IDS_"
    )
    @Size(
            max = 4000
    )
    protected String groupIds;
/*    @FieldDefine(
            title = "${column.remarks}"
    )*/
    @Column(
            name = "USER_IDS_"
    )
    @Size(
            max = 4000
    )
    protected String userIds;
/*    @FieldDefine(
            title = ""
    )*/
/*    @ManyToOne
    @JoinColumn(
            name = "INST_ID_"
    )
    protected BpmInst bpmInst;*/
    @Transient
    protected BpmInst bpmInst;
    @Column(
            name = "INST_ID_"
    )
    protected String instId;

    public BpmInstCtl() {
    }

    public BpmInstCtl(String in_ctlId) {
        this.setCtlId(in_ctlId);
    }

    public BpmInst getBpmInst() {
        return this.bpmInst;
    }

    public void setBpmInst(BpmInst in_bpmInst) {
        this.bpmInst = in_bpmInst;
    }

    public String getCtlId() {
        return this.ctlId;
    }

    public void setCtlId(String aValue) {
        this.ctlId = aValue;
    }

 /*   public String getInstId() {
        return this.getBpmInst() == null?null:this.getBpmInst().getInstId();
    }

    public void setInstId(String aValue) {
        if(aValue == null) {
            this.bpmInst = null;
        } else if(this.bpmInst == null) {
            this.bpmInst = new BpmInst(aValue);
        } else {
            this.bpmInst.setInstId(aValue);
        }

    }*/

    public String getType() {
        return this.type;
    }

    public void setType(String aValue) {
        this.type = aValue;
    }

    public String getRight() {
        return this.right;
    }

    public void setRight(String aValue) {
        this.right = aValue;
    }

    public String getAllowAttend() {
        return this.allowAttend;
    }

    public void setAllowAttend(String aValue) {
        this.allowAttend = aValue;
    }

    public String getAllowStartor() {
        return this.allowStartor;
    }

    public void setAllowStartor(String aValue) {
        this.allowStartor = aValue;
    }

    public String getGroupIds() {
        return this.groupIds;
    }

    public void setGroupIds(String aValue) {
        this.groupIds = aValue;
    }

    public String getUserIds() {
        return this.userIds;
    }

    public void setUserIds(String aValue) {
        this.userIds = aValue;
    }

    public String getIdentifyLabel() {
        return this.ctlId;
    }

    public Serializable getPkId() {
        return this.ctlId;
    }

    public void setPkId(Serializable pkId) {
        this.ctlId = (String)pkId;
    }

    public boolean equals(Object object) {
        if(!(object instanceof BpmInstCtl)) {
            return false;
        } else {
            BpmInstCtl rhs = (BpmInstCtl)object;
            return (new EqualsBuilder()).append(this.ctlId, rhs.ctlId).append(this.type, rhs.type).append(this.right, rhs.right).append(this.allowAttend, rhs.allowAttend).append(this.allowStartor, rhs.allowStartor).append(this.groupIds, rhs.groupIds).append(this.userIds, rhs.userIds).append(this.updateTime, rhs.updateTime).append(this.updateBy, rhs.updateBy).append(this.createTime, rhs.createTime).append(this.createBy, rhs.createBy).append(this.tenantId, rhs.tenantId).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.ctlId).append(this.type).append(this.right).append(this.allowAttend).append(this.allowStartor).append(this.groupIds).append(this.userIds).append(this.updateTime).append(this.updateBy).append(this.createTime).append(this.createBy).append(this.tenantId).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("ctlId", this.ctlId).append("type", this.type).append("right", this.right).append("allowAttend", this.allowAttend).append("allowStartor", this.allowStartor).append("groupIds", this.groupIds).append("userIds", this.userIds).append("updateTime", this.updateTime).append("updateBy", this.updateBy).append("createTime", this.createTime).append("createBy", this.createBy).append("tenantId", this.tenantId).toString();
    }
}