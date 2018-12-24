package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Postinfo;

import java.util.List;
import java.util.Map;

public interface IPostinfoService {
    Boolean changeTable();

    Boolean updateNodelevel();

    Map<String,Object> getPostTree();

    Postinfo queryOnePostByPostcode(String postcode);

    List<Postinfo> queryPostinfoList();

    Boolean validateByPostcode(String postcode);

    Boolean addOnePost(Postinfo postinfo);

    List<Map<String, String>> queryPostsRemoveChilren(String postcode);

    Boolean updateOnePost(Postinfo postinfo,String transactorusername);

    Boolean deletePostsByPostcode(String postcode);

    List<Map<String,Object>> getSortPostinformation(String postcode);

    Map<String,Object> submitSortdata(List<Map> respMap);
}
