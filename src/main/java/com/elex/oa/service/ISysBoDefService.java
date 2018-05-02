package com.elex.oa.service;

import com.elex.oa.entity.bo.SysBoDef;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/18 14:19
*/
public interface ISysBoDefService extends BaseService<SysBoDef>{
    SysBoDef get(String SysBoDef);

    List<SysBoDef> getPage(SysBoDef sysBoDef);
}
