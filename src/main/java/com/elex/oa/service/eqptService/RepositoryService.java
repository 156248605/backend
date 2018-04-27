package com.elex.oa.service.eqptService;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface RepositoryService {

    PageInfo<Repository> showRepository(Page page);

    PageInfo<Repository> searchRepository(Page page, HttpServletRequest request);

    String insertRepository(Repository repository, HttpServletRequest request);

    Repository position(HttpServletRequest request);

    void changeRepository(Repository repository, HttpServletRequest request);

    void deleteRepository(Repository repository, HttpServletRequest request);
}
