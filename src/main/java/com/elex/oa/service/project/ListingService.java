package com.elex.oa.service.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.AListQuery;
import com.elex.oa.entity.project.ApprovalList;
import com.github.pagehelper.PageInfo;

public interface ListingService {
    //审批清单列表查询
    PageInfo<ApprovalList> listQuery(Page page, AListQuery aListQuery);
}
