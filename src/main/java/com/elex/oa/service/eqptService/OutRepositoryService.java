package com.example.oa_file.service;

import com.example.oa_file.entity.Page;
import com.example.oa_file.entity.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface OutRepositoryService {
    /*出库单号查询*/
    PageInfo<Repository> ShowRepository(Page page, HttpServletRequest request);

    PageInfo<Repository> searchRepository(Page page, HttpServletRequest request);

    /*新建出库单*/
    String InsertRepository(HttpServletRequest request) throws ParseException;

    String OutRepository(HttpServletRequest request);
}
