package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IHRsetZgxlDao;
import com.elex.oa.entity.entity_shiyun.HRsetZgxl;
import com.elex.oa.service.service_shiyun.IHRsetZgxlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:最高学历
 * @Date:Created in  18:06 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetZgxlServiceImpl implements IHRsetZgxlService {
    @Autowired
    IHRsetZgxlDao ihRsetZgxlDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:07 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetZgxl hRsetZgxl) {
        Integer integer = ihRsetZgxlDao.insertOne(hRsetZgxl);
        return hRsetZgxl.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的信息
     *@Date: 18:07 2018\5\11 0011
     */
    @Override
    public List<HRsetZgxl> queryAll() {
        List<HRsetZgxl> hRsetZgxls = ihRsetZgxlDao.selectAll();
        return hRsetZgxls;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetZgxl> queryByConditions(HRsetZgxl hRsetZgxl) {
        List<HRsetZgxl> hRsetZgxls = ihRsetZgxlDao.selectByConditions(hRsetZgxl);
        return hRsetZgxls;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetZgxl queryById(Integer id) {
        HRsetZgxl hRsetZgxl = new HRsetZgxl();
        hRsetZgxl.setId(id);
        List<HRsetZgxl> hRsetZgxls = ihRsetZgxlDao.selectByConditions(hRsetZgxl);
        if(hRsetZgxls.size()==1){
            return hRsetZgxls.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetZgxl queryByZgxl(String zgxl) {
        HRsetZgxl hRsetZgxl = new HRsetZgxl();
        hRsetZgxl.setZgxl(zgxl);
        List<HRsetZgxl> hRsetZgxls = ihRsetZgxlDao.selectByConditions(hRsetZgxl);
        if(hRsetZgxls.size()==1){
            return hRsetZgxls.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetZgxl> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetZgxl> hRsetZgxls = ihRsetZgxlDao.selectAll();
        return new PageInfo<HRsetZgxl>(hRsetZgxls);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetZgxlDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetZgxl hRsetZgxl) {
        ihRsetZgxlDao.updateOne(hRsetZgxl);
    }
}
