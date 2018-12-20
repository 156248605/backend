package com.elex.oa.service.eqptService;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface ShiftRepositoryService {
    /*展示所有*/
    PageInfo<Repository> ShowRepository(Page page, HttpServletRequest request);

    /*移库单号查询*/
    PageInfo<Repository> searchRepository(Page page, HttpServletRequest request);

    /*新建移库单*/
    String NewRepository(HttpServletRequest request) throws ParseException;

    /*同步仓库*/
    void changeRepository(HttpServletRequest request);

    /*同步物料数量*/
    /*void changeMat(HttpServletRequest request);*/

    /*确定最新出库单号*/
    List showShiftId(HttpServletRequest request);

    String postCap(HttpServletRequest request);

    String canIn(HttpServletRequest request);

    String canOut(HttpServletRequest request);

    List<Repository> wdbhJ();

    List<Repository> wdbhL();

    List<Repository> wdbhG();

    List<Repository> wdbhT();

    List<Repository> showmatJ(HttpServletRequest request);

    List<Repository> showmatL(HttpServletRequest request);

    List<Repository> showmatG(HttpServletRequest request);

    List<Repository> showmatT(HttpServletRequest request);

    List<Repository> showprojL(HttpServletRequest request);

    List<Repository> showprojJ(HttpServletRequest request);

    String getInstId(String instid);

    void updateApprove(String instid);

    List<Repository> postInfo(HttpServletRequest request);

    String node(HttpServletRequest request);

    List<Repository> approveName(HttpServletRequest request);

    // 获取审批
    void getApprove(HttpServletRequest request);

    // 查询草稿
    PageInfo<Repository> showDraft(Page page);

    // 保存草稿
    void insertDraft(HttpServletRequest request) throws ParseException;

    // 删除草稿
    void deleteDraft(HttpServletRequest request);

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
}
