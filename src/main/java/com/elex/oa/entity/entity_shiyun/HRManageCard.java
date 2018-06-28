package com.elex.oa.entity.entity_shiyun;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:人事管理看板
 * @Date:Created in  11:53 2018\6\28 0028
 * @Modify By:
 */
public class HRManageCard implements Serializable{
    private static final long serialVersionUID = -4463644380735948544L;
    private String depname;//部门名称
    private Integer num;//部门人数
    private String ratio;//部门占比
    private List<User> users;//相应的人员

    public HRManageCard() {
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "HRManageCard{" +
                "depname='" + depname + '\'' +
                ", num=" + num +
                ", ratio='" + ratio + '\'' +
                '}';
    }
}
