package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetZyzsnameDao;
import com.elex.oa.entity.hr_entity.HRsetZyzsname;
import com.elex.oa.service.hr_service.IHRsetZyzsnameService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:职业证书名称
 * @Date:Created in  14:44 2018\5\12 0012
 * @Modify By:
 */
@Service
public class HRsetZyzsnameServiceImpl implements IHRsetZyzsnameService {
    @Autowired
    IHRsetZyzsnameDao ihRsetZyzsnameDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 14:45 2018\5\12 0012
     */
    @Override
    public Integer addOne(HRsetZyzsname hRsetZyzsname) {
        Integer integer = ihRsetZyzsnameDao.insertOne(hRsetZyzsname);
        return hRsetZyzsname.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 14:46 2018\5\12 0012
     */
    @Override
    public List<HRsetZyzsname> queryAll() {
        List<HRsetZyzsname> hRsetZyzsnames = ihRsetZyzsnameDao.selectAll();
        return hRsetZyzsnames;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetZyzsname> queryByConditions(HRsetZyzsname hRsetZyzsname) {
        List<HRsetZyzsname> hRsetZyzsnames = ihRsetZyzsnameDao.selectByConditions(hRsetZyzsname);
        return hRsetZyzsnames;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetZyzsname queryById(Integer id) {
        HRsetZyzsname hRsetZyzsname = new HRsetZyzsname();
        hRsetZyzsname.setId(id);
        List<HRsetZyzsname> hRsetZyzsnames = ihRsetZyzsnameDao.selectByConditions(hRsetZyzsname);
        if(hRsetZyzsnames.size()==1){
            return hRsetZyzsnames.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetZyzsname queryByZyzsname(String zyzsname) {
        HRsetZyzsname hRsetZyzsname = new HRsetZyzsname();
        hRsetZyzsname.setZyzsname(zyzsname);
        List<HRsetZyzsname> hRsetZyzsnames = ihRsetZyzsnameDao.selectByConditions(hRsetZyzsname);
        if(hRsetZyzsnames.size()==1){
            return hRsetZyzsnames.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetZyzsname> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetZyzsname> hRsetZyzsnames = ihRsetZyzsnameDao.selectAll();
        return new PageInfo<HRsetZyzsname>(hRsetZyzsnames);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetZyzsnameDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetZyzsname hRsetZyzsname) {
        ihRsetZyzsnameDao.updateOne(hRsetZyzsname);
    }
}
