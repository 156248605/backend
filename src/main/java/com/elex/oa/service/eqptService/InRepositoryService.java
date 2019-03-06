package com.elex.oa.service.eqptService;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
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

    List wdbh();

    List<Repository> showmat(HttpServletRequest request);
    List<Repository> showproj(HttpServletRequest request);

    // 获取创建流程的inst id
    String getInstId(String instid, HttpServletRequest request);

    // 更新审批意见
    void updateApprove(String instId);

    // 审批时给予查询信息
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

    /*所有入库通知*/
    PageInfo<Repository> showNotice(Page page, HttpServletRequest request);

    // 弹框子表
    List<HashMap<String,Object>> noticeChild(HttpServletRequest request);

    List<Repository> showprojProduce(HttpServletRequest request);
}
