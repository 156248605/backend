package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  11:06 2018\5\31 0031
 * @Modify By:
 */
@Table(name = "tb_hr_set_teachingstyle")
public class HRsetTeachingstyle implements Serializable {
    private static final long serialVersionUID = -2402100271639650918L;
    @Id
    private Integer id;//主键
    private String teachingstyle;//授课方式

    public HRsetTeachingstyle() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeachingstyle() {
        return teachingstyle;
    }

    public void setTeachingstyle(String teachingstyle) {
        this.teachingstyle = teachingstyle;
    }
}
