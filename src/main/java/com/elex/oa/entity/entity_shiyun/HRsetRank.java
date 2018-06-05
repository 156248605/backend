package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:职级
 * @Date:Created in  9:41 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_rank")
public class HRsetRank implements Serializable {
    private static final long serialVersionUID = 4676927191904758885L;
    @Id
    private Integer id;//主键
    private String rank;//职级

    public HRsetRank() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "HRsetRank{" +
                "id=" + id +
                ", rank='" + rank + '\'' +
                '}';
    }
}
