package com.elex.oa.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotEmpty;

@Table(
        name = "OS_RANK_TYPE"
)

public class OsRankType {

    @Id
    @Column(
            name = "ID_"
    )
    private String id;

    @Column(
            name = "NAME_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    private String name;

    @Column(
            name = "KEY_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String key;

    @Column(
            name = "LEVEL_"
    )
    private Integer level;

    @ManyToOne
    @JoinColumn(
            name = "DIM_ID_"
    )
    private OsDimension osDimension;

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

    public OsRankType() {
    }

    public OsRankType(String in_id) {
        this.setId(in_id);
    }

    @JsonBackReference
    public OsDimension getOsDimension() {
        return this.osDimension;
    }

    @JsonBackReference
    public void setOsDimension(OsDimension in_osDimension) {
        this.osDimension = in_osDimension;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String aValue) {
        this.id = aValue;
    }

    public String getDimId() {
        return this.getOsDimension() == null?null:this.getOsDimension().getDimId();
    }

    public void setDimId(String aValue) {
        if(aValue == null) {
            this.osDimension = null;
        } else if(this.osDimension == null) {
            this.osDimension = new OsDimension(aValue);
        } else {
            this.osDimension.setDimId(aValue);
        }

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

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer aValue) {
        this.level = aValue;
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
    


}
