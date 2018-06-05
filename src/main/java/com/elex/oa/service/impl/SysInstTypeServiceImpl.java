package com.elex.oa.service.impl;
import com.elex.oa.core.constants.MBoolean;
import com.elex.oa.dao.ISysInstTypeDao;
import com.elex.oa.entity.SysInstType;
import com.elex.oa.service.ISysInstTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SysInstTypeServiceImpl implements ISysInstTypeService {

    @Resource
    private ISysInstTypeDao sysInstTypeDao;

    @Override
    public List<SysInstType> getValidExludePlatform() {
        SysInstType sysInstType = new SysInstType();
        sysInstType.setEnabled(MBoolean.YES.name());
        sysInstType.setTypeCode("PLATFORM");
        return sysInstTypeDao.select(sysInstType);
    }

    @Override
    public List<SysInstType> getValidAll() {
        return sysInstTypeDao.getValidAll(MBoolean.YES.name());
    }

    @Override
    public SysInstType getByCode(String typeCode) {
        SysInstType sysInstType = new SysInstType();
        sysInstType.setTypeCode(typeCode);
        return sysInstTypeDao.selectOne(sysInstType);
    }
}
