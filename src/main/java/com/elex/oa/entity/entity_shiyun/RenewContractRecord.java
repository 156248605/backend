package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:合同续签类
 * @Date:Created in  16:11 2018\4\9 0009
 * @Modify By:
 */
@Table(name = "tab_hr_renewcontractrecord")
public class RenewContractRecord  implements Serializable{
    @Id
    private Integer id;//主键
    private Integer contractid;//合同ID
    private String startdate;//生效期
    private String enddate;//失效期
    private Integer zhbgruserid;//最后变跟人ID
    private String zhbgrtruename;//最后变跟人姓名
    private String zhbgdate;//最后变更日期

    public RenewContractRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContractid() {
        return contractid;
    }

    public void setContractid(Integer contractid) {
        this.contractid = contractid;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public Integer getZhbgruserid() {
        return zhbgruserid;
    }

    public void setZhbgruserid(Integer zhbgruserid) {
        this.zhbgruserid = zhbgruserid;
    }

    public String getZhbgrtruename() {
        return zhbgrtruename;
    }

    public void setZhbgrtruename(String zhbgrtruename) {
        this.zhbgrtruename = zhbgrtruename;
    }

    public String getZhbgdate() {
        return zhbgdate;
    }

    public void setZhbgdate(String zhbgdate) {
        this.zhbgdate = zhbgdate;
    }

    @Override
    public String toString() {
        return "RenewContractRecord{" +
                "id=" + id +
                ", contractid=" + contractid +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", zhbgruserid=" + zhbgruserid +
                ", zhbgrtruename='" + zhbgrtruename + '\'' +
                ", zhbgdate='" + zhbgdate + '\'' +
                '}';
    }
}
