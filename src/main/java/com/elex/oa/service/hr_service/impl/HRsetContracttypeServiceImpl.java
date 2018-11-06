package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetContracttypeDao;
import com.elex.oa.entity.hr_entity.HRsetContracttype;
import com.elex.oa.service.hr_service.IHRsetContracttypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:合同类型
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetContracttypeServiceImpl implements IHRsetContracttypeService{
    @Autowired
    IHRsetContracttypeDao ihRsetContracttypeDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetContracttype hRsetContracttype) {
        Integer integer = ihRsetContracttypeDao.insertOne(hRsetContracttype);
        return hRsetContracttype.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetContracttype> queryAll() {
        List<HRsetContracttype> hRsetContracttypes = ihRsetContracttypeDao.selectAll();
        return hRsetContracttypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetContracttype> queryByConditions(HRsetContracttype hRsetContracttype) {
        List<HRsetContracttype> hRsetContracttypes = ihRsetContracttypeDao.selectByConditions(hRsetContracttype);
        return hRsetContracttypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetContracttype queryById(Integer id) {
        HRsetContracttype hRsetContracttype = new HRsetContracttype();
        hRsetContracttype.setId(id);
        List<HRsetContracttype> hRsetContracttypes = ihRsetContracttypeDao.selectByConditions(hRsetContracttype);
        if(hRsetContracttypes.size()==1){
            return hRsetContracttypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetContracttype queryByContracttype(String contracttype) {
        HRsetContracttype hRsetContracttype = new HRsetContracttype();
        hRsetContracttype.setContracttype(contracttype);
        List<HRsetContracttype> hRsetContracttypes = ihRsetContracttypeDao.selectByConditions(hRsetContracttype);
        if(hRsetContracttypes.size()==1){
            return hRsetContracttypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetContracttype> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetContracttype> hRsetContracttypes = ihRsetContracttypeDao.selectAll();
        return new PageInfo<HRsetContracttype>(hRsetContracttypes);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetContracttypeDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetContracttype hRsetContracttype) {
        ihRsetContracttypeDao.updateOne(hRsetContracttype);
    }
}
