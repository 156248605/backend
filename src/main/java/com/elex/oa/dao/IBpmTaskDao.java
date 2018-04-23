package com.elex.oa.dao;

import com.elex.oa.entity.activiti.BpmTask;
import org.apache.ibatis.annotations.Mapper;

/**
 *@author hugo.zhao
 *@since 2018/4/9 17:03
*/
@Mapper
public interface IBpmTaskDao extends BaseDao<BpmTask> {
}
