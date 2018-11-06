package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetSsbgrcdDao;
import com.elex.oa.entity.hr_entity.HRsetSsbgrcd;
import com.elex.oa.service.hr_service.IHRsetSsbgrcdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:社保个人缴费比例
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetSsbgrcdServiceImpl implements IHRsetSsbgrcdService{
    @Autowired
    IHRsetSsbgrcdDao ihRsetSsbgrcdDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetSsbgrcd hRsetSsbgrcd) {
        Integer integer = ihRsetSsbgrcdDao.insertOne(hRsetSsbgrcd);
        return hRsetSsbgrcd.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetSsbgrcd> queryAll() {
        List<HRsetSsbgrcd> hRsetSsbgrcds = ihRsetSsbgrcdDao.selectAll();
        return hRsetSsbgrcds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetSsbgrcd> queryByConditions(HRsetSsbgrcd hRsetSsbgrcd) {
        List<HRsetSsbgrcd> hRsetSsbgrcds = ihRsetSsbgrcdDao.selectByConditions(hRsetSsbgrcd);
        return hRsetSsbgrcds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetSsbgrcd queryById(Integer id) {
        HRsetSsbgrcd hRsetSsbgrcd = new HRsetSsbgrcd();
        hRsetSsbgrcd.setId(id);
        List<HRsetSsbgrcd> hRsetSsbgrcds = ihRsetSsbgrcdDao.selectByConditions(hRsetSsbgrcd);
        if(hRsetSsbgrcds.size()==1){
            return hRsetSsbgrcds.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetSsbgrcd queryBySsbgrcd(String ssbgrcd) {
        HRsetSsbgrcd hRsetSsbgrcd = new HRsetSsbgrcd();
        hRsetSsbgrcd.setSsbgrcd(ssbgrcd);
        List<HRsetSsbgrcd> hRsetSsbgrcds = ihRsetSsbgrcdDao.selectByConditions(hRsetSsbgrcd);
        if(hRsetSsbgrcds.size()==1){
            return hRsetSsbgrcds.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetSsbgrcd> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetSsbgrcd> hRsetSsbgrcds = ihRsetSsbgrcdDao.selectAll();
        return new PageInfo<HRsetSsbgrcd>(hRsetSsbgrcds);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetSsbgrcdDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetSsbgrcd hRsetSsbgrcd) {
        ihRsetSsbgrcdDao.updateOne(hRsetSsbgrcd);
    }
}
