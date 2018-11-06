package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetRankDao;
import com.elex.oa.entity.hr_entity.HRsetRank;
import com.elex.oa.service.hr_service.IHRsetRankService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:职级
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetRankServiceImpl implements IHRsetRankService{
    @Autowired
    IHRsetRankDao ihRsetRankDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetRank hRsetRank) {
        Integer integer = ihRsetRankDao.insertOne(hRsetRank);
        return hRsetRank.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetRank> queryAll() {
        List<HRsetRank> hRsetRanks = ihRsetRankDao.selectAll();
        return hRsetRanks;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetRank> queryByConditions(HRsetRank hRsetRank) {
        List<HRsetRank> hRsetRanks = ihRsetRankDao.selectByConditions(hRsetRank);
        return hRsetRanks;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetRank queryById(Integer id) {
        HRsetRank hRsetRank = new HRsetRank();
        hRsetRank.setId(id);
        List<HRsetRank> hRsetRanks = ihRsetRankDao.selectByConditions(hRsetRank);
        if(hRsetRanks.size()==1){
            return hRsetRanks.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetRank queryByRank(String rank) {
        HRsetRank hRsetRank = new HRsetRank();
        hRsetRank.setRank(rank);
        List<HRsetRank> hRsetRanks = ihRsetRankDao.selectByConditions(hRsetRank);
        if(hRsetRanks.size()==1){
            return hRsetRanks.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetRank> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetRank> hRsetRanks = ihRsetRankDao.selectAll();
        return new PageInfo<HRsetRank>(hRsetRanks);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetRankDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetRank hRsetRank) {
        ihRsetRankDao.updateOne(hRsetRank);
    }
}
