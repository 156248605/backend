package com.elex.oa.dao;

import com.elex.oa.entity.bo.SysBoAttr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/13 13:43
*/
@Mapper
public interface ISysBoAttrDao  extends  BaseDao<SysBoAttr>{
    List<SysBoAttr> getAttrsByEntId(String entId);

}
