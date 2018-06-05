package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IHRsetSsbgscdDao;
import com.elex.oa.entity.entity_shiyun.HRsetSsbgscd;
import com.elex.oa.service.service_shiyun.IHRsetSsbgscdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:社保公司缴费比例
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetSsbgscdServiceImpl implements IHRsetSsbgscdService{
    @Autowired
    IHRsetSsbgscdDao ihRsetSsbgscdDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetSsbgscd hRsetSsbgscd) {
        Integer integer = ihRsetSsbgscdDao.insertOne(hRsetSsbgscd);
        return hRsetSsbgscd.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetSsbgscd> queryAll() {
        List<HRsetSsbgscd> hRsetSsbgscds = ihRsetSsbgscdDao.selectAll();
        return hRsetSsbgscds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetSsbgscd> queryByConditions(HRsetSsbgscd hRsetSsbgscd) {
        List<HRsetSsbgscd> hRsetSsbgscds = ihRsetSsbgscdDao.selectByConditions(hRsetSsbgscd);
        return hRsetSsbgscds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetSsbgscd queryById(Integer id) {
        HRsetSsbgscd hRsetSsbgscd = new HRsetSsbgscd();
        hRsetSsbgscd.setId(id);
        List<HRsetSsbgscd> hRsetSsbgscds = ihRsetSsbgscdDao.selectByConditions(hRsetSsbgscd);
        if(hRsetSsbgscds.size()==1){
            return hRsetSsbgscds.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetSsbgscd queryBySsbgscd(String ssbgscd) {
        HRsetSsbgscd hRsetSsbgscd = new HRsetSsbgscd();
        hRsetSsbgscd.setSsbgscd(ssbgscd);
        List<HRsetSsbgscd> hRsetSsbgscds = ihRsetSsbgscdDao.selectByConditions(hRsetSsbgscd);
        if(hRsetSsbgscds.size()==1){
            return hRsetSsbgscds.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetSsbgscd> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetSsbgscd> hRsetSsbgscds = ihRsetSsbgscdDao.selectAll();
        return new PageInfo<HRsetSsbgscd>(hRsetSsbgscds);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetSsbgscdDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetSsbgscd hRsetSsbgscd) {
        ihRsetSsbgscdDao.updateOne(hRsetSsbgscd);
    }
}
