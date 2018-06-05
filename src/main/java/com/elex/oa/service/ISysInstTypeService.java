package com.elex.oa.service;

import com.elex.oa.entity.SysInstType;

import java.util.List;

/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
public interface ISysInstTypeService {

    List<SysInstType> getValidExludePlatform();

    List<SysInstType> getValidAll();

    SysInstType getByCode(String typeCode);

}
