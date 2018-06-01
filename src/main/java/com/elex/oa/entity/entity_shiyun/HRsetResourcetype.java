package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  11:49 2018\5\31 0031
 * @Modify By:
 */
@Table(name = "tb_hr_set_resourcetype")
public class HRsetResourcetype implements Serializable {
    private static final long serialVersionUID = -1472539395588710268L;
    @Id
    private Integer id;//主键
    private String resourcetype;//资源类别

    public HRsetResourcetype() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }
}
