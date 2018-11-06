package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetGjjDao;
import com.elex.oa.entity.hr_entity.HRsetGjj;
import com.elex.oa.service.hr_service.IHRsetGjjService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:公积金基数
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetGjjServiceImpl implements IHRsetGjjService{
    @Autowired
    IHRsetGjjDao ihRsetGjjDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetGjj hRsetGjj) {
        Integer integer = ihRsetGjjDao.insertOne(hRsetGjj);
        return hRsetGjj.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetGjj> queryAll() {
        List<HRsetGjj> hRsetGjjs = ihRsetGjjDao.selectAll();
        return hRsetGjjs;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetGjj> queryByConditions(HRsetGjj hRsetGjj) {
            List<HRsetGjj> hRsetGjjs = ihRsetGjjDao.selectByConditions(hRsetGjj);
        return hRsetGjjs;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetGjj queryById(Integer id) {
        HRsetGjj hRsetGjj = new HRsetGjj();
        hRsetGjj.setId(id);
            List<HRsetGjj> hRsetGjjs = ihRsetGjjDao.selectByConditions(hRsetGjj);
        if(hRsetGjjs.size()==1){
            return hRsetGjjs.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetGjj queryByGjj(String gjj) {
        HRsetGjj hRsetGjj = new HRsetGjj();
        hRsetGjj.setGjj(gjj);
        List<HRsetGjj> hRsetGjjs = ihRsetGjjDao.selectByConditions(hRsetGjj);
        if(hRsetGjjs.size()==1){
            return hRsetGjjs.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetGjj> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetGjj> hRsetGjjs = ihRsetGjjDao.selectAll();
            return new PageInfo<HRsetGjj>(hRsetGjjs);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetGjjDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetGjj hRsetGjj) {
        ihRsetGjjDao.updateOne(hRsetGjj);
    }
}
