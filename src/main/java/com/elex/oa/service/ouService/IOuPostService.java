package com.elex.oa.service.ouService;

import com.elex.oa.entity.ou.OuPost;
import com.elex.oa.entity.ou.OuPostConditionInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IOuPostService {
    Object addOuPost(OuPost ouPost);

    PageInfo<OuPost> getPageOuPostList(Integer pageNum, Integer pageSize, OuPostConditionInfo ouPostConditionInfo);

    Map<String,Object> getParamsOfOuPost();
}
