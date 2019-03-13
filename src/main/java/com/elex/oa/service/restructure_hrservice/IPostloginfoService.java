package com.elex.oa.service.restructure_hrservice;

import com.elex.oa.entity.restructure_hrentity.Postloginfo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IPostloginfoService {
    PageInfo<Postloginfo> queryDeptLogInformations(Integer pageNum, Integer pageSize, Postloginfo postloginfo);

    List<Postloginfo> queryAllPostLogInformations();

    Map<String,String> removePostlogByIds(List<String> postlogids);

    Map<String,String> importPostloginformations(MultipartFile multipartFile);

}
