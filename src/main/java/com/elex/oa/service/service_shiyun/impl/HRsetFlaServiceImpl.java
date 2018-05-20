package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IHRsetFlaDao;
import com.elex.oa.entity.entity_shiyun.HRsetFla;
import com.elex.oa.service.service_shiyun.IHRsetFlaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:外语
 * @Date:Created in  10:43 2018\5\12 0012
 * @Modify By:
 */
@Service
public class HRsetFlaServiceImpl implements IHRsetFlaService {
    @Autowired
    IHRsetFlaDao ihRsetFlaDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 10:44 2018\5\12 0012
     */
    @Override
    public Integer addOne(HRsetFla hRsetFla) {
        Integer integer = ihRsetFlaDao.insertOne(hRsetFla);
        return hRsetFla.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 10:45 2018\5\12 0012
     */
    @Override
    public List<HRsetFla> queryAll() {
        List<HRsetFla> hRsetFlas = ihRsetFlaDao.selectAll();
        return hRsetFlas;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetFla> queryByConditions(HRsetFla hRsetFla) {
        List<HRsetFla> hRsetFlas = ihRsetFlaDao.selectByConditions(hRsetFla);
        return hRsetFlas;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetFla queryById(Integer id) {
        HRsetFla hRsetFla = new HRsetFla();
        hRsetFla.setId(id);
        List<HRsetFla> hRsetFlas = ihRsetFlaDao.selectByConditions(hRsetFla);
        if(hRsetFlas.size()==1){
            return hRsetFlas.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetFla queryByFla(String fla) {
        HRsetFla hRsetFla = new HRsetFla();
        hRsetFla.setFla(fla);
        List<HRsetFla> hRsetFlas = ihRsetFlaDao.selectByConditions(hRsetFla);
        if(hRsetFlas.size()==1){
            return hRsetFlas.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetFla> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetFla> depFunctionalTypes = ihRsetFlaDao.selectAll();
        return new PageInfo<HRsetFla>(depFunctionalTypes);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetFlaDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetFla hRsetFla) {
        ihRsetFlaDao.updateOne(hRsetFla);
    }
}
