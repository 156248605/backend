package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetChildrenDao;
import com.elex.oa.entity.hr_entity.HRsetChildren;
import com.elex.oa.service.hr_service.IHRsetChildrenService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（生育）
 * @Date:Created in  17:16 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetChildrenServiceImpl implements IHRsetChildrenService{
    @Autowired
    IHRsetChildrenDao ihRsetChildrenDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 17:17 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetChildren hRsetChildren) {
        Integer integer = ihRsetChildrenDao.insertOne(hRsetChildren);
        return hRsetChildren.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 17:17 2018\5\11 0011
     */
    @Override
    public List<HRsetChildren> queryAll() {
        List<HRsetChildren> hRsetChildren = ihRsetChildrenDao.selectAll();
        return hRsetChildren;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetChildren> queryByConditions(HRsetChildren hRsetChildren) {
        List<HRsetChildren> hRsetChildren1 = ihRsetChildrenDao.selectByConditions(hRsetChildren);
        return hRsetChildren1;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetChildren queryById(Integer id) {
        HRsetChildren hRsetChildren = new HRsetChildren();
        hRsetChildren.setId(id);
        List<HRsetChildren> hRsetChildren1 = ihRsetChildrenDao.selectByConditions(hRsetChildren);
        if(hRsetChildren1.size()==1){
            return hRsetChildren1.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetChildren queryByChildren(String children) {
        HRsetChildren hRsetChildren = new HRsetChildren();
        hRsetChildren.setChildren(children);
        List<HRsetChildren> hRsetChildren1 = ihRsetChildrenDao.selectByConditions(hRsetChildren);
        if(hRsetChildren1.size()==1){
            return hRsetChildren1.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetChildren> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetChildren> hRsetChildren = ihRsetChildrenDao.selectAll();
        return new PageInfo<HRsetChildren>(hRsetChildren);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetChildrenDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetChildren hRsetChildren) {
        ihRsetChildrenDao.updateOne(hRsetChildren);
    }
}
