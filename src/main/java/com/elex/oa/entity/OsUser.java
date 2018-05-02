package com.elex.oa.entity;

import com.elex.oa.org.model.ITenant;
import com.elex.oa.org.model.IUser;
import com.elex.oa.util.SysPropertiesUtil;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.security.core.GrantedAuthority;

/**
 *@author hugo.zhao
 *@since 2018/4/25 13:20
*/
public class OsUser implements UserDetails,IUser {
    public static final String FROM_ADDED = "ADDED";
    public static final String FROM_SYS = "SYSTEM";
    public static final String FROM_IMPORTED = "IMPORTED";
    public static final String STATUS_IN_JOB = "IN_JOB";
    public static final String STATUS_OUT_JOB = "OUT_JOB";
    public static final String STATUS_DEL_JOB = "DEL_JOB";
    public static final String ADMIN_USER_ID_ = "1";
    @Id
    @Column(
            name = "USER_ID_"
    )
    private String userId;
    @Column(
            name = "FULLNAME_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String fullname;
    @Column(
            name = "USER_NO_"
    )
    @Size(
            max = 64
    )
    @NotEmpty
    private String userNo;
    @Column(
            name = "ENTRY_TIME_"
    )
    private Date entryTime;
    @Column(
            name = "QUIT_TIME_"
    )
    private Date quitTime;
    @Column(
            name = "SEX_"
    )
    private String sex;
    @Column(
            name = "BIRTHDAY_"
    )
    private Date birthday;
    @Column(
            name = "STATUS_"
    )
    @Size(
            max = 20
    )
    @NotEmpty
    private String status;
    @Column(
            name = "FROM_"
    )
    @Size(
            max = 20
    )
    private String from;
    @Column(
            name = "MOBILE_"
    )
    @Size(
            max = 20
    )
    private String mobile;
    @Column(
            name = "EMAIL_"
    )
    @Size(
            max = 100
    )
    private String email;
    @Column(
            name = "ADDRESS_"
    )
    @Size(
            max = 255
    )
    private String address;
    @Column(
            name = "URGENT_"
    )
    @Size(
            max = 64
    )
    private String urgent;
    @Column(
            name = "URGENT_MOBILE_"
    )
    @Size(
            max = 20
    )
    private String urgentMobile;
    @Column(
            name = "QQ_"
    )
    @Size(
            max = 20
    )
    private String qq;
    @Column(
            name = "PHOTO_"
    )
    @Size(
            max = 255
    )
    private String photo;

    @Column(
            name = "SYNC_WX_"
    )
    private int syncWx = 0;
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

    public void setGroupIds(Set<String> groupIds) {
        this.groupIds = groupIds;
    }

    @Transient
    private OsGroup mainDep;
    @Transient
    private OsGroup company;
    @Transient
    private List<OsGroup> canDeps;
    @Transient
    private List<OsGroup> canGroups;
    @Transient
    private Set<String> groupIds = new HashSet();
    @Transient
    private ITenant tenant;
    @Transient
    private SysAccount sysAccount;
    @Transient
    private String depPathNames;
    @Transient
    private Collection<GrantedAuthority> authorities = new ArrayList();
    @Transient
    private OsRelInst upLowRelInst;

    public String getDomain() {
        return this.sysAccount != null?this.sysAccount.getDomain():null;
    }

    public OsUser() {
    }

    public OsRelInst getUpLowRelInst() {
        return this.upLowRelInst;
    }

    public void setUpLowRelInst(OsRelInst upLowRelInst) {
        this.upLowRelInst = upLowRelInst;
    }

    public OsUser(String in_userId) {
        this.setUserId(in_userId);
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String aValue) {
        this.userId = aValue;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String aValue) {
        this.fullname = aValue;
    }

    public Date getEntryTime() {
        return this.entryTime;
    }

    public void setEntryTime(Date aValue) {
        this.entryTime = aValue;
    }

    public Date getQuitTime() {
        return this.quitTime;
    }

    public void setQuitTime(Date aValue) {
        this.quitTime = aValue;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String aValue) {
        this.status = aValue;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String aValue) {
        this.from = aValue;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String aValue) {
        this.mobile = aValue;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String aValue) {
        this.email = aValue;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String aValue) {
        this.address = aValue;
    }

    public String getUrgent() {
        return this.urgent;
    }

    public void setUrgent(String aValue) {
        this.urgent = aValue;
    }

    public String getUrgentMobile() {
        return this.urgentMobile;
    }

    public void setUrgentMobile(String aValue) {
        this.urgentMobile = aValue;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String aValue) {
        this.qq = aValue;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String aValue) {
        this.photo = aValue;
    }

    public String getIdentifyLabel() {
        return this.getFullname();
    }

    public Serializable getPkId() {
        return this.userId;
    }

    public void setPkId(Serializable pkId) {
        this.userId = (String)pkId;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public OsGroup getMainDep() {
        return this.mainDep;
    }

    public void setMainDep(OsGroup mainDep) {
        this.mainDep = mainDep;
    }

    public List<OsGroup> getCanDeps() {
        return this.canDeps;
    }

    public void setCanDeps(List<OsGroup> canDeps) {
        this.canDeps = canDeps;
    }

    public List<OsGroup> getCanGroups() {
        return this.canGroups;
    }

    public void setCanGroups(List<OsGroup> canGroups) {
        this.canGroups = canGroups;
    }

    public boolean equals(Object object) {
        if(!(object instanceof OsUser)) {
            return false;
        } else {
            OsUser rhs = (OsUser)object;
            return (new EqualsBuilder()).append(this.userId, rhs.userId).append(this.tenantId, rhs.tenantId).isEquals();
        }
    }
    public SysAccount getSysAccount() {
        return this.sysAccount;
    }

    public void setSysAccount(SysAccount sysAccount) {
        this.sysAccount = sysAccount;
    }

    public String getIdentityType() {
        return "USER";
    }

    public String getIdentityName() {
        return this.fullname;
    }

    public String getIdentityId() {
        return this.getUserId();
    }

    public ITenant getTenant() {
        return this.tenant;
    }

    @Override
    public void setTenant(ITenant var1) {

    }

    public String getUsername() {
        return this.sysAccount == null?"":this.sysAccount.getName() + "@" + this.sysAccount.getDomain();
    }

    public void setUsername(String userName) {
    }

    @Transient
    public String getPwd() {
        return this.sysAccount == null?"":this.sysAccount.getPwd();
    }

    public void setPwd(String pwd) {
    }

    public boolean isSuperAdmin() {
        if(this.sysAccount == null) {
            return false;
        } else {
            String adminAccount = SysPropertiesUtil.getAdminAccount();
            String account = this.sysAccount.getName();
            if(account.indexOf("@") == -1) {
                return account.equalsIgnoreCase(adminAccount);
            } else {
                int idx = account.indexOf("@");
                return account.substring(0, idx).equalsIgnoreCase(adminAccount);
            }
        }
    }

    public static void main(String[] args) {
        String account = "admin@abc.com";
        System.out.println(account.substring(0, account.indexOf("@")));
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return this.sysAccount == null?"":this.sysAccount.getPwd();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return "IN_JOB".equals(this.status);
    }

    public String getMainGroupId() {
        return this.mainDep != null?this.mainDep.getGroupId():null;
    }

    public int getSyncWx() {
        return this.syncWx;
    }

    public void setSyncWx(int syncWx) {
        this.syncWx = syncWx;
    }

    public Set<String> getGroupIds() {
        return this.groupIds;
    }

    public OsGroup getCompany() {
        return this.company;
    }

    public void setCompany(OsGroup company) {
        this.company = company;
    }

    public String getCompanyId() {
        return this.company != null?this.company.getGroupId():null;
    }

    public String getCompanyName() {
        return this.company != null?this.company.getName():null;
    }

    public String getMainGroupName() {
        return this.mainDep != null?this.mainDep.getName():null;
    }

    public String getDepPathNames() {
        return this.depPathNames;
    }

    public void setDepPathNames(String depPathNames) {
        this.depPathNames = depPathNames;
    }

    public String getUserUpLowPath() {
        return this.upLowRelInst == null?null:this.upLowRelInst.getPath();
    }
}
