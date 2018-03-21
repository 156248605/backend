package com.elex.oa.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name="bpm_form_view")
public class BpmFormView {
    @Id
    @Column(
            name = "VIEW_ID_"
    )
    private String viewId;
    //分类ID
    @Column(
            name = "TREE_ID_"
    )
    private String treeId;
    //名称
    @Column(
            name = "NAME_"
    )
    private String name;
    //标识键
    @Column(
            name = "KEY_"
    )
    private String key;
    //类型
    @Column(
            name = "TYPE_"
    )
    private String type;
    //表单展示URL
    @Column(
            name = "RENDER_URL_"
    )
    private String renderUrl;
    //版本号
    @Column(
            name = "VERSION_"
    )
    private Integer version;
    //是否为主版本
    @Column(
            name = "IS_MAIN_"
    )
    private String isMain;
    //隶属主版本视图ID
    @Column(
            name = "MAIN_VIEW_ID_"
    )
    private String mainViewId;
    //视图说明
    @Column(
            name = "DESCP_"
    )
    private String descp;
    //状态
    @Column(
            name = "STATUS_"
    )
    private String status;
    //是否绑定业务模型
    @Column(
            name = "IS_BIND_MD_"
    )
    private String isBindMd;
    //模板内容
    @Column(
            name = "TEMPLATE_VIEW_"
    )
    private String templateView;
    //模板类型ID
    @Column(
            name = "TEMPLATE_ID_"
    )
    private String templateId;
    //tab展示模式
    @Column(
            name = "DISPLAY_TYPE_"
    )
    private String displayType;
    //转换过的模板
    @Column(
            name = "TEMPLATE_"
    )
    private String template;
    //租用机构Id
    @Column(
            name = "TENANT_ID_"
    )
    private String tenantId;
    //创建人ID
    @Column(
            name = "CREATE_BY_"
    )
    private String createBy;
    //创建时间
    @Column(
            name = "CREATE_TIME_"
    )
    private Date createTime;
    //创建时间字符串
    private String createTimeStr;
    //更新人ID
    @Column(
            name = "UPDATE_BY_"
    )
    private String updateBy;
    //更新时间
    @Column(
            name = "UPDATE_TIME_"
    )
    private Date updateTime;
    //BO定义ID
    @Column(
            name = "BO_DEFID_"
    )
    private String boDefid;
    //标题
    @Column(
            name = "TITLE_"
    )
    private String title;
    //按钮定义
    @Column(
            name = "BUTTON_DEF_"
    )
    private String buttonDef;
    //PDF模板
    @Column(
            name = "PDF_TEMP_"
    )
    private String pdfTemp;

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getIsBindMd() {
        return isBindMd;
    }

    public void setIsBindMd(String isBindMd) {
        this.isBindMd = isBindMd;
    }

    public String getTemplateView() {
        return templateView;
    }

    public void setTemplateView(String templateView) {
        this.templateView = templateView;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBoDefid() {
        return boDefid;
    }

    public void setBoDefid(String boDefid) {
        this.boDefid = boDefid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getButtonDef() {
        return buttonDef;
    }

    public void setButtonDef(String buttonDef) {
        this.buttonDef = buttonDef;
    }

    public String getPdfTemp() {
        return pdfTemp;
    }

    public void setPdfTemp(String pdfTemp) {
        this.pdfTemp = pdfTemp;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}