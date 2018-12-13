package com.elex.oa.dao.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Postlevelrelationshipinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPostlevelrelationshipinfoDao {
    void insert(Postlevelrelationshipinfo postlevelrelationshipinfo);

    List<Postlevelrelationshipinfo> selectByEntity(Postlevelrelationshipinfo postlevelrelationshipinfo);

    void deleteById(String id);

    List<Postlevelrelationshipinfo> selectAll();
}
