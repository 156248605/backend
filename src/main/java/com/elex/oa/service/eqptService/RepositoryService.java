package com.example.oa_file.service;

import com.example.oa_file.entity.Page;
import com.example.oa_file.entity.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface RepositoryService {

    PageInfo<Repository> showRepository(Page page);

    PageInfo<Repository> searchRepository(Page page,HttpServletRequest request);

    String insertRepository(Repository repository,HttpServletRequest request);

    Repository position(HttpServletRequest request);

    void changeRepository(Repository repository,HttpServletRequest request);

    void deleteRepository(Repository repository,HttpServletRequest request);
}
