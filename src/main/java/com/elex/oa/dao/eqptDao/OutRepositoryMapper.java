package com.elex.oa.dao.eqptDao;



import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface OutRepositoryMapper {
    List<Repository> findAll(Repository repository);

    void insertNew(Repository repository);

    List showOUTID(Repository repository);

    void delete(Repository repository);

    List<Repository> warning();

    void updWarn(Repository repository);

    List<Repository> warn();

    void updUser(Repository repository);

    List<Repository> wdbhR();

    List<Repository> wdbhX();

    List<Repository> wdbhC();

    List<Repository> showmatX(String wdbh);

    List<Repository> showmatR(String wdbh);

    List<Repository> showmatC(String wdbh);

    String showmatSN(Repository repository);

    List<Repository> showprojX(String wdbh);

    List<Repository> showprojR(String wdbh);

    // 绑定流程
    void updateInstId(String instid);

    void updateApprove(String instid, String secondOne, String thirdOne, String fourthOne);

    List<Repository> getInId(String instid);

    String node(String taskid);

    List<Repository> approveName(String instid);

    // 查找草稿
    List<Repository> findDraft();

    // 添加草稿
    void insertDraft(String repTcategory, String outid, String outtime, String outnum, String outinfo, String reptid, String postid, String materialid, String materialname, String spec, String unit, String sn, String bn, String outreptc, String remark, String projid, String projname, String c, String firstOne, String secondOne, String thirdOne, String fourthOne, String AUTHOR);

    // 确认草稿
    String checkDraft(Repository repository);

    // 读取草稿
    List<Repository> getDraft(String inId);

    // 删除草稿
    void deleteDraft(Repository repository);

    List<HashMap<String, Object>> getNoticeR();

    List<HashMap<String, Object>> getNoticeX();

    List<HashMap<String, Object>> noticeChild(String wdbh);

    List<Repository> allNoticeR();

    List<Repository> allNoticeX();

    String priceOfId(String id);
}
