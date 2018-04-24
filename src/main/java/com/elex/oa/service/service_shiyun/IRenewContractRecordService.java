package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.RenewContractRecord;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:合同续签记录（接口）
 * @Date:Created in  13:43 2018\4\10 0010
 * @Modify By:
 */
public interface IRenewContractRecordService {
    /**
     *@Author:ShiYun;
     *@Description:根据合同ID查询合同续签记录
     *@Date: 13:45 2018\4\10 0010
     */
    public List<RenewContractRecord> queryRenewContractRecordsByContractId(Integer contractid);
}
