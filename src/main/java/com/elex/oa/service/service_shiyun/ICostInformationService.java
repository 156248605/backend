package com.elex.oa.service.service_shiyun;


import com.elex.oa.entity.entity_shiyun.CostInformation;

/**
 * @Author:ShiYun;
 * @Description:人事信息的成本信息
 * @Date:Created in  11:57 2018\4\9 0009
 * @Modify By:
 */
public interface ICostInformationService {
    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息的成本信息
     *@Date: 11:58 2018\4\9 0009
     */
    public CostInformation queryOneById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:添加成本信息
     *@Date: 18:17 2018\4\11 0011
     */
    public Integer saveOne(CostInformation costInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改成本信息
     *@Date: 16:41 2018\4\12 0012
     */
    public void modifyOne(CostInformation costInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除成本信息
     *@Date: 15:24 2018\8\20 0020
     */
    public void remvoeOne(Integer costinformationid);
}
