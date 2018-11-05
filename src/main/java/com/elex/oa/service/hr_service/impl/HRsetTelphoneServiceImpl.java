package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr_shiyun.IHRsetTelphoneDao;
import com.elex.oa.entity.hr_entity.HRsetTelphone;
import com.elex.oa.service.hr_service.IHRsetTelphoneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:办公电话
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetTelphoneServiceImpl implements IHRsetTelphoneService{
    @Autowired
    IHRsetTelphoneDao ihRsetTelphoneDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetTelphone hRsetTelphone) {
        Integer integer = ihRsetTelphoneDao.insertOne(hRsetTelphone);
        return hRsetTelphone.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetTelphone> queryAll() {
        List<HRsetTelphone> hRsetTelphones = ihRsetTelphoneDao.selectAll();
        return hRsetTelphones;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetTelphone> queryByConditions(HRsetTelphone hRsetTelphone) {
        List<HRsetTelphone> hRsetTelphones = ihRsetTelphoneDao.selectByConditions(hRsetTelphone);
        return hRsetTelphones;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetTelphone queryById(Integer id) {
        HRsetTelphone hRsetTelphone = new HRsetTelphone();
        hRsetTelphone.setId(id);
        List<HRsetTelphone> hRsetTelphones = ihRsetTelphoneDao.selectByConditions(hRsetTelphone);
        if(hRsetTelphones.size()==1){
            return hRsetTelphones.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetTelphone queryByTelphone(String telphone) {
        HRsetTelphone hRsetTelphone = new HRsetTelphone();
        hRsetTelphone.setTelphone(telphone);
        List<HRsetTelphone> hRsetTelphones = ihRsetTelphoneDao.selectByConditions(hRsetTelphone);
        if(hRsetTelphones.size()==1){
            return hRsetTelphones.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetTelphone> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetTelphone> hRsetTelphones = ihRsetTelphoneDao.selectAll();
        return new PageInfo<HRsetTelphone>(hRsetTelphones);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetTelphoneDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetTelphone hRsetTelphone) {
        ihRsetTelphoneDao.updateOne(hRsetTelphone);
    }
}
