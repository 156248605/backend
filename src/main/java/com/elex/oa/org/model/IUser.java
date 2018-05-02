package com.elex.oa.org.model;
import java.util.Set;
/**
 *@author hugo.zhao
 *@since 2018/4/24 18:03
*/
public interface IUser extends IdentityInfo {
    String getUserId();

    void setUserId(String var1);

    ITenant getTenant();

    void setTenant(ITenant var1);

    String getUsername();

    String getDomain();

    void setUsername(String var1);

    String getFullname();

    void setFullname(String var1);

    String getPwd();

    void setPwd(String var1);

    boolean isSuperAdmin();

    String getMainGroupId();

    String getMainGroupName();

    Set<String> getGroupIds();

    String getCompanyId();

    String getCompanyName();

    String getStatus();

    String getUserUpLowPath();
}
