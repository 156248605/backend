package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Postloginfo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IPostloginfoService {
    PageInfo<Postloginfo> queryDeptLogInformations(Integer pageNum, Integer pageSize, Postloginfo postloginfo);

    List<Postloginfo> queryAllPostLogInformations();

    Map<String,String> removePostlogByIds(List<String> postlogids);

}
