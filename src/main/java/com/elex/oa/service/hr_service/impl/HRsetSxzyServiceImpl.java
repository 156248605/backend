package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr_shiyun.IHRsetSxzyDao;
import com.elex.oa.entity.hr_entity.HRsetSxzy;
import com.elex.oa.service.hr_service.IHRsetSxzyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:所学专业
 * @Date:Created in  9:59 2018\5\12 0012
 * @Modify By:
 */
@Service
public class HRsetSxzyServiceImpl implements IHRsetSxzyService {
    @Autowired
    IHRsetSxzyDao ihRsetSxzyDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 10:00 2018\5\12 0012
     */
    @Override
    public Integer addOne(HRsetSxzy hRsetSxzy) {
        Integer integer = ihRsetSxzyDao.insertOne(hRsetSxzy);
        return hRsetSxzy.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 10:00 2018\5\12 0012
     */
    @Override
    public List<HRsetSxzy> queryAll() {
        List<HRsetSxzy> hRsetSxzies = ihRsetSxzyDao.selectAll();
        return hRsetSxzies;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetSxzy> queryByConditions(HRsetSxzy hRsetSxzy) {
        List<HRsetSxzy> hRsetSxzies = ihRsetSxzyDao.selectByConditions(hRsetSxzy);
        return hRsetSxzies;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetSxzy queryById(Integer id) {
        HRsetSxzy hRsetSxzy = new HRsetSxzy();
        hRsetSxzy.setId(id);
        List<HRsetSxzy> hRsetSxzies = ihRsetSxzyDao.selectByConditions(hRsetSxzy);
        if(hRsetSxzies.size()==1){
            return hRsetSxzies.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetSxzy queryBySxzy(String sxzy) {
        HRsetSxzy hRsetSxzy = new HRsetSxzy();
        hRsetSxzy.setSxzy(sxzy);
        List<HRsetSxzy> hRsetSxzies = ihRsetSxzyDao.selectByConditions(hRsetSxzy);
        if(hRsetSxzies.size()==1){
            return hRsetSxzies.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetSxzy> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetSxzy> hRsetSxzies = ihRsetSxzyDao.selectAll();
        return new PageInfo<HRsetSxzy>(hRsetSxzies);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetSxzyDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetSxzy hRsetSxzy) {
        ihRsetSxzyDao.updateOne(hRsetSxzy);
    }
}
