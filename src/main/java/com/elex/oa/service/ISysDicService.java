package com.elex.oa.service;

import com.elex.oa.entity.SysDic;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/3/13 17:34
*/
public interface ISysDicService extends BaseService<SysDic>  {

    public List<SysDic> getByTreeId(String treeID);

}
