package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IHRsetZzmmDao;
import com.elex.oa.entity.entity_shiyun.HRsetZzmm;
import com.elex.oa.service.service_shiyun.IHRsetZzmmService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（政治面貌）
 * @Date:Created in  17:46 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetZzmmServiceImpl implements IHRsetZzmmService {
    @Autowired
    IHRsetZzmmDao ihRsetZzmmDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 17:47 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetZzmm hRsetZzmm) {
        Integer integer = ihRsetZzmmDao.insertOne(hRsetZzmm);
        return hRsetZzmm.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 17:47 2018\5\11 0011
     */
    @Override
    public List<HRsetZzmm> queryAll() {
        List<HRsetZzmm> hRsetZzmms = ihRsetZzmmDao.selectAll();
        return hRsetZzmms;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetZzmm> queryByConditions(HRsetZzmm hRsetZzmm) {
        List<HRsetZzmm> hRsetZzmms = ihRsetZzmmDao.selectByConditions(hRsetZzmm);
        return hRsetZzmms;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetZzmm queryById(Integer id) {
        HRsetZzmm hRsetZzmm = new HRsetZzmm();
        hRsetZzmm.setId(id);
        List<HRsetZzmm> hRsetZzmms = ihRsetZzmmDao.selectByConditions(hRsetZzmm);
        if(hRsetZzmms.size()==1){
            return hRsetZzmms.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetZzmm queryByZzmm(String zzmm) {
        HRsetZzmm hRsetZzmm = new HRsetZzmm();
        hRsetZzmm.setZzmm(zzmm);
        List<HRsetZzmm> hRsetZzmms = ihRsetZzmmDao.selectByConditions(hRsetZzmm);
        if(hRsetZzmms.size()==1){
            return hRsetZzmms.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetZzmm> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetZzmm> hRsetZzmms = ihRsetZzmmDao.selectAll();
        return new PageInfo<HRsetZzmm>(hRsetZzmms);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetZzmmDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetZzmm hRsetZzmm) {
        ihRsetZzmmDao.updateOne(hRsetZzmm);
    }
}
