package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IManageInformationDao;
import com.elex.oa.entity.hr_entity.ManageInformation;
import com.elex.oa.service.hr_service.IManageInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:ShiYun;
 * @Description:人事信息的管理信息
 * @Date:Created in  10:26 2018\4\9 0009
 * @Modify By:
 */
@Service
public class ManageInformationServiceImpl implements IManageInformationService {

    @Resource
    IManageInformationDao iManageInformationDao;

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息的管理信息
     *@Date: 10:27 2018\4\9 0009
     */
    @Override
    public ManageInformation queryOneById(Integer id) {
        ManageInformation manageInformation = iManageInformationDao.selectById(id);
        return manageInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加管理信息并返回主键
     *@Date: 16:10 2018\4\11 0011
     */
    @Override
    public Integer saveOne(ManageInformation manageInformation) {
        Integer manageInformationId = iManageInformationDao.insertOne(dosomethingBeforeSaveone(manageInformation));
        return manageInformation.getId();
    }



    /**
     *@Author:ShiYun;
     *@Description:修改管理信息
     *@Date: 16:12 2018\4\12 0012
     */
    @Override
    public void modifyOne(ManageInformation manageInformation) {
        manageInformation = dosomethingBeforeSaveone(manageInformation);
        Boolean valBoolean = validateEntityBeforeModifyOne(manageInformation);
        if (valBoolean) {
            iManageInformationDao.updateOne(manageInformation);
        }
    }

    private Boolean validateEntityBeforeModifyOne(ManageInformation manageInformation) {
        Boolean valBoolean = true;
        if(manageInformation.getId()==null)valBoolean=false;
        if(manageInformation.getRankid()==null)valBoolean=false;
        if(manageInformation.getEmployeetypeid()==null)valBoolean=false;
        if(manageInformation.getEntrydate()==null || "".equals(manageInformation.getEntrydate()))valBoolean=false;
        if(manageInformation.getZhuanzhengdate()==null || "".equals(manageInformation.getZhuanzhengdate()))valBoolean=false;
        return valBoolean;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除管理信息
     *@Date: 15:12 2018\8\20 0020
     */
    @Override
    public void removeOne(Integer manageinformationid) {
        iManageInformationDao.deleteById(manageinformationid);
    }

    @Override
    public void removeAll() {
        iManageInformationDao.deleteAll();
    }

    private ManageInformation dosomethingBeforeSaveone(ManageInformation manageInformation) {
        if(null!=manageInformation.getEntrydate() && "null".equals(manageInformation.getEntrydate()))manageInformation.setEntrydate(null);
        if(null!=manageInformation.getZhuanzhengdate() && "null".equals(manageInformation.getZhuanzhengdate()))manageInformation.setZhuanzhengdate(null);
        return manageInformation;
    }
}
