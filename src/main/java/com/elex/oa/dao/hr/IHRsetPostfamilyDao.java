package com.elex.oa.dao.hr;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.hr_entity.hr_set.PostfamilyAndPostgrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IHRsetPostfamilyDao extends BaseDao<PostfamilyAndPostgrade> {
}
