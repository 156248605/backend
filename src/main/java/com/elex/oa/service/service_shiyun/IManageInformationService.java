package com.elex.oa.service.service_shiyun;


import com.elex.oa.entity.entity_shiyun.ManageInformation;

/**
 * @Author:ShiYun;
 * @Description:人事信息的管理信息
 * @Date:Created in  10:24 2018\4\9 0009
 * @Modify By:
 */
public interface IManageInformationService {
    /**
     *@Author:ShiYun;
     *@Description:人事信息的管理信息
     *@Date: 10:25 2018\4\9 0009
     */
    public ManageInformation queryOneById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:添加管理信息
     *@Date: 16:08 2018\4\11 0011
     */
    public Integer saveOne(ManageInformation manageInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改管理信息
     *@Date: 16:11 2018\4\12 0012
     */
    public void modifyOne(ManageInformation manageInformation);
}