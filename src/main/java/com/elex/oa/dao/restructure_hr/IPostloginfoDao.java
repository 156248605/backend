package com.elex.oa.dao.restructure_hr;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.restructure_hrentity.Postloginfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPostloginfoDao extends BaseDao<Postloginfo> {
    List<Postloginfo> selectPostloginfoListByConditions(Postloginfo postloginfo);
}
