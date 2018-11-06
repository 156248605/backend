package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetGjjgrcdDao;
import com.elex.oa.entity.hr_entity.HRsetGjjgrcd;
import com.elex.oa.service.hr_service.IHRsetGjjgrcdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:公积金个人缴费比例
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetGjjgrcdServiceImpl implements IHRsetGjjgrcdService{
    @Autowired
    IHRsetGjjgrcdDao ihRsetGjjgrcdDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetGjjgrcd hRsetGjjgrcd) {
        Integer integer = ihRsetGjjgrcdDao.insertOne(hRsetGjjgrcd);
        return hRsetGjjgrcd.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetGjjgrcd> queryAll() {
        List<HRsetGjjgrcd> hRsetGjjgrcds = ihRsetGjjgrcdDao.selectAll();
        return hRsetGjjgrcds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetGjjgrcd> queryByConditions(HRsetGjjgrcd hRsetGjjgrcd) {
        List<HRsetGjjgrcd> hRsetGjjgrcds = ihRsetGjjgrcdDao.selectByConditions(hRsetGjjgrcd);
        return hRsetGjjgrcds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetGjjgrcd queryById(Integer id) {
        HRsetGjjgrcd hRsetGjjgrcd = new HRsetGjjgrcd();
        hRsetGjjgrcd.setId(id);
        List<HRsetGjjgrcd> hRsetGjjgrcds = ihRsetGjjgrcdDao.selectByConditions(hRsetGjjgrcd);
        if(hRsetGjjgrcds.size()==1){
            return hRsetGjjgrcds.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetGjjgrcd queryByGjjgrcd(String gjjgrcd) {
        HRsetGjjgrcd hRsetGjjgrcd = new HRsetGjjgrcd();
        hRsetGjjgrcd.setGjjgrcd(gjjgrcd);
        List<HRsetGjjgrcd> hRsetGjjgrcds = ihRsetGjjgrcdDao.selectByConditions(hRsetGjjgrcd);
        if(hRsetGjjgrcds.size()==1){
            return hRsetGjjgrcds.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetGjjgrcd> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetGjjgrcd> hRsetGjjgrcds = ihRsetGjjgrcdDao.selectAll();
        return new PageInfo<HRsetGjjgrcd>(hRsetGjjgrcds);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetGjjgrcdDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetGjjgrcd hRsetGjjgrcd) {
        ihRsetGjjgrcdDao.updateOne(hRsetGjjgrcd);
    }
}
