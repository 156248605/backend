package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.personalinformation.BaseInformation;

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

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除基本信息表
     *@Date: 15:00 2018\8\20 0020
     */
    public void removeOne(Integer baseinformationid);

    void removeAll();
}
