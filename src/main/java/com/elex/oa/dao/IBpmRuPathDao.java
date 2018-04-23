package com.elex.oa.dao;

import com.elex.oa.entity.BpmRuPath;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/4/9 17:18
*/
@Mapper
public interface IBpmRuPathDao extends BaseDao<BpmRuPath> {

    List<BpmRuPath> getByActInstId(String actInstId);

    List<BpmRuPath> getLatestByActInstId(Map<String,String> map);

}
