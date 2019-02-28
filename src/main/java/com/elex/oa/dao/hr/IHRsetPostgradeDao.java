package com.elex.oa.dao.hr;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.hr_entity.hr_set.PostgradeAndPostrank;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IHRsetPostgradeDao extends BaseDao<PostgradeAndPostrank> {
}
