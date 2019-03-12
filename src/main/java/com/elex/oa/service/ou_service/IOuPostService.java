package com.elex.oa.service.ou_service;

import com.elex.oa.entity.ou.OuPost;
import com.elex.oa.entity.ou.OuPostConditionInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IOuPostService {
    Object addOuPost(OuPost ouPost);

    Object updatePost(OuPost ouPost);

    PageInfo<OuPost> getPageOuPostList(Integer pageNum, Integer pageSize, OuPostConditionInfo ouPostConditionInfo);

    Map<String,Object> getParamsOfOuPost();

    Object validateByPostcode(String postcode);

    Object validateByPostcodeButSelf(String postcode, String postid);

    Object getRecommendedOuPostcode();

    Object queryAllPostcodeON();

    Object changeOuPostState(String flag, List<String> postIdList);
}
