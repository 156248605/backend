package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetEmergencyrpDao;
import com.elex.oa.entity.hr_entity.HRsetEmergencyrp;
import com.elex.oa.service.hr_service.IHRsetEmergencyrpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:应急联系人关系
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetEmergencyrpServiceImpl implements IHRsetEmergencyrpService{
    @Autowired
    IHRsetEmergencyrpDao ihRsetEmergencyrpDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetEmergencyrp hRsetEmergencyrp) {
        Integer integer = ihRsetEmergencyrpDao.insertOne(hRsetEmergencyrp);
        return hRsetEmergencyrp.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetEmergencyrp> queryAll() {
        List<HRsetEmergencyrp> hRsetEmergencyrps = ihRsetEmergencyrpDao.selectAll();
        return hRsetEmergencyrps;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetEmergencyrp> queryByConditions(HRsetEmergencyrp hRsetEmergencyrp) {
        List<HRsetEmergencyrp> hRsetEmergencyrps = ihRsetEmergencyrpDao.selectByConditions(hRsetEmergencyrp);
        return hRsetEmergencyrps;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetEmergencyrp queryById(Integer id) {
        HRsetEmergencyrp hRsetEmergencyrp = new HRsetEmergencyrp();
        hRsetEmergencyrp.setId(id);
        List<HRsetEmergencyrp> hRsetEmergencyrps = ihRsetEmergencyrpDao.selectByConditions(hRsetEmergencyrp);
        if(hRsetEmergencyrps.size()==1){
            return hRsetEmergencyrps.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetEmergencyrp queryByEmergencyrp(String emergencyrp) {
        HRsetEmergencyrp hRsetEmergencyrp = new HRsetEmergencyrp();
        hRsetEmergencyrp.setEmergencyrp(emergencyrp);
        List<HRsetEmergencyrp> hRsetEmergencyrps = ihRsetEmergencyrpDao.selectByConditions(hRsetEmergencyrp);
        if(hRsetEmergencyrps.size()==1){
            return hRsetEmergencyrps.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetEmergencyrp> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetEmergencyrp> hRsetEmergencyrps = ihRsetEmergencyrpDao.selectAll();
        return new PageInfo<HRsetEmergencyrp>(hRsetEmergencyrps);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetEmergencyrpDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetEmergencyrp hRsetEmergencyrp) {
        ihRsetEmergencyrpDao.updateOne(hRsetEmergencyrp);
    }
}
