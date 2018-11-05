package com.elex.oa.service.hr_service;


import com.elex.oa.entity.hr_entity.OtherInformation;

/**
 * @Author:ShiYun;
 * @Description:人事信息的其他信息
 * @Date:Created in  13:46 2018\4\9 0009
 * @Modify By:
 */
public interface IOtherInformationService {
    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息的其他信息
     *@Date: 13:46 2018\4\9 0009
     */
    public OtherInformation queryOneById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的其它信息
     *@Date: 18:57 2018\4\11 0011
     */
    public Integer saveOne(OtherInformation otherInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改其他信息
     *@Date: 16:54 2018\4\12 0012
     */
    public void modifyOne(OtherInformation otherInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除其它信息
     *@Date: 15:32 2018\8\20 0020
     */
    public void removeOne(Integer otherinformationid);

    void removeAll();
}
