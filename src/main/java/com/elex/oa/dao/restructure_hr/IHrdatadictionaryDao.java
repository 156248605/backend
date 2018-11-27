package com.elex.oa.dao.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IHrdatadictionaryDao {
    void insert(Hrdatadictionary hrDataDictionary);

    List<Hrdatadictionary> selectByEntity(Hrdatadictionary hrdatadictionary);
}
