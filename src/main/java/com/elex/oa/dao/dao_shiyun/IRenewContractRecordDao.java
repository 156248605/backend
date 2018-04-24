package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.RenewContractRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:合同续签记录（持久层）
 * @Date:Created in  13:33 2018\4\10 0010
 * @Modify By:
 */
@Mapper
public interface IRenewContractRecordDao {
    /**
     *@Author:ShiYun;
     *@Description:根据合同ID查询合同续签记录
     *@Date: 13:33 2018\4\10 0010
     */
    public List<RenewContractRecord> selectByContractId(Integer contractid);
}
