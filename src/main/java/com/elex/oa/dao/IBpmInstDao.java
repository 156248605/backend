package com.elex.oa.dao;

import com.elex.oa.entity.activiti.BpmInst;
import org.apache.ibatis.annotations.Mapper;

/**
 *@author hugo.zhao
 *@since 2018/4/10 9:43
*/
@Mapper
public interface IBpmInstDao extends  BaseDao<BpmInst>{
    BpmInst getByActInstId(String actInstId);
}
