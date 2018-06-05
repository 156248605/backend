package com.elex.oa.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.elex.oa.core.entity.BaseTenantEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
@Table(
        name = "BPM_INST_DATA"
)
public class BpmInstData extends BaseTenantEntity {
/*    @FieldDefine(
            title = "主键"
    )*/
    @Id
    @Column(
            name = "ID_"
    )
    protected String id;
/*    @FieldDefine(
            title = "业务对象ID"
    )*/
    @Column(
            name = "BO_DEF_ID_"
    )
    protected String boDefId;
/*    @FieldDefine(
            title = "实例ID_"
    )*/
    @Column(
            name = "INST_ID_"
    )
    protected String instId;
/*    @FieldDefine(
            title = "业务表主键"
    )*/
    @Column(
            name = "PK_"
    )
    protected String pk;

    public BpmInstData() {
    }

    public BpmInstData(String in_id) {
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

    public void setBoDefId(String boDefId) {
        this.boDefId = boDefId;
    }

    public String getBoDefId() {
        return this.boDefId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getInstId() {
        return this.instId;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getPk() {
        return this.pk;
    }

    public boolean equals(Object object) {
        if(!(object instanceof BpmInstData)) {
            return false;
        } else {
            BpmInstData rhs = (BpmInstData)object;
            return (new EqualsBuilder()).append(this.id, rhs.id).append(this.boDefId, rhs.boDefId).append(this.instId, rhs.instId).append(this.pk, rhs.pk).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.id).append(this.boDefId).append(this.instId).append(this.pk).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("id", this.id).append("boDefId", this.boDefId).append("instId", this.instId).append("pk", this.pk).toString();
    }
}