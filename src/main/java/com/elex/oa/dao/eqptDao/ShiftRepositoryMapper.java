package com.elex.oa.dao.eqptDao;


import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ShiftRepositoryMapper {
    List<Repository> showRepository(Repository repository);

    List<Repository> searchShift(Repository repository);

    void insertNew(String id, String time, String reptC, String num, String info, String shiftId, String shiftTime, String shiftReptC, String shiftNum, String shiftInfo, String outRept, String outPost, String inRept, String inPost, String materialId, String materialName, String spec, String unit, String sn, String bn, String remark, String projId, String projName);

    List showSHIFTID(Repository repository);

    List<Repository> wdbhJ();

    List<Repository> wdbhL();

    List<Repository> wdbhG();

    List<Repository> wdbhT();

    List<Repository> showmatJ(String wdbh);

    List<Repository> showmatL(String wdbh);

    List<Repository> showmatG(String wdbh);

    List<Repository> showmatT(String wdbh);

    String theNumberOut(Repository repository);

    String theNumberIn(Repository repository);

    List<Repository> showprojL(String wdbh);

    List<Repository> showprojJ(String wdbh);

    void updateInstId(String instid);

    void updateApprove(String instid, String secondOne, String thirdOne, String fourthOne);

    List<Repository> getInId(String instid);

    String node(String taskid);

    List<Repository> approveName(String instid);

    // 查找草稿
    List<Repository> findDraft();

    // 添加草稿

    // 确认草稿
    String checkDraft(Repository repository);

    // 读取草稿
    List<Repository> getDraft(String inId);

    // 删除草稿
    void deleteDraft(Repository repository);

    void insertDraft(String shiftId, String shiftTime, String shiftReptC, String shiftNum, String shiftInfo, String outRept, String outPost, String inRept, String inPost, String materialId, String materialName, String spec, String unit, String sn, String bn, String remark, String projid, String projname, String c, String firstOne, String secondOne, String thirdOne, String fourthOne, String AUTHOR);

    List<HashMap<String, Object>> noticeChild(String wdbh);

    List<HashMap<String, Object>> getNoticeS();

    List<HashMap<String, Object>> getNoticeJ();

    List<Repository> allNoticeJ();

    List<Repository> allNoticeS();
    List<Repository> allNoticeG();
    List<Repository> allNoticeT();
}
