package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.ICostInformationDao;
import com.elex.oa.entity.hr_entity.costinformation.CostInformation;
import com.elex.oa.service.hr_service.ICostInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:ShiYun;
 * @Description:人事信息的成本信息
 * @Date:Created in  11:59 2018\4\9 0009
 * @Modify By:
 */
@Service
public class CostInformationServiceImpl implements ICostInformationService {

    @Resource
    ICostInformationDao iCostInformationDao;

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息的成本信息
     *@Date: 13:09 2018\4\9 0009
     */
    @Override
    public CostInformation queryOneById(Integer id) {
        CostInformation costInformation = iCostInformationDao.selectById(id);
        return costInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加成本信息
     *@Date: 18:18 2018\4\11 0011
     */
    @Override
    public Integer saveOne(CostInformation costInformation) {
        Integer costInformationId = iCostInformationDao.insertOne(dosomethingBeforeSaveone(costInformation));
        return costInformation.getId();
    }



    /**
     *@Author:ShiYun;
     *@Description:修改成本信息
     *@Date: 16:42 2018\4\12 0012
     */
    @Override
    public void modifyOne(CostInformation costInformation) {
        if (null != costInformation) {
            iCostInformationDao.updateOne(dosomethingBeforeSaveone(costInformation));
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除成本信息
     *@Date: 15:25 2018\8\20 0020
     */
    @Override
    public void remvoeOne(Integer costinformationid) {
        iCostInformationDao.deleteById(costinformationid);
    }

    @Override
    public void remvoeAll() {
        iCostInformationDao.deleteAll();
    }

    private CostInformation dosomethingBeforeSaveone(CostInformation costInformation) {
        if(null!=costInformation.getSalaryaccount() && "null".equals(costInformation.getSalaryaccount()))costInformation.setSalaryaccount(null);
        if(null!=costInformation.getSbcode() && "null".equals(costInformation.getSbcode()))costInformation.setSbcode(null);
        if(null!=costInformation.getGjjcode() && "null".equals(costInformation.getGjjcode()))costInformation.setGjjcode(null);
        return costInformation;
    }
}
