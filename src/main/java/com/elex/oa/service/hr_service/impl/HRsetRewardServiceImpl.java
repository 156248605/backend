package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetRewardDao;
import com.elex.oa.entity.hr_entity.HRsetReward;
import com.elex.oa.service.hr_service.IHRsetRewardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:奖励
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetRewardServiceImpl implements IHRsetRewardService{
    @Autowired
    IHRsetRewardDao ihRsetRewardDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetReward hRsetReward) {
        Integer integer = ihRsetRewardDao.insertOne(hRsetReward);
        return hRsetReward.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetReward> queryAll() {
        List<HRsetReward> hRsetRewards = ihRsetRewardDao.selectAll();
        return hRsetRewards;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetReward> queryByConditions(HRsetReward hRsetReward) {
        List<HRsetReward> hRsetRewards = ihRsetRewardDao.selectByConditions(hRsetReward);
        return hRsetRewards;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetReward queryById(Integer id) {
        HRsetReward hRsetReward = new HRsetReward();
        hRsetReward.setId(id);
        List<HRsetReward> hRsetRewards = ihRsetRewardDao.selectByConditions(hRsetReward);
        if(hRsetRewards.size()==1){
            return hRsetRewards.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetReward queryByReward(String reward) {
        HRsetReward hRsetReward = new HRsetReward();
        hRsetReward.setReward(reward);
        List<HRsetReward> hRsetRewards = ihRsetRewardDao.selectByConditions(hRsetReward);
        if(hRsetRewards.size()==1){
            return hRsetRewards.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetReward> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetReward> hRsetRewards = ihRsetRewardDao.selectAll();
        return new PageInfo<HRsetReward>(hRsetRewards);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetRewardDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetReward hRsetReward) {
        ihRsetRewardDao.updateOne(hRsetReward);
    }
}
