package com.elex.oa.entity.bo;

/**
 *@author hugo.zhao
 *@since 2018/4/18 13:39
*/
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Table(
        name = "SYS_BO_RELATION"
)
public class SysBoRelation {
    @Id
    @Column(
            name = "ID_"
    )
    private String id;
    @Column(
            name = "BO_ENTID_"
    )
    private String boEntid;
    @Column(
            name = "RELATION_TYPE_"
    )
    private String relationType;
    @ManyToOne
    @JoinColumn(
            name = "BO_DEFID_"
    )
    private SysBoDef sysBoDef;
    @Column(
            name = "IS_REF_"
    )
    private int isRef = 0;
    @Column(
            name = "FORM_ALIAS_"
    )
    private String formAlias = "";

    public SysBoRelation() {
    }

    public SysBoRelation(String in_id) {
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

    public void setBoEntid(String boEntid) {
        this.boEntid = boEntid;
    }

    public String getBoEntid() {
        return this.boEntid;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getRelationType() {
        return this.relationType;
    }

    public SysBoDef getSysBoDef() {
        return this.sysBoDef;
    }

    public void setSysBoDef(SysBoDef in_sysBoDef) {
        this.sysBoDef = in_sysBoDef;
    }

    public String getBoDefid() {
        return this.getSysBoDef() == null?null:this.getSysBoDef().getId();
    }

    public void setBoDefid(String aValue) {
        if(aValue == null) {
            this.sysBoDef = null;
        } else if(this.sysBoDef == null) {
            this.sysBoDef = new SysBoDef(aValue);
        } else {
            this.sysBoDef.setId(aValue);
        }

    }

    public int getIsRef() {
        return this.isRef;
    }

    public void setIsRef(int isRef) {
        this.isRef = isRef;
    }

    public String getFormAlias() {
        return this.formAlias;
    }

    public void setFormAlias(String formAlias) {
        this.formAlias = formAlias;
    }

    public boolean equals(Object object) {
        if(!(object instanceof SysBoRelation)) {
            return false;
        } else {
            SysBoRelation rhs = (SysBoRelation)object;
            return (new EqualsBuilder()).append(this.id, rhs.id).append(this.boEntid, rhs.boEntid).append(this.relationType, rhs.relationType).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.id).append(this.boEntid).append(this.relationType).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("id", this.id).append("boEntid", this.boEntid).append("relationType", this.relationType).toString();
    }
}

