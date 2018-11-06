package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.ContractInformation;
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

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除合同信息
     *@Date: 17:48 2018\5\25 0025
     */
    public void deleteOne(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 17:53 2018\5\25 0025
     */
    public List<ContractInformation> selectAll(ContractInformation contractInformation);

    /**
     *@Author:ShiYun;
     *@Description:获得续签合同（根据userid获得合同信息）
     *@Date: 15:11 2018\5\28 0028
     */
    public List<ContractInformation> selectByUserid(Integer userid);

    /**
     *@Author:ShiYun;
     *@Description:修改合同信息
     *@Date: 10:15 2018\5\29 0029
     */
    public void updateOne(ContractInformation contractInformation);
}
