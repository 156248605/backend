package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:外语
 * @Date:Created in  10:36 2018\5\12 0012
 * @Modify By:
 */
@Table(name = "tb_hr_set_fla")
public class HRsetFla implements Serializable {
    private static final long serialVersionUID = 4321602770808513021L;
    @Id
    private Integer id;//主键
    private String fla;//外语

    public HRsetFla() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFla() {
        return fla;
    }

    public void setFla(String fla) {
        this.fla = fla;
    }

    @Override
    public String toString() {
        return "HRsetFla{" +
                "id=" + id +
                ", fla='" + fla + '\'' +
                '}';
    }
}
