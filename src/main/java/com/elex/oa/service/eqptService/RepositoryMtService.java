package com.elex.oa.service.eqptService;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RepositoryMtService {

    // 显示所有仓库维护
    PageInfo<Repository> showRepositoryMt(Page page);

    // 查找对应维护
    PageInfo<Repository> searchRepositoryMt(Page page, HttpServletRequest request);

    // 修改维护信息
    void changeRepositoryMt(HttpServletRequest request);

    // 新建维护信息
    void insertRepositoryMt(HttpServletRequest request);

    void deleteRepositoryMt(HttpServletRequest request);

    // 固定库位
    List<Material> MatList();

    void updFix(HttpServletRequest request);
    void updOtherFix(HttpServletRequest request);
    List<User> username();
}
