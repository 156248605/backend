package com.elex.oa.dao;

import com.elex.oa.entity.SysTree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

      public List<SysTree> selectByCatKey(Map<String,String> map);

    //新增表单分类
    public int addFormCategory(Map<String,Object> map);

    //删除表单分类
    public int deleteFormCategory(String id);

}
