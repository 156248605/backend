package com.elex.oa.service.impl;

import com.elex.oa.dao.ISysPropertiesDao;
import com.elex.oa.entity.SysProperties;
import com.elex.oa.service.ISysPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@author hugo.zhao
 *@since 2018/4/18 11:12
*/
@Service
public class SysPropertiesServiceImpl extends BaseServiceImpl<SysProperties> implements ISysPropertiesService {
    @Autowired
    private ISysPropertiesDao sysPropertiesDao;

    @Override
    public SysProperties getPropertiesByName(String name) {
        SysProperties sysProperties = new SysProperties();
        sysProperties.setAlias(name);
        return sysPropertiesDao.selectOne(sysProperties);
    }
}
