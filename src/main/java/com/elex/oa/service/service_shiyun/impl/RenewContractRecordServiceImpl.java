package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IRenewContractRecordDao;
import com.elex.oa.entity.entity_shiyun.RenewContractRecord;
import com.elex.oa.service.service_shiyun.IRenewContractRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:合同续签记录（业务层）
 * @Date:Created in  13:47 2018\4\10 0010
 * @Modify By:
 */
@Service
public class RenewContractRecordServiceImpl implements IRenewContractRecordService {

    @Autowired
    IRenewContractRecordDao iRenewContractRecordDao;

    /**
     *@Author:ShiYun;
     *@Description:根据合同ID查询合同续签记录
     *@Date: 13:48 2018\4\10 0010
     */
    @Override
    public List<RenewContractRecord> queryRenewContractRecordsByContractId(Integer contractid) {
        List<RenewContractRecord> renewContractRecords = iRenewContractRecordDao.selectByContractId(contractid);
        return renewContractRecords;
    }
}
