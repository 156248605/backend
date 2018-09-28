package com.elex.oa.service.eqptService;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
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

    String negative(HttpServletRequest request);

    List<Repository> wdbhR();

    List<Repository> wdbhX();

    List<Repository> wdbhC();

    List<Repository> showmatC(HttpServletRequest request);

    List<Repository> showprojX(HttpServletRequest request);

    List<Repository> showprojR(HttpServletRequest request);

    String getInstId(String instid);

    void updateApprove(String instid);

    String node(HttpServletRequest request);

    List<Repository> postInfo(HttpServletRequest request);

    List<Repository> approveName(HttpServletRequest request);

    // 获取审批
    void getApprove(HttpServletRequest request);

    // 查询草稿
    PageInfo<Repository> showDraft(Page page);

    // 保存草稿
    void insertDraft(HttpServletRequest request) throws ParseException;

    // 确认是否是草稿
    String checkDraft(HttpServletRequest request);

    // 返回草稿信息
    List<Repository> postDraft(HttpServletRequest request);

    // 入库通知弹框
    List<HashMap<String,Object>> notice(HttpServletRequest request);

    // 弹框子表
    List<HashMap<String,Object>> noticeChild(HttpServletRequest request);

    /*所有出库通知*/
    PageInfo<Repository> showNotice(Page page, HttpServletRequest request);

    // 根据ID查物料价格
    String priceOfId(HttpServletRequest request);
}
