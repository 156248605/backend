package com.elex.oa.dao.eqptDao;



import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutRepositoryMapper {
    List<Repository> findAll();

    void insertNew(String reptCategory, String outId, String outTime, String outNum, String reptId, String position, String sn, String bn);

    String searchId(Material material);
}
