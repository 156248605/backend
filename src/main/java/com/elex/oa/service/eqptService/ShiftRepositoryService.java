package com.elex.oa.service.eqptService;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface ShiftRepositoryService {
    /*展示所有*/
    PageInfo<Repository> ShowRepository(Page page, HttpServletRequest request);

    /*移库单号查询*/
    PageInfo<Repository> searchRepository(Page page, HttpServletRequest request);

    /*新建移库单*/
    void NewRepository(HttpServletRequest request) throws ParseException;

    /*同步仓库*/
    void changeRepository(HttpServletRequest request);

    /*同步物料数量*/
    /*void changeMat(HttpServletRequest request);*/

    /*确定最新出库单号*/
    List showShiftId(HttpServletRequest request);

    String postCap(HttpServletRequest request);
}
