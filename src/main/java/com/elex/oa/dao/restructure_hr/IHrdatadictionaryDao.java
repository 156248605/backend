package com.elex.oa.dao.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IHrdatadictionaryDao {
    String insert(Hrdatadictionary hrDataDictionary);
}
