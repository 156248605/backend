package com.elex.oa.entity.project;

public class OsUser {
    private String userId;
    private String fullName;

    public OsUser() {
    }

    public OsUser(String userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "OsUser{" +
                "userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
