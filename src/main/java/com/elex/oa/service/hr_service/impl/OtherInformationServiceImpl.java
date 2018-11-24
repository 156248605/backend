package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IOtherInformationDao;
import com.elex.oa.entity.hr_entity.OtherInformation;
import com.elex.oa.service.hr_service.IOtherInformationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:ShiYun;
 * @Description:人事信息的其他信息
 * @Date:Created in  13:48 2018\4\9 0009
 * @Modify By:
 */
@Service
public class OtherInformationServiceImpl implements IOtherInformationService {

    @Resource
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
        Integer otherInformationId = iOtherInformationDao.insertOne(dosomethingBeforeSaveone(otherInformation));
        return otherInformation.getId();
    }



    /**
     *@Author:ShiYun;
     *@Description:修改其他信息
     *@Date: 16:55 2018\4\12 0012
     */
    @Override
    public void modifyOne(OtherInformation otherInformation) {
        if (getaBooleanBeforeModifyone(otherInformation)) {
            iOtherInformationDao.updateOne(dosomethingBeforeSaveone(otherInformation));
        }
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

    @Override
    public void removeAll() {
        iOtherInformationDao.deleteAll();
    }

    private OtherInformation dosomethingBeforeSaveone(OtherInformation otherInformation) {
        if(null!=otherInformation.getPrivateemail() && "null".equals(otherInformation.getPrivateemail()))otherInformation.setPrivateemail(null);
        if(null!=otherInformation.getCompanyemail() && "null".equals(otherInformation.getCompanyemail()))otherInformation.setCompanyemail(null);
        if(null!=otherInformation.getEmergencycontract() && "null".equals(otherInformation.getEmergencycontract()))otherInformation.setEmergencycontract(null);
        if(null!=otherInformation.getEmergencyphone() && "null".equals(otherInformation.getEmergencyphone()))otherInformation.setEmergencyphone(null);
        if(null!=otherInformation.getAddress() && "null".equals(otherInformation.getAddress()))otherInformation.setAddress(null);
        if(null!=otherInformation.getRemark() && "null".equals(otherInformation.getRemark()))otherInformation.setRemark(null);
        return otherInformation;
    }

    private Boolean getaBooleanBeforeModifyone(OtherInformation otherInformation) {
        Boolean isNotNull = false;
        if(StringUtils.isNotEmpty(otherInformation.getPrivateemail()))isNotNull=true;
        if(StringUtils.isNotEmpty(otherInformation.getCompanyemail()))isNotNull=true;
        if(StringUtils.isNotEmpty(otherInformation.getEmergencycontract()))isNotNull=true;
        if(otherInformation.getEmergencyrpid()!=null)isNotNull=true;
        if(StringUtils.isNotEmpty(otherInformation.getEmergencyphone()))isNotNull=true;
        if(StringUtils.isNotEmpty(otherInformation.getAddress()))isNotNull=true;
        if(StringUtils.isNotEmpty(otherInformation.getRemark()))isNotNull=true;
        return isNotNull;
    }
}
