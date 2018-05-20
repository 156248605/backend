package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:合同类型
 * @Date:Created in  10:21 2018\5\15 0015
 * @Modify By:
 */
@Table(name = "tb_hr_set_contracttype")
public class HRsetContracttype implements Serializable {
    private static final long serialVersionUID = -5749889688470953636L;
    @Id
    private Integer id;//主键
    private String contracttype;//合同类型

    public HRsetContracttype() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContracttype() {
        return contracttype;
    }

    public void setContracttype(String contracttype) {
        this.contracttype = contracttype;
    }

    @Override
    public String toString() {
        return "HRsetContracttype{" +
                "id=" + id +
                ", contracttype='" + contracttype + '\'' +
                '}';
    }
}
