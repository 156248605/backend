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
import java.util.Map;

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
    public Integer addOne(HRset hRset) {
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
    public List<HRset> queryAll() {
        List<HRset> hRsetList = ihRsetDao.selectAll();
        return hRsetList;
    }

    @Override
    public List<HRset> queryByConditions(HRset hRset) {
        return ihRsetDao.selectByConditions(hRset);
    }

    @Override
    public HRset queryById(Integer id) {
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
    public Boolean removeOne(Integer id) {
        try {
            ihRsetDao.deleteOne(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public HRset modifyOne(HRset hRset) {
        try {
            ihRsetDao.updateOne(hRset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset(hRset.getId()));
        return hRsetList.size()==0?null:hRsetList.get(0);
    }

    @Override
    public Boolean modifyHRset(HRset hRset) {
        System.out.println(hRset.toString());
        try {
            ihRsetDao.updateOne(hRset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset(hRset.getId()));
        return hRsetList.size()==0?false:true;
    }

    /**
     * @Author: shiyun
     * @Description: 删除多个
     * @Date  2018\11\9 0009 13:34
     * @Param [ids]
     * @return java.util.Map<java.lang.Integer,java.lang.String>
     **/
    @Override
    public Map<Integer, String> removeMultiple(List<Integer> ids) {
        Map<Integer,String> map = new HashMap<>();
        for(Integer i=0;i<ids.size();i++){
            Boolean aBoolean = true;
            try {
                ihRsetDao.deleteOne(ids.get(i));
            } catch (Exception e) {
                e.printStackTrace();
                aBoolean = false;
            }
            if(aBoolean){
                map.put(ids.get(i),"删除成功！");
            }else {
                map.put(ids.get(i),"删除失败！");
            }
        }
        return map;
    }

    @Override
    public Boolean queryValidateHRset(HRset hRset) {
        List<HRset> hRsetList = ihRsetDao.selectByConditions(hRset);
        if(hRsetList==null || hRsetList.size()==0){
            return false;
        }
        return true;
    }
}