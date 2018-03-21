package com.elex.oa.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *@author hugo.zhao
 *@since 2018/3/17 13:11
*/
@Table(
        name = "BPM_FORM_VIEW"
)
public class BpmFormView {
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Id
    @Column(

            name = "VIEW_ID_"
    )
    private String viewId;

    @Column(
            name = "NAME_"
    )
    @Size(
            max = 255
    )
    @NotEmpty
    private String name;

    @Column(
            name = "TENANT_ID_"
    )
    private String tenantId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTemplateView() {
        return templateView;
    }

    public void setTemplateView(String templateView) {
        this.templateView = templateView;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getRenderUrl() {
        return renderUrl;
    }

    public void setRenderUrl(String renderUrl) {
        this.renderUrl = renderUrl;
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

    public String getIsBindMd() {
        return isBindMd;
    }

    public void setIsBindMd(String isBindMd) {
        this.isBindMd = isBindMd;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getMainViewId() {
        return mainViewId;
    }

    public void setMainViewId(String mainViewId) {
        this.mainViewId = mainViewId;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getBoDefId() {
        return boDefId;
    }

    public void setBoDefId(String boDefId) {
        this.boDefId = boDefId;
    }

    public String getButtonDef() {
        return buttonDef;
    }

    public void setButtonDef(String buttonDef) {
        this.buttonDef = buttonDef;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getPdfTemp() {
        return pdfTemp;
    }

    public void setPdfTemp(String pdfTemp) {
        this.pdfTemp = pdfTemp;
    }

    public String getOldViewId() {
        return oldViewId;
    }

    public void setOldViewId(String oldViewId) {
        this.oldViewId = oldViewId;
    }

    public String getOldBodefId() {
        return oldBodefId;
    }

    public void setOldBodefId(String oldBodefId) {
        this.oldBodefId = oldBodefId;
    }

    @Column(
            name = "KEY_"
            
    )
    @Size(
            max = 100
    )
    @NotEmpty
    private String key;
    @Column(
            name = "TYPE_"
    )
    @Size(
            max = 50
    )
    @NotEmpty
    private String type;
    @Column(
            name = "TITLE_"
    )
    @Size(
            max = 2000
    )
    private String title;
    @Column(
            name = "TEMPLATE_VIEW_"
    )
    @Size(
            max = 2147483647
    )
    private String templateView;
    @Column(
            name = "TEMPLATE_"
    )
    @Size(
            max = 2147483647
    )
    private String template;
    @Column(
            name = "RENDER_URL_"
    )
    @Size(
            max = 255
    )
    private String renderUrl;
    @Column(
            name = "VERSION_"
    )
    private Integer version;
    @Column(
            name = "IS_MAIN_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    private String isMain;
    @Column(
            name = "IS_BIND_MD_"
    )
    @Size(
            max = 20
    )
    private String isBindMd;
    @Column(
            name = "TEMPLATE_ID_"
    )
    @Size(
            max = 50
    )
    private String templateId;
    @Transient
    private String templateName;
    @Column(
            name = "MAIN_VIEW_ID_"
    )
    @Size(
            max = 64
    )
    private String mainViewId;

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Column(
            name = "DESCP_"
    )
    @Size(
            max = 500

    )
    private String descp;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 20
    )
    @NotEmpty

    private String status;
    @Column(
            name = "UPDATE_BY_"
    )
    private String createBy;
    @Column(
            name = "CREATE_BY_"
    )
    private String updateBy;

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
            name = "CREATE_TIME_"

    )
    private  String createTime;

    @Column(
            name = "UPDATE_TIME_"
    )
    private  String updateTime;


    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(
            name = "TREE_ID_"
    )
    private String treeId;
    @Column(
            name = "BO_DEFID_"
    )
    private String boDefId = "";
    @Column(
            name = "BUTTON_DEF_"
    )
    private String buttonDef = "";
    @Column(
            name = "DISPLAY_TYPE_"
    )
    @Size(
            max = 64
    )
    private String displayType;

    @Column(
            name = "PDF_TEMP_"
    )
    @Size(
            max = 2147483647
    )
    private String pdfTemp;
    @Transient
    private String oldViewId = null;
    @Transient
    private String oldBodefId = null;
/*    @Transient
    private BpmSolFv bpmSolFv = null;*/









}
