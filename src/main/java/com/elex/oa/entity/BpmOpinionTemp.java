package com.elex.oa.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Table(
        name = "BPM_OPINION_TEMP"
)
public class BpmOpinionTemp{
    public static final String TYPE_INST = "instId";
    public static final String TYPE_TASK = "taskId";
    @Id
    @Column(
            name = "ID_"
    )
    protected String id;

    @Column(
            name = "TYPE_"
    )
    protected String type;

    @Column(
            name = "INST_ID_"
    )
    protected String instId;

    @Column(
            name = "OPINION_"
    )
    protected String opinion;

    @Column(
            name = "ATTACHMENT_"
    )
    protected String attachment;

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

    public BpmOpinionTemp() {
    }

    public BpmOpinionTemp(String in_id) {
        this.setPkId(in_id);
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

    public String getId() {
        return this.id;
    }

    public void setId(String aValue) {
        this.id = aValue;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getInstId() {
        return this.instId;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getOpinion() {
        return this.opinion;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAttachment() {
        return this.attachment;
    }
}

