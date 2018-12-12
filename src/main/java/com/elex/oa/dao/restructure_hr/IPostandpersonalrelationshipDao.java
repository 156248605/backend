package com.elex.oa.dao.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Postandpersonalrelationshipinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPostandpersonalrelationshipDao {
    void insert(Postandpersonalrelationshipinfo postandpersonalrelationshipinfo);
}
