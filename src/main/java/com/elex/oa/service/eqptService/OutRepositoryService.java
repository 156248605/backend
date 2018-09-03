package com.elex.oa.service.eqptService;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface OutRepositoryService {
    /*出库单号查询*/
    PageInfo<Repository> ShowRepository(Page page, HttpServletRequest request);

    PageInfo<Repository> searchRepository(Page page, HttpServletRequest request);

    /*新建出库单*/
    String InsertRepository(HttpServletRequest request) throws ParseException;

    /*更新仓库*/
    void OutRepository(HttpServletRequest request);

    /*更新物料*/
    void OutMaterial(HttpServletRequest request) throws ParseException;

    /*新建出库单*/
    List showOutId(HttpServletRequest request);

    /*删除出库单*/
    void DeleteOutRept(HttpServletRequest request);

    /*判断能否出库*/
    String canOut(HttpServletRequest request);

    List<Repository> wdbhR();

    List<Repository> wdbhX();

    List<Repository> wdbhC();

    List<Repository> showmatC(HttpServletRequest request);

    List<Repository> showprojX(HttpServletRequest request);

    List<Repository> showprojR(HttpServletRequest request);

    String getInstId(String instid);

    void updateApprove(String instid, HttpServletRequest request);

    String node(HttpServletRequest request);

    List<Repository> postInfo(HttpServletRequest request);

    List<Repository> approveName(HttpServletRequest request);
}
