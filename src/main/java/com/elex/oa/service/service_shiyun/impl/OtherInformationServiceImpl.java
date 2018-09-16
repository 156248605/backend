package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IOtherInformationDao;
import com.elex.oa.entity.entity_shiyun.OtherInformation;
import com.elex.oa.service.service_shiyun.IOtherInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:ShiYun;
 * @Description:人事信息的其他信息
 * @Date:Created in  13:48 2018\4\9 0009
 * @Modify By:
 */
@Service
public class OtherInformationServiceImpl implements IOtherInformationService {

    @Autowired
    IOtherInformationDao iOtherInformationDao;

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息的其他信息
     *@Date: 13:48 2018\4\9 0009
     */
    @Override
    public OtherInformation queryOneById(Integer id) {
        OtherInformation otherInformation = iOtherInformationDao.selectById(id);
        return otherInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的其它信息
     *@Date: 18:58 2018\4\11 0011
     */
    @Override
    public Integer saveOne(OtherInformation otherInformation) {
        Integer otherInformationId = iOtherInformationDao.insertOne(otherInformation);
        return otherInformation.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:修改其他信息
     *@Date: 16:55 2018\4\12 0012
     */
    @Override
    public void modifyOne(OtherInformation otherInformation) {
        iOtherInformationDao.updateOne(otherInformation);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除其它信息
     *@Date: 15:33 2018\8\20 0020
     */
    @Override
    public void removeOne(Integer otherinformationid) {
        iOtherInformationDao.deleteById(otherinformationid);
    }
}
