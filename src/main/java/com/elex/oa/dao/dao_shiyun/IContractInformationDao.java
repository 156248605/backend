package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.ContractInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:合同信息
 * @Date:Created in  16:31 2018\4\9 0009
 * @Modify By:
 */
@Mapper
public interface IContractInformationDao {
    /**
     *@Author:ShiYun;
     *@Description:根据条件查询合同信息
     *@Date: 16:32 2018\4\9 0009
     */
    public List<ContractInformation> selectByConditions(ContractInformation contractInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询合同信息
     *@Date: 16:46 2018\4\9 0009
     */
    public ContractInformation selectById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:添加合同信息并返回主键
     *@Date: 14:19 2018\4\20 0020
     */
    public Integer insertOne(ContractInformation contractInformation);
}
