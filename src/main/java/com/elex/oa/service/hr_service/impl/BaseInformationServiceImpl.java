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
        Integer baseInformationId = iBaseInformationDao.insertOne(dosomethingBeforeSaveOne(baseInformation));
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
        if(StringUtils.isNotEmpty(baseInformation.getUserphoto()))isNull=true;
        if(StringUtils.isNotEmpty(baseInformation.getIdphoto1()))isNull=true;
        if(StringUtils.isNotEmpty(baseInformation.getIdphoto2()))isNull=true;
        if(StringUtils.isNotEmpty(baseInformation.getEnglishname()))isNull=true;
        if(StringUtils.isNotEmpty(baseInformation.getIdcode()))isNull=true;
        if(StringUtils.isNotEmpty(baseInformation.getBirthday()))isNull=true;
        if(StringUtils.isNotEmpty(baseInformation.getConstellation()))isNull=true;
        if(StringUtils.isNotEmpty(baseInformation.getChinesecs()))isNull=true;
        if(baseInformation.getRaceid()!=null)isNull=true;
        if(StringUtils.isNotEmpty(baseInformation.getMarriage()))isNull=true;
        if(baseInformation.getChildrenid()!=null)isNull=true;
        if(baseInformation.getZzmmid()!=null)isNull=true;
        if(baseInformation.getZgxlid()!=null)isNull=true;
        if(baseInformation.getByyxid()!=null)isNull=true;
        if(baseInformation.getSxzyid()!=null)isNull=true;
        if(baseInformation.getPyfsid()!=null)isNull=true;
        if(baseInformation.getFirstlaid()!=null)isNull=true;
        if(baseInformation.getElselaid()!=null)isNull=true;
        if(baseInformation.getPosttitleid()!=null)isNull=true;
        if(baseInformation.getZyzstypeid()!=null)isNull=true;
        if(baseInformation.getZyzsnameid()!=null)isNull=true;
        if(StringUtils.isNotEmpty(baseInformation.getFirstworkingtime()))isNull=true;
        if(baseInformation.getParentcompanyid()!=null)isNull=true;
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
        if(StringUtils.isNotEmpty(baseInformation.getUserphoto()) && "null".equals(baseInformation.getUserphoto()))baseInformation.setUserphoto(null);
        if(StringUtils.isNotEmpty(baseInformation.getIdphoto1()) && "null".equals(baseInformation.getIdphoto1()))baseInformation.setIdphoto1(null);
        if(StringUtils.isNotEmpty(baseInformation.getIdphoto2()) && "null".equals(baseInformation.getIdphoto2()))baseInformation.setIdphoto2(null);
        if(StringUtils.isNotEmpty(baseInformation.getEnglishname()) && "null".equals(baseInformation.getEnglishname()))baseInformation.setEnglishname(null);
        if(StringUtils.isNotEmpty(baseInformation.getIdcode()) && "null".equals(baseInformation.getIdcode()))baseInformation.setIdcode(null);
        if(StringUtils.isNotEmpty(baseInformation.getBirthday()) && "null".equals(baseInformation.getBirthday()))baseInformation.setBirthday(null);
        if(StringUtils.isNotEmpty(baseInformation.getConstellation()) && "null".equals(baseInformation.getConstellation()))baseInformation.setConstellation(null);
        if(StringUtils.isNotEmpty(baseInformation.getChinesecs()) && "null".equals(baseInformation.getChinesecs()))baseInformation.setChinesecs(null);
        if(StringUtils.isNotEmpty(baseInformation.getMarriage()) && "null".equals(baseInformation.getMarriage()))baseInformation.setMarriage(null);
        if(StringUtils.isNotEmpty(baseInformation.getFirstworkingtime()) && "null".equals(baseInformation.getFirstworkingtime()))baseInformation.setFirstworkingtime(null);
        if(StringUtils.isNotEmpty(baseInformation.getHj()) && "null".equals(baseInformation.getHj()))baseInformation.setHj(null);
        return baseInformation;
    }
}
