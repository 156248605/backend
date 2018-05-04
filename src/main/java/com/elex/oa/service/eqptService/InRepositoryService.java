package com.elex.oa.service.eqptService;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface InRepositoryService {
    /*入库单号查询*/
    PageInfo<Repository> ShowRepository(Page page, HttpServletRequest request);

    PageInfo<Repository> searchRepository(Page page, HttpServletRequest request);

    /*新建入库单*/
    String NewRepository(HttpServletRequest request) throws ParseException;

    /*同步物料*/
    void InsertMaterial(HttpServletRequest request) throws ParseException;

    /*同步仓库*/
    String InsertRepository(HttpServletRequest request);
}
