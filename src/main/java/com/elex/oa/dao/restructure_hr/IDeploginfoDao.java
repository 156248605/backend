package com.elex.oa.dao.restructure_hr;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.restructure_hrentity.Deploginfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IDeploginfoDao extends BaseDao<Deploginfo> {

    List<Deploginfo> selectDeploginfoListByConditions(Deploginfo deploginfo);
}
