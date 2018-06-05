package com.elex.oa.dao;

import com.elex.oa.entity.BpmSolution;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface IBpmSolutionDao extends BaseDao<BpmSolution> {
    List<BpmSolution> getSolutions(Map<String,Object> map);

    List<BpmSolution> getSolutionsByAdmin(Map<String,Object> map);

    //BpmSolution getByKey()

}
