package com.elex.oa.dao;

import com.elex.oa.entity.BpmFormView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
@Mapper
public interface IBpmFormViewDao extends BaseDao<BpmFormView> {
  //查询所有的表单数据
  public List<BpmFormView> getAllBpmFormView(Map<String,Object> paramMap);
}
