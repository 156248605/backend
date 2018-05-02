package com.elex.oa.entity.bo;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 *@author hugo.zhao
 *@since 2018/4/17 17:26
*/
@Table(
        name = "SYS_BO_DEFINITION"
)
public class SysBoDef{
    public static final String VERSION_BASE = "base";
    public static final String VERSION_NEW = "new";
    public static final String VERSION_DIFF = "diff";
    public static final String GEN_MODE_CREATE = "create";
    public static final String GEN_MODE_FORM = "form";
    public static final String BO_YES = "yes";
    public static final String BO_NO = "no";
    @Id
    @Column(
            name = "ID_"
    )
    private String id;
    @Column(
            name = "NAME_"
    )
    private String name;

    @Column(
            name = "ALAIS_"
    )
    private String alais;

    @Column(
            name = "COMMENT_"
    )
    private String comment;

    @Column(
            name = "SUPPORT_DB_"
    )
    private String supportDb;

    @Column(
            name = "GEN_MODE_"
    )
    private String genMode;

    @Column(
            name = "TREE_ID_"
    )
    private String treeId;

    @Column(
            name = "OPINION_DEF_"
    )
    private String opinionDef;

 /*   @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "sysBoDef",
            fetch = FetchType.LAZY
    )
    private Set<SysBoRelation> sysBoRelations = new HashSet();*/
    @Transient
    private SysBoEnt SysBoEnt;

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

    public SysBoDef() {
    }

    public SysBoDef(String in_id) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAlais(String alais) {
        this.alais = alais;
    }

    public String getAlais() {
        return this.alais;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    public void setSupportDb(String supportDb) {
        this.supportDb = supportDb;
    }

    public String getSupportDb() {
        return this.supportDb;
    }

    public void setGenMode(String genMode) {
        this.genMode = genMode;
    }

    public String getGenMode() {
        return this.genMode;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getTreeId() {
        return this.treeId;
    }

 /*   public Set<SysBoRelation> getSysBoRelation() {
        return this.sysBoRelations;
    }

    public void setSysBoRelation(Set<SysBoRelation> in_sysBoRelation) {
        this.sysBoRelations = in_sysBoRelation;
    }*/

    public SysBoEnt getSysBoEnt() {
        return this.SysBoEnt;
    }

    public void setSysBoEnt(SysBoEnt sysBoEnt) {
        this.SysBoEnt = sysBoEnt;
    }

    public String getOpinionDef() {
        return this.opinionDef;
    }

    public void setOpinionDef(String opinionDef) {
        this.opinionDef = opinionDef;
    }

    public boolean equals(Object object) {
        if(!(object instanceof SysBoDef)) {
            return false;
        } else {
            SysBoDef rhs = (SysBoDef)object;
            return (new EqualsBuilder()).append(this.id, rhs.id).append(this.name, rhs.name).append(this.alais, rhs.alais).append(this.comment, rhs.comment).append(this.supportDb, rhs.supportDb).append(this.genMode, rhs.genMode).append(this.treeId, rhs.treeId).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.id).append(this.name).append(this.alais).append(this.comment).append(this.supportDb).append(this.genMode).append(this.treeId).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("id", this.id).append("name", this.name).append("alais", this.alais).append("comment", this.comment).append("supportDb", this.supportDb).append("genMode", this.genMode).append("treeId", this.treeId).toString();
    }
}

