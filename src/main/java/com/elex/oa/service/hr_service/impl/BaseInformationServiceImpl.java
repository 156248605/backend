package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IBaseInformationDao;
import com.elex.oa.entity.hr_entity.personalinformation.BaseInformation;
import com.elex.oa.service.hr_service.IBaseInformationService;
import org.apache.commons.lang.StringUtils;
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
        return iBaseInformationDao.selectById(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的基本信息
     *@Date: 14:33 2018\4\12 0012
     */
    @Override
    public Integer saveOne(BaseInformation baseInformation) {
        iBaseInformationDao.insertOne(dosomethingBeforeSaveOne(baseInformation));
        return baseInformation.getId();
    }



    /**
     *@Author:ShiYun;
     *@Description:修改人事信息的基本信息
     *@Date: 14:34 2018\4\12 0012
     */
    @Override
    public void modifyOne(BaseInformation baseInformation) {
        if (getaBooleanBeforeModifyone(baseInformation)) {
            iBaseInformationDao.updateOne(dosomethingBeforeSaveOne(baseInformation));
        }
    }

    private Boolean getaBooleanBeforeModifyone(BaseInformation baseInformation) {
        Boolean isNull = false;
        if(StringUtils.isNotBlank(baseInformation.getUserphoto())
        || StringUtils.isNotBlank(baseInformation.getIdphoto1())
        || StringUtils.isNotBlank(baseInformation.getIdphoto2())
        || StringUtils.isNotBlank(baseInformation.getEnglishname())
        || StringUtils.isNotBlank(baseInformation.getIdcode())
        || StringUtils.isNotBlank(baseInformation.getBirthday())
        || StringUtils.isNotBlank(baseInformation.getConstellation())
        || StringUtils.isNotBlank(baseInformation.getChinesecs())
        || baseInformation.getRaceid()!=null
        || StringUtils.isNotBlank(baseInformation.getMarriage())
        || baseInformation.getChildrenid()!=null
        || baseInformation.getZzmmid()!=null
        || baseInformation.getZgxlid()!=null
        || baseInformation.getByyxid()!=null
        || baseInformation.getSxzyid()!=null
        || baseInformation.getPyfsid()!=null
        || baseInformation.getFirstlaid()!=null
        || baseInformation.getElselaid()!=null
        || baseInformation.getPosttitleid()!=null
        || baseInformation.getZyzstypeid()!=null
        || baseInformation.getZyzsnameid()!=null
        || StringUtils.isNotBlank(baseInformation.getFirstworkingtime())
        || baseInformation.getParentcompanyid()!=null)isNull=true;
        return isNull;
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

    private BaseInformation dosomethingBeforeSaveOne(BaseInformation baseInformation) {
        baseInformation.setUserphoto(baseInformation.getUserphoto().replace("null",""));
        baseInformation.setIdphoto1(baseInformation.getIdphoto1().replace("null",""));
        baseInformation.setIdphoto2(baseInformation.getIdphoto2().replace("null",""));
        baseInformation.setEnglishname(baseInformation.getEnglishname().replace("null",""));
        baseInformation.setIdcode(baseInformation.getIdcode().replace("null",""));
        baseInformation.setBirthday(baseInformation.getBirthday().replace("null",""));
        baseInformation.setConstellation(baseInformation.getConstellation().replace("null",""));
        baseInformation.setChinesecs(baseInformation.getChinesecs().replace("null",""));
        baseInformation.setMarriage(baseInformation.getMarriage().replace("null",""));
        baseInformation.setFirstworkingtime(baseInformation.getFirstworkingtime().replace("null",""));
        baseInformation.setHj(baseInformation.getHj().replace("null",""));
        return baseInformation;
    }
}
