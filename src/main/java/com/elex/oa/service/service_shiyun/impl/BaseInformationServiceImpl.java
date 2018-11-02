package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IBaseInformationDao;
import com.elex.oa.entity.entity_shiyun.BaseInformation;
import com.elex.oa.service.service_shiyun.IBaseInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:ShiYun;
 * @Description:人事信息的基本信息
 * @Date:Created in  18:17 2018\4\8 0008
 * @Modify By:
 */
@Service
public class BaseInformationServiceImpl implements IBaseInformationService {

    @Resource
    IBaseInformationDao iBaseInformationDao;

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息的基本信息
     *@Date: 18:18 2018\4\8 0008
     */
    @Override
    public BaseInformation queryOneById(Integer id) {
        BaseInformation baseInformation = iBaseInformationDao.selectById(id);
        return baseInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的基本信息
     *@Date: 14:33 2018\4\12 0012
     */
    @Override
    public Integer saveOne(BaseInformation baseInformation) {
        Integer baseInformationId = iBaseInformationDao.insertOne(baseInformation);
        return baseInformation.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:修改人事信息的基本信息
     *@Date: 14:34 2018\4\12 0012
     */
    @Override
    public void modifyOne(BaseInformation baseInformation) {
        iBaseInformationDao.updateOne(baseInformation);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除基本信息表
     *@Date: 15:02 2018\8\20 0020
     */
    @Override
    public void removeOne(Integer baseinformationid) {
        iBaseInformationDao.deleteById(baseinformationid);
    }

    @Override
    public void removeAll() {
        iBaseInformationDao.deleteAll();
    }
}
