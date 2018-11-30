package com.elex.oa.dao.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Postinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPostinfoDao {
    void insert(Postinfo postinfo);

    List<Postinfo> selectByEntity(Postinfo postinfo);

    void updateByEntity(Postinfo postinfo);
}
