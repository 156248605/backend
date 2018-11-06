package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetDimissionreasonDao;
import com.elex.oa.entity.hr_entity.HRsetDimissionreason;
import com.elex.oa.service.hr_service.IHRsetDimissionreasonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:离职原因
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetDimissionreasonServiceImpl implements IHRsetDimissionreasonService{
    @Autowired
    IHRsetDimissionreasonDao ihRsetDimissionreasonDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetDimissionreason hRsetDimissionreason) {
        Integer integer = ihRsetDimissionreasonDao.insertOne(hRsetDimissionreason);
        return hRsetDimissionreason.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetDimissionreason> queryAll() {
        List<HRsetDimissionreason> hRsetDimissionreasons = ihRsetDimissionreasonDao.selectAll();
        return hRsetDimissionreasons;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetDimissionreason> queryByConditions(HRsetDimissionreason hRsetDimissionreason) {
        List<HRsetDimissionreason> hRsetDimissionreasons = ihRsetDimissionreasonDao.selectByConditions(hRsetDimissionreason);
        return hRsetDimissionreasons;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetDimissionreason queryById(Integer id) {
        HRsetDimissionreason hRsetDimissionreason = new HRsetDimissionreason();
        hRsetDimissionreason.setId(id);
        List<HRsetDimissionreason> hRsetDimissionreasons = ihRsetDimissionreasonDao.selectByConditions(hRsetDimissionreason);
        if(hRsetDimissionreasons.size()==1){
            return hRsetDimissionreasons.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetDimissionreason queryByDimissionreason(String dimissionreason) {
        HRsetDimissionreason hRsetDimissionreason = new HRsetDimissionreason();
        hRsetDimissionreason.setDimissionreason(dimissionreason);
        List<HRsetDimissionreason> hRsetDimissionreasons = ihRsetDimissionreasonDao.selectByConditions(hRsetDimissionreason);
        if(hRsetDimissionreasons.size()==1){
            return hRsetDimissionreasons.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetDimissionreason> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetDimissionreason> hRsetDimissionreasons = ihRsetDimissionreasonDao.selectAll();
        return new PageInfo<HRsetDimissionreason>(hRsetDimissionreasons);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetDimissionreasonDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetDimissionreason hRsetDimissionreason) {
        ihRsetDimissionreasonDao.updateOne(hRsetDimissionreason);
    }
}
