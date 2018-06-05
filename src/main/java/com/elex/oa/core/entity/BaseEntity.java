package com.elex.oa.core.entity;
import com.elex.oa.core.constants.MBoolean;
import com.elex.oa.json.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    protected static final long serialVersionUID = 1L;
/*    @FieldDefine(
            title = "创建时间",
            group = "操作信息",
            sortable = MBoolean.TRUE,
            defaultCol = MBoolean.TRUE
    )*/
    @Column(
            name = "CREATE_TIME_"
    )
    /*@XStreamConverter(DateConverter.class)*/
    protected Date createTime;
/*    @FieldDefine(
            title = "更新时间",
            group = "操作信息",
            sortable = MBoolean.TRUE,
            defaultCol = MBoolean.TRUE
    )*/
    @Column(
            name = "UPDATE_TIME_"
    )
   /* @XStreamConverter(DateConverter.class)*/
    protected Date updateTime;
/*    @FieldDefine(
            title = "创建人",
            group = "操作信息",
            sortable = MBoolean.TRUE,
            defaultCol = MBoolean.TRUE
    )*/
    @Column(
            name = "CREATE_BY_"
    )
    @Size(
            max = 64
    )
    protected String createBy;
/*    @FieldDefine(
            title = "更新人",
            group = "操作信息",
            sortable = MBoolean.TRUE,
            defaultCol = MBoolean.TRUE
    )*/
    @Column(
            name = "UPDATE_BY_"
    )
    @Size(
            max = 64
    )
    protected String updateBy;

    public BaseEntity() {
    }

    public abstract String getIdentifyLabel();

    public abstract Serializable getPkId();

    public abstract void setPkId(Serializable var1);

    @JsonSerialize(
            using = JsonDateSerializer.class
    )
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonSerialize(
            using = JsonDateSerializer.class
    )
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}