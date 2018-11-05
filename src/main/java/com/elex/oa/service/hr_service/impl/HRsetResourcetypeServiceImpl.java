package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr_shiyun.IHRsetResourcetypeDao;
import com.elex.oa.entity.hr_entity.HRsetResourcetype;
import com.elex.oa.service.hr_service.IHRsetResourcetypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:资源类别
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetResourcetypeServiceImpl implements IHRsetResourcetypeService{
    @Autowired
    IHRsetResourcetypeDao ihRsetResourcetypeDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetResourcetype hRsetResourcetype) {
        Integer integer = ihRsetResourcetypeDao.insertOne(hRsetResourcetype);
        return hRsetResourcetype.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetResourcetype> queryAll() {
        List<HRsetResourcetype> hRsetResourcetypeList = ihRsetResourcetypeDao.selectAll();
        return hRsetResourcetypeList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetResourcetype> queryByConditions(HRsetResourcetype hRsetResourcetype) {
        List<HRsetResourcetype> hRsetResourcetypeList = ihRsetResourcetypeDao.selectByConditions(hRsetResourcetype);
        return hRsetResourcetypeList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetResourcetype queryById(Integer id) {
        HRsetResourcetype hRsetResourcetype = new HRsetResourcetype();
        hRsetResourcetype.setId(id);
        List<HRsetResourcetype> hRsetResourcetypeList = ihRsetResourcetypeDao.selectByConditions(hRsetResourcetype);
        if(hRsetResourcetypeList.size()==1){
            return hRsetResourcetypeList.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetResourcetype queryByResourcetype(String resourcetype) {
        HRsetResourcetype hRsetResourcetype = new HRsetResourcetype();
        hRsetResourcetype.setResourcetype(resourcetype);
        List<HRsetResourcetype> hRsetResourcetypeList = ihRsetResourcetypeDao.selectByConditions(hRsetResourcetype);
        if(hRsetResourcetypeList.size()==1){
            return hRsetResourcetypeList.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetResourcetype> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetResourcetype> hRsetResourcetypeList = ihRsetResourcetypeDao.selectAll();
        return new PageInfo<HRsetResourcetype>(hRsetResourcetypeList);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetResourcetypeDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetResourcetype hRsetResourcetype) {
        ihRsetResourcetypeDao.updateOne(hRsetResourcetype);
    }
}
