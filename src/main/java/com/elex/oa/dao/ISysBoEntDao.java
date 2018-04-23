package com.elex.oa.dao;
import com.elex.oa.entity.bo.SysBoEnt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/13 11:49
*/
@Mapper
public interface ISysBoEntDao extends BaseDao<SysBoEnt>{
   List<SysBoEnt> getByBoDefId(String boDefId);

}
