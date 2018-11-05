package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr_shiyun.IHRsetRaceDao;
import com.elex.oa.entity.hr_entity.HRsetRace;
import com.elex.oa.service.hr_service.IHRsetRaceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  16:49 2018\5\9 0009
 * @Modify By:
 */
@Service
public class HRsetRaceServiceImpl implements IHRsetRaceService {
    @Autowired
    IHRsetRaceDao iRaceDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 16:50 2018\5\9 0009
     */
    @Override
    public Integer addOne(HRsetRace race) {
        Integer integer = iRaceDao.insertOne(race);
        return race.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 16:50 2018\5\9 0009
     */
    @Override
    public List<HRsetRace> queryAll() {
        List<HRsetRace> races = iRaceDao.selectAll();
        return races;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetRace> queryByConditions(HRsetRace hRsetRace) {
        List<HRsetRace> hRsetRaces = iRaceDao.selectByConditions(hRsetRace);
        return hRsetRaces;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetRace queryById(Integer id) {
        HRsetRace hRsetRace = new HRsetRace();
        hRsetRace.setId(id);
        List<HRsetRace> hRsetRaces = iRaceDao.selectByConditions(hRsetRace);
        if(hRsetRaces.size()==1){
            return hRsetRaces.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetRace queryByRace(String rzce) {
        HRsetRace hRsetRace = new HRsetRace();
        hRsetRace.setRace(rzce);
        List<HRsetRace> hRsetRaces = iRaceDao.selectByConditions(hRsetRace);
        if(hRsetRaces.size()==1){
            return hRsetRaces.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetRace> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetRace> hRsetRaces = iRaceDao.selectAll();
        return new PageInfo<HRsetRace>(hRsetRaces);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        iRaceDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetRace hRsetRace) {
        iRaceDao.updateOne(hRsetRace);
    }
}
