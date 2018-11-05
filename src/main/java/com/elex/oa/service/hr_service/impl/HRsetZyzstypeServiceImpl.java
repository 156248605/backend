package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr_shiyun.IHRsetZyzstypeDao;
import com.elex.oa.entity.hr_entity.HRsetZyzstype;
import com.elex.oa.service.hr_service.IHRsetZyzstypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:职业证书类型
 * @Date:Created in  11:36 2018\5\12 0012
 * @Modify By:
 */
@Service
public class HRsetZyzstypeServiceImpl implements IHRsetZyzstypeService {
    @Autowired
    IHRsetZyzstypeDao ihRsetZyzstypeDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 11:37 2018\5\12 0012
     */
    @Override
    public Integer addOne(HRsetZyzstype hRsetZyzstype) {
        Integer integer = ihRsetZyzstypeDao.insertOne(hRsetZyzstype);
        return hRsetZyzstype.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 11:38 2018\5\12 0012
     */
    @Override
    public List<HRsetZyzstype> queryAll() {
        List<HRsetZyzstype> hRsetZyzstypes = ihRsetZyzstypeDao.selectAll();
        return hRsetZyzstypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:28 2018\5\14 0014
     */
    @Override
    public List<HRsetZyzstype> queryByConditions(HRsetZyzstype hRsetZyzstype) {
        List<HRsetZyzstype> hRsetZyzstypes = ihRsetZyzstypeDao.selectByConditions(hRsetZyzstype);
        return hRsetZyzstypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:29 2018\5\14 0014
     */
    @Override
    public HRsetZyzstype queryById(Integer id) {
        HRsetZyzstype hRsetZyzstype = new HRsetZyzstype();
        hRsetZyzstype.setId(id);
        List<HRsetZyzstype> hRsetZyzstypes = ihRsetZyzstypeDao.selectByConditions(hRsetZyzstype);
        if(hRsetZyzstypes.size()==1){
            return hRsetZyzstypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:30 2018\5\14 0014
     */
    @Override
    public HRsetZyzstype queryByZyzstype(String zyzstype) {
        HRsetZyzstype hRsetZyzstype = new HRsetZyzstype();
        hRsetZyzstype.setZyzstype(zyzstype);
        List<HRsetZyzstype> hRsetZyzstypes = ihRsetZyzstypeDao.selectByConditions(hRsetZyzstype);
        if(hRsetZyzstypes.size()==1){
            return hRsetZyzstypes.get(0);
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:37 2018\5\19 0019
     */
    @Override
    public PageInfo<HRsetZyzstype> queryByParam(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<HRsetZyzstype> hRsetZyzstypes = ihRsetZyzstypeDao.selectAll();
        return new PageInfo<HRsetZyzstype>(hRsetZyzstypes);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 16:45 2018\5\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        ihRsetZyzstypeDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:35 2018\5\19 0019
     */
    @Override
    public void modifyOne(HRsetZyzstype hRsetZyzstype) {
        ihRsetZyzstypeDao.updateOne(hRsetZyzstype);
    }
}
