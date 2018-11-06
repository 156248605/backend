package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetPyfsDao;
import com.elex.oa.entity.hr_entity.HRsetPyfs;
import com.elex.oa.service.hr_service.IHRsetPyfsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:培养方式
 * @Date:Created in  10:22 2018\5\12 0012
 * @Modify By:
 */
@Service
public class HRsetPyfsServiceImpl implements IHRsetPyfsService {
    @Autowired
    IHRsetPyfsDao ihRsetPyfsDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 10:23 2018\5\12 0012
     */
    @Override
    public Integer addOne(HRsetPyfs hRsetPyfs) {
        Integer integer = ihRsetPyfsDao.insertOne(hRsetPyfs);
        return hRsetPyfs.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 10:24 2018\5\12 0012
     */
    @Override
    public List<HRsetPyfs> queryAll() {
        List<HRsetPyfs> hRsetPyfs = ihRsetPyfsDao.selectAll();
        return hRsetPyfs;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetPyfs> queryByConditions(HRsetPyfs hRsetPyfs) {
        List<HRsetPyfs> hRsetPyfs1 = ihRsetPyfsDao.selectByConditions(hRsetPyfs);
        return hRsetPyfs1;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetPyfs queryById(Integer id) {
        HRsetPyfs hRsetPyfs = new HRsetPyfs();
        hRsetPyfs.setId(id);
        List<HRsetPyfs> hRsetPyfs1 = ihRsetPyfsDao.selectByConditions(hRsetPyfs);
        if(hRsetPyfs1.size()==1){
            return hRsetPyfs1.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetPyfs queryByPyfs(String pyfs) {
        HRsetPyfs hRsetPyfs = new HRsetPyfs();
        hRsetPyfs.setPyfs(pyfs);
        List<HRsetPyfs> hRsetPyfs1 = ihRsetPyfsDao.selectByConditions(hRsetPyfs);
        if(hRsetPyfs1.size()==1){
            return hRsetPyfs1.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetPyfs> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetPyfs> hRsetPyfs = ihRsetPyfsDao.selectAll();
        return new PageInfo<HRsetPyfs>(hRsetPyfs);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetPyfsDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetPyfs hRsetPyfs) {
        ihRsetPyfsDao.updateOne(hRsetPyfs);
    }
}
