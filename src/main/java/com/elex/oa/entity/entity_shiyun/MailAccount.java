package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 *@Author:ShiYun;
 *@Description;邮箱账号实体类
 *@Date:2018/3/3 11:43
 */
@Table(name = "tb_email_cfg")
public class MailAccount implements Serializable{
    @Id
    private Integer id;
    private String userId;
    private String trueName;
    private String emailAddress;
    private String backAddress;
    private String pop3;
    private String smpt;
    private String username;
    private String password;

    public MailAccount() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBackAddress() {
        return backAddress;
    }

    public void setBackAddress(String backAddress) {
        this.backAddress = backAddress;
    }

    public String getPop3() {
        return pop3;
    }

    public void setPop3(String pop3) {
        this.pop3 = pop3;
    }

    public String getSmpt() {
        return smpt;
    }

    public void setSmpt(String smpt) {
        this.smpt = smpt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MailAccount{" +
                "id=" + id +
                ", userID='" + userId + '\'' +
                ", trueName='" + trueName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", backAddress='" + backAddress + '\'' +
                ", pop3='" + pop3 + '\'' +
                ", smpt='" + smpt + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
