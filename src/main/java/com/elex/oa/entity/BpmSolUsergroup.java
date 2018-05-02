package com.elex.oa.entity;

/**
 *@author hugo.zhao
 *@since 2018/4/24 19:44
*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import com.alibaba.fastjson.JSONObject;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.elex.oa.util.StringUtil;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.aspectj.bridge.MessageUtil;

@Table(
        name = "BPM_SOL_USERGROUP"
)
public class BpmSolUsergroup{
    public static String GROUP_TYPE_TASK = "task";
    public static String GROUP_TYPE_COPY = "copy";
    @Id
    @Column(
            name = "ID_"
    )
    private String id;
    @Column(
            name = "GROUP_NAME_"
    )
    @Size(
            max = 50
    )
    private String groupName;
    @Column(
            name = "SOL_ID_"
    )
    @Size(
            max = 50
    )
    private String solId;
    @Column(
            name = "ACT_DEF_ID_"
    )
    @Size(
            max = 64
    )
    private String actDefId;
    @Column(
            name = "GROUP_TYPE_"
    )
    @Size(
            max = 50
    )
    private String groupType;
    @Column(
            name = "NODE_ID_"
    )
    @Size(
            max = 50
    )
    private String nodeId;
    @Column(
            name = "NODE_NAME_"
    )
    @Size(
            max = 50
    )
    private String nodeName;
    @Column(
            name = "SETTING_"
    )
    @Size(
            max = 2000
    )
    private String setting;
    @Column(
            name = "SN_"
    )
    private Integer sn;
    @Column(
            name = "NOTIFY_TYPE_"
    )
    @Size(
            max = 50
    )
    private String notifyType;

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
    @Transient
    @OneToMany
    private List<BpmSolUser> userList = new ArrayList();

    public BpmSolUsergroup() {
    }

    public String getActDefId() {
        return this.actDefId;
    }

    public void setActDefId(String actDefId) {
        this.actDefId = actDefId;
    }

    public BpmSolUsergroup(String in_id) {
        this.setId(in_id);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String aValue) {
        this.id = aValue;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String aValue) {
        this.groupName = aValue;
    }

    public String getSolId() {
        return this.solId;
    }

    public void setSolId(String aValue) {
        this.solId = aValue;
    }

    public String getGroupType() {
        return this.groupType;
    }

    public void setGroupType(String aValue) {
        this.groupType = aValue;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String aValue) {
        this.nodeId = aValue;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String aValue) {
        this.nodeName = aValue;
    }

    public String getSetting() {
        return this.setting;
    }

    public void setSetting(String aValue) {
        this.setting = aValue;
    }

    public Integer getSn() {
        return this.sn;
    }

    public void setSn(Integer aValue) {
        this.sn = aValue;
    }

    public String getNotifyType() {
        return this.notifyType;
    }

/*    public String getDisplayNotifyType() {
        if(StringUtil.isEmpty(this.notifyType)) {
            return "";
        } else {
            Map map = MessageUtil.getMapMsgType();
            String[] aryType = this.notifyType.split(",");
            String str = "";

            for(int i = 0; i < aryType.length; ++i) {
                String type = aryType[i];
                if(i == 0) {
                    str = str + (String)map.get(type);
                } else {
                    str = str + "," + (String)map.get(type);
                }
            }

            return str;
        }
    }*/

    public void setNotifyType(String aValue) {
        this.notifyType = aValue;
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

    public List<BpmSolUser> getUserList() {
        return this.userList;
    }

    public void setUserList(List<BpmSolUser> userList) {
        this.userList = userList;
    }

    public boolean equals(Object object) {
        if(!(object instanceof BpmSolUsergroup)) {
            return false;
        } else {
            BpmSolUsergroup rhs = (BpmSolUsergroup)object;
            return (new EqualsBuilder()).append(this.id, rhs.id).append(this.groupName, rhs.groupName).append(this.solId, rhs.solId).append(this.groupType, rhs.groupType).append(this.nodeId, rhs.nodeId).append(this.nodeName, rhs.nodeName).append(this.tenantId, rhs.tenantId).append(this.setting, rhs.setting).append(this.sn, rhs.sn).append(this.notifyType, rhs.notifyType).append(this.createBy, rhs.createBy).append(this.createTime, rhs.createTime).append(this.updateBy, rhs.updateBy).append(this.updateTime, rhs.updateTime).isEquals();
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(-82280557, -700257973)).append(this.id).append(this.groupName).append(this.solId).append(this.groupType).append(this.nodeId).append(this.nodeName).append(this.tenantId).append(this.setting).append(this.sn).append(this.notifyType).append(this.createBy).append(this.createTime).append(this.updateBy).append(this.updateTime).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this)).append("id", this.id).append("groupName", this.groupName).append("solId", this.solId).append("groupType", this.groupType).append("nodeId", this.nodeId).append("nodeName", this.nodeName).append("tenantId", this.tenantId).append("setting", this.setting).append("sn", this.sn).append("notifyType", this.notifyType).append("createBy", this.createBy).append("createTime", this.createTime).append("updateBy", this.updateBy).append("updateTime", this.updateTime).toString();
    }

    public static void main(String[] args) {
        BpmSolUsergroup group = new BpmSolUsergroup();
        group.setId("11");
        group.setGroupName("分组名称");
        group.setGroupType("copy");
        ArrayList list = new ArrayList();
        BpmSolUser user = new BpmSolUser();
        user.setConfig("123");
        user.setUserType("User");
        list.add(user);
        user = new BpmSolUser();
        user.setConfig("12333");
        user.setUserType("User");
        list.add(user);
        group.setUserList(list);
        String str = JSONObject.toJSONString(group);
        System.out.println(str);
    }
}
