package com.elex.oa.dao.eqptDao;


import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShiftRepositoryMapper {
    List<Repository> showRepository();

    List<Repository> searchShift();

    void insertNew(String shiftId, String shiftTime, String shiftReptC, String shiftNum, String shiftInfo, String outRept, String outPost, String inRept, String inPost, String materialId, String materialName, String spec, String unit, String sn, String bn, String remark);

    List showSHIFTID(Repository repository);

    Material lockMat(Material material);
}
