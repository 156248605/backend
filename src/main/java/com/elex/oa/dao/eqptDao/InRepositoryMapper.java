package com.elex.oa.dao.eqptDao;



import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InRepositoryMapper {
    List<Repository> findAll();

    void insertNew(String reptCategory, String inId, String inTime, String inNum, String reptId, String position, String sn, String bn);

    List searchId(Material material);

    String ID (Material material);

    String INID(Repository repository);

    String OUTID(Repository repository);

    String searchSn(Material material);
}
