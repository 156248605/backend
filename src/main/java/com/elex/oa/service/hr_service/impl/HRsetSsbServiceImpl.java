package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetSsbDao;
import com.elex.oa.entity.hr_entity.HRsetSsb;
import com.elex.oa.service.hr_service.IHRsetSsbService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:社保基数
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetSsbServiceImpl implements IHRsetSsbService{
    @Autowired
    IHRsetSsbDao ihRsetSsbDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetSsb hRsetSsb) {
        Integer integer = ihRsetSsbDao.insertOne(hRsetSsb);
        return hRsetSsb.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetSsb> queryAll() {
        List<HRsetSsb> hRsetSsbs = ihRsetSsbDao.selectAll();
        return hRsetSsbs;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetSsb> queryByConditions(HRsetSsb hRsetSsb) {
        List<HRsetSsb> hRsetSsbs = ihRsetSsbDao.selectByConditions(hRsetSsb);
        return hRsetSsbs;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetSsb queryById(Integer id) {
        HRsetSsb hRsetSsb = new HRsetSsb();
        hRsetSsb.setId(id);
        List<HRsetSsb> hRsetSsbs = ihRsetSsbDao.selectByConditions(hRsetSsb);
        if(hRsetSsbs.size()==1){
            return hRsetSsbs.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetSsb queryBySsb(String ssb) {
        HRsetSsb hRsetSsb = new HRsetSsb();
        hRsetSsb.setSsb(ssb);
        List<HRsetSsb> hRsetSsbs = ihRsetSsbDao.selectByConditions(hRsetSsb);
        if(hRsetSsbs.size()==1){
            return hRsetSsbs.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetSsb> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetSsb> hRsetSsbs = ihRsetSsbDao.selectAll();
        return new PageInfo<HRsetSsb>(hRsetSsbs);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetSsbDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetSsb hRsetSsb) {
        ihRsetSsbDao.updateOne(hRsetSsb);
    }
}
