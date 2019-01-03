package com.elex.oa.entity.hr_entity;

import com.elex.oa.common.hr.Commons;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_id_user")
public class User implements Serializable{
    @Id
    private Integer id;//主键
    private String username;//用户名
    private String password;//密码
    private String truename;//姓名
    private Integer isactive;//是否激活
    private Integer state;//状态
    private String employeenumber;//员工号

    public User() {
    }

    public User(Integer id, String employeenumber) {
        this.id = id;
        this.employeenumber = employeenumber;
    }

    public User(Integer id, String username, String truename, Integer isactive, String employeenumber) {
        this.id = id;
        this.username = username;
        this.truename = truename;
        this.isactive = isactive;
        this.employeenumber = employeenumber;
        this.password = "123456";
        this.state = 1;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", truename='" + truename + '\'' +
                ", isactive=" + isactive +
                ", state=" + state +
                ", employeenumber='" + employeenumber + '\'' +
                '}';
    }
}
