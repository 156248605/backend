package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetSbjndDao;
import com.elex.oa.entity.hr_entity.HRsetSbjnd;
import com.elex.oa.service.hr_service.IHRsetSbjndService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:社保缴纳地
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetSbjndServiceImpl implements IHRsetSbjndService{
    @Autowired
    IHRsetSbjndDao ihRsetSbjndDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetSbjnd hRsetSbjnd) {
        Integer integer = ihRsetSbjndDao.insertOne(hRsetSbjnd);
        return hRsetSbjnd.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetSbjnd> queryAll() {
        List<HRsetSbjnd> hRsetSbjnds = ihRsetSbjndDao.selectAll();
        return hRsetSbjnds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetSbjnd> queryByConditions(HRsetSbjnd hRsetSbjnd) {
        List<HRsetSbjnd> hRsetSbjnds = ihRsetSbjndDao.selectByConditions(hRsetSbjnd);
        return hRsetSbjnds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetSbjnd queryById(Integer id) {
        HRsetSbjnd hRsetSbjnd = new HRsetSbjnd();
        hRsetSbjnd.setId(id);
        List<HRsetSbjnd> hRsetSbjnds = ihRsetSbjndDao.selectByConditions(hRsetSbjnd);
        if(hRsetSbjnds.size()==1){
            return hRsetSbjnds.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetSbjnd queryBySbjnd(String sbjnd) {
        HRsetSbjnd hRsetSbjnd = new HRsetSbjnd();
        hRsetSbjnd.setSbjnd(sbjnd);
        List<HRsetSbjnd> hRsetSbjnds = ihRsetSbjndDao.selectByConditions(hRsetSbjnd);
        if(hRsetSbjnds.size()==1){
            return hRsetSbjnds.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetSbjnd> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetSbjnd> hRsetSbjnds = ihRsetSbjndDao.selectAll();
        return new PageInfo<HRsetSbjnd>(hRsetSbjnds);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetSbjndDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetSbjnd hRsetSbjnd) {
        ihRsetSbjndDao.updateOne(hRsetSbjnd);
    }
}
