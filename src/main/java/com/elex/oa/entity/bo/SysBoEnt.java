package com.elex.oa.entity.bo;

/**
 *@author hugo.zhao
 *@since 2018/4/18 9:56
*/
import com.elex.oa.util.StringUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.elex.oa.util.DbUtil;
@Table(
        name = "SYS_BO_ENTITY"
)
public class SysBoEnt {
    public static final String RELATION_TYPE_MAIN = "main";
    public static final String RELATION_TYPE_SUB = "sub";
    public static final String SQL_FK = "REF_ID_";
    public static final String SQL_PATH = "PATH_";
    public static final String SQL_FK_STATEMENT = "#{fk}";
    public static final String SQL_PK = "ID_";
    public static final String COMPLEX_NAME = "_NAME";
    public static final String SUB_PRE = "SUB_";
    public static final String FIELD_USER = "CREATE_USER_ID_";
    public static final String FIELD_USER_NAME_ = "CREATE_USER_NAME_";
    public static final String FIELD_TENANT = "TENANT_ID_";
    public static final String FIELD_INST = "INST_ID_";
    public static final String FIELD_INST_STATUS_ = "INST_STATUS_";
    public static final String FIELD_GROUP = "CREATE_GROUP_ID_";
    public static final String FIELD_GROUP_NAME = "CREATE_GROUP_NAME_";
    public static final String FIELD_CREATE_TIME = "CREATE_TIME_";
    public static final String FIELD_CREATE_DATE = "CREATE_DATE_";
    public static final String FIELD_UPDTIME_TIME = "UPDATE_TIME_";
    public static final String FIELD_UPDTIME_DATE = "UPDATE_DATE_";
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
            name = "COMMENT_"
    )
    private String comment;
    @Column(
            name = "TABLE_NAME_"
    )
    private String tableName;
    @Column(
            name = "DS_NAME_"
    )
    private String dsName;
    @Column(
            name = "GEN_TABLE_"
    )
    private String genTable;
    @Column(
            name = "TREE_ID_"
    )
    private String treeId;
    @Column(
            name = "IS_REF_"
    )
    private int isRef = 0;
    @Column(
            name = "FORM_ALIAS_"
    )
    private String formAlias = "";
    @Column(
            name = "EXT_JSON_"
    )
    private String extJson = "";
    @Transient
    private String boDefId;
    @Transient
    private String relationType = "main";
    @Transient
    private List<SysBoAttr> sysBoAttrs = new ArrayList();
    @Transient
    private List<SysBoEnt> boEntList = new ArrayList();
    @Transient
    private String version = "new";
    @Transient
    private boolean hasConflict = false;


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

    public SysBoEnt() {
    }

    public SysBoEnt(String in_id) {
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

    public String getBoDefId() {
        return this.boDefId;
    }

    public void setBoDefId(String boDefId) {
        this.boDefId = boDefId;
    }

    public String getName() {
        return this.name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        if(StringUtil.isEmpty(this.tableName)) {
            String tbName = DbUtil.getTablePre() + this.name;
            return tbName;
        } else {
            return this.tableName;
        }
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getDsName() {
        return this.dsName;
    }

    public void setGenTable(String genTable) {
        this.genTable = genTable;
    }

    public String getGenTable() {
        return this.genTable;
    }

    public List<SysBoAttr> getSysBoAttrs() {
        return this.sysBoAttrs;
    }

    public void setSysBoAttrs(List<SysBoAttr> in_sysBoAttr) {
        this.sysBoAttrs = in_sysBoAttr;
    }

    public String getRelationType() {
        return this.relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public List<SysBoEnt> getBoEntList() {
        return this.boEntList;
    }

    public void setBoEntList(List<SysBoEnt> boEntList) {
        this.boEntList = boEntList;
    }

    public void clearSubBoEnt() {
        this.boEntList.clear();
    }

    public void addBoEnt(SysBoEnt boEnt) {
        boEnt.setRelationType("sub");
        this.boEntList.add(boEnt);
    }

    public void addBoAttr(SysBoAttr boAttr) {
        this.hasEqualName(boAttr);
        this.sysBoAttrs.add(boAttr);
    }

    private void hasEqualName(SysBoAttr boAttr) {
        String name = boAttr.getName();
        Iterator var3 = this.sysBoAttrs.iterator();

        SysBoAttr attr;
        do {
            if(!var3.hasNext()) {
                return;
            }

            attr = (SysBoAttr)var3.next();
        } while(!name.equalsIgnoreCase(attr.getName()));

        boAttr.setContain(true);
        this.setHasConflict(true);
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTreeId() {
        return this.treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public boolean isHasConflict() {
        return this.hasConflict;
    }

    public void setHasConflict(boolean hasConflict) {
        this.hasConflict = hasConflict;
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

    public String getExtJson() {
        return this.extJson;
    }

    public void setExtJson(String extJson) {
        this.extJson = extJson;
    }

    public boolean equals(Object object) {
        if(!(object instanceof SysBoEnt)) {
            return false;
        } else {
            SysBoEnt rhs = (SysBoEnt)object;
            return (new EqualsBuilder()).append(this.name, rhs.name).append(this.tableName, rhs.tableName).append(this.dsName, rhs.dsName).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.id).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("id", this.id).append("name", this.name).append("comment", this.comment).append("tableName", this.tableName).append("dsName", this.dsName).append("genTable", this.genTable).toString();
    }
}

