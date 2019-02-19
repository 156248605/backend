package com.elex.oa.dao.business;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.business.Opportunity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IOpportunityDao extends BaseDao<Opportunity> {
    List<Opportunity> selectByOpportunityAndPrincipalUsername(Opportunity opportunity);
}
