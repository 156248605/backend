package com.elex.oa.dao.eqptDao;



import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InRepositoryMapper {
    List<Repository> findAll();

    void insertNew(String REPTcategory, String INID, String INTIME, String INNUM, String reptCategory, String inId, String inTime, String inNum, String inInfo, String reptId, String postId, String materialId, String materialName, String spec, String unit, String sn, String bn, String inReptC, String check, String remark, String projId, String projName, String c);

    String ID (Material material);

    void delete(Repository repository);

    List showINID(Repository repository);

    List<Repository> wdbh();

    List<Repository> showmat(String wdbh);

    List<Repository> showproj(String wdbh);


    // 流程绑定事件
    List<Repository> getInId(String instId);

    String node(String taskid);

    void updateInstId(String instid);

    List<Repository> approveName(String instid);

    void updateApprove(String instId, String secondOne, String thirdOne, String instid);

    // 查找草稿
    List<Repository> findDraft();

    // 添加草稿
    void insertDraft(String repTcategory, String inid, String intime, String innum, String ininfo, String reptid, String postid, String materialid, String materialname, String spec, String unit, String sn, String bn, String inreptc, String check, String remark, String projid, String projname, String c, String firstOne, String secondOne, String thirdOne, String fourthOne);

    // 确认草稿
    String checkDraft(Repository repository);

    // 读取草稿
    List<Repository> getDraft(String inId);

    // 删除草稿
    void deleteDraft(Repository repository);
}
