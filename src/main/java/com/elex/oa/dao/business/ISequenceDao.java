package com.elex.oa.dao.business;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.business.Sequence;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISequenceDao extends BaseDao<Sequence> {
}
