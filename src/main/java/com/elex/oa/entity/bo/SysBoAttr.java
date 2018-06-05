package com.elex.oa.entity.bo;

/**
 *@author hugo.zhao
 *@since 2018/4/18 10:05
*/
import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.elex.oa.core.entity.BaseTenantEntity;
import com.elex.oa.util.DbUtil;
import com.elex.oa.util.StringUtil;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
@Table(
        name = "SYS_BO_ATTR"
)
public class SysBoAttr extends BaseTenantEntity implements Cloneable  {

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
            name = "FIELD_NAME_"
    )
    private String fieldName;
    @Column(
            name = "COMMENT_"
    )
    private String comment;

    @Column(
            name = "DATA_TYPE_"
    )
    private String dataType;

    @Column(
            name = "LENGTH_"
    )
    private Integer length;

    @Column(
            name = "DECIMAL_LENGTH_"
    )
    private Integer decimalLength;

    @Column(
            name = "CONTROL_"
    )
    private String control;

    @Column(
            name = "EXT_JSON_"
    )
    private String extJson;
    private int isSingle = 1;

    @Column(
            name = "ENT_ID_"
    )
    private String entId;
    @Transient
    private SysBoEnt sysBoEnt;
    @Transient
    private String status = "new";
    @Transient
    private String diffConent = "";
    @Transient
    private JSONObject extJsonObj;
    @Transient
    private boolean isContain = false;

    public SysBoAttr() {
    }

    public SysBoAttr(String in_id) {
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

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        if(StringUtil.isEmpty(this.fieldName)) {
            String columnPre = DbUtil.getColumnPre();
            return columnPre + this.getName();
        } else {
            return this.fieldName;
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return StringUtil.isEmpty(this.comment)?this.name:this.comment;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getLength() {
        return this.length;
    }

    public void setDecimalLength(Integer decimalLength) {
        this.decimalLength = decimalLength;
    }

    public Integer getDecimalLength() {
        return this.decimalLength;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getControl() {
        return this.control;
    }

    public void setExtJson(String extJson) {
        if(StringUtil.isNotEmpty(extJson)) {
            this.extJsonObj = JSONObject.parseObject(extJson);
        }

        this.extJson = extJson;
    }

    public String getExtJson() {
        return StringUtil.isEmpty(this.extJson)?"{}":this.extJson;
    }

    public boolean getRequired() {
        JSONObject json = JSONObject.parseObject(this.getExtJson());
        String required = json.getString("required");
        Boolean flag = Boolean.valueOf(false);
        if("true".equals(required)) {
            flag = Boolean.valueOf(true);
        }

        return flag.booleanValue();
    }

    public String getPropByName(String name) {
        return this.extJsonObj == null?"":(this.extJsonObj.containsKey(name)?this.extJsonObj.getString(name):"");
    }

    public Integer getIntPropByName(String name) {
        return this.extJsonObj == null?Integer.valueOf(-1):(this.extJsonObj.containsKey(name)?this.extJsonObj.getInteger(name):Integer.valueOf(-1));
    }

    public SysBoEnt getSysBoEnt() {
        return this.sysBoEnt;
    }

    public void setSysBoEnt(SysBoEnt in_sysBoEnt) {
        this.sysBoEnt = in_sysBoEnt;
    }

    public String getEntId() {
        return this.getSysBoEnt() == null?null:this.getSysBoEnt().getId();
    }

    public void setEntId(String aValue) {
        if(aValue == null) {
            this.sysBoEnt = null;
        } else if(this.sysBoEnt == null) {
            this.sysBoEnt = new SysBoEnt(aValue);
        } else {
            this.sysBoEnt.setId(aValue);
        }

    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiffConent() {
        return this.diffConent;
    }

    public void setDiffConent(String diffConent) {
        this.diffConent = diffConent;
    }

    public int getIsSingle() {
        return this.isSingle;
    }

    public boolean single() {
        return this.isSingle == 1;
    }

    public void setIsSingle(int isSingle) {
        this.isSingle = isSingle;
    }

    public boolean isContain() {
        return this.isContain;
    }

    public void setContain(boolean isContain) {
        this.isContain = isContain;
    }

    public boolean equals(Object object) {
        if(!(object instanceof SysBoAttr)) {
            return false;
        } else {
            SysBoAttr rhs = (SysBoAttr)object;
            boolean rtn = (new EqualsBuilder()).append(this.name.toLowerCase(), rhs.name.toLowerCase()).append(this.dataType, rhs.dataType).append(this.control, rhs.control).isEquals();
            return !rtn?rtn:("varchar".equals(this.dataType)?this.length.equals(rhs.length):(!"number".equals(this.dataType)?true:this.length.equals(rhs.length) && this.decimalLength.equals(rhs.decimalLength)));
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.id).append(this.name).append(this.fieldName).append(this.comment).append(this.dataType).append(this.length).append(this.decimalLength).append(this.control).append(this.extJson).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("id", this.id).append("name", this.name).append("fieldName", this.fieldName).append("comment", this.comment).append("dataType", this.dataType).append("length", this.length).append("decimalLength", this.decimalLength).append("control", this.control).append("extJson", this.extJson).toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

