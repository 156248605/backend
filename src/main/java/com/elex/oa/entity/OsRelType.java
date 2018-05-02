package com.elex.oa.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Table(
        name = "OS_REL_TYPE"
)
@JsonIgnoreProperties({"dim1", "dim2"})
public class OsRelType {
    public static final String REL_TYPE_USER_USER = "USER-USER";
    public static final String REL_TYPE_GROUP_GROUP = "GROUP-GROUP";
    public static final String REL_TYPE_GROUP_USER = "GROUP-USER";
    public static final String REL_CAT_GROUP_USER_BELONG = "GROUP-USER-BELONG";
    public static final String REL_CAT_GROUP_USER_BELONG_ID = "1";
    public static final String REL_CAT_GROUP_USER_LEADER = "GROUP-USER-LEADER";
    public static final String REL_CAT_GROUP_USER_LEADER_ID = "2";
    public static final String REL_CAT_USER_UP_LOWER = "USER-UP-LOWER";
    public static final String REL_CAT_USER_UP_LOWER_ID = "3";
    public static final String REL_CAT_DEP_POS = "GROUP-DEP-POS";
    public static final String REL_CAT_DEP_POS_ID = "4";
    public static final String CONST_TYPE_ONE_ONE = "ONE-ONE";
    public static final String CONST_TYPE_ONE_MANY = "ONE-MANY";
    public static final String CONST_TYPE_MANY_ONE = "MANY-ONE";
    public static final String CONST_TYPE_MANY_MANY = "MANY-MANY";
    @Id
    @Column(
            name = "ID_"
    )
    protected String id;
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String name;

    @Column(
            name = "KEY_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    protected String key;

    @Column(
            name = "REL_TYPE_"
    )
    @Size(
            max = 40
    )
    @NotEmpty
    protected String relType;

    @Column(
            name = "CONST_TYPE_"
    )
    @Size(
            max = 40
    )
    @NotEmpty
    protected String constType;

    @Column(
            name = "PARTY1_"
    )
    @Size(
            max = 128
    )
    @NotEmpty
    protected String party1;

    @Column(
            name = "PARTY2_"
    )
    @Size(
            max = 128
    )
    @NotEmpty
    protected String party2;

    @Column(
            name = "DIM_ID1_"
    )
    protected String dim1;

    @Column(
            name = "DIM_ID2_"
    )
    protected String dim2;

    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 40
    )
    @NotEmpty
    protected String status;

    @Column(
            name = "IS_SYSTEM_"
    )
    @Size(
            max = 10
    )
    @NotEmpty
    protected String isSystem;

    @Column(
            name = "IS_DEFAULT_"
    )
    @Size(
            max = 10
    )
    @NotEmpty
    protected String isDefault;

    @Column(
            name = "IS_TWO_WAY_"
    )
    @Size(
            max = 10
    )
    @NotEmpty
    protected String isTwoWay;

    @Column(
            name = "MEMO_"
    )
    @Size(
            max = 255
    )
    protected String memo;

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

    public OsRelType() {
    }

    public OsRelType(String in_id) {
        this.setId(in_id);
    }


    public String getRelTypeName() {
        return "USER-USER".equals(this.relType)?"用户与用户关系":("GROUP-GROUP".equals(this.relType)?"用户组与用户组关系":("GROUP-USER".equals(this.relType)?"用户组与用户关系":""));
    }

    public String getId() {
        return this.id;
    }

    public void setId(String aValue) {
        this.id = aValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String aValue) {
        this.name = aValue;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String aValue) {
        this.key = aValue;
    }

    public String getRelType() {
        return this.relType;
    }

    public void setRelType(String aValue) {
        this.relType = aValue;
    }

    public String getConstType() {
        return this.constType;
    }

    public void setConstType(String aValue) {
        this.constType = aValue;
    }

    public String getParty1() {
        return this.party1;
    }

    public void setParty1(String aValue) {
        this.party1 = aValue;
    }

    public String getParty2() {
        return this.party2;
    }

    public void setParty2(String aValue) {
        this.party2 = aValue;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String aValue) {
        this.status = aValue;
    }

    public String getIsSystem() {
        return this.isSystem;
    }

    public void setIsSystem(String aValue) {
        this.isSystem = aValue;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String aValue) {
        this.isDefault = aValue;
    }

    public String getIsTwoWay() {
        return this.isTwoWay;
    }

    public void setIsTwoWay(String aValue) {
        this.isTwoWay = aValue;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String aValue) {
        this.memo = aValue;
    }

    public String getIdentifyLabel() {
        return this.name;
    }

    public Serializable getPkId() {
        return this.id;
    }

    public void setPkId(Serializable pkId) {
        this.id = (String)pkId;
    }

    public boolean equals(Object object) {
        if(!(object instanceof OsRelType)) {
            return false;
        } else {
            OsRelType rhs = (OsRelType)object;
            return (new EqualsBuilder()).append(this.id, rhs.id).append(this.name, rhs.name).append(this.key, rhs.key).append(this.relType, rhs.relType).append(this.constType, rhs.constType).append(this.party1, rhs.party1).append(this.party2, rhs.party2).append(this.status, rhs.status).append(this.isSystem, rhs.isSystem).append(this.isDefault, rhs.isDefault).append(this.isTwoWay, rhs.isTwoWay).append(this.memo, rhs.memo).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.tenantId, rhs.tenantId).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

    public String getDim1() {
        return dim1;
    }

    public void setDim1(String dim1) {
        this.dim1 = dim1;
    }

    public String getDim2() {
        return dim2;
    }

    public void setDim2(String dim2) {
        this.dim2 = dim2;
    }
}

