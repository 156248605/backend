package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetGjjgscdDao;
import com.elex.oa.entity.hr_entity.HRsetGjjgscd;
import com.elex.oa.service.hr_service.IHRsetGjjgscdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:公积金缴费比例
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetGjjgscdServiceImpl implements IHRsetGjjgscdService{
    @Autowired
    IHRsetGjjgscdDao ihRsetGjjgscdDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetGjjgscd hRsetGjjgscd) {
        Integer integer = ihRsetGjjgscdDao.insertOne(hRsetGjjgscd);
        return hRsetGjjgscd.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetGjjgscd> queryAll() {
        List<HRsetGjjgscd> hRsetGjjgscds = ihRsetGjjgscdDao.selectAll();
        return hRsetGjjgscds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetGjjgscd> queryByConditions(HRsetGjjgscd hRsetGjjgscd) {
        List<HRsetGjjgscd> hRsetGjjgscds = ihRsetGjjgscdDao.selectByConditions(hRsetGjjgscd);
        return hRsetGjjgscds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetGjjgscd queryById(Integer id) {
        HRsetGjjgscd hRsetGjjgscd = new HRsetGjjgscd();
        hRsetGjjgscd.setId(id);
        List<HRsetGjjgscd> hRsetGjjgscds = ihRsetGjjgscdDao.selectByConditions(hRsetGjjgscd);
        if(hRsetGjjgscds.size()==1){
            return hRsetGjjgscds.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetGjjgscd queryByGjjgscd(String gjjgscd) {
        HRsetGjjgscd hRsetGjjgscd = new HRsetGjjgscd();
        hRsetGjjgscd.setGjjgscd(gjjgscd);
        List<HRsetGjjgscd> hRsetGjjgscds = ihRsetGjjgscdDao.selectByConditions(hRsetGjjgscd);
        if(hRsetGjjgscds.size()==1){
            return hRsetGjjgscds.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetGjjgscd> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetGjjgscd> hRsetGjjgscds = ihRsetGjjgscdDao.selectAll();
        return new PageInfo<HRsetGjjgscd>(hRsetGjjgscds);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetGjjgscdDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetGjjgscd hRsetGjjgscd) {
        ihRsetGjjgscdDao.updateOne(hRsetGjjgscd);
    }
}
