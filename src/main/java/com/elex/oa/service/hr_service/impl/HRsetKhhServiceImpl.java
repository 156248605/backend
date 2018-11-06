package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetKhhDao;
import com.elex.oa.entity.hr_entity.HRsetKhh;
import com.elex.oa.service.hr_service.IHRsetKhhService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:开户行
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetKhhServiceImpl implements IHRsetKhhService{
    @Autowired
    IHRsetKhhDao ihRsetKhhDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetKhh hRsetKhh) {
        Integer integer = ihRsetKhhDao.insertOne(hRsetKhh);
        return hRsetKhh.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetKhh> queryAll() {
        List<HRsetKhh> hRsetKhhs = ihRsetKhhDao.selectAll();
        return hRsetKhhs;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetKhh> queryByConditions(HRsetKhh hRsetKhh) {
        List<HRsetKhh> hRsetKhhs = ihRsetKhhDao.selectByConditions(hRsetKhh);
        return hRsetKhhs;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetKhh queryById(Integer id) {
        HRsetKhh hRsetKhh = new HRsetKhh();
        hRsetKhh.setId(id);
        List<HRsetKhh> hRsetKhhs = ihRsetKhhDao.selectByConditions(hRsetKhh);
        if(hRsetKhhs.size()==1){
            return hRsetKhhs.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetKhh queryByKhh(String khh) {
        HRsetKhh hRsetKhh = new HRsetKhh();
        hRsetKhh.setKhh(khh);
        List<HRsetKhh> hRsetKhhs = ihRsetKhhDao.selectByConditions(hRsetKhh);
        if(hRsetKhhs.size()==1){
            return hRsetKhhs.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetKhh> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetKhh> hRsetKhhs = ihRsetKhhDao.selectAll();
        return new PageInfo<HRsetKhh>(hRsetKhhs);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetKhhDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetKhh hRsetKhh) {
        ihRsetKhhDao.updateOne(hRsetKhh);
    }
}
