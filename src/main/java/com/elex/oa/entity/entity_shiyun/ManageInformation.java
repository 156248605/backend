package com.elex.oa.entity.entity_shiyun;

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
    private Integer rankid;//职级ID
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

    public Integer getRankid() {
        return rankid;
    }

    public void setRankid(Integer rankid) {
        this.rankid = rankid;
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
                ", rankid=" + rankid +
                ", entrydate='" + entrydate + '\'' +
                ", zhuanzhengdate='" + zhuanzhengdate + '\'' +
                ", employeetypeid=" + employeetypeid +
                '}';
    }
}
