package com.elex.oa.dao;

import com.elex.oa.entity.SysTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
@Mapper
public interface ISysTreeDao extends BaseDao<SysTree> {

    /**
     * 根据TREE_ID查询
     *
     */
      public SysTree getTreeById(String id);
}
