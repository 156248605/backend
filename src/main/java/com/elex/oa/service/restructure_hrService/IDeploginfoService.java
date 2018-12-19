package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Deploginfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDeploginfoService {
    PageInfo<Deploginfo> queryDeptLogInformations(Integer pageNum,Integer pageSize,Deploginfo deploginfo);

    List<Deploginfo> queryAllDeptLogInformations();
}
