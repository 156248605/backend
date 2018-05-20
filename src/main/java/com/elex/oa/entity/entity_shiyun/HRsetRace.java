package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:HR设置（民族）
 * @Date:Created in  16:33 2018\5\9 0009
 * @Modify By:
 */
@Table
public class HRsetRace implements Serializable{

    private static final long serialVersionUID = 3159366675296620702L;
    @Id
    private Integer id;
    private String race;

    public HRsetRace(Integer id, String race) {
        this.id = id;
        this.race = race;
    }

    public HRsetRace() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", race='" + race + '\'' +
                '}';
    }
}
