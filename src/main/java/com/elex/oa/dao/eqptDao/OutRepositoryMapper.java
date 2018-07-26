package com.elex.oa.dao.eqptDao;



import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutRepositoryMapper {
    List<Repository> findAll();

    void insertNew(String reptCategory, String outId, String outTime, String outNum, String outInfo, String reptId, String position, String materialId, String materialName, String spec, String unit, String sn, String bn, String outReptC, String remark);

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
}
