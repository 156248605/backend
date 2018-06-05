package com.elex.oa.form.entity;
import java.util.Map;

import com.elex.oa.org.model.IGroup;
import org.apache.commons.lang.StringUtils;
public class FieldRight {
    private Boolean all;
    private String userIds;
    private String unames;
    private String groupIds;
    private String gnames;

    public FieldRight() {
    }

    public Boolean getAll() {
        return this.all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

    public String getUnames() {
        return this.unames;
    }

    public void setUnames(String unames) {
        this.unames = unames;
    }

    public String getGroupIds() {
        return this.groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getGnames() {
        return this.gnames;
    }

    public void setGnames(String gnames) {
        this.gnames = gnames;
    }

    public String getUserIds() {
        return this.userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public boolean isInUserIds(String userId) {
        if(StringUtils.isEmpty(this.userIds)) {
            return false;
        } else {
            String[] uIds = this.userIds.split("[,]");
            String[] var3 = uIds;
            int var4 = uIds.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String uId = var3[var5];
                if(userId.equals(uId)) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean isInGroups(Map<String, IGroup> groups) {
        if(StringUtils.isEmpty(this.groupIds)) {
            return false;
        } else {
            String[] gIds = this.groupIds.split("[,]");
            String[] var3 = gIds;
            int var4 = gIds.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String gId = var3[var5];
                if(groups.containsKey(gId)) {
                    return true;
                }
            }

            return false;
        }
    }
}
