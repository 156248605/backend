package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetPosttitleDao;
import com.elex.oa.entity.hr_entity.HRsetPosttitle;
import com.elex.oa.service.hr_service.IHRsetPosttitleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:职称
 * @Date:Created in  11:34 2018\5\12 0012
 * @Modify By:
 */
@Service
public class HRsetPosttitleServiceImpl implements IHRsetPosttitleService {
    @Autowired
    IHRsetPosttitleDao ihRsetPosttitleDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 11:35 2018\5\12 0012
     */
    @Override
    public Integer addOne(HRsetPosttitle hRsetPosttitle) {
        Integer integer = ihRsetPosttitleDao.insertOne(hRsetPosttitle);
        return hRsetPosttitle.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 11:36 2018\5\12 0012
     */
    @Override
    public List<HRsetPosttitle> queryAll() {
        List<HRsetPosttitle> hRsetPosttitles = ihRsetPosttitleDao.selectAll();
        return hRsetPosttitles;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetPosttitle> queryByConditions(HRsetPosttitle hRsetPosttitle) {
        List<HRsetPosttitle> hRsetPosttitles = ihRsetPosttitleDao.selectByConditions(hRsetPosttitle);
        return hRsetPosttitles;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetPosttitle queryById(Integer id) {
        HRsetPosttitle hRsetPosttitle = new HRsetPosttitle();
        hRsetPosttitle.setId(id);
        List<HRsetPosttitle> hRsetPosttitles = ihRsetPosttitleDao.selectByConditions(hRsetPosttitle);
        if(hRsetPosttitles.size()==1){
            return hRsetPosttitles.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetPosttitle queryByPosttitle(String posttitle) {
        HRsetPosttitle hRsetPosttitle = new HRsetPosttitle();
        hRsetPosttitle.setPosttitle(posttitle);
        List<HRsetPosttitle> hRsetPosttitles = ihRsetPosttitleDao.selectByConditions(hRsetPosttitle);
        if(hRsetPosttitles.size()==1){
            return hRsetPosttitles.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetPosttitle> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetPosttitle> hRsetPosttitles = ihRsetPosttitleDao.selectAll();
        return new PageInfo<HRsetPosttitle>(hRsetPosttitles);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetPosttitleDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetPosttitle hRsetPosttitle) {
        ihRsetPosttitleDao.updateOne(hRsetPosttitle);
    }
}
