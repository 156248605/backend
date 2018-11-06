package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetLegalholidayDao;
import com.elex.oa.entity.hr_entity.HRsetLegalholiday;
import com.elex.oa.service.hr_service.IHRsetLegalholidayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:法定假
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetLegalholidayServiceImpl implements IHRsetLegalholidayService{
    @Autowired
    IHRsetLegalholidayDao ihRsetLegalholidayDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetLegalholiday hRsetLegalholiday) {
        Integer integer = ihRsetLegalholidayDao.insertOne(hRsetLegalholiday);
        return hRsetLegalholiday.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetLegalholiday> queryAll() {
        List<HRsetLegalholiday> hRsetLegalholidays = ihRsetLegalholidayDao.selectAll();
        return hRsetLegalholidays;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetLegalholiday> queryByConditions(HRsetLegalholiday hRsetLegalholiday) {
        List<HRsetLegalholiday> hRsetLegalholidays = ihRsetLegalholidayDao.selectByConditions(hRsetLegalholiday);
        return hRsetLegalholidays;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetLegalholiday queryById(Integer id) {
        HRsetLegalholiday hRsetLegalholiday = new HRsetLegalholiday();
        hRsetLegalholiday.setId(id);
        List<HRsetLegalholiday> hRsetLegalholidays = ihRsetLegalholidayDao.selectByConditions(hRsetLegalholiday);
        if(hRsetLegalholidays.size()==1){
            return hRsetLegalholidays.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetLegalholiday queryByLegalholiday(String legalholiday) {
        HRsetLegalholiday hRsetLegalholiday = new HRsetLegalholiday();
        hRsetLegalholiday.setLegalholiday(legalholiday);
        List<HRsetLegalholiday> hRsetLegalholidays = ihRsetLegalholidayDao.selectByConditions(hRsetLegalholiday);
        if(hRsetLegalholidays.size()==1){
            return hRsetLegalholidays.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetLegalholiday> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetLegalholiday> hRsetLegalholidays = ihRsetLegalholidayDao.selectAll();
        return new PageInfo<HRsetLegalholiday>(hRsetLegalholidays);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetLegalholidayDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetLegalholiday hRsetLegalholiday) {
        ihRsetLegalholidayDao.updateOne(hRsetLegalholiday);
    }
}
