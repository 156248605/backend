package com.elex.oa.dao.eqptDao;


import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShiftRepositoryMapper {
    List<Repository> showRepository();

    List<Repository> searchShift(Repository repository);

    void insertNew(String shiftId, String shiftTime, String shiftReptC, String shiftNum, String shiftInfo, String outRept, String outPost, String inRept, String inPost, String materialId, String materialName, String spec, String unit, String sn, String bn, String remark,String projId,String projName);

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
}
