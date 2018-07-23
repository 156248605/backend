package com.elex.oa.service.eqptService;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface InRepositoryService {
    /*入库单号查询*/
    PageInfo<Repository> ShowRepository(Page page, HttpServletRequest request);

    PageInfo<Repository> searchRepository(Page page, HttpServletRequest request);

    /*入库单号*/
    List showInId(HttpServletRequest request);

    /*新建入库单*/
    String NewRepository(HttpServletRequest request) throws ParseException;

    /*入库申请*/
    String checkInRepository(HttpServletRequest request);

    /*更新物料*/
    void InsertMaterial(HttpServletRequest request) throws ParseException;

    /*同步仓库*/
    void InsertRepository(HttpServletRequest request);

    /*删除入库单*/
    void DeleteInRept(HttpServletRequest request);

    /*判断能否入库*/
    String canIn(HttpServletRequest request);

    /*判断库位容量*/
    String postCap(HttpServletRequest request);
}
