package com.elex.oa.dao.restructure_hr;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.restructure_hrentity.Depinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IDepinfoDao extends BaseDao<Depinfo> {
    List<Depinfo> selectByEntity(Depinfo depinfo);

    Depinfo selectByDepcode(String depcode);

}
