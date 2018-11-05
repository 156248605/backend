package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr_shiyun.IHRsetFunctionalTypeDao;
import com.elex.oa.entity.hr_entity.HRsetFunctionalType;
import com.elex.oa.service.hr_service.IHRsetFunctionalTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description: HR设置（职能类型）业务层
 * @Date:Created in  17:02 2018\5\10 0010
 * @Modify By:
 */
@Service
public class HRsetFunctionalTypeServiceImpl implements IHRsetFunctionalTypeService {
    @Autowired
    IHRsetFunctionalTypeDao iDepFunctionalTypeDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条职能类型信息并返回主键
     *@Date: 17:03 2018\5\10 0010
     */
    @Override
    public Integer addOne(HRsetFunctionalType depFunctionalType) {
        Integer integer = iDepFunctionalTypeDao.insertOne(depFunctionalType);
        return depFunctionalType.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的职能类型信息
     *@Date: 17:04 2018\5\10 0010
     */
    @Override
    public List<HRsetFunctionalType> queryAll() {
        List<HRsetFunctionalType> depFunctionalTypes = iDepFunctionalTypeDao.selectAll();
        return depFunctionalTypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询职能信息
     *@Date: 9:32 2018\5\14 0014
     */
    @Override
    public List<HRsetFunctionalType> queryByCondtions(HRsetFunctionalType hRsetFunctionalType) {
        List<HRsetFunctionalType> hRsetFunctionalTypes = iDepFunctionalTypeDao.selectByConditions(hRsetFunctionalType);
        return hRsetFunctionalTypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:22 2018\5\14 0014
     */
    @Override
    public HRsetFunctionalType queryById(Integer id) {
        HRsetFunctionalType hRsetFunctionalType = new HRsetFunctionalType();
        hRsetFunctionalType.setId(id);
        List<HRsetFunctionalType> hRsetFunctionalTypes = iDepFunctionalTypeDao.selectByConditions(hRsetFunctionalType);
        if(hRsetFunctionalTypes.size()==1){
            return hRsetFunctionalTypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据职能类型查询对象
     *@Date: 10:24 2018\5\14 0014
     */
    @Override
    public HRsetFunctionalType queryByFuctionaltype(String functionaltype) {
        HRsetFunctionalType hRsetFunctionalType = new HRsetFunctionalType();
        hRsetFunctionalType.setFunctionaltype(functionaltype);
        List<HRsetFunctionalType> hRsetFunctionalTypes = iDepFunctionalTypeDao.selectByConditions(hRsetFunctionalType);
        if(hRsetFunctionalTypes.size()==1){
            return hRsetFunctionalTypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetFunctionalType> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetFunctionalType> depFunctionalTypes = iDepFunctionalTypeDao.selectAll();
        return new PageInfo<HRsetFunctionalType>(depFunctionalTypes);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        iDepFunctionalTypeDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetFunctionalType hRsetFunctionalType) {
        iDepFunctionalTypeDao.updateOne(hRsetFunctionalType);
    }
}
