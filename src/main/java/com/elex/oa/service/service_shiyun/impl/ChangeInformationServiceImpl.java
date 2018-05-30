package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IChangeInformaionDao;
import com.elex.oa.entity.entity_shiyun.ChangeInformation;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.service_shiyun.IChangeInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:变更信息（业务层）
 * @Date:Created in  18:52 2018\4\12 0012
 * @Modify By:
 */
@Service
public class ChangeInformationServiceImpl extends BaseServiceImpl<ChangeInformation> implements IChangeInformationService {
    @Autowired
    IChangeInformaionDao iChangeInformaionDao;

    /**
     *@Author:ShiYun;
     *@Description:添加变更信息并返回主键
     *@Date: 18:53 2018\4\12 0012
     */
    @Override
    public Integer addOne(ChangeInformation changeInformation) {
        Integer changeInformationId = iChangeInformaionDao.insertOne(changeInformation);
        return changeInformationId;
    }

    /**
     *@Author:ShiYun;
     *@Description:变更信息列表
     *@Date: 11:52 2018\4\13 0013
     */
    @Override
    public PageInfo<ChangeInformation> queryAll(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        List<ChangeInformation> changeInformations = iChangeInformaionDao.selectByConditions((ChangeInformation) paramMap.get("entity"));
        PageInfo<ChangeInformation> changeInformationPageInfo = new PageInfo<>(changeInformations);

        return changeInformationPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息（不分页）
     *@Date: 10:43 2018\5\25 0025
     */
    @Override
    public List<ChangeInformation> queryAll() {
        List<ChangeInformation> changeInformations = iChangeInformaionDao.selectAll();
        return changeInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除信息
     *@Date: 10:44 2018\5\25 0025
     */
    @Override
    public void removeOne(Integer id) {
        iChangeInformaionDao.deleteOne(id);
    }
}
