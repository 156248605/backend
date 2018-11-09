package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetByyxDao;
import com.elex.oa.entity.hr_entity.HRsetByyx;
import com.elex.oa.service.hr_service.IHRsetByyxService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:毕业院校
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetByyxServiceImpl implements IHRsetByyxService{
    @Autowired
    IHRsetByyxDao ihRsetByyxDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetByyx hRsetByyx) {
        Integer integer = ihRsetByyxDao.insertOne(hRsetByyx);
        return hRsetByyx.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetByyx> queryAll() {
        List<HRsetByyx> hRsetByyxes = ihRsetByyxDao.selectAll();
        return hRsetByyxes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetByyx> queryByConditions(HRsetByyx hRsetByyx) {
        List<HRsetByyx> hRsetByyxes = ihRsetByyxDao.selectByConditions(hRsetByyx);
        return hRsetByyxes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetByyx queryById(Integer id) {
        HRsetByyx hRsetByyx = new HRsetByyx();
        hRsetByyx.setId(id);
        List<HRsetByyx> hRsetByyxes = ihRsetByyxDao.selectByConditions(hRsetByyx);
        if(hRsetByyxes.size()==1){
            return hRsetByyxes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetByyx queryByByyx(String byyx) {
        HRsetByyx hRsetByyx = new HRsetByyx();
        hRsetByyx.setByyx(byyx);
        List<HRsetByyx> hRsetByyxes = ihRsetByyxDao.selectByConditions(hRsetByyx);
        if(hRsetByyxes.size()==1){
            return hRsetByyxes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetByyx> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetByyx> hRsetByyxes = ihRsetByyxDao.selectAll();
        return new PageInfo<HRsetByyx>(hRsetByyxes);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetByyxDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetByyx hRsetByyx) {
        ihRsetByyxDao.updateOne(hRsetByyx);
    }
}