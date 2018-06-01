package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IHRsetDimissiondirectionDao;
import com.elex.oa.entity.entity_shiyun.HRsetDimissiondirection;
import com.elex.oa.service.service_shiyun.IHRsetDimissiondirectionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:离职去向
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetDimissiondirectionServiceImpl implements IHRsetDimissiondirectionService{
    @Autowired
    IHRsetDimissiondirectionDao ihRsetDimissiondirectionDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetDimissiondirection hRsetDimissiondirection) {
        Integer integer = ihRsetDimissiondirectionDao.insertOne(hRsetDimissiondirection);
        return hRsetDimissiondirection.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetDimissiondirection> queryAll() {
        List<HRsetDimissiondirection> hRsetDimissiondirections = ihRsetDimissiondirectionDao.selectAll();
        return hRsetDimissiondirections;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetDimissiondirection> queryByConditions(HRsetDimissiondirection hRsetDimissiondirection) {
        List<HRsetDimissiondirection> hRsetDimissiondirections = ihRsetDimissiondirectionDao.selectByConditions(hRsetDimissiondirection);
        return hRsetDimissiondirections;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetDimissiondirection queryById(Integer id) {
        HRsetDimissiondirection hRsetDimissiondirection = new HRsetDimissiondirection();
        hRsetDimissiondirection.setId(id);
        List<HRsetDimissiondirection> hRsetDimissiondirections = ihRsetDimissiondirectionDao.selectByConditions(hRsetDimissiondirection);
        if(hRsetDimissiondirections.size()==1){
            return hRsetDimissiondirections.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetDimissiondirection queryByDimissiondirection(String dimissiondirection) {
        HRsetDimissiondirection hRsetDimissiondirection = new HRsetDimissiondirection();
        hRsetDimissiondirection.setDimissiondirection(dimissiondirection);
        List<HRsetDimissiondirection> hRsetDimissiondirections = ihRsetDimissiondirectionDao.selectByConditions(hRsetDimissiondirection);
        if(hRsetDimissiondirections.size()==1){
            return hRsetDimissiondirections.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetDimissiondirection> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetDimissiondirection> hRsetDimissiondirections = ihRsetDimissiondirectionDao.selectAll();
        return new PageInfo<HRsetDimissiondirection>(hRsetDimissiondirections);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetDimissiondirectionDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetDimissiondirection hRsetDimissiondirection) {
        ihRsetDimissiondirectionDao.updateOne(hRsetDimissiondirection);
    }
}
