package com.elex.oa.dao.business;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.business.Opportunity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IOpportunityDao extends BaseDao<Opportunity> {
    List<Opportunity> selectByOpportunityAndPrincipalUsername(Map<String, Object> map);

    List<Opportunity> selectByUsername(Map<String, Object> map);
}
