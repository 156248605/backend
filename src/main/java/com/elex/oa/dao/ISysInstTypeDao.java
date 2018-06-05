package com.elex.oa.dao;

import com.elex.oa.entity.SysInstType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISysInstTypeDao extends BaseDao<SysInstType>{
    List<SysInstType> getValidAll(String enabled);

}
