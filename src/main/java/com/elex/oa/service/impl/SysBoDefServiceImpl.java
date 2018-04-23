package com.elex.oa.service.impl;
import com.elex.oa.dao.ISysBoDefDao;
import com.elex.oa.entity.bo.SysBoDef;
import com.elex.oa.service.ISysBoDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@author hugo.zhao
 *@since 2018/4/18 14:20
*/
@Service
public class SysBoDefServiceImpl extends BaseServiceImpl<SysBoDef> implements ISysBoDefService{
    @Autowired
    private ISysBoDefDao sysBoDefDao;
    @Override
    public SysBoDef get(String SysBoDef) {
        return this.sysBoDefDao.selectByPrimaryKey(SysBoDef);
    }
}
