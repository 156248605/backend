package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.project.ListingDao;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.AListQuery;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.service.project.ListingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ListingImpl  implements ListingService {

    @Resource
    private ListingDao listingDao;

    //审批清单列表查询
    @Override
    public PageInfo<ApprovalList> listQuery(Page page, AListQuery aListQuery) {
        List<String> dateList = JSONArray.parseArray(aListQuery.getDate3(),String.class);
        if(dateList.get(0).equals("")) {

        } else {
            aListQuery.setDate31(dateList.get(0));
            aListQuery.setDate32(dateList.get(1));
        }
        List<String> list4 = JSONArray.parseArray(aListQuery.getSelect4(),String.class);
        List<String> list5 = JSONArray.parseArray(aListQuery.getSelect5(),String.class);
        if(list4.size() > 0) {
            aListQuery.setList4(list4);
        }
        if(list5.size() > 0) {
            aListQuery.setList5(list5);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ApprovalList> list = listingDao.listQuery(aListQuery);
        return new PageInfo<>(list);
    }
}
