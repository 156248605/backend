package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.BaseInformation;

/**
 * @Author:ShiYun;
 * @Description:人事信息的基本信息
 * @Date:Created in  18:14 2018\4\8 0008
 * @Modify By:
 */
public interface IBaseInformationService {
    /**
     *@Author:ShiYun;
     *@Description:根据基本信息ID查询基本信息
     *@Date: 18:15 2018\4\8 0008
     */
    public BaseInformation queryOneById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的基本信息并返回主键
     *@Date: 18:35 2018\4\10 0010
     */
    public Integer saveOne(BaseInformation baseInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改人事信息的基本信息
     *@Date: 14:32 2018\4\12 0012
     */
    public void modifyOne(BaseInformation baseInformation);

}
