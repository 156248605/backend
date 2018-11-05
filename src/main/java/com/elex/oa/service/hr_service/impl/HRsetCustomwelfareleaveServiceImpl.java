package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr_shiyun.IHRsetCustomwelfareleaveDao;
import com.elex.oa.entity.hr_entity.HRsetCustomwelfareleave;
import com.elex.oa.service.hr_service.IHRsetCustomwelfareleaveService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:自定义福利假
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetCustomwelfareleaveServiceImpl implements IHRsetCustomwelfareleaveService{
    @Autowired
    IHRsetCustomwelfareleaveDao ihRsetCustomwelfareleaveDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetCustomwelfareleave hRsetCustomwelfareleave) {
        Integer integer = ihRsetCustomwelfareleaveDao.insertOne(hRsetCustomwelfareleave);
        return hRsetCustomwelfareleave.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetCustomwelfareleave> queryAll() {
        List<HRsetCustomwelfareleave> hRsetCustomwelfareleaveList = ihRsetCustomwelfareleaveDao.selectAll();
        return hRsetCustomwelfareleaveList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetCustomwelfareleave> queryByConditions(HRsetCustomwelfareleave hRsetCustomwelfareleave) {
        List<HRsetCustomwelfareleave> hRsetCustomwelfareleaveList = ihRsetCustomwelfareleaveDao.selectByConditions(hRsetCustomwelfareleave);
        return hRsetCustomwelfareleaveList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetCustomwelfareleave queryById(Integer id) {
        HRsetCustomwelfareleave hRsetCustomwelfareleave = new HRsetCustomwelfareleave();
        hRsetCustomwelfareleave.setId(id);
        List<HRsetCustomwelfareleave> hRsetCustomwelfareleaveList = ihRsetCustomwelfareleaveDao.selectByConditions(hRsetCustomwelfareleave);
        if(hRsetCustomwelfareleaveList.size()==1){
            return hRsetCustomwelfareleaveList.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetCustomwelfareleave queryByCustomwelfareleave(String customwelfareleave) {
        HRsetCustomwelfareleave hRsetCustomwelfareleave = new HRsetCustomwelfareleave();
        hRsetCustomwelfareleave.setCustomwelfareleave(customwelfareleave);
        List<HRsetCustomwelfareleave> hRsetCustomwelfareleaveList = ihRsetCustomwelfareleaveDao.selectByConditions(hRsetCustomwelfareleave);
        if(hRsetCustomwelfareleaveList.size()==1){
            return hRsetCustomwelfareleaveList.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetCustomwelfareleave> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetCustomwelfareleave> hRsetCustomwelfareleaveList = ihRsetCustomwelfareleaveDao.selectAll();
        return new PageInfo<HRsetCustomwelfareleave>(hRsetCustomwelfareleaveList);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetCustomwelfareleaveDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetCustomwelfareleave hRsetCustomwelfareleave) {
        ihRsetCustomwelfareleaveDao.updateOne(hRsetCustomwelfareleave);
    }
}
