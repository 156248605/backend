package com.elex.oa.dao.eqptDao;



import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutRepositoryMapper {
    List<Repository> findAll();

    void insertNew(String reptCategory, String outId, String outTime, String outNum, String outInfo, String reptId, String position, String materialId, String materialName, String spec, String unit, String sn, String bn, String outReptC, String remark,String projId,String projName);

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
}
