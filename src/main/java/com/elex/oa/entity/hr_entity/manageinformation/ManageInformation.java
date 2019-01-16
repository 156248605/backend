package com.elex.oa.entity.hr_entity.manageinformation;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author:ShiYun;
 * @Description:管理信息
 * @Date:Created in  17:38 2018\3\20 0020
 * @Modify By:
 */
@Table(name = "tb_id_manageinformation")
public class ManageInformation {
    @Id
    private Integer id;//主键
    private Integer postlevelid;//岗级ID
    private Integer postrankid;//职级ID
    private String entrydate;//入职日期
    private String zhuanzhengdate;//转正日期
    private Integer employeetypeid;//员工类型ID

    public ManageInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostlevelid() {
        return postlevelid;
    }

    public Integer getPostrankid() {
        return postrankid;
    }

    public void setPostrankid(Integer postrankid) {
        this.postrankid = postrankid;
    }

    public void setPostlevelid(Integer postlevelid) {
        this.postlevelid = postlevelid;
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

    public Integer getEmployeetypeid() {
        return employeetypeid;
    }

    public void setEmployeetypeid(Integer employeetypeid) {
        this.employeetypeid = employeetypeid;
    }

    @Override
    public String toString() {
        return "ManageInformation{" +
                "id=" + id +
                ", postlevelid=" + postlevelid +
                ", entrydate='" + entrydate + '\'' +
                ", zhuanzhengdate='" + zhuanzhengdate + '\'' +
                ", employeetypeid=" + employeetypeid +
                '}';
    }
}
