package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.service.hr_service.IHRsetService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: HR设置
 * @Author shiyun
 * @Date 2018\11\6 0006 17:23
 * @Version 1.0
 **/
@Service
public class HRsetServiceImpl implements IHRsetService {
    @Resource
    private IHRsetDao ihRsetDao;

    /**
     * @Author: shiyun
     * @Description: TODO
     * @Date  2018\11\6 0006 17:29
     * @Param [hRset]
     * @return java.lang.Object
     **/
    @Override
    public Object addOne(HRset hRset) {
        ihRsetDao.insertOne(hRset);
        return hRset.getId();
    }

    /**
     * @Author: shiyun
     * @Description: TODO
     * @Date  2018\11\6 0006 17:42
     * @Param []
     * @return java.lang.Object
     **/
    @Override
    public Object queryAll() {
        List<HRset> hRsetList = ihRsetDao.selectAll();
        return hRsetList;
    }

    @Override
    public List<HRset> queryByConditions(HRset hRset) {
        return ihRsetDao.selectByConditions(hRset);
    }

    @Override
    public Object queryById(Integer id) {
        List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset(id));
        return hRsetList.size()==0?null:hRsetList.get(0);
    }

    @Override
    public PageInfo queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        HRset hRset = (HRset) paramMap.get("entity");
        PageHelper.startPage(pageNum, pageSize);
        List<HRset> hRsetList = ihRsetDao.selectByConditions(hRset);
        return new PageInfo<HRset>(hRsetList);
    }

    @Override
    public Object removeOne(Integer id) {
        ihRsetDao.deleteOne(id);
        return true;
    }

    @Override
    public Boolean modifyOne(HRset hRset) {
        try {
            ihRsetDao.updateOne(hRset);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}