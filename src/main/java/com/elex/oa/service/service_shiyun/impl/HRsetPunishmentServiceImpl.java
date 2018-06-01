package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IHRsetPunishmentDao;
import com.elex.oa.entity.entity_shiyun.HRsetPunishment;
import com.elex.oa.service.service_shiyun.IHRsetPunishmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:惩罚
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetPunishmentServiceImpl implements IHRsetPunishmentService{
    @Autowired
    IHRsetPunishmentDao ihRsetPunishmentDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetPunishment hRsetPunishment) {
        Integer integer = ihRsetPunishmentDao.insertOne(hRsetPunishment);
        return hRsetPunishment.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetPunishment> queryAll() {
        List<HRsetPunishment> hRsetPunishments = ihRsetPunishmentDao.selectAll();
        return hRsetPunishments;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetPunishment> queryByConditions(HRsetPunishment hRsetPunishment) {
        List<HRsetPunishment> hRsetPunishments = ihRsetPunishmentDao.selectByConditions(hRsetPunishment);
        return hRsetPunishments;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetPunishment queryById(Integer id) {
        HRsetPunishment hRsetPunishment = new HRsetPunishment();
        hRsetPunishment.setId(id);
        List<HRsetPunishment> hRsetPunishments = ihRsetPunishmentDao.selectByConditions(hRsetPunishment);
        if(hRsetPunishments.size()==1){
            return hRsetPunishments.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetPunishment queryByPunishment(String punishment) {
        HRsetPunishment hRsetPunishment = new HRsetPunishment();
        hRsetPunishment.setPunishment(punishment);
        List<HRsetPunishment> hRsetPunishments = ihRsetPunishmentDao.selectByConditions(hRsetPunishment);
        if(hRsetPunishments.size()==1){
            return hRsetPunishments.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetPunishment> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetPunishment> hRsetPunishments = ihRsetPunishmentDao.selectAll();
        return new PageInfo<HRsetPunishment>(hRsetPunishments);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetPunishmentDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetPunishment hRsetPunishment) {
        ihRsetPunishmentDao.updateOne(hRsetPunishment);
    }
}
