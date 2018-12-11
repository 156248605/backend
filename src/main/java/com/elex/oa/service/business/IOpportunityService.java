package com.elex.oa.service.business;

import com.elex.oa.entity.business.Opportunity;
import com.github.pagehelper.PageInfo;

public interface IOpportunityService {
    Boolean transforClueToOpportunity(Opportunity opportunity);

    PageInfo<Opportunity> getPageInfoByCondition(Integer pageNum,Integer pageSize,Opportunity opportunity);

    Opportunity getDetailOpportunityinfo(String opportunitycode);

    Boolean modifyOpportunityInfo(Opportunity opportunity);

    Boolean closeOpportunityInfo(String opportunitycode);
}
