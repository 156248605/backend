package com.elex.oa.service;

import com.elex.oa.entity.SysTree;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/14 9:33
*/
public interface ISysTreeService extends BaseService<SysTree> {

    public List<SysTree> selectByCatKey(Map<String,String> map);
}
