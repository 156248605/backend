package com.elex.oa.entity;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Size;


@Table(
        name = "BPM_DEF"
)
public class BpmDef{
    public static final String STATUS_INIT = "INIT";
    public static final String STATUS_DEPLOY = "DEPLOYED";

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getActDefId() {
        return actDefId;
    }

    public void setActDefId(String actDefId) {
        this.actDefId = actDefId;
    }

    public String getActDepId() {
        return actDepId;
    }

    public void setActDepId(String actDepId) {
        this.actDepId = actDepId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getMainDefId() {
        return mainDefId;
    }

    public void setMainDefId(String mainDefId) {
        this.mainDefId = mainDefId;
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

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
    public String getIdentifyLabel() {
        return this.subject;
    }

    @Id
    @Column(
            name = "DEF_ID_"
    )

    protected String defId;
    @Column(
            name = "SUBJECT_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    protected String subject;
    @Column(
            name = "DESCP_"
    )
    @Size(
            max = 1024
    )
    protected String descp;
    @Column(
            name = "KEY_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    protected String key;
    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 255
    )
    protected String actDefId;
    @Column(
            name = "ACT_DEP_ID_"
    )
    @Size(
            max = 255
    )
    protected String actDepId;
    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    protected String status;
    @Column(
            name = "VERSION_"
    )
    protected Integer version;
    @Column(
            name = "IS_MAIN_"
    )
    @Size(
            max = 20
    )
    protected String isMain;
    @Column(
            name = "SETTING_"
    )
    @Size(
            max = 2147483647
    )
    protected String setting;
    @Column(
            name = "MODEL_ID_"
    )
    @Size(
            max = 64
    )
    protected String modelId;
    @Column(
            name = "MAIN_DEF_ID_"
    )
    @Size(
            max = 64
    )
    protected String mainDefId;
    @Column(
            name = "TREE_ID_"
    )
    protected String treeId;

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

    public SysTree getSysTree() {
        return sysTree;
    }

    public void setSysTree(SysTree sysTree) {
        this.sysTree = sysTree;
    }

    @Transient
    private SysTree sysTree;


    public BpmDef() {
    }


}
