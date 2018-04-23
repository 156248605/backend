package com.elex.oa.entity.activiti;
import com.elex.oa.entity.BpmSolution;
import com.elex.oa.util.StringUtil;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *@author hugo.zhao
 *@since 2018/4/13 9:58
*/
@Table(
        name = "BPM_SOL_FV"
)
public class BpmSolFv {
    @Id
    @Column(
            name = "ID_"
    )
    private String id;
    @Column(
            name = "NODE_ID_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    private String nodeId;
    @Column(
            name = "NODE_TEXT_"
    )
    @Size(
            max = 255
    )
    private String nodeText;
    @Column(
            name = "FORM_TYPE_"
    )
    @Size(
            max = 30
    )
    private String formType;
    @Column(
            name = "FORM_URI_"
    )
    @Size(
            max = 255
    )
    private String formUri;
    @Column(
            name = "FORM_NAME_"
    )
    @Size(
            max = 255
    )
    private String formName;
    @Column(
            name = "PRINT_URI_"
    )
    @Size(
            max = 255
    )
    private String printUri;
    @Column(
            name = "PRINT_NAME_"
    )
    @Size(
            max = 255
    )
    private String printName;
    @Column(
            name = "SN_"
    )
    private Integer sn;

    public String getSolId() {
        return SolId;
    }

    public void setSolId(String solId) {
        SolId = solId;
    }

    @Column(
            name = "MOBILE_ALIAS_"
    )

    @Size(
            max = 255
    )
    private String mobileAlias = "";
    @Column(
            name = "MOBILE_NAME_"
    )
    @Size(
            max = 255
    )
    private String mobileName = "";
    @Column(
            name = "TAB_RIGHTS_"
    )
    @Size(
            max = 65535
    )
    private String tabRights = "";
    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 64
    )
    private String actDefId;
    @Column(
            name = "IS_USE_CFORM_"
    )
    @Size(
            max = 20
    )
    private String isUseCform;
    @Column(
            name = "DATA_CONFS_"
    )
    private String dataConfs;
    @Column(
            name = "MOBILE_FORMS_"
    )
    @Size(
            max = 65535
    )
    private String mobileForms;
    @Column(
            name = "PRINT_FORMS_"
    )
    @Size(
            max = 65535
    )
    private String printForms;
    @Column(
            name = "COND_FORMS_"
    )
    @Size(
            max = 65535
    )
    private String condForms;
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

    @Column(
            name = "SOL_ID_"
    )
    private String SolId;
    @Transient
    private BpmSolution bpmSolution;
    @Transient
    private String tabTitle = "";
    @Transient
    private String groupTitle = null;

    public String getDataConfs() {
        return this.dataConfs;
    }

    public void setDataConfs(String dataConfs) {
        this.dataConfs = dataConfs;
    }

    public String getGroupTitle() {
        return this.groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getPrintUri() {
        return this.printUri;
    }

    public void setPrintUri(String printUri) {
        this.printUri = printUri;
    }

    public String getPrintName() {
        return this.printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public String getActDefId() {
        return this.actDefId;
    }

    public void setActDefId(String actDefId) {
        this.actDefId = actDefId;
    }

    public String getIsUseCform() {
        return this.isUseCform;
    }

    public void setIsUseCform(String isUseCform) {
        this.isUseCform = isUseCform;
    }

    public String getCondForms() {
        return StringUtil.isEmpty(this.condForms)?"[]":this.condForms;
    }

    public void setCondForms(String condForms) {
        this.condForms = condForms;
    }

    public BpmSolFv() {
    }

    public BpmSolFv(String in_id) {
        this.setId(in_id);
    }

    public BpmSolution getBpmSolution() {
        return this.bpmSolution;
    }

    public void setBpmSolution(BpmSolution in_bpmSolution) {
        this.bpmSolution = in_bpmSolution;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String aValue) {
        this.id = aValue;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String aValue) {
        this.nodeId = aValue;
    }

    public String getNodeText() {
        return this.nodeText;
    }

    public void setNodeText(String aValue) {
        this.nodeText = aValue;
    }

    public String getFormType() {
        return this.formType;
    }

    public void setFormType(String aValue) {
        this.formType = aValue;
    }

    public String getFormUri() {
        return this.formUri;
    }

    public void setFormUri(String aValue) {
        this.formUri = aValue;
    }

    public String getTabRights() {
        return this.tabRights;
    }

    public void setTabRights(String tabRights) {
        this.tabRights = tabRights;
    }

    public Integer getSn() {
        return this.sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
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

    public String getFormName() {
        return this.formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getMobileAlias() {
        return this.mobileAlias;
    }

    public void setMobileAlias(String mobileAlias) {
        this.mobileAlias = mobileAlias;
    }

    public String getMobileName() {
        return this.mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getTabTitle() {
        return this.tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public String getMobileForms() {
        return StringUtil.isEmpty(this.mobileForms)?"[]":this.mobileForms;
    }

    public void setMobileForms(String mobileForms) {
        this.mobileForms = mobileForms;
    }

    public String getPrintForms() {
        return StringUtil.isEmpty(this.printForms)?"[]":this.printForms;
    }

    public void setPrintForms(String printForms) {
        this.printForms = printForms;
    }
}

