package com.elex.oa.entity;

/**
 *@author hugo.zhao
 *@since 2018/4/18 10:26
*/
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.elex.oa.util.EncryptUtil;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import java.io.Serializable;
@Table(
        name = "SYS_PROPERTIES"
)
public class SysProperties {
    @Id
    @Column(
            name = "PRO_ID_"
    )
    private String proId;
    @Column(
            name = "NAME_"
    )
    private String name;
    @Column(
            name = "ALIAS_"
    )
    private String alias;
    @Column(
            name = "GLOBAL_"
    )
    private String global;
    @Column(
            name = "ENCRYPT_"
    )
    private String encrypt;
    @Column(
            name = "VALUE_"
    )
    private String value;
    @Column(
            name = "CATEGORY_"
    )
    private String category;
    @Column(
            name = "DESCRIPTION_"
    )
    private String description;

    public SysProperties() {
    }

    public SysProperties(String in_id) {
        this.setPkId(in_id);
    }

    public String getIdentifyLabel() {
        return this.proId;
    }

    public Serializable getPkId() {
        return this.proId;
    }

    public void setPkId(Serializable pkId) {
        this.proId = (String)pkId;
    }

    public String getProId() {
        return this.proId;
    }

    public void setProId(String aValue) {
        this.proId = aValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setGlobal(String global) {
        this.global = global;
    }

    public String getGlobal() {
        return this.global;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getEncrypt() {
        return this.encrypt;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getVal() throws Exception {
        return "YES".equals(this.encrypt)? EncryptUtil.decrypt(this.value):this.value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean equals(Object object) {
        if(!(object instanceof SysProperties)) {
            return false;
        } else {
            SysProperties rhs = (SysProperties)object;
            return (new EqualsBuilder()).append(this.proId, rhs.proId).append(this.name, rhs.name).append(this.alias, rhs.alias).append(this.global, rhs.global).append(this.encrypt, rhs.encrypt).append(this.value, rhs.value).append(this.category, rhs.category).append(this.description, rhs.description).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.proId).append(this.name).append(this.alias).append(this.global).append(this.encrypt).append(this.value).append(this.category).append(this.description).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("proId", this.proId).append("name", this.name).append("alias", this.alias).append("global", this.global).append("encrypt", this.encrypt).append("value", this.value).append("category", this.category).append("description", this.description).toString();
    }
}
