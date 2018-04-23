package com.elex.oa.service;

import com.elex.oa.entity.bo.SysBoDef;

/**
 *@author hugo.zhao
 *@since 2018/4/18 14:19
*/
public interface ISysBoDefService extends BaseService<SysBoDef>{
    SysBoDef get(String SysBoDef);
}
