package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IHRsetEmployeetypeDao;
import com.elex.oa.entity.entity_shiyun.HRsetEmployeetype;
import com.elex.oa.service.service_shiyun.IHRsetEmployeetypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:员工类型
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetEmployeetypeServiceImpl implements IHRsetEmployeetypeService{
    @Autowired
    IHRsetEmployeetypeDao ihRsetEmployeetypeDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetEmployeetype hRsetEmployeetype) {
        Integer integer = ihRsetEmployeetypeDao.insertOne(hRsetEmployeetype);
        return hRsetEmployeetype.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetEmployeetype> queryAll() {
        List<HRsetEmployeetype> hRsetEmployeetypes = ihRsetEmployeetypeDao.selectAll();
        return hRsetEmployeetypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetEmployeetype> queryByConditions(HRsetEmployeetype hRsetEmployeetype) {
        List<HRsetEmployeetype> hRsetEmployeetypes = ihRsetEmployeetypeDao.selectByConditions(hRsetEmployeetype);
        return hRsetEmployeetypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetEmployeetype queryById(Integer id) {
        HRsetEmployeetype hRsetEmployeetype = new HRsetEmployeetype();
        hRsetEmployeetype.setId(id);
        List<HRsetEmployeetype> hRsetEmployeetypes = ihRsetEmployeetypeDao.selectByConditions(hRsetEmployeetype);
        if(hRsetEmployeetypes.size()==1){
            return hRsetEmployeetypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetEmployeetype queryByEmployeetype(String employeetype) {
        HRsetEmployeetype hRsetEmployeetype = new HRsetEmployeetype();
        hRsetEmployeetype.setEmployeetype(employeetype);
        List<HRsetEmployeetype> hRsetEmployeetypes = ihRsetEmployeetypeDao.selectByConditions(hRsetEmployeetype);
        if(hRsetEmployeetypes.size()==1){
            return hRsetEmployeetypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetEmployeetype> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetEmployeetype> hRsetEmployeetypes = ihRsetEmployeetypeDao.selectAll();
        return new PageInfo<HRsetEmployeetype>(hRsetEmployeetypes);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetEmployeetypeDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetEmployeetype hRsetEmployeetype) {
        ihRsetEmployeetypeDao.updateOne(hRsetEmployeetype);
    }
}
