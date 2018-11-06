package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetParentcompanyDao;
import com.elex.oa.entity.hr_entity.HRsetParentcompany;
import com.elex.oa.service.hr_service.IHRsetParentcompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:上家公司
 * @Date:Created in  14:49 2018\5\12 0012
 * @Modify By:
 */
@Service
public class HRsetParentcompanyServiceImpl implements IHRsetParentcompanyService {
    @Autowired
    IHRsetParentcompanyDao ihRsetParentcompanyDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 14:50 2018\5\12 0012
     */
    @Override
    public Integer addOne(HRsetParentcompany hRsetParentcompany) {
        Integer integer = ihRsetParentcompanyDao.insertOne(hRsetParentcompany);
        return hRsetParentcompany.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 14:53 2018\5\12 0012
     */
    @Override
    public List<HRsetParentcompany> queryAll() {
        List<HRsetParentcompany> hRsetParentcompanies = ihRsetParentcompanyDao.selectAll();
        return hRsetParentcompanies;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetParentcompany> queryByConditions(HRsetParentcompany hRsetParentcompany) {
        List<HRsetParentcompany> hRsetParentcompanies = ihRsetParentcompanyDao.selectByConditions(hRsetParentcompany);
        return hRsetParentcompanies;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetParentcompany queryById(Integer id) {
        HRsetParentcompany hRsetParentcompany = new HRsetParentcompany();
        hRsetParentcompany.setId(id);
        List<HRsetParentcompany> hRsetParentcompanies = ihRsetParentcompanyDao.selectByConditions(hRsetParentcompany);
        if(hRsetParentcompanies.size()==1){
            return hRsetParentcompanies.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetParentcompany queryByParentcompanyname(String parentcompanyname) {
        HRsetParentcompany hRsetParentcompany = new HRsetParentcompany();
        hRsetParentcompany.setParentcompanyname(parentcompanyname);
        List<HRsetParentcompany> hRsetParentcompanies = ihRsetParentcompanyDao.selectByConditions(hRsetParentcompany);
        if(hRsetParentcompanies.size()==1){
            return hRsetParentcompanies.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetParentcompany> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetParentcompany> hRsetParentcompanies = ihRsetParentcompanyDao.selectAll();
        return new PageInfo<HRsetParentcompany>(hRsetParentcompanies);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetParentcompanyDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetParentcompany hRsetParentcompany) {
        ihRsetParentcompanyDao.updateOne(hRsetParentcompany);
    }
}
