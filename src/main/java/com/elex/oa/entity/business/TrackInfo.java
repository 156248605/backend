package com.elex.oa.entity.business;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\5 0005 15:45
 * @Version 1.0
 **/
@Table(name = "tb_business_track")
public class TrackInfo implements Serializable {
    private static final long serialVersionUID = 5566730055155126561L;
    @Id
    private String code;//跟踪日志编码
    private String dependence_code;//线索或商机编码
    private String track_content;//跟踪内容
    private String track_date;//变更时间

    public TrackInfo() {
    }

    public TrackInfo(String dependence_code) {
        this.dependence_code = dependence_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDependence_code() {
        return dependence_code;
    }

    public void setDependence_code(String dependence_code) {
        this.dependence_code = dependence_code;
    }

    public String getTrack_content() {
        return track_content;
    }

    public void setTrack_content(String track_content) {
        this.track_content = track_content;
    }

    public String getTrack_date() {
        return track_date;
    }

    public void setTrack_date(String track_date) {
        this.track_date = track_date;
    }

    @Override
    public String toString() {
        return "TrackInfo{" +
                "code='" + code + '\'' +
                ", dependence_code='" + dependence_code + '\'' +
                ", track_content='" + track_content + '\'' +
                ", track_date='" + track_date + '\'' +
                '}';
    }
}