package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.costinformation.CostInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:ShiYun;
 * @Description:人事信息的成本信息
 * @Date:Created in  11:49 2018\4\9 0009
 * @Modify By:
 */
@Mapper
public interface ICostInformationDao {
    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息的成本信息
     *@Date: 11:49 2018\4\9 0009
     */
    public CostInformation selectById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:添加成本信息并返回主键
     *@Date: 17:57 2018\4\11 0011
     */
    public Integer insertOne(CostInformation costInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改成本信息
     *@Date: 16:31 2018\4\12 0012
     */
    public void updateOne(CostInformation costInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除成本信息
     *@Date: 15:23 2018\8\20 0020
     */
    public void deleteById(Integer id);

    void deleteAll();
}
