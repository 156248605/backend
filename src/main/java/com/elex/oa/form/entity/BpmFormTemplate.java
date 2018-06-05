package com.elex.oa.form.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


@Table(
        name = "BPM_FORM_TEMPLATE"
)
/*@TableDefine(
        title = "表单模版"
)*/
public class BpmFormTemplate{
 /*   @FieldDefine(
            title = "PKID"
    )*/
    @Id
    @Column(
            name = "ID_"
    )
    protected String id;
/*    @FieldDefine(
            title = "模版名称"
    )*/
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 50
    )
    protected String name;
/*    @FieldDefine(
            title = "别名"
    )*/
    @Column(
            name = "ALIAS_"
    )
    @Size(
            max = 50
    )
    protected String alias;
/*    @FieldDefine(
            title = "模版"
    )*/
    @Column(
            name = "TEMPLATE_"
    )
    @Size(
            max = 65535
    )
    protected String template;
/*    @FieldDefine(
            title = "模版类型 (pc,mobile)"
    )*/
    @Column(
            name = "TYPE_"
    )
    @Size(
            max = 50
    )
    protected String type;
/*    @FieldDefine(
            title = "初始添加的(1是,0否)"
    )*/
    @Column(
            name = "INIT_"
    )
    protected Integer init = Integer.valueOf(0);
/*    @FieldDefine(
            title = "分类"
    )*/
    @Column(
            name = "CATEGORY_"
    )
    @Size(
            max = 50
    )
    protected String category = "";

    public BpmFormTemplate() {
    }

    public BpmFormTemplate(String in_id) {
        this.setId(in_id);
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

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String aValue) {
        this.alias = aValue;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String aValue) {
        this.template = aValue;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String aValue) {
        this.type = aValue;
    }

    public Integer getInit() {
        return this.init;
    }

    public void setInit(Integer aValue) {
        this.init = aValue;
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean equals(Object object) {
        if(!(object instanceof BpmFormTemplate)) {
            return false;
        } else {
            BpmFormTemplate rhs = (BpmFormTemplate)object;
            return (new EqualsBuilder()).append(this.id, rhs.id).append(this.name, rhs.name).append(this.alias, rhs.alias).append(this.template, rhs.template).append(this.type, rhs.type).append(this.init, rhs.init).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.id).append(this.name).append(this.alias).append(this.template).append(this.type).append(this.init).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("id", this.id).append("name", this.name).append("alias", this.alias).append("template", this.template).append("type", this.type).append("init", this.init).toString();
    }
}

