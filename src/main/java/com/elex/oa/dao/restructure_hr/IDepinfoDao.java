package com.elex.oa.dao.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Depinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IDepinfoDao {
    void insert(Depinfo depinfo);

    List<Depinfo> selectByEntity(Depinfo depinfo);

    Depinfo selectByDepcode(String depcode);
}
