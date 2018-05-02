package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description: 用户部门关系表
 * @Date:Created in  10:46 2018\3\17 0017
 * @Modify By:
 */
@Table(name = "tb_id_membership")
public class Membership implements Serializable{
    @Id
    private Integer id;
    private Integer depid;//部门ID
    private Integer userid;//用户ID

    public Membership() {
    }

    public Membership(Integer id, Integer depid, Integer userid) {
        this.id = id;
        this.depid = depid;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", depid=" + depid +
                ", userid=" + userid +
                '}';
    }
}
