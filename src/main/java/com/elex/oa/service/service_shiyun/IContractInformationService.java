package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.ContractInformation;
import com.elex.oa.util.util_shiyun.PageHelper;

import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:合同信息（接口）
 * @Date:Created in  16:48 2018\4\9 0009
 * @Modify By:
 */
public interface IContractInformationService {
    /**
     *@Author:ShiYun;
     *@Description:根据条件（分页）查询合同信息
     *@Date: 16:49 2018\4\9 0009
     */
    public PageHelper<ContractInformation> queryByConditions(Map<String,Object> paramMap);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询合同信息
     *@Date: 16:52 2018\4\9 0009
     */
    public ContractInformation queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据条件（不分页）查询合同信息
     *@Date: 17:35 2018\4\12 0012
     */
    public List<ContractInformation> queryByEntity(ContractInformation contractInformation);

    /**
     *@Author:ShiYun;
     *@Description:添加合同信息并返回主键
     *@Date: 14:27 2018\4\20 0020
     */
    public Integer addOne(ContractInformation contractInformation);
}
