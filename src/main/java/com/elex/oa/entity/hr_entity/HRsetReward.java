package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  10:58 2018\5\31 0031
 * @Modify By:
 */
@Table(name = "tb_hr_set_reward")
public class HRsetReward implements Serializable {
    private static final long serialVersionUID = -2829550780216373932L;
    @Id
    private Integer id;//主键
    private String reward;//奖励

    public HRsetReward() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
