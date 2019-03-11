package com.elex.oa.dao.reportobstacles;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.reportObstacles.ObstaclesInfo;
import com.elex.oa.entity.reportObstacles.ObstaclesQueryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IObstaclesInfoDao extends BaseDao<ObstaclesInfo> {
    List<ObstaclesInfo> selectByConditions(ObstaclesQueryInfo obstaclesQueryInfo);
}
