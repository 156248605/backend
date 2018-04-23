package com.elex.oa.service;

import com.elex.oa.entity.SysProperties;

/**
 *@author hugo.zhao
 *@since 2018/4/18 11:03
*/
public interface ISysPropertiesService extends BaseService<SysProperties>{
    SysProperties getPropertiesByName(String name);

}
