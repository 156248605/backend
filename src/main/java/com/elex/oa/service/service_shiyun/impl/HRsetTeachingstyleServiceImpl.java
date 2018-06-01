package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IHRsetTeachingstyleDao;
import com.elex.oa.entity.entity_shiyun.HRsetTeachingstyle;
import com.elex.oa.service.service_shiyun.IHRsetTeachingstyleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:授课方式
 * @Date:Created in  18:40 2018\5\11 0011
 * @Modify By:
 */
@Service
public class HRsetTeachingstyleServiceImpl implements IHRsetTeachingstyleService{
    @Autowired
    IHRsetTeachingstyleDao ihRsetTeachingstyleDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:40 2018\5\11 0011
     */
    @Override
    public Integer addOne(HRsetTeachingstyle hRsetTeachingstyle) {
        Integer integer = ihRsetTeachingstyleDao.insertOne(hRsetTeachingstyle);
        return hRsetTeachingstyle.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:41 2018\5\11 0011
     */
    @Override
    public List<HRsetTeachingstyle> queryAll() {
        List<HRsetTeachingstyle> hRsetTeachingstyleList = ihRsetTeachingstyleDao.selectAll();
        return hRsetTeachingstyleList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetTeachingstyle> queryByConditions(HRsetTeachingstyle hRsetTeachingstyle) {
        List<HRsetTeachingstyle> hRsetTeachingstyleList = ihRsetTeachingstyleDao.selectByConditions(hRsetTeachingstyle);
        return hRsetTeachingstyleList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetTeachingstyle queryById(Integer id) {
        HRsetTeachingstyle hRsetTeachingstyle = new HRsetTeachingstyle();
        hRsetTeachingstyle.setId(id);
        List<HRsetTeachingstyle> hRsetTeachingstyleList = ihRsetTeachingstyleDao.selectByConditions(hRsetTeachingstyle);
        if(hRsetTeachingstyleList.size()==1){
            return hRsetTeachingstyleList.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetTeachingstyle queryByTeachingstyle(String teachingstyle) {
        HRsetTeachingstyle hRsetTeachingstyle = new HRsetTeachingstyle();
        hRsetTeachingstyle.setTeachingstyle(teachingstyle);
        List<HRsetTeachingstyle> hRsetTeachingstyleList = ihRsetTeachingstyleDao.selectByConditions(hRsetTeachingstyle);
        if(hRsetTeachingstyleList.size()==1){
            return hRsetTeachingstyleList.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetTeachingstyle> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetTeachingstyle> hRsetTeachingstyleList = ihRsetTeachingstyleDao.selectAll();
        return new PageInfo<HRsetTeachingstyle>(hRsetTeachingstyleList);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetTeachingstyleDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetTeachingstyle hRsetTeachingstyle) {
        ihRsetTeachingstyleDao.updateOne(hRsetTeachingstyle);
    }
}
