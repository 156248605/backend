package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr_shiyun.IHRsetCoursetypeDao;
import com.elex.oa.entity.hr_entity.HRsetCoursetype;
import com.elex.oa.service.hr_service.IHRsetCoursetypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:课程类别
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetCoursetypeServiceImpl implements IHRsetCoursetypeService{
    @Autowired
    IHRsetCoursetypeDao ihRsetCoursetypeDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetCoursetype hRsetCoursetype) {
        Integer integer = ihRsetCoursetypeDao.insertOne(hRsetCoursetype);
        return hRsetCoursetype.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetCoursetype> queryAll() {
        List<HRsetCoursetype> hRsetCoursetypeList = ihRsetCoursetypeDao.selectAll();
        return hRsetCoursetypeList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetCoursetype> queryByConditions(HRsetCoursetype hRsetCoursetype) {
        List<HRsetCoursetype> hRsetCoursetypeList = ihRsetCoursetypeDao.selectByConditions(hRsetCoursetype);
        return hRsetCoursetypeList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetCoursetype queryById(Integer id) {
        HRsetCoursetype hRsetCoursetype = new HRsetCoursetype();
        hRsetCoursetype.setId(id);
        List<HRsetCoursetype> hRsetCoursetypeList = ihRsetCoursetypeDao.selectByConditions(hRsetCoursetype);
        if(hRsetCoursetypeList.size()==1){
            return hRsetCoursetypeList.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetCoursetype queryByCoursetype(String coursetype) {
        HRsetCoursetype hRsetCoursetype = new HRsetCoursetype();
        hRsetCoursetype.setCoursetype(coursetype);
        List<HRsetCoursetype> hRsetCoursetypeList = ihRsetCoursetypeDao.selectByConditions(hRsetCoursetype);
        if(hRsetCoursetypeList.size()==1){
            return hRsetCoursetypeList.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetCoursetype> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetCoursetype> hRsetCoursetypeList = ihRsetCoursetypeDao.selectAll();
        return new PageInfo<HRsetCoursetype>(hRsetCoursetypeList);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetCoursetypeDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetCoursetype hRsetCoursetype) {
        ihRsetCoursetypeDao.updateOne(hRsetCoursetype);
    }
}
