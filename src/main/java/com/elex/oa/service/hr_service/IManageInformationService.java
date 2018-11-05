package com.elex.oa.service.hr_service;


import com.elex.oa.entity.hr_entity.ManageInformation;

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

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除管理信息
     *@Date: 15:11 2018\8\20 0020
     */
    public void removeOne(Integer manageinformationid);

    void removeAll();
}
