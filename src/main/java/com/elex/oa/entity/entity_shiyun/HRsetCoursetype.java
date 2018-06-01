package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  11:08 2018\5\31 0031
 * @Modify By:
 */
@Table(name = "tb_hr_set_coursetype")
public class HRsetCoursetype implements Serializable {
    private static final long serialVersionUID = 4518989471661650988L;
    @Id
    private Integer id;//主键
    private String coursetype;//课程类别

    public HRsetCoursetype() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }
}
