package com.elex.oa.dao;

import com.elex.oa.entity.OsGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/4/25 16:35
*/
@Mapper
public interface IOsGroupDao extends BaseDao<OsGroup> {

    List<OsGroup> getByUserIdRelTypeId(Map<String,String> map);

}
