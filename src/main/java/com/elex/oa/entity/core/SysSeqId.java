package com.elex.oa.entity.core;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.elex.oa.core.entity.BaseTenantEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(
        name = "SYS_SEQ_ID"
)
/*@TableDefine(
        title = "系统流水号"
)*/
public class SysSeqId extends BaseTenantEntity {
    public static final String BPM_INST_SEQ_ID = "1";
    public static final String GEN_TYPE_DAY = "DAY";
    public static final String GEN_TYPE_WEEK = "WEEK";
    public static final String GEN_TYPE_MONTH = "MONTH";
    public static final String GEN_TYPE_YEAR = "YEAR";
    public static final String GEN_TYPE_AUTO = "AUTO";
    public static final String ALIAS_BPM_INST_BILL_NO = "BPM_INST_BILL_NO";
  /*  @FieldDefine(
            title = "PKID"
    )*/
    @Id
    @Column(
            name = "SEQ_ID_"
    )
    protected String seqId;
/*    @FieldDefine(
            title = "名称"
    )*/
    @Column(
            name = "NAME_"
    )
    @Size(
            max = 80
    )
    @NotEmpty
    protected String name;
  /*  @FieldDefine(
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
            title = "当前日期"
    )*/
    @Column(
            name = "CUR_DATE_"
    )
    protected Date curDate;
/*    @FieldDefine(
            title = "规则"
    )*/
    @Column(
            name = "RULE_"
    )
    @Size(
            max = 100
    )
    protected String rule;
/*    @FieldDefine(
            title = "规则配置"
    )*/
    @Column(
            name = "RULE_CONF_"
    )
    @Size(
            max = 512
    )
    protected String ruleConf;
/*    @FieldDefine(
            title = "初始值"
    )*/
    @Column(
            name = "INIT_VAL_"
    )
    protected Integer initVal;
/*    @FieldDefine(
            title = "生成方式"
    )*/
    @Column(
            name = "GEN_TYPE_"
    )
    @Size(
            max = 20
    )
    protected String genType;
 /*   @FieldDefine(
            title = "流水号长度"
    )*/
    @Column(
            name = "LEN_"
    )
    protected Integer len;
/*    @FieldDefine(
            title = "当前值"
    )*/
    @Column(
            name = "CUR_VAL"
    )
    protected Integer curVal;
/*    @FieldDefine(
            title = "步长"
    )*/
    @Column(
            name = "STEP_"
    )
    protected Short step;
/*    @FieldDefine(
            title = "备注"
    )*/
    @Column(
            name = "MEMO_"
    )
    @Size(
            max = 512
    )
    protected String memo;
/*    @FieldDefine(
            title = "系统默认"
    )*/
    @Column(
            name = "IS_DEFAULT_"
    )
    @Size(
            max = 20
    )
    protected String isDefault;

    public SysSeqId() {
    }

    public SysSeqId(String in_seqId) {
        this.setSeqId(in_seqId);
    }

    public String getSeqId() {
        return this.seqId;
    }

    public void setSeqId(String aValue) {
        this.seqId = aValue;
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

    public Date getCurDate() {
        return this.curDate;
    }

    public void setCurDate(Date aValue) {
        this.curDate = aValue;
    }

    public String getRule() {
        return this.rule;
    }

    public void setRule(String aValue) {
        this.rule = aValue;
    }

    public Integer getInitVal() {
        return this.initVal;
    }

    public void setInitVal(Integer aValue) {
        this.initVal = aValue;
    }

    public String getGenType() {
        return this.genType;
    }

    public void setGenType(String aValue) {
        this.genType = aValue;
    }

    public Integer getLen() {
        return this.len;
    }

    public void setLen(Integer aValue) {
        this.len = aValue;
    }

    public Integer getCurVal() {
        return this.curVal;
    }

    public void setCurVal(Integer aValue) {
        this.curVal = aValue;
    }

    public Short getStep() {
        return this.step;
    }

    public void setStep(Short aValue) {
        this.step = aValue;
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
        return this.seqId;
    }

    public void setPkId(Serializable pkId) {
        this.seqId = (String)pkId;
    }

    public String getRuleConf() {
        return this.ruleConf;
    }

    public void setRuleConf(String ruleConf) {
        this.ruleConf = ruleConf;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public boolean equals(Object object) {
        if(!(object instanceof SysSeqId)) {
            return false;
        } else {
            SysSeqId rhs = (SysSeqId)object;
            return (new EqualsBuilder()).append(this.seqId, rhs.seqId).append(this.name, rhs.name).append(this.alias, rhs.alias).append(this.curDate, rhs.curDate).append(this.rule, rhs.rule).append(this.initVal, rhs.initVal).append(this.genType, rhs.genType).append(this.len, rhs.len).append(this.curVal, rhs.curVal).append(this.step, rhs.step).append(this.memo, rhs.memo).append(this.tenantId, rhs.tenantId).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.seqId).append(this.name).append(this.alias).append(this.curDate).append(this.rule).append(this.initVal).append(this.genType).append(this.len).append(this.curVal).append(this.step).append(this.memo).append(this.tenantId).append(this.createBy).append(this.createTime).append(this.updateBy).append(this.updateTime).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("seqId", this.seqId).append("name", this.name).append("alias", this.alias).append("curDate", this.curDate).append("rule", this.rule).append("initVal", this.initVal).append("genType", this.genType).append("len", this.len).append("curVal", this.curVal).append("step", this.step).append("memo", this.memo).append("tenantId", this.tenantId).append("createBy", this.createBy).append("createTime", this.createTime).append("updateBy", this.updateBy).append("updateTime", this.updateTime).toString();
    }
}

