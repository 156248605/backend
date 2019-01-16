package com.elex.oa.entity.hr_entity.manageinformation;

import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\15 0015 11:13
 * @Version 1.0
 **/
public class ManageInformationAddInfo {
    private Integer userid;
    private Integer depid;
    private List<Integer> postids;
    private String postrank;
    private String employeetype;
    private String entrydate;
    private String zhuanzhengdate;

    public ManageInformationAddInfo() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public List<Integer> getPostids() {
        return postids;
    }

    public void setPostids(List<Integer> postids) {
        this.postids = postids;
    }

    public String getPostrank() {
        return postrank;
    }

    public void setPostrank(String postrank) {
        this.postrank = postrank;
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getZhuanzhengdate() {
        return zhuanzhengdate;
    }

    public void setZhuanzhengdate(String zhuanzhengdate) {
        this.zhuanzhengdate = zhuanzhengdate;
    }

    @Override
    public String toString() {
        return "ManageInformationAddInfo{" +
                "userid=" + userid +
                ", depid=" + depid +
                ", postids=" + postids +
                ", postrank='" + postrank + '\'' +
                ", employeetype='" + employeetype + '\'' +
                ", entrydate='" + entrydate + '\'' +
                ", zhuanzhengdate='" + zhuanzhengdate + '\'' +
                '}';
    }
}