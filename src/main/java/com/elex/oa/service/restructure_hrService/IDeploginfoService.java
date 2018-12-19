package com.elex.oa.service.restructure_hrService;

import com.elex.oa.entity.restructure_hrentity.Deploginfo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IDeploginfoService {
    PageInfo<Deploginfo> queryDeptLogInformations(Integer pageNum,Integer pageSize,Deploginfo deploginfo);

    List<Deploginfo> queryAllDeptLogInformations();

    Map<String,String> importDeploginformations(MultipartFile multipartFile);
}
