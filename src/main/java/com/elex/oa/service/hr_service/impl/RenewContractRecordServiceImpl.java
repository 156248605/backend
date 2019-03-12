package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IRenewContractRecordDao;
import com.elex.oa.entity.hr_entity.RenewContractRecord;
import com.elex.oa.service.hr_service.IRenewContractRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:合同续签记录（业务层）
 * @Date:Created in  13:47 2018\4\10 0010
 * @Modify By:
 */
@Service
public class RenewContractRecordServiceImpl implements IRenewContractRecordService {

    @Resource
    IRenewContractRecordDao iRenewContractRecordDao;

    /**
     *@Author:ShiYun;
     *@Description:根据合同ID查询合同续签记录
     *@Date: 13:48 2018\4\10 0010
     */
    @Override
    public List<RenewContractRecord> queryRenewContractRecordsByContractId(Integer contractid) {
        return iRenewContractRecordDao.selectByContractId(contractid);
    }
}
