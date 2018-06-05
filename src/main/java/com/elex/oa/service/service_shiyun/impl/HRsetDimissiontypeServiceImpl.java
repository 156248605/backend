package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IHRsetDimissiontypeDao;
import com.elex.oa.entity.entity_shiyun.HRsetDimissiontype;
import com.elex.oa.service.service_shiyun.IHRsetDimissiontypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:离职类型
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetDimissiontypeServiceImpl implements IHRsetDimissiontypeService{
    @Autowired
    IHRsetDimissiontypeDao ihRsetDimissiontypeDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetDimissiontype hRsetDimissiontype) {
        Integer integer = ihRsetDimissiontypeDao.insertOne(hRsetDimissiontype);
        return hRsetDimissiontype.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetDimissiontype> queryAll() {
        List<HRsetDimissiontype> hRsetDimissiontypes = ihRsetDimissiontypeDao.selectAll();
        return hRsetDimissiontypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetDimissiontype> queryByConditions(HRsetDimissiontype hRsetDimissiontype) {
        List<HRsetDimissiontype> hRsetDimissiontypes = ihRsetDimissiontypeDao.selectByConditions(hRsetDimissiontype);
        return hRsetDimissiontypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetDimissiontype queryById(Integer id) {
        HRsetDimissiontype hRsetDimissiontype = new HRsetDimissiontype();
        hRsetDimissiontype.setId(id);
        List<HRsetDimissiontype> hRsetDimissiontypes = ihRsetDimissiontypeDao.selectByConditions(hRsetDimissiontype);
        if(hRsetDimissiontypes.size()==1){
            return hRsetDimissiontypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetDimissiontype queryByDimissiontype(String Dimissiontype) {
        HRsetDimissiontype hRsetDimissiontype = new HRsetDimissiontype();
        hRsetDimissiontype.setDimissiontype(Dimissiontype);
        List<HRsetDimissiontype> hRsetDimissiontypes = ihRsetDimissiontypeDao.selectByConditions(hRsetDimissiontype);
        if(hRsetDimissiontypes.size()==1){
            return hRsetDimissiontypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetDimissiontype> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetDimissiontype> hRsetDimissiontypes = ihRsetDimissiontypeDao.selectAll();
        return new PageInfo<HRsetDimissiontype>(hRsetDimissiontypes);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetDimissiontypeDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetDimissiontype hRsetDimissiontype) {
        ihRsetDimissiontypeDao.updateOne(hRsetDimissiontype);
    }
}
