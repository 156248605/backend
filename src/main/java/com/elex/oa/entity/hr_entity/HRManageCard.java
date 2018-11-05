package com.elex.oa.entity.hr_entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:人事管理看板
 * @Date:Created in  11:53 2018\6\28 0028
 * @Modify By:
 */
public class HRManageCard implements Serializable{
    private static final long serialVersionUID = -4463644380735948544L;
    private String depname;//部门名称
    private Integer deptid;//部门ID
    private Integer parentid;//上级部门ID
    private Integer num;//部门人数
    private String ratio;//部门占比
    private Integer intoNum;//入职人数
    private Integer outNum;//离职人数
    private List<Map> users;//相应的人员
    private List<Dept> childDepts;//子部门

    public HRManageCard() {
    }

    public Integer getIntoNum() {
        return intoNum;
    }

    public void setIntoNum(Integer intoNum) {
        this.intoNum = intoNum;
    }

    public Integer getOutNum() {
        return outNum;
    }

    public void setOutNum(Integer outNum) {
        this.outNum = outNum;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public HRManageCard(Integer deptid) {
        this.deptid = deptid;
    }

    public List<Dept> getChildDepts() {
        return childDepts;
    }

    public void setChildDepts(List<Dept> childDepts) {
        this.childDepts = childDepts;
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

    public List<Map> getUsers() {
        return users;
    }

    public void setUsers(List<Map> users) {
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
