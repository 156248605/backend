package com.elex.oa.entity.activiti;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 *@author hugo.zhao
 *@since 2018/4/8 19:26
*/
public class BpmInst {
    public static final String STATUS_RUNNING = "RUNNING";
    public static final String STATUS_SUCCESS_END = "SUCCESS_END";
    public static final String STATUS_DRAFTED = "DRAFTED";
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_ABORT_END = "ABORT_END";
    public static final String STATUS_CANCEL_END = "CANCEL_END";
    public static final String STATUS_DISCARD = "DISCARD_END";
    public static final String BO_DEF_ID = "bo_Def_Id_";
    
    @Id
    @Column(
            name = "INST_ID_"
    )
    private String instId;
    @Column(
            name = "DEF_ID_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String defId;

    @Column(
            name = "ACT_INST_ID_"
    )
    @Size(
            max = 64
    )
    private String actInstId;

    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String actDefId;

    @Column(
            name = "SOL_ID_"
    )
    @Size(
            max = 64
    )
    private String solId;

    @Column(
            name = "SUBJECT_"
    )
    @Size(
            max = 350
    )
    private String subject;

    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 20
    )
    private String status;

    @Column(
            name = "VERSION_"
    )
    private Integer version;

    @Column(
            name = "BUS_KEY_"
    )
    @Size(
            max = 64
    )
    private String busKey;

    @Column(
            name = "BILL_NO_"
    )
    @Size(
            max = 64
    )
    private String billNo;

    @Column(
            name = "INST_NO_"
    )
    @Size(
            max = 64
    )
    private String instNo;

    @Column(
            name = "DATA_SAVE_MODE_"
    )
    @Size(
            max = 64
    )
    private String dataSaveMode = "";

    public String getBoDefId() {
        return boDefId;
    }

    public void setBoDefId(String boDefId) {
        this.boDefId = boDefId;
    }

    @Column(
            name = "BO_DEF_ID_"
    )
    private String boDefId = "";

    @Column(
            name = "CHECK_FILE_ID_"
    )
    @Size(
            max = 64
    )
    private String checkFileId;

    @Column(
            name = "IS_TEST_"
    )
    @Size(
            max = 20
    )
    private String isTest;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "GMT+8"
    )
    @Column(
            name = "END_TIME_"
    )
    private Date endTime;

    @Column(
            name = "IS_USE_BMODEL_"
    )
    @Size(
            max = 30
    )
    private String isUseBmodel;

    @Column(
            name = "SUPPORT_MOBILE_"
    )
    private int supportMobile = 0;

    @Column(
            name = "START_DEP_ID_"
    )
    @Size(
            max = 64
    )
    private String startDepId;

    @Column(
            name = "START_DEP_FULL_"
    )
    @Size(
            max = 300
    )
    private String startDepFull;
    @Column(
            name = "ERRORS_"
    )
    @Size(
            max = 2147483647
    )
    private String errors;
    @Transient
    private JSONObject rightJson;
    @Transient
    private String taskNodes;
    @Transient
    private String taskNodeUsers;
    @Transient
    private String treeName;

    @Column(
            name = "TENANT_ID_"
    )
    @Size(
            max = 64
    )
    private String tenantId = null;

    @Column(
            name = "CREATE_TIME_"
    )
    private Date createTime;

    @Column(
            name = "CREATE_BY_"
    )
    @Size(
            max = 64
    )
    private String createBy;
    @Column(
            name = "UPDATE_BY_"
    )
    @Size(
            max = 64
    )
    private String updateBy;

    @Column(
            name = "UPDATE_TIME_"

    )
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBillNo() {
        return this.billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BpmInst() {
    }

    public BpmInst(String in_instId) {
        this.setInstId(in_instId);
    }

    public String getInstNo() {
        return this.instNo;
    }

    public void setInstNo(String instNo) {
        this.instNo = instNo;
    }

    public String getInstId() {
        return this.instId;
    }

    public void setInstId(String aValue) {
        this.instId = aValue;
    }

    public String getDefId() {
        return this.defId;
    }

    public void setDefId(String aValue) {
        this.defId = aValue;
    }

    public String getActInstId() {
        return this.actInstId;
    }

    public void setActInstId(String aValue) {
        this.actInstId = aValue;
    }

    public String getActDefId() {
        return this.actDefId;
    }

    public void setActDefId(String aValue) {
        this.actDefId = aValue;
    }

    public String getSolId() {
        return this.solId;
    }

    public void setSolId(String aValue) {
        this.solId = aValue;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String aValue) {
        this.subject = aValue;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String aValue) {
        this.status = aValue;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer aValue) {
        this.version = aValue;
    }

    public String getBusKey() {
        return this.busKey;
    }

    public void setBusKey(String aValue) {
        this.busKey = aValue;
    }

    public String getStatusLabel() {
        return "RUNNING".equals(this.status)?"运行中":("DRAFTED".equals(this.status)?"草稿":("SUCESS_END".equals(this.status)?"成功结束":("DISCARD_END".equals(this.status)?"作废":("ABORT_END".equals(this.status)?"异常中止结束":("PENDING".equals(this.status)?"挂起":"")))));
    }

    public String getStatusLabelHtml() {
        return "RUNNING".equals(this.status)?"<span style=\'color:blue\'>运行中</span>":("DRAFTED".equals(this.status)?"<span style=\'color:gray\'>草稿</span>":("SUCESS_END".equals(this.status)?"<span style=\'color:green\'>成功结束</span>":("DISCARD_END".equals(this.status)?"<span style=\'color:red\'>作废</span>":("ABORT_END".equals(this.status)?"<span style=\'color:red\'>异常中止结束</span>":("PENDING".equals(this.status)?"<span style=\'color:gray\'>挂起</span>":"")))));
    }

    public String getIsTest() {
        return this.isTest;
    }

    public void setIsTest(String aValue) {
        this.isTest = aValue;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date aValue) {
        this.endTime = aValue;
    }

    public String getCheckFileId() {
        return this.checkFileId;
    }

    public void setCheckFileId(String checkFileId) {
        this.checkFileId = checkFileId;
    }

    public String getIdentifyLabel() {
        return this.instId;
    }

    public Serializable getPkId() {
        return this.instId;
    }

    public void setPkId(Serializable pkId) {
        this.instId = (String)pkId;
    }

    public String getIsUseBmodel() {
        return this.isUseBmodel;
    }

    public void setIsUseBmodel(String isUseBmodel) {
        this.isUseBmodel = isUseBmodel;
    }

    public String getDataSaveMode() {
        return this.dataSaveMode;
    }

    public String getStartDepId() {
        return this.startDepId;
    }

    public void setStartDepId(String startDepId) {
        this.startDepId = startDepId;
    }

    public String getStartDepFull() {
        return this.startDepFull;
    }

    public void setStartDepFull(String startDepFull) {
        this.startDepFull = startDepFull;
    }

    public void setDataSaveMode(String dataSaveMode) {
        this.dataSaveMode = dataSaveMode;
    }

    public String getErrors() {
        return this.errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public JSONObject getRightJson() {
        return this.rightJson;
    }

    public void setRightJson(JSONObject rightJson) {
        this.rightJson = rightJson;
    }

    public String getTaskNodes() {
        return this.taskNodes;
    }

    public void setTaskNodes(String taskNodes) {
        this.taskNodes = taskNodes;
    }

    public String getTaskNodeUsers() {
        return this.taskNodeUsers;
    }

    public void setTaskNodeUsers(String taskNodeUsers) {
        this.taskNodeUsers = taskNodeUsers;
    }

    public String getTreeName() {
        return this.treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public int getSupportMobile() {
        return this.supportMobile;
    }

    public void setSupportMobile(int supportMobile) {
        this.supportMobile = supportMobile;
    }
    
    
}
