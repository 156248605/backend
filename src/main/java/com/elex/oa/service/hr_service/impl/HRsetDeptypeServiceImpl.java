package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr_shiyun.IHRsetDeptypeDao;
import com.elex.oa.entity.hr_entity.HRsetDeptype;
import com.elex.oa.service.hr_service.IHRsetDeptypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:部门类型
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetDeptypeServiceImpl implements IHRsetDeptypeService{
    @Autowired
    IHRsetDeptypeDao ihRsetDeptypeDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetDeptype hRsetDeptype) {
        Integer integer = ihRsetDeptypeDao.insertOne(hRsetDeptype);
        return hRsetDeptype.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetDeptype> queryAll() {
        List<HRsetDeptype> hRsetDeptypes = ihRsetDeptypeDao.selectAll();
        return hRsetDeptypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetDeptype> queryByConditions(HRsetDeptype hRsetDeptype) {
        List<HRsetDeptype> hRsetDeptypes = ihRsetDeptypeDao.selectByConditions(hRsetDeptype);
        return hRsetDeptypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetDeptype queryById(Integer id) {
        HRsetDeptype hRsetDeptype = new HRsetDeptype();
        hRsetDeptype.setId(id);
        List<HRsetDeptype> hRsetDeptypes = ihRsetDeptypeDao.selectByConditions(hRsetDeptype);
        if(hRsetDeptypes.size()==1){
            return hRsetDeptypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetDeptype queryByDeptype(String deptype) {
        HRsetDeptype hRsetDeptype = new HRsetDeptype();
        hRsetDeptype.setDeptype(deptype);
        List<HRsetDeptype> hRsetDeptypes = ihRsetDeptypeDao.selectByConditions(hRsetDeptype);
        if(hRsetDeptypes.size()==1){
            return hRsetDeptypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetDeptype> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetDeptype> hRsetDeptypes = ihRsetDeptypeDao.selectAll();
        return new PageInfo<HRsetDeptype>(hRsetDeptypes);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetDeptypeDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetDeptype hRsetDeptype) {
        ihRsetDeptypeDao.updateOne(hRsetDeptype);
    }
}
