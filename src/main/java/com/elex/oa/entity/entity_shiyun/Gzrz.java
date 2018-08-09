package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:工作日志
 * @Date:Created in  15:09 2018\8\2 0002
 * @Modify By:
 */
@Table(name = "w_gzrz")
public class Gzrz implements Serializable{
    private static final long serialVersionUID = -9169223954403473324L;
    private String id;//
    private String starttime;//
    private String truename;//
    private String depname;//
    private String status;//

    public Gzrz() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Gzrz{" +
                "id='" + id + '\'' +
                ", starttime='" + starttime + '\'' +
                ", truename='" + truename + '\'' +
                ", depname='" + depname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
