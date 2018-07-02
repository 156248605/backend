package com.elex.oa.controller.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.AListQuery;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.service.project.ListingService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/listing")
public class ListingController {
    @Autowired
    private ListingService listingService;

    //审批清单列表查询
    @RequestMapping("/list_query")
    @ResponseBody
    public PageInfo<ApprovalList> listQuery(Page page, AListQuery aListQuery) {
        return listingService.listQuery(page,aListQuery);
    }
}
