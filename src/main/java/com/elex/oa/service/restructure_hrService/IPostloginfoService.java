package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Postloginfo;
import com.github.pagehelper.PageInfo;

public interface IPostloginfoService {
    PageInfo<Postloginfo> queryDeptLogInformations(Integer pageNum, Integer pageSize, Postloginfo postloginfo);
}
