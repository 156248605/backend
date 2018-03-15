package com.elex.oa.dao;

import com.elex.oa.entity.SysDic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/3/13 17:32
*/
@Mapper
public interface ISysDicDao extends BaseDao<SysDic> {
    public List<SysDic> getByTreeId(String treeId);



}
