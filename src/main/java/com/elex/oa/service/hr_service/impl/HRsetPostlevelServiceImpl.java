package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetPostlevelDao;
import com.elex.oa.entity.hr_entity.HRsetPostlevel;
import com.elex.oa.service.hr_service.IHRsetPostlevelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（岗位级别）
 * @Date:Created in  18:12 2018\5\10 0010
 * @Modify By:
 */
@Service
public class HRsetPostlevelServiceImpl implements IHRsetPostlevelService {
    @Autowired
    IHRsetPostlevelDao iPostLevelDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息并返回主键
     *@Date: 18:13 2018\5\10 0010
     */
    @Override
    public Integer addOne(HRsetPostlevel postlevel) {
        Integer integer = iPostLevelDao.insertOne(postlevel);
        return postlevel.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的信息
     *@Date: 18:14 2018\5\10 0010
     */
    @Override
    public List<HRsetPostlevel> queryAll() {
        List<HRsetPostlevel> postlevels = iPostLevelDao.selectAll();
        return postlevels;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetPostlevel> queryByConditions(HRsetPostlevel hRsetPostlevel) {
        List<HRsetPostlevel> hRsetPostlevels = iPostLevelDao.selectByConditions(hRsetPostlevel);
        return hRsetPostlevels;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetPostlevel queryById(Integer id) {
        HRsetPostlevel hRsetPostlevel = new HRsetPostlevel();
        hRsetPostlevel.setId(id);
        List<HRsetPostlevel> hRsetPostlevels = iPostLevelDao.selectByConditions(hRsetPostlevel);
        if(hRsetPostlevels.size()==1){
            return hRsetPostlevels.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据级别查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetPostlevel queryByPostlevel(String postlevel) {
        HRsetPostlevel hRsetPostlevel = new HRsetPostlevel();
        hRsetPostlevel.setPostlevel(postlevel);
        List<HRsetPostlevel> hRsetPostlevels = iPostLevelDao.selectByConditions(hRsetPostlevel);
        if(hRsetPostlevels.size()==1){
            return hRsetPostlevels.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetPostlevel> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetPostlevel> hRsetPostlevels = iPostLevelDao.selectAll();
        return new PageInfo<HRsetPostlevel>(hRsetPostlevels);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        iPostLevelDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetPostlevel hRsetPostlevel) {
        iPostLevelDao.updateOne(hRsetPostlevel);
    }
}
