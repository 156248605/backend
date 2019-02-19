package com.elex.oa.service.business;

import com.elex.oa.entity.business.Opportunity;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface IOpportunityService {
    Boolean transforClueToOpportunity(Opportunity opportunity);

    PageInfo<Opportunity> getPageInfoByCondition(Integer pageNum, Integer pageSize, Opportunity opportunity, String flag);

    Opportunity getDetailOpportunityinfo(String opportunitycode);

    Opportunity getDetailOpportunityinfoByCluecode(String cluecode);

    Boolean modifyOpportunityInfo(Opportunity opportunity);

    Boolean closeOpportunityInfo(String opportunitycode);

    Map<String,Object> getBusinessInfoByState_OFF();
}
